package ro.ase.dam.aeminar_06_10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private int pozitie = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bar existent = getIntent().getParcelableExtra("bar");
        pozitie = getIntent().getIntExtra("pozitie", -1);

        if (existent != null) {
            completeazaCampuri(existent);
        }
    }

    private void completeazaCampuri(Bar bar) {
        EditText numeEt = findViewById(R.id.nume_et);
        Spinner orasSpinner = findViewById(R.id.oras_spinner);
        RadioGroup tipRg = findViewById(R.id.tip_rg);
        CheckBox terasaCb = findViewById(R.id.terasa_cb);
        Switch nonstopSw = findViewById(R.id.nonstop_sw);
        RatingBar ratingRb = findViewById(R.id.rating_rb);
        EditText pretEt = findViewById(R.id.pret_et);
        Button salveazaBtn = findViewById(R.id.salveaza_btn);

        numeEt.setText(bar.getNume());

        String[] orase = getResources().getStringArray(R.array.orase);
        for (int i = 0; i < orase.length; i++) {
            if (orase[i].equals(bar.getOras())) {
                orasSpinner.setSelection(i);
                break;
            }
        }

        if (bar.getTip().equals(getString(R.string.tip_pub))) {
            tipRg.check(R.id.tip_pub_rb);
        } else if (bar.getTip().equals(getString(R.string.tip_club))) {
            tipRg.check(R.id.tip_club_rb);
        } else {
            tipRg.check(R.id.tip_cafenea_rb);
        }

        terasaCb.setChecked(bar.isAreTerasa());
        nonstopSw.setChecked(bar.isNonStop());
        ratingRb.setRating(bar.getRating());
        pretEt.setText(String.valueOf(bar.getPretMediu()));
        salveazaBtn.setText(R.string.salveaza_modificari);
    }

    public void salveazaBar(View view) {
        EditText numeEt = findViewById(R.id.nume_et);
        Spinner orasSpinner = findViewById(R.id.oras_spinner);
        RadioGroup tipRg = findViewById(R.id.tip_rg);
        CheckBox terasaCb = findViewById(R.id.terasa_cb);
        Switch nonstopSw = findViewById(R.id.nonstop_sw);
        RatingBar ratingRb = findViewById(R.id.rating_rb);
        EditText pretEt = findViewById(R.id.pret_et);

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
            pret = 0;
        }

        Bar bar = new Bar(nume, oras, tip, areTerasa, nonStop, rating, pret);

        Bundle bundle = new Bundle();
        bundle.putParcelable("bar", bar);
        bundle.putInt("pozitie", pozitie);

        Intent it = new Intent();
        it.putExtras(bundle);
        setResult(RESULT_OK, it);
        finish();
    }
}