package de.unistuttgart.iste.ese.api.userapplication;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserApplicationRepository extends CrudRepository<UserApplication, Long> {
    UserApplication findById(long id);
    List<UserApplication> findByRideId(long rideId);
    
    List<UserApplication> findByUserId(long userId);
}
