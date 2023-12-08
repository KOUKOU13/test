package de.unistuttgart.iste.ese.api.address;

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
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    /*public AddressRepository getAddressRepository() {
        return addressRepository;
    }*/

    // executed after start-up and dependency injection
    @PostConstruct
    public void init() {
        long numberOfAddresses = addressRepository.count();
        if (numberOfAddresses == 0) {
            addressRepository.save(new Address("Stuttgart", "70174", "Universität Stadtmitte"));
            addressRepository.save(new Address("Stuttgart", "70569", "Universität"));
            addressRepository.save(new Address("Stuttgart", "70629", "Flughafen"));
            addressRepository.save(new Address("Esslingen am Neckar", "73728", "Bahnhof"));
            addressRepository.save(new Address("Ludwigsburg", "71638", "Bahnhof"));
        }
    }

    // get all addresses
    @GetMapping("/addresses")
    public List<Address> getAddresses() {
        List<Address> allAddresses = (List<Address>) addressRepository.findAll();
        return allAddresses;
    }

    // get a single Address
    @GetMapping("/addresses/{id}")
    public Address getAddress(@PathVariable("id") long id) {

        Address searchedAddress = addressRepository.findById(id);
        if (searchedAddress != null) {
            return searchedAddress;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Address with ID %s not found!", id));
    }

    // create a Address
    @PostMapping("/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public Address createAddress(@Valid @RequestBody Address requestBody) {
        Address Address = new Address(requestBody.getCity(), requestBody.getPostcode(),
            requestBody.getDistrict());
        Address savedAddress = addressRepository.save(Address);
        return savedAddress;
    }

    // update a Address
    @PutMapping("/addresses/{id}")
    public Address updateAddress(@PathVariable("id") long id, @Valid @RequestBody Address requestBody) {
        requestBody.setId(id);
        Address AddressToUpdate = addressRepository.findById(id);
        if (AddressToUpdate != null) {
            Address savedAddress = addressRepository.save(requestBody);
            return savedAddress;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Address with ID %s not found!", id));
    }

    // delete a Address
    @DeleteMapping("/addresses/{id}")
    public Address deleteAddress(@PathVariable("id") long id) {

        Address AddressToDelete = addressRepository.findById(id);
        if (AddressToDelete != null) {
            addressRepository.deleteById(id);
            return AddressToDelete;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Address with ID %s not found!", id));
    }
}
