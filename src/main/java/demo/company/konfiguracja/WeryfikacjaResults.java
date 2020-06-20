package demo.company.konfiguracja;

public class WeryfikacjaResults {

    private String profilRyzyka;
    private long limit;

    public WeryfikacjaResults(String profilRyzyka, long limit) {
        this.profilRyzyka = profilRyzyka;
        this.limit = limit;
    }

    public String getProfilRyzyka() {
        return profilRyzyka;
    }

    public long getLimit() {
        return limit;
    }
}
