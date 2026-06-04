package Service;

import Interface.DiscountPolicy;
import Model.*;

import java.util.List;

public class ReservationService {
    private List<Student> students;
    private List<Equipment> equipmentList;
    private List<Reservation> reservations;

    private DiscountPolicy discountPolicy;
    private int reservationCounter = 1;

    public ReservationService(List<Student> students, List<Equipment> equipmentList, List<Reservation> reservations, DiscountPolicy discountPolicy) {
        this.students = students;
        this.equipmentList = equipmentList;
        this.reservations = reservations;
        this.discountPolicy = discountPolicy;
    }

    public Reservation createReservation(String studentId, String equipmentId, int days) {
        if (days < 1 || days > 14) {
            System.out.println(" Nieprawidłowa liczba dni.");
            return null;
        }

        Student student = students.stream()
                .filter(s -> s.getId().equals(studentId))
                .findFirst()
                .orElse(null);

        if (student == null) {
            System.out.println(" Student nie istnieje.");
            return null;
        }

        Equipment equipment = equipmentList.stream()
                .filter(e -> e.getId().equals(equipmentId))
                .findFirst()
                .orElse(null);

        if (equipment == null) {
            System.out.println(" Sprzęt nie istnieje.");
            return null;
        }

        if (!equipment.isAvailable()) {
            System.out.println(" Sprzęt niedostępny.");
            return null;
        }

        String reservationId = String.format("R%03d", reservationCounter++);

        Reservation reservation = new Reservation(reservationId, student, equipment, days);

        equipment.setAvailable(false);

        reservations.add(reservation);

        double cost = reservation.calculateTotalCost(discountPolicy);

        System.out.println(" Koszt: " + cost + " PLN");
        return reservation;
    }

    public void returnEquipment(String reservationId) {

        Reservation reservation = reservations.stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElse(null);

        if (reservation == null) {
            System.out.println(" Nie znaleziono rezerwacji.");
            return;
        }

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            System.out.println(" Rezerwacja nie jest aktywna.");
            return;
        }

        reservation.setStatus(ReservationStatus.RETURNED);

        reservation.getEquipment().setAvailable(true);

        int points = (int)(reservation.calculateTotalCost(discountPolicy) / 10);

        reservation.getStudent().addPoints(points);

        System.out.println(" Student otrzymał " + points + " punktów.");
    }

    public void printReport() {

        double revenue = 0;

        System.out.println("\nZakończone rezerwacje:");

        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.RETURNED) {

                System.out.println(r.getDisplayText());

                revenue += r.calculateTotalCost(discountPolicy);
            }
        }

        System.out.println("\nPrzychód: " + revenue + " PLN");

        Student bestStudent = students.get(0);

        for (Student s : students) {
            if (s.getLoyaltyPoints() >
                    bestStudent.getLoyaltyPoints()) {
                bestStudent = s;
            }
        }

        System.out.println(" Najwięcej punktów ma: " + bestStudent.getFullName() + " (" + bestStudent.getLoyaltyPoints() + ")");
    }
}