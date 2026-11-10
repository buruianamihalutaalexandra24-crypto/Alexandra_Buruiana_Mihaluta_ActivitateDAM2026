package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int COD_ADAUGARE = 45;
    private static final int COD_LISTA = 46;

    private ArrayList<Bar> bare = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void deschideAdaugare(View view) {
        Intent it = new Intent(this, AddActivity.class);
        startActivityForResult(it, COD_ADAUGARE);
    }

    public void deschideLista(View view) {
        Intent it = new Intent(this, ListaActivity.class);
        it.putExtra("bare", bare);
        startActivityForResult(it, COD_LISTA);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK || data == null) {
            return;
        }

        TextView barTv = findViewById(R.id.bar_tv);

        if (requestCode == COD_ADAUGARE) {
            Bar bar = (Bar) data.getSerializableExtra("bar");
            if (bar != null) {
                bare.add(bar);
                barTv.setText("Număr de bare: " + bare.size() + "\n\n" + bar.toString());
                Toast.makeText(this, "Bar adăugat. Total: " + bare.size(),
                        Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == COD_LISTA) {
            ArrayList<Bar> actualizata = (ArrayList<Bar>) data.getSerializableExtra("bare");
            if (actualizata != null) {
                bare = actualizata;
                barTv.setText("Număr de bare: " + bare.size());
            }
        }
    }
}