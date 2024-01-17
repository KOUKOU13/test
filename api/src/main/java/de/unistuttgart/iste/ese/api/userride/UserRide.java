package de.unistuttgart.iste.ese.api.userride;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "userrides")
public class UserRide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @NotNull
    private long userId;

    @NotNull
    private long rideId;

    @NotNull
    private int passengerCount;

    @NotNull
    private int generation;

    public UserRide() {}

    public UserRide(@NotNull long userId, @NotNull long rideId, int passengerCount, int generation) {
        this.userId = userId;
        this.rideId = rideId;
        this.passengerCount = passengerCount;
        this.generation = generation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRideId() {
        return rideId;
    }

    public void setRideId(long rideId) {
        this.rideId = rideId;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }
}
