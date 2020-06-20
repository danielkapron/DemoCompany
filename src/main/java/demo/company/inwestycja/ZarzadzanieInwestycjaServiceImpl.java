package demo.company.inwestycja;

import demo.company.Konto;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Currency;

public class ZarzadzanieInwestycjaServiceImpl implements ZarzadzanieInwestycjaService {

    public static final MathContext MATH_CONTEXT = new MathContext(34, RoundingMode.DOWN);

    @Override
    public void dodajFundusz(Konto konto, BigDecimal wartoscInwestycji, Currency waluta) {
        if (waluta != Currency.getInstance("PLN")) {
            throw new IllegalArgumentException("Wspierane są wyłącznie konta w walucie PLN.");
        }
        konto.setWolneSrodki(konto.getWolneSrodki().add(wartoscInwestycji, MATH_CONTEXT));
    }

    @Override
    public boolean kup(Konto konto, String funduszId, BigDecimal wartoscInwestycji) throws IOException {
        if (konto.getWolneSrodki().compareTo(wartoscInwestycji) < 0) {
            throw new IllegalArgumentException("Niewystarczająca ilość wolnych środków.");
        }
        if (wykonajTransakcje(funduszId, wartoscInwestycji, "BUY")) {
            konto.setWolneSrodki(konto.getWolneSrodki().subtract(wartoscInwestycji, MATH_CONTEXT));
            konto.getInwestycje().add(funduszId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean sprzedaj(Konto konto, String funduszId, BigDecimal wartoscInwestycji) throws IOException {
        if (!konto.getInwestycje().contains(funduszId)) {
            throw new IllegalArgumentException("Na koncie nie ma funduszu " + funduszId);
        }
        //if a holding in this fund already exists, short-selling is allowed too
        if (wykonajTransakcje(funduszId, wartoscInwestycji, "SELL")) {
            konto.getInwestycje().remove(funduszId);
            konto.setWolneSrodki(konto.getWolneSrodki().add(wartoscInwestycji, MATH_CONTEXT));
            return true;
        } else {
            return false;
        }
    }

    boolean wykonajTransakcje(String funduszId,
                              BigDecimal wartoscInwestycji,
                              String direction) throws IOException {
        return new ZewnetrznyBroker().dokonajInwestycji(funduszId, wartoscInwestycji, direction);
    }
}
