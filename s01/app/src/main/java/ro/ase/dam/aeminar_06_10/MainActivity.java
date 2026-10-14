package ro.ase.dam.aeminar_06_10;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("seminar", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("seminar", "onResume");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("seminar", "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("seminar", "onDestroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("seminar", "onPause");

    }

    public void openActivity(View view) {
        Intent it = new Intent(this, activitateRezultat.class);
        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Mesaj din Activitate NOUA");
        bundle.putInt("numar1", 5);
        bundle.putInt("numar2", 7);
        it.putExtras(bundle);

        startActivityForResult(it, 83);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("temp", "ok");
        if(requestCode == 83)
        {
        Log.e("temp", "ok22");
            if(resultCode==RESULT_OK){
                String mesajItoarcere = data.getStringExtra("mesaj");
                int suma = data.getIntExtra("suma",0);
                Toast.makeText(this,mesajItoarcere+" "+suma, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

