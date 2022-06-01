package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void testReplacesMissingMaxInPlatformTierWithInfinity(){

        final Platform.Tier[] tiers = new Platform.Tier[1];
        tiers[0] = new Platform.Tier().setRate(1.0); // set a rate but no Max value!
        Platform p = new Platform().setName("Platform").setTiers(tiers);
        var tiersStream = AllocationController.tiersFromPlatform(p);

        assertThat(tiersStream.findFirst().get().getMax()).isEqualTo(Double.POSITIVE_INFINITY);

    }
}
