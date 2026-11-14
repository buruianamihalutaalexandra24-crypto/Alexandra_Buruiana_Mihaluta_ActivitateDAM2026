package ro.ase.dam.aeminar_06_10;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Images> lista;

    public ImageAdapter(Context context, int layout, List<Images> lista) {
        this.context = context;
        this.layout = layout;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
        }

        Images item = lista.get(position);

        ImageView imagineIV = convertView.findViewById(R.id.imagineIV);
        TextView descriereTV = convertView.findViewById(R.id.descriereTV);

        imagineIV.setImageBitmap(item.getImage());
        descriereTV.setText(item.getTextAfisat());

        return convertView;
    }
}