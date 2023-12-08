package de.unistuttgart.iste.ese.api.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByFirstName(String firstName);
    User findByLastName(String lastName);
    User findByEmail(String email);

    User findById(long id);
}
