package de.unistuttgart.iste.ese.api.ride;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rides")
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    private long startId;

    @NotNull
    private long destId;

    @NotNull
    private long driverId;

    @NotNull
    @Min(1)
    private int passengerLimit;

    @NotNull
    private long startTimestamp;

    @NotNull
    private boolean isSmokingAllowed;
    
    @NotNull
    private boolean isPetTransportAllowed;

    @NotNull
    private String description;

    @NotNull
    private double price;

    // empty default constructor is necessary for JPA
    public Ride() {}

    public Ride(long startId, long destId, long driverId, int passengerLimit, long startTimestamp, boolean isSmokingAllowed, boolean isPetTransportAllowed, String description, double price) {
        this.startId = startId;
        this.destId = destId;
        this.driverId = driverId;
        this.passengerLimit = passengerLimit;
        this.startTimestamp = startTimestamp;
        this.isSmokingAllowed = isSmokingAllowed;
        this.isPetTransportAllowed = isPetTransportAllowed;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStartId() {
        return startId;
    }

    public void setStartId(long startId) {
        this.startId = startId;
    }
    
    public long getDestId() {
        return destId;
    }

    public void setDestId(long destId) {
        this.destId = destId;
    }
    
    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }
    
    public int getPassengerLimit() {
        return passengerLimit;
    }

    public void setPassengerLimit(int passengerLimit) {
        this.passengerLimit = passengerLimit;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    public void setStartTimestamp(long startTimestamp) {
        this.startTimestamp = startTimestamp;
    }

    public boolean isSmokingAllowed() {
        return isSmokingAllowed;
    }

    public void setSmokingAllowed(boolean isSmokingAllowed) {
        this.isSmokingAllowed = isSmokingAllowed;
    }

    public boolean isPetTransportAllowed() {
        return isPetTransportAllowed;
    }

    public void setPetTransportAllowed(boolean isPetTransportAllowed) {
        this.isPetTransportAllowed = isPetTransportAllowed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
