package model;

public final class FreeRoom extends Room {
    final Double price = 0.0;


    public FreeRoom(String roomNumber, Double price, RoomType enumeration) {
        super(roomNumber, price, enumeration);
        price = this.price;
    }

    @Override
    public String toString() {
        return "Room number " + roomNumber + " is of room type " + enumeration + " and it is free!";
    }
}
