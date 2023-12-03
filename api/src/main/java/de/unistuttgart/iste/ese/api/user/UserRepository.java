package de.unistuttgart.iste.ese.api.users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);

    User findById(long id);
}
