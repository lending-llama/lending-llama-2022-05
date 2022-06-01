package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.split.client.SplitClientImpl;
import io.split.client.utils.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AllocationControllerAllocationTest {

	@Autowired
	private MockMvc mvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
	public void findsBest() throws Exception {
        var expected = Arrays.asList(
            new Allocation().setName("Ledn").setRate(6.25),
            new Allocation().setName("BlockFi").setRate(4.5)
        );
        mvc.perform(MockMvcRequestBuilders.get("/allocations?amount=1.1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(expected)));
	}

    @Test
    public void ratesWithoutMaxValueAreNotNull() throws Exception {
        List<PlatformTier> platformTierList = new ArrayList<>();
        platformTierList.add(new PlatformTier().setName("Ledn").setRate(6.25).setMax(1d));
        platformTierList.add(new PlatformTier().setName("BlockFi").setRate(4.5).setMax(0.1d));
        platformTierList.add(new PlatformTier().setName("Ledn").setRate(1.0).setMax(null));
        assertThat(AllocationController.buildPlatformTierSubList(1.2, platformTierList)).
    }
}
