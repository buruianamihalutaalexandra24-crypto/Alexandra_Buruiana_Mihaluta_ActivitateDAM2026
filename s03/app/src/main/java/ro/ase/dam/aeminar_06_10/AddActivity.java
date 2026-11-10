package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //        setContentView(R.layout.activity_add);   // varianta făcută de mine
        setContentView(R.layout.activity_add_styled);     // varianta făcută cu AI
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void salveazaBar(View view) {
        // 1. Găsim view-urile după id
        EditText numeEt = findViewById(R.id.nume_et);
        Spinner orasSpinner = findViewById(R.id.oras_spinner);
        RadioGroup tipRg = findViewById(R.id.tip_rg);
        CheckBox terasaCb = findViewById(R.id.terasa_cb);
        Switch nonstopSw = findViewById(R.id.nonstop_sw);
        RatingBar ratingRb = findViewById(R.id.rating_rb);
        EditText pretEt = findViewById(R.id.pret_et);

        // 2. Citim valorile
        String nume = numeEt.getText().toString();
        String oras = orasSpinner.getSelectedItem().toString();
        RadioButton tipSelectat = findViewById(tipRg.getCheckedRadioButtonId());
        String tip = tipSelectat.getText().toString();
        boolean areTerasa = terasaCb.isChecked();
        boolean nonStop = nonstopSw.isChecked();
        float rating = ratingRb.getRating();

        int pret = 0;
        try {
            pret = Integer.parseInt(pretEt.getText().toString());
        } catch (NumberFormatException e) {
            // câmp gol sau nenumeric: rămâne 0
        }

        // 3. Creăm obiectul
        Bar bar = new Bar(nume, oras, tip, areTerasa, nonStop, rating, pret);

        // 4. Îl punem într-un Bundle și îl trimitem înapoi
        Bundle bundle = new Bundle();
        bundle.putSerializable("bar", bar);

        Intent it = new Intent();
        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
}