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
    private UserRepository userRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {
    }

    // get all users
    @GetMapping("/users")
    public List<User> getusers() {
        return (List<User>) userRepository.findAll();
    }

    // get a single User
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") long id) {

        User searchedUser = userRepository.findById(id);
        if (searchedUser != null) {
            return searchedUser;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    @PostMapping("/users/login/{email}")
    public User loginUser(@PathVariable("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            String.format("User with email %s does not exist!", email));
    }

    // create a User
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody User requestBody) {
        User user = new User(requestBody.getFirstName(), requestBody.getLastName(),
            requestBody.getEmail(), requestBody.getDescription(),
            requestBody.getCarDescription(), requestBody.getPassword());
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    // // update a User
    // @PutMapping("/users/{id}")
    // public User updateUser(@PathVariable("id") long id, @Valid @RequestBody User requestBody) {
    //     requestBody.setId(id);
    //     User userToUpdate = userRepository.findById(id);
    //     if (userToUpdate != null) {
    //         return userRepository.save(requestBody);
    //     }
    //     throw new ResponseStatusException(HttpStatus.NOT_FOUND,
    //             String.format("User with ID %s not found!", id));
    // }

    // update a User
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable("id") long id, @Valid @RequestBody User requestBody) {
        requestBody.setId(id);
        User userToUpdate = userRepository.findById(id);
        if (userToUpdate != null) {
            requestBody.setPassword(userToUpdate.getPassword());
            return userRepository.save(requestBody);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    @PutMapping("users/{id}/changepassword")
    public User updatePassword(@PathVariable("id") long id, @Valid @RequestBody UserPasswordChange requestBody) {
        User userToUpdate = userRepository.findById(id);
        if (userToUpdate != null) {
            if (userToUpdate.getPassword().equals(requestBody.getOldPassword())) {
                userToUpdate.setPassword(requestBody.getNewPassword());
                return userRepository.save(userToUpdate);
            }
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    String.format("Passwords do not match!"));
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }

    // delete a User
    @DeleteMapping("/users/{id}")
    public User deleteUser(@PathVariable("id") long id) {
        User userToDelete = userRepository.findById(id);
        if (userToDelete != null) {
            userRepository.deleteById(id);
            return userToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with ID %s not found!", id));
    }
}
