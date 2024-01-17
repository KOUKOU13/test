package de.unistuttgart.iste.ese.api.userapplication;

import de.unistuttgart.iste.ese.api.ApiVersion1;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ApiVersion1
public class UserApplicationController {
    @Autowired
    private UserApplicationRepository userApplicationRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {
    }

    // get all UserApplications
    @GetMapping("/userapplications")
    public List<UserApplication> getuserapplications() {
        List<UserApplication> allUserApplications = (List<UserApplication>) userApplicationRepository.findAll();
        return allUserApplications;
    }

    // get a single UserApplication
    @GetMapping("/userapplications/{id}")
    public UserApplication getUser(@PathVariable("id") long id) {
        UserApplication searchedUserApplication = userApplicationRepository.findById(id);
        if (searchedUserApplication != null) {
            return searchedUserApplication;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserApplication with ID %s not found!", id));
    }

    @GetMapping("userapplication/user/{id}")
    public List<UserApplication> getUserApplicationsByUserId(@PathVariable("id") long id) {
        List<UserApplication> allUserApplications = userApplicationRepository.findByUserId(id);
        return allUserApplications;
    }

    @GetMapping("userapplication/ride/{id}")
    public List<UserApplication> getUserApplicationByRideId(@PathVariable("id") long id) {
        List<UserApplication> allUserApplications = userApplicationRepository.findByRideId(id);
        return allUserApplications;
    }

    // create a UserApplication
    @PostMapping("/userapplications")
    @ResponseStatus(HttpStatus.CREATED)
    public UserApplication createuserapplications(@Valid @RequestBody UserApplication requestBody) {
        UserApplication userApplication = new UserApplication(
            requestBody.getUserId(), requestBody.getRideId(),
            requestBody.getPassengerCount(), requestBody.getMessage(),
            requestBody.isAccepted(), requestBody.isRejected(),
            requestBody.getRejectionMessage()
            );
        return userApplicationRepository.save(userApplication);
    }

    // update a UserApplication
    @PutMapping("/userapplications/{id}")
    public UserApplication updateUser(@PathVariable("id") long id, @Valid @RequestBody UserApplication requestBody) {
        requestBody.setId(id);
        UserApplication userApplicationToUpdate = userApplicationRepository.findById(id);
        if (userApplicationToUpdate != null) {
            return userApplicationRepository.save(requestBody);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserApplication with ID %s not found!", id));
    }

    // delete a UserApplication
    @DeleteMapping("/userapplications/{id}")
    public UserApplication deleteUser(@PathVariable("id") long id) {
        UserApplication userApplicationToDelete = userApplicationRepository.findById(id);
        if (userApplicationToDelete != null) {
            userApplicationRepository.deleteById(id);
            return userApplicationToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserApplication with ID %s not found!", id));
    }
}
