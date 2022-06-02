package com.example.platform;

import com.example.AllocationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

@Component
public class PlatformFetcher {
    public static final String API_BASE_URL = "https://priceless-khorana-4dd263.netlify.app/";
    @Autowired
    private RestTemplate restTemplate;

    public static Stream<PlatformTier> tiersFromPlatform(final Platform p) {
        return Stream.of(p.getTiers()).map(t ->
            new PlatformTier()
                .setName(p.getName())
                .setRate(t.getRate())
                .setMax( null == t.getMax()? Double.POSITIVE_INFINITY : t.getMax()));
    }

    public List<PlatformTier> getPlatformTiersDescByRate(String apiLocation, AllocationController allocationController) {
        var url = API_BASE_URL + apiLocation;
        var platforms = restTemplate.getForObject(url, Platform[].class);
        return stream(platforms).flatMap(PlatformFetcher::tiersFromPlatform)
            .sorted(Comparator.comparingDouble(PlatformTier::getRate).reversed())
            .collect(Collectors.toList());
    }
}
