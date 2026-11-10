package ro.ase.dam.aeminar_06_10;

import android.os.Parcel;
import android.os.Parcelable;

public class Bar implements Parcelable {

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

    protected Bar(Parcel in) {
        nume = in.readString();
        oras = in.readString();
        tip = in.readString();
        areTerasa = in.readByte() != 0;
        nonStop = in.readByte() != 0;
        rating = in.readFloat();
        pretMediu = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeString(oras);
        dest.writeString(tip);
        dest.writeByte((byte) (areTerasa ? 1 : 0));
        dest.writeByte((byte) (nonStop ? 1 : 0));
        dest.writeFloat(rating);
        dest.writeInt(pretMediu);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bar> CREATOR = new Creator<Bar>() {
        @Override
        public Bar createFromParcel(Parcel in) {
            return new Bar(in);
        }

        @Override
        public Bar[] newArray(int size) {
            return new Bar[size];
        }
    };

    public String getNume() { return nume; }
    public String getOras() { return oras; }
    public String getTip() { return tip; }
    public boolean isAreTerasa() { return areTerasa; }
    public boolean isNonStop() { return nonStop; }
    public float getRating() { return rating; }
    public int getPretMediu() { return pretMediu; }

    public void setNume(String nume) { this.nume = nume; }
    public void setOras(String oras) { this.oras = oras; }
    public void setTip(String tip) { this.tip = tip; }
    public void setAreTerasa(boolean areTerasa) { this.areTerasa = areTerasa; }
    public void setNonStop(boolean nonStop) { this.nonStop = nonStop; }
    public void setRating(float rating) { this.rating = rating; }
    public void setPretMediu(int pretMediu) { this.pretMediu = pretMediu; }

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