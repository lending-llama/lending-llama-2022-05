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

import static com.example.AllocationController.API_BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AllocationControllerAllocationTest {

    public static final String PLATFORM_NAME = "platform1";
    public static final double PLATFORM_RATE = 5.0;
    public static final Platform PLATFORM_FROM_EXTERNAL_API = new Platform()
        .setName(PLATFORM_NAME)
        .setTiers(new Platform.Tier[]{new Platform.Tier().setRate(PLATFORM_RATE)});
    public static final List<Allocation> EXPECTED_ALLOCATIONS = List.of(
        new Allocation().setName(PLATFORM_NAME).setRate(PLATFORM_RATE)
    );
    @Autowired
	private MockMvc mvc;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private FeatureStore featurestore;

    private MockRestServiceServer mockServer;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void selectsAllocationsFromAvailable() throws Exception {

        featurestore.setMultipleTiersFeatureToggle(true);

        var url = API_BASE_URL + "btc-rates.json";

        mockServer.expect(requestTo(new URI(url)))
            .andRespond(withStatus(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(mapper.writeValueAsString(List.of(PLATFORM_FROM_EXTERNAL_API))));

        mvc.perform(MockMvcRequestBuilders.get("/allocations?amount=1.1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(EXPECTED_ALLOCATIONS)));
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
