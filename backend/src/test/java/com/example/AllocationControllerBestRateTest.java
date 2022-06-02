package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AllocationControllerBestRateTest {
    @Autowired
    private TestRestTemplate template;

    @ParameterizedTest
    @ValueSource(strings = {"/best-rate", "/best-rate-btc"})
    public void returnsBestRate(String url) throws Exception {
        var res = template.getForEntity(url, Allocation.class);
        assertThat(res.getBody()).isEqualTo(new Allocation().setName("Ledn").setRate(6.25));
    }

    @Test
    void returns404ForUnknownCoins(){
        var notACoin = "/best-rate-asdf";
        var res = template.getForEntity(notACoin, Allocation.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
