package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity {

    private ArrayList<Bar> bare;

    @SuppressWarnings("unchecked")
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

        bare = (ArrayList<Bar>) getIntent().getSerializableExtra("bare");
        if (bare == null) {
            bare = new ArrayList<>();
        }

        ListView listView = findViewById(R.id.lista_lv);
        ArrayAdapter<Bar> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, bare);
        listView.setAdapter(adapter);

        trimiteListaInapoi();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Bar selectat = bare.get(position);
            Toast.makeText(this, selectat.toString(), Toast.LENGTH_LONG).show();
        });

        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            bare.remove(position);
            adapter.notifyDataSetChanged();
            trimiteListaInapoi();
            Toast.makeText(this, "Bar șters", Toast.LENGTH_SHORT).show();
            return true;
        });
    }

    private void trimiteListaInapoi() {
        Intent it = new Intent();
        it.putExtra("bare", bare);
        setResult(RESULT_OK, it);
    }
}