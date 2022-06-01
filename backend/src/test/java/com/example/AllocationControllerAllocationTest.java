package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AllocationControllerAllocationTest {

	@Autowired
	private MockMvc mvc;
    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void selectsAllocationsFromAvailable() throws Exception {
        var url = "https://priceless-khorana-4dd263.netlify.app/btc-rates.json";

        final var platformName = "platform1";
        final var platformRate = 5.0;

        var expected = List.of(
            new Allocation().setName(platformName).setRate(platformRate)
        );

        var platformFromExternalApi = new Platform()
            .setName(platformName)
            .setTiers(new Platform.Tier[]{new Platform.Tier().setRate(platformRate)});

        mockServer.expect(requestTo(new URI(url)))
            .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(mapper.writeValueAsString(List.of(platformFromExternalApi))));

        mvc.perform(MockMvcRequestBuilders.get("/allocations?amount=1.1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expected)));
	}

    @Test
    void testReplacesMissingMaxInPlatformTierWithInfinity(){

        final Platform.Tier[] tiers = new Platform.Tier[1];
        tiers[0] = new Platform.Tier().setRate(1.0); // set a rate but no Max value!
        Platform p = new Platform().setName("Platform").setTiers(tiers);
        var tiersStream = AllocationController.tiersFromPlatform(p);

        assertThat(tiersStream.findFirst().get().getMax()).isEqualTo(Double.POSITIVE_INFINITY);

    }
}
