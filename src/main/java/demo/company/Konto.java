package demo.company;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

public class Konto {

    private String id;
    private String imie;
    private String nazwisko;
    private LocalDate dob;
    private String identyfikatorPodatkowy;
    private BigDecimal zainwestowaneSrodki;
    private Currency waluta;
    private Set<String> inwestycje;
    private BigDecimal wolneSrodki;
    private LocalDate dataEmerytury;
    private LocalDate dataOtwarcia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getIdentyfikatorPodatkowy() {
        return identyfikatorPodatkowy;
    }

    public void setIdentyfikatorPodatkowy(String identyfikatorPodatkowy) {
        this.identyfikatorPodatkowy = identyfikatorPodatkowy;
    }

    public BigDecimal getZainwestowaneSrodki() {
        return zainwestowaneSrodki;
    }

    public void setZainwestowaneSrodki(BigDecimal zainwestowaneSrodki) {
        this.zainwestowaneSrodki = zainwestowaneSrodki;
    }

    public Currency getWaluta() {
        return waluta;
    }

    public void setWaluta(Currency waluta) {
        this.waluta = waluta;
    }

    public Set<String> getInwestycje() {
        return inwestycje;
    }

    public void setInwestycje(Set<String> inwestycje) {
        this.inwestycje = inwestycje;
    }

    public BigDecimal getWolneSrodki() {
        return wolneSrodki;
    }

    public void setWolneSrodki(BigDecimal wolneSrodki) {
        this.wolneSrodki = wolneSrodki;
    }

    public LocalDate getDataEmerytury() {
        return dataEmerytury;
    }

    public void setDataEmerytury(LocalDate dataEmerytury) {
        this.dataEmerytury = dataEmerytury;
    }

    public LocalDate getDataOtwarcia() {
        return dataOtwarcia;
    }

    public void setDataOtwarcia(LocalDate dataOtwarcia) {
        this.dataOtwarcia = dataOtwarcia;
    }
}
