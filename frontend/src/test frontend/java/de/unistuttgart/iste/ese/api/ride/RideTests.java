package de.unistuttgart.iste.ese.api.ride;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import de.unistuttgart.iste.ese.api.user.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class RideTests {

    @Autowired
    private MockMvc mockMvc;

    // Example of how to mock repository to test only the controller. The Spring
    // annotation that depends on mockito framework
    @MockBean
    private RideRepository rideRepository;

    @Test
    @DisplayName("Expect one element in the returned JSON array. JPA repository is mocked")
    void testReturningTheListOfAddresses_dbMocked() throws Exception {

        User driver = new User(
            "Max",
            "Mustermann",
            "Max@Mustermail.de",
            "Lorem ipsum",
            "Dolor sit amet",
            "12345");
        List<Ride> allRides = new ArrayList<Ride>();
        allRides.add(new Ride(1, 2, driver.getId(), 2, System.currentTimeMillis() / 1000L, false, true, "Loop", 10.0d, 0));

        // mocks the underneath call to JPA repository
        BDDMockito.given(rideRepository.findAll()).willReturn(allRides);

        // performs a get request
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rides"));

        // expected status code
        result.andExpect(MockMvcResultMatchers.status().isOk());
        // expected string in the content
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Loop")));


        // $ <- indicates the json root element
        result.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
    }
}

