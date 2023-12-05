package de.unistuttgart.iste.ese.api.ride;

import org.springframework.data.repository.CrudRepository;

public interface RideRepository extends CrudRepository<Ride, Long> {
    Ride findById(long id);
}
