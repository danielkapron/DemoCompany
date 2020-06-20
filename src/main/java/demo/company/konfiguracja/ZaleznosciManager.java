package demo.company.konfiguracja;

import java.time.LocalDate;

public interface ZaleznosciManager {
    String pobierzId(String imie, String drugieImie, String nazwisko, String identyfikatorPodatkowy, LocalDate dob);
}
