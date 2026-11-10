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

public class MainActivity extends AppCompatActivity {

    private static final int COD_ADAUGARE = 45;

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == COD_ADAUGARE && resultCode == RESULT_OK && data != null) {
            Bar bar = (Bar) data.getSerializableExtra("bar");
            if (bar != null) {
                TextView barTv = findViewById(R.id.bar_tv);
                barTv.setText(bar.toString());
                Toast.makeText(this, bar.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
}