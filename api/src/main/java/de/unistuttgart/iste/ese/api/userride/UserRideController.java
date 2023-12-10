package de.unistuttgart.iste.ese.api.userride;

import de.unistuttgart.iste.ese.api.ApiVersion1;
import de.unistuttgart.iste.ese.api.user.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import de.unistuttgart.iste.ese.api.user.User;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ApiVersion1
public class UserRideController {
    @Autowired
    private UserRideRepository userRideRepository;

    @Autowired
    private UserRepository userRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {
    }

    // get all userrides
    @GetMapping("/userrides")
    public List<UserRide> getUserRides() {
        List<UserRide> allUserRides = (List<UserRide>) userRideRepository.findAll();
        return allUserRides;
    }

    // get a single User
    @GetMapping("/userrides/{id}")
    public UserRide getUser(@PathVariable("id") long id) {
        UserRide searchedUserRide = userRideRepository.findById(id);
        if (searchedUserRide != null) {
            return searchedUserRide;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserRide with ID %s not found!", id));
    }

    @GetMapping("/userrides/ride/{id}")
    public List<User> getAllUsersForRide(@PathVariable("id") long id) {
        // TODO: this needs to be changed to a join asap.
        List<User> allUsers = (List<User>) userRepository.findAll();
        List<Long> allUserIdsForRide = ((List<UserRide>) userRideRepository
            .findByRideId(id))
            .stream()
            .map(ur -> ur.getUserId())
            .collect(Collectors.toList());
        return allUsers.stream()
            .filter(u -> allUserIdsForRide.contains(u.getId()))
            .toList();
    }

    // create a User
    @PostMapping("/userrides")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRide createUserRides(@Valid @RequestBody UserRide requestBody) {
        UserRide userRide = new UserRide(requestBody.getUserId(), requestBody.getRideId());
        return userRideRepository.save(userRide);
    }

    // update a User
    @PutMapping("/userrides/{id}")
    public UserRide updateUser(@PathVariable("id") long id, @Valid @RequestBody UserRide requestBody) {
        requestBody.setId(id);
        UserRide userRideToUpdate = userRideRepository.findById(id);
        if (userRideToUpdate != null) {
            return userRideRepository.save(requestBody);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserRide with ID %s not found!", id));
    }

    // delete a User
    @DeleteMapping("/userrides/{id}")
    public UserRide deleteUser(@PathVariable("id") long id) {
        UserRide userRideToDelete = userRideRepository.findById(id);
        if (userRideToDelete != null) {
            userRideRepository.deleteById(id);
            return userRideToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("UserRide with ID %s not found!", id));
    }
}
