package demo.company.konfiguracja;

import java.io.IOException;
import java.time.LocalDate;

public interface WeryfikacjaService {
    WeryfikacjaResults potwierdz(String imie, String nazwisko, String identyfikatorPodatkowy, LocalDate dob) throws IOException;
}
