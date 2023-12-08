package de.unistuttgart.iste.ese.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(
    name = "addresses",
    uniqueConstraints = { @UniqueConstraint(name = "noDuplicateAddress",
        columnNames = { "city", "postcode", "street", "houseNumber" } )
    })
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 1, max = 64)
    private String city;

    @NotNull
    @Pattern(regexp = "^[0-9]{5}$")
    private String postcode;

    @NotNull
    private String district;

    // empty default constructor is necessary for JPA
    public Address() {}

    public Address(String city, String postcode, String district) {
        this.city = city;
        this.postcode = postcode;
        this.district = district;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
