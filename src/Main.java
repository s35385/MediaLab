import Model.*;
import Service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        List<Equipment> equipment = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        students.add(
                new Student(
                        "S001",
                        "Anna Kowalska",
                        "12c",
                        120
                ));

        students.add(
                new Student(
                        "S002",
                        "Marek Nowak",
                        "12c",
                        40
                ));

        students.add(
                new Student(
                        "S003",
                        "Julia Zielinska",
                        "13a",
                        0
                ));

        equipment.add(
                new LaptopSet(
                        "E001",
                        "Lenovo ThinkPad Lab",
                        80,
                        32,
                        true
                ));

        equipment.add(
                new LaptopSet(
                        "E002",
                        "Dell XPS Demo",
                        100,
                        16,
                        false
                ));

        equipment.add(
                new CameraKit(
                        "E003",
                        "Sony Content Kit",
                        90,
                        3,
                        true
                ));

        equipment.add(
                new CameraKit(
                        "E004",
                        "Canon Interview Kit",
                        70,
                        1,
                        true
                ));

        ReservationService service =
                new ReservationService(
                        students,
                        equipment,
                        reservations,
                        new LoyaltyDiscountPolicy()
                );

        int option;

        do {

            System.out.println("""
                    1. Lista studentów
                    2. Lista sprzętu
                    3. Utwórz rezerwację
                    4. Zwrot sprzętu
                    5. Aktywne rezerwacje
                    6. Raport
                    0. Wyjście
                    """);

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1 -> {
                    students.forEach(System.out::println);
                }

                case 2 -> {
                    equipment.forEach(
                            e -> System.out.println(
                                    e.getDisplayText()));
                }

                case 3 -> {

                    System.out.print(" ID studenta: ");
                    String studentId =
                            scanner.nextLine();

                    System.out.print(" ID sprzętu: ");
                    String equipmentId =
                            scanner.nextLine();

                    System.out.print(" Dni: ");
                    int days =
                            scanner.nextInt();
                    scanner.nextLine();

                    Reservation reservation =
                            service.createReservation(
                                    studentId,
                                    equipmentId,
                                    days
                            );

                    if (reservation != null) {
                        System.out.println(
                                reservation.getDisplayText());
                    }
                }

                case 4 -> {

                    System.out.print(" ID rezerwacji: ");
                    String id =
                            scanner.nextLine();

                    service.returnEquipment(id);
                }

                case 5 -> {

                    reservations.stream()
                            .filter(r ->
                                    r.getStatus()
                                            == ReservationStatus.ACTIVE)
                            .forEach(r ->
                                    System.out.println(
                                            r.getDisplayText()));
                }

                case 6 -> service.printReport();

                case 0 -> System.out.println(" Koniec.");

                default -> System.out.println(" Błędna opcja.");
            }

        } while (option != 0);
    }
}