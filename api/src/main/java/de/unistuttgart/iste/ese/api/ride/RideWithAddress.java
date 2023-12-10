package de.unistuttgart.iste.ese.api.ride;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RideWithAddress {

    @NotNull
    private String startCity;

    @NotNull
    private String startPostCode;

    @NotNull
    private String startDistrict;

    @NotNull
    private String dstCity;

    @NotNull
    private String dstPostCode;

    @NotNull
    private String dstDistrict;
    
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

    public RideWithAddress() {}

    public RideWithAddress(@NotNull String startCity, @NotNull String startPostCode, @NotNull String startDistrict,
            @NotNull String dstCity, @NotNull String dstPostCode, @NotNull String dstDistrict, @NotNull long driverId,
            @NotNull @Min(1) int passengerLimit, @NotNull long startTimestamp, @NotNull boolean isSmokingAllowed,
            @NotNull boolean isPetTransportAllowed, @NotNull String description) {
        this.startCity = startCity;
        this.startPostCode = startPostCode;
        this.startDistrict = startDistrict;
        this.dstCity = dstCity;
        this.dstPostCode = dstPostCode;
        this.dstDistrict = dstDistrict;
        this.driverId = driverId;
        this.passengerLimit = passengerLimit;
        this.startTimestamp = startTimestamp;
        this.isSmokingAllowed = isSmokingAllowed;
        this.isPetTransportAllowed = isPetTransportAllowed;
        this.description = description;
    }

    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public String getStartPostCode() {
        return startPostCode;
    }

    public void setStartPostCode(String startPostCode) {
        this.startPostCode = startPostCode;
    }

    public String getStartDistrict() {
        return startDistrict;
    }

    public void setStartDistrict(String startDistrict) {
        this.startDistrict = startDistrict;
    }

    public String getDstCity() {
        return dstCity;
    }

    public void setDstCity(String dstCity) {
        this.dstCity = dstCity;
    }

    public String getDstPostCode() {
        return dstPostCode;
    }

    public void setDstPostCode(String dstPostCode) {
        this.dstPostCode = dstPostCode;
    }

    public String getDstDistrict() {
        return dstDistrict;
    }

    public void setDstDistrict(String dstDistrict) {
        this.dstDistrict = dstDistrict;
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
