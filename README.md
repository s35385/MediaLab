# MediaLab

**Krótki opis klas**

Main – uruchamia program, tworzy przykładowe dane oraz obsługuje menu i komunikację z użytkownikiem.

Student – przechowuje dane studenta oraz liczbę punktów lojalnościowych.

Equipment – abstrakcyjna klasa bazowa reprezentująca sprzęt możliwy do wypożyczenia.

LaptopSet – reprezentuje zestaw laptopowy i oblicza jego cenę dzienną na podstawie parametrów sprzętu.

CameraKit – reprezentuje zestaw kamerowy i oblicza jego cenę dzienną na podstawie liczby obiektywów oraz statywu.

Reservation – przechowuje informacje o rezerwacji, łącząc studenta ze sprzętem oraz przechowując liczbę dni i status rezerwacji.

ReservationService – zawiera logikę biznesową programu, m.in. tworzenie rezerwacji, zwrot sprzętu oraz generowanie raportów.

LoyaltyDiscountPolicy – odpowiada za naliczanie zniżki dla studentów posiadających odpowiednią liczbę punktów lojalnościowych.

**Krótki opis interfejsów**

Displayable – interfejs służący do przygotowania tekstu wyświetlanego w konsoli. Implementują go klasy Equipment oraz Reservation.

DiscountPolicy – interfejs definiujący sposób naliczania rabatu. Implementuje go klasa LoyaltyDiscountPolicy.

**Przykład polimorfizmu**

Polimorfizm występuje podczas obliczania ceny sprzętu. Obiekty klas LaptopSet i CameraKit są przechowywane w kolekcji typu List<Equipment>. Wywołanie metody calculateDailyPrice() powoduje uruchomienie odpowiedniej wersji metody zależnie od rzeczywistego typu obiektu, dzięki czemu każdy rodzaj sprzętu wylicza cenę według własnych zasad.
