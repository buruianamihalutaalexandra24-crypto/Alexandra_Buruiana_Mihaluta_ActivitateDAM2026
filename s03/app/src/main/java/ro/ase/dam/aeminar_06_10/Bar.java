package ro.ase.dam.aeminar_06_10;

import java.io.Serializable;

public class Bar implements Serializable {

    private String nume;
    private String oras;
    private String tip;
    private boolean areTerasa;
    private boolean nonStop;
    private float rating;
    private int pretMediu;

    public Bar(String nume, String oras, String tip, boolean areTerasa,
               boolean nonStop, float rating, int pretMediu) {
        this.nume = nume;
        this.oras = oras;
        this.tip = tip;
        this.areTerasa = areTerasa;
        this.nonStop = nonStop;
        this.rating = rating;
        this.pretMediu = pretMediu;
    }

    public String getNume() { return nume; }
    public String getOras() { return oras; }
    public String getTip() { return tip; }
    public boolean isAreTerasa() { return areTerasa; }
    public boolean isNonStop() { return nonStop; }
    public float getRating() { return rating; }
    public int getPretMediu() { return pretMediu; }

    @Override
    public String toString() {
        return "Bar{" +
                "nume='" + nume + '\'' +
                ", oras='" + oras + '\'' +
                ", tip='" + tip + '\'' +
                ", areTerasa=" + areTerasa +
                ", nonStop=" + nonStop +
                ", rating=" + rating +
                ", pretMediu=" + pretMediu +
                '}';
    }
}