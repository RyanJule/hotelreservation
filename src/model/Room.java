package model;

public class Room implements IRoom {
    String roomNumber;
    Double price;
    RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        super();
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String toString() {
        return "Room Number " + roomNumber + " is a " + enumeration + " and it costs " + price + "\n";
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    public void setRoomType(RoomType enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public int hashCode() {
        int value;
        value = Integer.parseInt(this.roomNumber);
        return value;
    }

    @Override
    public boolean equals(Object o) {
        IRoom room = (IRoom) o;
        if (room.getRoomNumber() == roomNumber) { return true; }
        else { return false; }
    }
}
