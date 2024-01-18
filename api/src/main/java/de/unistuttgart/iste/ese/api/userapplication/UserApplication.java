package de.unistuttgart.iste.ese.api.userapplication;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "userapplications")
public class UserApplication {
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
    private String message;

    @NotNull
    private boolean isAccepted;

    @NotNull
    private boolean isRejected;

    private String rejectionMessage;

    public UserApplication() {}

    public UserApplication(@NotNull long userId, @NotNull long rideId, @NotNull int passengerCount,
            @NotNull String message, @NotNull boolean isAccepted, @NotNull boolean isRejected,
            String rejectionMessage) {
        this.userId = userId;
        this.rideId = rideId;
        this.passengerCount = passengerCount;
        this.message = message;
        this.isAccepted = isAccepted;
        this.isRejected = isRejected;
        this.rejectionMessage = rejectionMessage;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean isRejected) {
        this.isRejected = isRejected;
    }

    public String getRejectionMessage() {
        return rejectionMessage;
    }

    public void setRejectionMessage(String rejectionMessage) {
        this.rejectionMessage = rejectionMessage;
    }
}
