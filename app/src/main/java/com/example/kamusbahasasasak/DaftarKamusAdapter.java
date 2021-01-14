package com.example.kamusbahasasasak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DaftarKamusAdapter extends ArrayAdapter<Kamus> {
    Context context;

    public DaftarKamusAdapter(@NonNull Context context, @NonNull List<Kamus> objects) {
        super(context, R.layout.row_kamus, objects);
        this.context = context;
    }

    class ViewHolder {
        TextView txTgl;
        TextView txKata;
        TextView txArti;
        TextView txJenisTempat;
        TextView txContoh;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Kamus tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_kamus,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.txTgl = convertView.findViewById(R.id.row_tx_tgl_kamus);
            viewHolder.txKata = convertView.findViewById(R.id.row_tx_kata);

            viewHolder.txArti= convertView.findViewById(R.id.row_tx_arti);
            viewHolder.txContoh = convertView.findViewById(R.id.row_tx_contoh);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txTgl.setText(GenericUtility.DATE_FORMAT.format(tr.getTanggal()));
        viewHolder.txKata.setText(tr.getKata());

        viewHolder.txArti.setText(tr.getArti());
        viewHolder.txContoh.setText(tr.getContoh());
        return convertView;
    }
}
