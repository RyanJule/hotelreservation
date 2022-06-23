package model;

import java.util.Date;

public final class Reservation {
    private Customer customer;
    private IRoom room;
    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return customer.firstName + " " + customer.lastName + " has reserved " + room.getRoomNumber() + " from " + checkInDate.toString() + " to " + checkOutDate.toString();
    }

    @Override
    public int hashCode() {
        int value;
        String id;
        int checkIn;
        int checkOut;
        checkIn = (int) (checkInDate.getTime()/1000);
        checkOut = (int) (checkOutDate.getTime()/1000);
        id = String.valueOf(room) + String.valueOf(checkIn) + String.valueOf(checkOut);
        value = id.hashCode();
        return value;
    }

    @Override
    public boolean equals(Object o) {
        Reservation reservation = (Reservation) o;
        if (reservation.getRoom() == room && reservation.getCheckInDate() == checkInDate && reservation.getCheckOutDate() == checkOutDate) { return true; }
        else { return false; }
    }

}
