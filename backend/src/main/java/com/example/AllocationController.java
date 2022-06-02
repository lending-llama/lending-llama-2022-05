package com.example;

import com.example.platform.PlatformFetcher;
import com.example.platform.PlatformTier;
import io.split.client.SplitClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@RestController
public class AllocationController {

    @Autowired
    private FeatureStore featureStore;

    private SplitClient splitClient;

    @Autowired
    private PlatformFetcher platformFetcher;

    public AllocationController(SplitClient splitClient) {
        this.splitClient = splitClient;
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

        var tier1 = platformFetcher.getPlatformTiersDescByRate(apiLocation, this).get(0);
        return new Allocation().setName(tier1.getName()).setRate(tier1.getRate());
    }

    public Allocation getBestEthRate() {
        var tier1 = platformFetcher.getPlatformTiersDescByRate("eth-rates.json", this).get(0);
        return new Allocation().setName(tier1.getName()).setRate(tier1.getRate());
    }

    @GetMapping("/allocations")
    public Stream<Allocation> getAllocation(@RequestParam Double amount) throws Exception {

        var flag = featureStore.getMultipleTiersFeatureToggle();
        if (!flag) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var platformTiers = platformFetcher.getPlatformTiersDescByRate("btc-rates.json", this);

        var count = (int) IntStream.range(1, platformTiers.size())
            .takeWhile(i -> platformTiers.stream().limit(i).mapToDouble(PlatformTier::getMax).sum() < amount)
            .count();

        return platformTiers.subList(0, count+1).stream().map(
            t -> new Allocation().setName(t.getName()).setRate(t.getRate())
        );
    }
}
