package Model;

import Interface.Displayable;
import Interface.DiscountPolicy;

public class Reservation implements Displayable {
    private String id;
    private Student student;
    private Equipment equipment;
    private int days;
    private ReservationStatus status;

    public Reservation(String id, Student student, Equipment equipment, int days) {
        this.id = id;
        this.student = student;
        this.equipment = equipment;
        this.days = days;
        this.status = ReservationStatus.ACTIVE;
    }

    public double calculateTotalCost(DiscountPolicy policy) {
        double cost = equipment.calculateDailyPrice() * days;
        return policy.applyDiscount(student, cost);
    }

    public String getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getDays() {
        return days;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String getDisplayText() {
        return id +
                " student: " + student.getFullName() +
                " sprzęt: " + equipment.getName() +
                " dni: " + days +
                " status: " + status;
    }
}