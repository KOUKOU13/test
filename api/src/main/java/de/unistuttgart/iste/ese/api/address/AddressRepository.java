package de.unistuttgart.iste.ese.api.address;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {
    List<Address> findByCity(String city);
    List<Address> findByPostcode(String postcode);

    Address findByCityAndPostcodeAndDistrict(String city, String postcode, String district);

    Address findById(long id);
}
