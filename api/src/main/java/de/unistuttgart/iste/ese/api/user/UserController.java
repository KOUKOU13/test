package de.unistuttgart.iste.ese.api.user;

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
    }

    // get all users
    @GetMapping("/users")
    public List<User> getusers() {
        List<User> allusers = (List<User>) UserRepository.findAll();
        return allusers;
    }

    // get a single User
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) {

        User searchedUser = UserRepository.findById(id);
        if (searchedUser != null) {
            return searchedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    // create a User
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User requestBody) {
        User User = new User(requestBody.getFirstName(), requestBody.getLastName(), requestBody.getEmail());
        User savedUser = UserRepository.save(User);
        return savedUser;
    }

    // update a User
    @PutMapping("/users/{id}")
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
    @DeleteMapping("/users/{id}")
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
