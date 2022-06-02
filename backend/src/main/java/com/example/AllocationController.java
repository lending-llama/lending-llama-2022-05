package com.example;

import io.split.client.SplitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@RestController
public class AllocationController {

    public static final String API_BASE_URL = "https://priceless-khorana-4dd263.netlify.app/";
    private RestTemplate restTemplate;
    private SplitClient splitClient;
    private FeatureStore featureStore;

    public AllocationController(RestTemplate restTemplate, SplitClient splitClient, FeatureStore featureStore) {
        this.restTemplate = restTemplate;
        this.splitClient = splitClient;
        this.featureStore = featureStore;
    }

    @GetMapping(value={"/best-rate", "/best-rate-{currencyParam}"})
    public Allocation getBestRate(@PathVariable Optional<String> currencyParam) {
        var apiLocations = Map.of("btc", "btc-rates.json",
        "eth", "eth-rates");
        var currencyName = currencyParam.orElseGet(()->"btc");
        if (!apiLocations.containsKey(currencyName)){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Foo Not Found");
        }

        var apiLocation = apiLocations.get(currencyName);

        var tier1 = getPlatformTiersDescByRate(apiLocation).get(0);
        return new Allocation().setName(tier1.getName()).setRate(tier1.getRate());
    }

    public Allocation getBestEthRate() {
        var tier1 = getPlatformTiersDescByRate("eth-rates.json").get(0);
        return new Allocation().setName(tier1.getName()).setRate(tier1.getRate());
    }

    @GetMapping("/allocations")
    public Stream<Allocation> getAllocation(@RequestParam Double amount) throws Exception {
        if (!"on".equals(featureStore.getTreatment())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var platformTiers = getPlatformTiersDescByRate("btc-rates.json");

        var count = (int) IntStream.range(1, platformTiers.size())
            .takeWhile(i -> platformTiers.stream().limit(i).mapToDouble(PlatformTier::getMax).sum() < amount)
            .count();

        return platformTiers.subList(0, count+1).stream().map(
            t -> new Allocation().setName(t.getName()).setRate(t.getRate())
        );
    }

    private List<PlatformTier> getPlatformTiersDescByRate(String apiLocation) {
        var url = API_BASE_URL + apiLocation;
        var platforms = restTemplate.getForObject(url, Platform[].class);
        return stream(platforms).flatMap(AllocationController::tiersFromPlatform)
            .sorted(Comparator.comparingDouble(PlatformTier::getRate).reversed())
            .collect(Collectors.toList());
    }

    public static Stream<PlatformTier> tiersFromPlatform(final Platform p) {
        return Stream.of(p.getTiers()).map(t ->
            new PlatformTier()
                .setName(p.getName())
                .setRate(t.getRate())
                .setMax( null == t.getMax()? Double.POSITIVE_INFINITY : t.getMax()));
    }


}
