import demo.company.KontoRepository;
import demo.company.konfiguracja.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;

import javax.management.relation.RoleUnresolved;
import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class OtwarcieKontaServiceTest {

    private static final String IMIE = "Jan";
    private static final String DRUGIE_IMIE = "";
    private static final String NAZWISKO = "Kowalski";
    private static final String IDENTYFIKATOR_PODATKOWY = "000000000";
    private static final LocalDate DATA = LocalDate.of(2000,5,1);



    private OtwarcieKontaService otwarcieKontaService;

    private WeryfikacjaService weryfikacjaService = Mockito.mock(WeryfikacjaService.class);
    private ZaleznosciManager zaleznosciManager = Mockito.mock(ZaleznosciManager.class);
    private KontoRepository kontoRepository = Mockito.mock(KontoRepository.class);
    private OtwarcieKontaEventPublisher eventPublisher = Mockito.mock(OtwarcieKontaEventPublisher.class);

    @BeforeEach
    void setUp(){
     otwarcieKontaService = new OtwarcieKontaService(null, null,null,null);
    }

    @Test
    void powinienemOtworzycKonto() throws IOException {

        Mockito.when(weryfikacjaService.potwierdz(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenReturn(new WeryfikacjaResults("LOW", 1000));

        Mockito.when(zaleznosciManager.pobierzId(IMIE, DRUGIE_IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenReturn("123");

       OtwarcieKontaStatus status = otwarcieKontaService.openAccount(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA);

       assertEquals(OtwarcieKontaStatus.OPENED, status);
    }

    @Test
    void niePowinienemOtworzycKontaGdyNieakceptowanyProfilRyzyka() throws IOException{
        Mockito.when(weryfikacjaService.potwierdz(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenReturn(new WeryfikacjaResults(OtwarcieKontaService.UNACCEPTABLE_RISK_PROFILE, 0));

        OtwarcieKontaStatus status = otwarcieKontaService.openAccount(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA);

        assertEquals(OtwarcieKontaStatus.DECLINED, status);
    }

    @Test
    void niePowinienemOtworzycKontaGdyRyzykoNull() throws IOException{
        Mockito.when(weryfikacjaService.potwierdz(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenReturn(null);

        OtwarcieKontaStatus status = otwarcieKontaService.openAccount(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA);

        assertEquals(OtwarcieKontaStatus.DECLINED, status);
    }

    @Test
    void powienienemObsluzycWyjatekGdyWeryfikacjaServiceGoZwraca() throws IOException{

        Mockito.when(weryfikacjaService.potwierdz(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenThrow(new IOException());
        assertThrows(RuntimeException.class, () -> {
            otwarcieKontaService.openAccount(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA);
        });
    }

    @Test
    void powienienemObsluzycWyjatekGdyWeryfikacjaServiceGoZrzuci() throws IOException{
        Mockito.when(weryfikacjaService.potwierdz(IMIE, NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA))
                .thenReturn(new WeryfikacjaResults("LOW", 1000));
        Mockito.when(zaleznosciManager.pobierzId(Mockito.eq(IMIE), Mockito.eq(DRUGIE_IMIE), Mockito.eq(NAZWISKO), Mockito.eq(IDENTYFIKATOR_PODATKOWY), Mockito.eq(DATA)))
                .thenThrow(new RuntimeException());
        assertThrows(RuntimeException.class, () ->{
            otwarcieKontaService.openAccount(IMIE,NAZWISKO, IDENTYFIKATOR_PODATKOWY, DATA);
        });


    }




}
