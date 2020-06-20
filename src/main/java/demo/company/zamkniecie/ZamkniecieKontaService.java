package demo.company.zamkniecie;

import demo.company.Konto;
import demo.company.konfiguracja.OtwarcieKontaService;
import demo.company.konfiguracja.WeryfikacjaResults;
import demo.company.konfiguracja.WeryfikacjaService;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class ZamkniecieKontaService {

    public static final int RETIREMENT_AGE = 65;
    private WeryfikacjaService backgroundCheckService;
    private Clock clock;

    public ZamkniecieKontaService(WeryfikacjaService backgroundCheckService, Clock clock) {
        this.backgroundCheckService = backgroundCheckService;
        this.clock = clock;
    }

    public ZamkniecieKontaResponse closeAccount(Konto konto) throws IOException {
        Period accountHolderAge = Period.between(konto.getDob(), LocalDate.now(clock));
        if (accountHolderAge.getYears() < RETIREMENT_AGE) {
            return new ZamkniecieKontaResponse(ZamkniecieKontaStatus.CLOSING_DENIED, LocalDateTime.now(clock));
        } else {
            final WeryfikacjaResults backgroundCheckResults = backgroundCheckService.potwierdz(
                    konto.getImie(),
                    konto.getNazwisko(),
                    konto.getIdentyfikatorPodatkowy(),
                    konto.getDob());
            if (backgroundCheckResults == null) {
                return new ZamkniecieKontaResponse(ZamkniecieKontaStatus.CLOSING_PENDING, LocalDateTime.now(clock));
            } else {
                final String riskProfile = backgroundCheckResults.getProfilRyzyka();
                if (riskProfile.equals(OtwarcieKontaService.UNACCEPTABLE_RISK_PROFILE)) {
                    return new ZamkniecieKontaResponse(ZamkniecieKontaStatus.CLOSING_PENDING, LocalDateTime.now(clock));
                } else {
                    return new ZamkniecieKontaResponse(ZamkniecieKontaStatus.CLOSING_OK, LocalDateTime.now(clock));
                }
            }

        }
    }
}
