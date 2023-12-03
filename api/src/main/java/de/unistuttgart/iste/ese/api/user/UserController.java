package de.unistuttgart.iste.ese.api.users;

import de.unistuttgart.iste.ese.api.ApiVersion1;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@ApiVersion1
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {

        // check if DB is empty
        long numberOfUsers = UserRepository.count();
        if (numberOfUsers == 0) {
            // adding sample data for demonstration purposes
            User octoUser = new User("OctoUser");
            UserRepository.save(octoUser);

            User grumpyUser = new User("Grumpy User");
            UserRepository.save(grumpyUser);
        }
    }

    // get all Users
    @GetMapping("/Users")
    public List<User> getUsers() {
        List<User> allUsers = (List<User>) UserRepository.findAll();
        return allUsers;
    }

    // get a single User
    @GetMapping("/Users/{id}")
    public User getUser(@PathVariable("id") long id) {

        User searchedUser = UserRepository.findById(id);
        if (searchedUser != null) {
            return searchedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    // create a User
    @PostMapping("/Users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User requestBody) {
        User User = new User(requestBody.getName());
        User savedUser = UserRepository.save(User);
        return savedUser;
    }

    // update a User
    @PutMapping("/Users/{id}")
    public User updateUser(@PathVariable("id") long id, @Valid @RequestBody User requestBody) {
        requestBody.setId(id);
        User UserToUpdate = UserRepository.findById(id);
        if (UserToUpdate != null) {
            User savedUser = UserRepository.save(requestBody);
            return savedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    // delete a User
    @DeleteMapping("/Users/{id}")
    public User deleteUser(@PathVariable("id") long id) {

        User UserToDelete = UserRepository.findById(id);
        if (UserToDelete != null) {
            UserRepository.deleteById(id);
            return UserToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }
}
