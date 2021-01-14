package com.example.kamusbahasasasak;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class FormKamusActivity extends AppCompatActivity {

    Button btnSimpan;
    TextInputLayout tilKata,tilArti,tilContoh;
    EditText edtTgl;
    Spinner spJenisKamus;
    Date tanggalKamus;
    final String[] tipeKamus = {Kamus.KATABENDA, Kamus.KATAKERJA, Kamus.KATASIFAT};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_kamus);
        inisialisasiView();
    }

    private void inisialisasiView() {
        btnSimpan = findViewById(R.id.btn_simpan);
        btnSimpan.setOnClickListener(view -> simpan());
        edtTgl = findViewById(R.id.edt_tgl);
        edtTgl.setOnClickListener(view -> pickDate());
        tilKata = findViewById(R.id.til_kata);
        tilArti= findViewById(R.id.til_arti);
        tilContoh= findViewById(R.id.til_contoh);
        spJenisKamus = findViewById(R.id.spn_jenis);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                tipeKamus
        );
        spJenisKamus.setAdapter(adapter);
        spJenisKamus.setSelection(0);
    }

    private void simpan() {
        if (isDataValid()) {
            Kamus tr = new Kamus();

            tr.setKata(tilKata.getEditText().getText().toString());
            tr.setJenis(spJenisKamus.getSelectedItem().toString());
            tr.setArti(tilArti.getEditText().getText().toString());
            tr.setContoh(tilContoh.getEditText().getText().toString());
            tr.setTanggal(tanggalKamus);
            SharedPreferenceUtility.addFavorite(this,tr);
            Toast.makeText(this,"Data berhasil disimpan",Toast.LENGTH_SHORT).show();

            // Kembali ke layar sebelumnya setelah 500 ms (0.5 detik)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);


        }
    }

    private boolean isDataValid() {
        if (tilContoh.getEditText().getText().toString().isEmpty()
                || tilArti.getEditText().getText().toString().isEmpty()
                || tilKata.getEditText().getText().toString().isEmpty()

        ) {
            Toast.makeText(this,"Lengkapi semua isian",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /*
        Dipanggil saat user ingin mengisi tanggal transaksi
        Menampilkan date picker dalam popup dialog
     */
    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int thn = c.get(Calendar.YEAR);
        int bln = c.get(Calendar.MONTH);
        int tgl = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (DatePickerDialog.OnDateSetListener) (view, yyyy, mm, dd) -> {
                    edtTgl.setText(dd + "-" + (mm + 1) + "-" + yyyy);
                    c.set(yyyy,mm,dd);
                    tanggalKamus= c.getTime();
                },
                thn, bln, tgl);
        datePickerDialog.show();
    }

}