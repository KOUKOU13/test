package de.unistuttgart.iste.ese.api.address;

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

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressTests {

    @Autowired
    private MockMvc mockMvc;

    // Example of how to mock repository to test only the controller. The Spring
    // annotation that depends on mockito framework
    @MockBean
    private AddressRepository addressRepository;

    @Test
    @DisplayName("Expect six elements in the returned JSON array. JPA repository is mocked")
    void testReturningTheListOfAddresses_dbMocked() throws Exception {

        List<Address> allAddresses = new ArrayList<Address>();
        allAddresses.add(new Address("Stuttgart", "70174", "Universität Stadtmitte"));
        allAddresses.add(new Address("Stuttgart", "70569", "Universität"));
        allAddresses.add(new Address("Stuttgart", "70629", "Flughafen"));
        allAddresses.add(new Address("Esslingen am Neckar", "73728", "Bahnhof"));
        allAddresses.add(new Address("Ludwigsburg", "71638", "Bahnhof"));
        allAddresses.add(new Address("Frankfurt am Main", "60549", "Flughafen Fernbahnhof"));

        // mocks the underneath call to JPA repository
        BDDMockito.given(addressRepository.findAll()).willReturn(allAddresses);

        // performs a get request
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/addresses"));

        // expected status code
        result.andExpect(MockMvcResultMatchers.status().isOk());
        // expected string in the content
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Frankfurt am Main")));
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Flughafen Fernbahnhof")));
        // $ <- indicates the json root element
        result.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(6)));
    }
}

