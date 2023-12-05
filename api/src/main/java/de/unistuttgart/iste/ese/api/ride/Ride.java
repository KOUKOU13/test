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

    // empty default constructor is necessary for JPA
    public Ride() {}

    public Ride(long startId, long destId, long driverId, int passengerLimit) {
        this.startId = startId;
        this.destId = destId;
        this.driverId = driverId;
        this.passengerLimit = passengerLimit;
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
}
