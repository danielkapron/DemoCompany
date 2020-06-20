package demo.company.inwestycja;

import demo.company.Konto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;

public interface ZarzadzanieInwestycjaService {

    void dodajFundusz(Konto konto, BigDecimal wartoscInwestycji, Currency waluta);

    boolean kup(Konto konto, String funduszId, BigDecimal wartoscInwestycji) throws IOException;

    boolean sprzedaj(Konto konto, String funduszId, BigDecimal wartoscInwestycji) throws IOException;
}
