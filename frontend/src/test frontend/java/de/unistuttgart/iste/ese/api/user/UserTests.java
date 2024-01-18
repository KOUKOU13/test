package de.unistuttgart.iste.ese.api.user;

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
public class UserTests {

    @Autowired
    private MockMvc mockMvc;

    // Example of how to mock repository to test only the controller. The Spring
    // annotation that depends on mockito framework
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Expect two elements in the returned JSON array. JPA repository is mocked")
    void testReturningTheListOfAddresses_dbMocked() throws Exception {

        List<User> allUsers = new ArrayList<User>();
        allUsers.add(new User(
            "Max",
            "Mustermann",
            "Max@Mustermail.de",
            "Lorem ipsum",
            "Dolor sit amet",
            "12345"));
        allUsers.add(new User(
            "Klaus",
            "Kleber",
            "Kleber@Mustermail.de",
            "Lorem ipsum",
            null,
            "12345"));

        // mocks the underneath call to JPA repository
        BDDMockito.given(userRepository.findAll()).willReturn(allUsers);

        // performs a get request
        ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"));

        // expected status code
        result.andExpect(MockMvcResultMatchers.status().isOk());
        // expected string in the content
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Max")));
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Mustermann")));
        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Dolor sit amet")));

        result.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Kleber@Mustermail.de")));

        // $ <- indicates the json root element
        result.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
    }
}

