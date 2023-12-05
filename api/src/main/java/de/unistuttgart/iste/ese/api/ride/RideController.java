package de.unistuttgart.iste.ese.api.ride;

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
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {

    }

    // get all rides
    @GetMapping("/rides")
    public List<Ride> getrides() {
        List<Ride> allrides = (List<Ride>) rideRepository.findAll();
        return allrides;
    }

    // get a single Ride
    @GetMapping("/rides/{id}")
    public Ride getRide(@PathVariable("id") long id) {

        Ride searchedRide = rideRepository.findById(id);
        if (searchedRide != null) {
            return searchedRide;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Ride with ID %s not found!", id));
    }

    // create a Ride
    @PostMapping("/rides")
    @ResponseStatus(HttpStatus.CREATED)
    public Ride createRide(@Valid @RequestBody Ride requestBody) {
        Ride Ride = new Ride(requestBody.getStartId(), requestBody.getDestId(),
            requestBody.getDriverId(), requestBody.getPassengerLimit());
        Ride savedRide = rideRepository.save(Ride);
        return savedRide;
    }

    // update a Ride
    @PutMapping("/rides/{id}")
    public Ride updateRide(@PathVariable("id") long id, @Valid @RequestBody Ride requestBody) {
        requestBody.setId(id);
        Ride RideToUpdate = rideRepository.findById(id);
        if (RideToUpdate != null) {
            Ride savedRide = rideRepository.save(requestBody);
            return savedRide;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Ride with ID %s not found!", id));
    }

    // delete a Ride
    @DeleteMapping("/rides/{id}")
    public Ride deleteRide(@PathVariable("id") long id) {

        Ride RideToDelete = rideRepository.findById(id);
        if (RideToDelete != null) {
            rideRepository.deleteById(id);
            return RideToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Ride with ID %s not found!", id));
    }
}
