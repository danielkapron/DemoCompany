package demo.company;

import demo.company.konfiguracja.WeryfikacjaResults;

import java.time.LocalDate;

public interface KontoRepository {

    boolean save(String id, String imie, String nazwisko, String identyfikatorPodatkowy, LocalDate dob, WeryfikacjaResults weryfikacjaResults);

    boolean isExpired(Konto konto);

    boolean save(Konto konto);
}
