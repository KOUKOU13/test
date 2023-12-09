package de.unistuttgart.iste.ese.api.ride;

import de.unistuttgart.iste.ese.api.ApiVersion1;
import de.unistuttgart.iste.ese.api.address.Address;
import de.unistuttgart.iste.ese.api.address.AddressRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@ApiVersion1
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private AddressRepository addressRepository;

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {

    }

    // get all rides
    @GetMapping("/rides")
    public List<Ride> getRides() {
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

    @GetMapping("rides/{start}/{dst}")
    public List<Ride> getRidesFromTo(@PathVariable("start") String start, @PathVariable("dst") String dst) {
        List<Address> allAddresses = (List<Address>) addressRepository.findAll();
        List<Long> startAddresses = allAddresses
            .stream()
            .filter(a -> a.getCity().equals(start))
            .map(a -> a.getId())
            .collect(Collectors.toList());
        List<Long> dstAddresses = allAddresses
            .stream()
            .filter(a -> a.getCity().equals(dst))
            .map(a -> a.getId())
            .collect(Collectors.toList());
        List<Ride> allRides = (List<Ride>) rideRepository.findAll();
        List<Ride> rides = allRides
            .stream()
            .filter(r -> startAddresses.contains(r.getStartId()) && dstAddresses.contains(r.getDestId()))
            .collect(Collectors.toList());
        return rides;
    }

    @GetMapping("/rides/date/{date}")
    public List<Ride> getRidesUntil(@PathVariable("date") long date) {
        return ((List<Ride>) rideRepository.findAll()).stream()
            .filter(r -> 
                (r.getStartTimestamp() < date) &&
                (r.getStartTimestamp() > System.currentTimeMillis() / 1000L)
                )
            .toList();
    }

    @GetMapping("/rides/driver/{driverId}")
    public List<Ride> getRidesByDriverId(@PathVariable("driverId") long driverId) {
        return ((List<Ride>) rideRepository.findAll()).stream()
            .filter(r -> 
                (r.getDriverId() == driverId)
                )
            .toList();
    }

    // create a Ride
    @PostMapping("/rides")
    @ResponseStatus(HttpStatus.CREATED)
    public Ride createRide(@Valid @RequestBody Ride requestBody) {
        Ride Ride = new Ride(requestBody.getStartId(), requestBody.getDestId(),
            requestBody.getDriverId(), requestBody.getPassengerLimit(),
            requestBody.getStartTimestamp(), requestBody.isSmokingAllowed(),
            requestBody.isPetTransportAllowed(), requestBody.getDescription());
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
