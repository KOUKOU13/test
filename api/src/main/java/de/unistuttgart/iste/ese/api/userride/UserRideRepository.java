package de.unistuttgart.iste.ese.api.userride;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRideRepository extends CrudRepository<UserRide, Long> {
    UserRide findById(long id);
    List<UserRide> findByRideId(long rideId);
    
    List<UserRide> findByUserId(long userId);
}
