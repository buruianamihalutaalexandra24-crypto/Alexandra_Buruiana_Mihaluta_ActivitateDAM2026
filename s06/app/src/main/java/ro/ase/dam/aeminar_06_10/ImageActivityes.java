package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageActivityes extends AppCompatActivity {

    private List<Images> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_image_activityes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lv = findViewById(R.id.imagini);

        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent it = new Intent(this, WebViewActivity.class);
            it.putExtra("link", images.get(i).getLink());
            startActivity(it);
        });

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://cdn.prod.website-files.com/60414b21f1ffcdbb0d5ad688/65b161236e6a7d5c6711b4ca_types-of-bars.jpg");
        linkuriImagini.add("https://images.stockcake.com/public/d/a/1/da1ae429-9184-4116-af4a-a829c06a78e4_large/cozy-bar-interior-stockcake.jpg");
        linkuriImagini.add("https://www.scauneonline.ro/image/_Diverse/Amenajare%20bar%20acasa/amenajare-bar-acasa-16.jpg");
        linkuriImagini.add("https://images.stockcake.com/public/5/7/c/57c5157e-79f7-4245-90f4-7b7aac677062_large/vintage-bar-interior-stockcake.jpg");
        linkuriImagini.add("https://www.foodandwine.com/thmb/8rtGtUmtC0KiJCDxAUXP_cfwgM8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/GTM-Best-US-Bars-Katana-Kitten-FT-BLOG0423-fa9f2ba9925c47abb4afb0abd25d915a.jpg");

        List<String> texte = new ArrayList<>();
        texte.add("Tipuri de baruri");
        texte.add("Bar cu atmosferă caldă");
        texte.add("Bar amenajat acasă");
        texte.add("Bar în stil vintage");
        texte.add("Unul dintre cele mai bune baruri din SUA");

        List<String> linkuriPagini = new ArrayList<>();
        linkuriPagini.add("https://en.wikipedia.org/wiki/Bar_(establishment)");
        linkuriPagini.add("https://en.wikipedia.org/wiki/Pub");
        linkuriPagini.add("https://en.wikipedia.org/wiki/Cocktail");
        linkuriPagini.add("https://en.wikipedia.org/wiki/Speakeasy");
        linkuriPagini.add("https://en.wikipedia.org/wiki/Bartender");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            List<Bitmap> imagini = new ArrayList<>();

            for (String link : linkuriImagini) {
                imagini.add(descarcaBitmap(link));
            }

            handler.post(() -> {
                int reusite = 0;
                images = new ArrayList<>();
                for (int i = 0; i < imagini.size(); i++) {
                    if (imagini.get(i) != null) {
                        reusite++;
                    }
                    images.add(new Images(texte.get(i), imagini.get(i), linkuriPagini.get(i)));
                }
                ImageAdapter adapter = new ImageAdapter(this, R.layout.image_layout, images);
                lv.setAdapter(adapter);
                Toast.makeText(this, "Descărcate: " + reusite + " din " + imagini.size(),
                        Toast.LENGTH_LONG).show();
            });
        });
    }

    private Bitmap descarcaBitmap(String link) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(link);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            con.setConnectTimeout(10000);
            con.setReadTimeout(10000);

            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.e("IMG", "cod nepotrivit pentru " + link);
                return null;
            }

            InputStream is = con.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];
            int citit;
            while ((citit = is.read(buffer)) != -1) {
                baos.write(buffer, 0, citit);
            }
            is.close();
            byte[] date = baos.toByteArray();

            BitmapFactory.Options optiuni = new BitmapFactory.Options();
            optiuni.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(date, 0, date.length, optiuni);
            Log.d("IMG", "dimensiuni originale " + optiuni.outWidth + "x" + optiuni.outHeight
                    + ", octeti descarcati " + date.length);

            int latura = Math.max(optiuni.outWidth, optiuni.outHeight);
            int esantion = 1;
            while (latura / esantion > 800) {
                esantion *= 2;
            }

            BitmapFactory.Options optiuniFinale = new BitmapFactory.Options();
            optiuniFinale.inSampleSize = esantion;
            return BitmapFactory.decodeByteArray(date, 0, date.length, optiuniFinale);
        } catch (Exception e) {
            Log.e("IMG", "eroare pentru " + link + ": " + e);
            return null;
        } catch (OutOfMemoryError e) {
            Log.e("IMG", "memorie insuficienta pentru " + link);
            return null;
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}