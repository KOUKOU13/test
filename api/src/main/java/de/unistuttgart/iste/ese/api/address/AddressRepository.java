package de.unistuttgart.iste.ese.api.address;

import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByCity(String city);
    Address findByPostcode(String postcode);

    Address findById(long id);
}
