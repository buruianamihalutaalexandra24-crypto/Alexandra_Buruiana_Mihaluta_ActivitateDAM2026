package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private static final int COD_MODIFICARE = 47;

    private ArrayList<Bar> bare;
    private BarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bare = getIntent().getParcelableArrayListExtra("bare");
        if (bare == null) {
            bare = new ArrayList<>();
        }

        ListView listView = findViewById(R.id.lista_lv);
        adapter = new BarAdapter(this, bare);
        listView.setAdapter(adapter);

        trimiteListaInapoi();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent it = new Intent(this, AddActivity.class);
            it.putExtra("bar", bare.get(position));
            it.putExtra("pozitie", position);
            startActivityForResult(it, COD_MODIFICARE);
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            bare.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();
            Toast.makeText(this, "Bar șters", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_MODIFICARE && resultCode == RESULT_OK && data != null) {
            Bar nou = data.getParcelableExtra("bar");
            int pozitie = data.getIntExtra("pozitie", -1);

            if (nou != null && pozitie >= 0 && pozitie < bare.size()) {
                Bar selectat = bare.get(pozitie);
                selectat.setNume(nou.getNume());
                selectat.setOras(nou.getOras());
                selectat.setTip(nou.getTip());
                selectat.setAreTerasa(nou.isAreTerasa());
                selectat.setNonStop(nou.isNonStop());
                selectat.setRating(nou.getRating());
                selectat.setPretMediu(nou.getPretMediu());

                adapter.notifyDataSetChanged();
                trimiteListaInapoi();
                Toast.makeText(this, "Bar modificat", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void trimiteListaInapoi() {
        Intent it = new Intent();
        it.putParcelableArrayListExtra("bare", bare);
        setResult(RESULT_OK, it);
    }
}