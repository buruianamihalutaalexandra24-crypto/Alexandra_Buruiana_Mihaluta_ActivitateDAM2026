package ro.ase.dam.aeminar_06_10;

import android.graphics.Bitmap;

public class Images {

    private String textAfisat;
    private Bitmap image;
    private String link;

    public Images(String textAfisat, Bitmap image, String link) {
        this.textAfisat = textAfisat;
        this.image = image;
        this.link = link;
    }

    public String getTextAfisat() {
        return textAfisat;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getLink() {
        return link;
    }
}