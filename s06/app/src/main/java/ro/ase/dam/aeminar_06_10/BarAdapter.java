package ro.ase.dam.aeminar_06_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BarAdapter extends ArrayAdapter<Bar> {

    public BarAdapter(@NonNull Context context, @NonNull ArrayList<Bar> bare) {
        super(context, 0, bare);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_bar, parent, false);
        }

        Bar bar = getItem(position);

        TextView numeTv = convertView.findViewById(R.id.nume_tv);
        TextView orasTipTv = convertView.findViewById(R.id.oras_tip_tv);
        TextView ratingPretTv = convertView.findViewById(R.id.rating_pret_tv);
        TextView facilitatiTv = convertView.findViewById(R.id.facilitati_tv);

        numeTv.setText(bar.getNume());
        orasTipTv.setText(bar.getOras() + " • " + bar.getTip());
        ratingPretTv.setText("Rating: " + bar.getRating() + "  |  Preț mediu: "
                + bar.getPretMediu() + " lei");
        facilitatiTv.setText((bar.isAreTerasa() ? "Cu terasă" : "Fără terasă") + " • "
                + (bar.isNonStop() ? "Non-stop" : "Program normal"));

        return convertView;
    }
}