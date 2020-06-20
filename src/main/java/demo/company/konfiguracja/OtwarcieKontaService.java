package demo.company.konfiguracja;

import demo.company.KontoRepository;

import java.io.IOException;
import java.time.LocalDate;

public class OtwarcieKontaService {

    public static final String UNACCEPTABLE_RISK_PROFILE = "HIGH";
    private WeryfikacjaService weryfikacjaService;
    private ZaleznosciManager zaleznosciManager;
    private KontoRepository kontoRepository;
    private OtwarcieKontaEventPublisher eventPublisher;


    public OtwarcieKontaService(WeryfikacjaService weryfikacjaService,
                                ZaleznosciManager zaleznosciManager,
                                KontoRepository kontoRepository,
                                OtwarcieKontaEventPublisher eventPublisher) {
        this.weryfikacjaService = weryfikacjaService;
        this.zaleznosciManager = zaleznosciManager;
        this.kontoRepository = kontoRepository;
        this.eventPublisher = eventPublisher;
    }


    public OtwarcieKontaStatus openAccount(String imie, String nazwisko, String identyfikatorPodatkowy, LocalDate dob)
            throws IOException {

        final WeryfikacjaResults weryfikacjaResults = weryfikacjaService.potwierdz(imie,
                nazwisko,
                identyfikatorPodatkowy,
                dob);

        if (weryfikacjaResults == null ||
                weryfikacjaResults.getProfilRyzyka().equals(UNACCEPTABLE_RISK_PROFILE)) {
            return OtwarcieKontaStatus.DECLINED;
        } else {
            final String id = zaleznosciManager.pobierzId(imie, "", nazwisko, identyfikatorPodatkowy, dob);
            if (id != null) {
                kontoRepository.save(id, imie, nazwisko, identyfikatorPodatkowy, dob, weryfikacjaResults);
                eventPublisher.notify(id);
                return OtwarcieKontaStatus.OPENED;
            } else {
                return OtwarcieKontaStatus.DECLINED;
            }
        }
    }
}
