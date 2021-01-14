package com.example.kamusbahasasasak;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTambahKamus;
    ImageButton btnChangeUserName;
    ListView lvDaftarKamus;
    TextView txNoData, txUsername;
    DaftarKamusAdapter adapter;
    List<Kamus> daftarKamus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inisialisasiView();
        loadDataKamus();
        setupListview();
    }

    private void inisialisasiView() {
        btnTambahKamus = findViewById(R.id.btn_add_kamus);
        btnTambahKamus.setOnClickListener(view -> bukaFormTambahKamus());
        btnChangeUserName = findViewById(R.id.btn_change_username);
        btnChangeUserName.setOnClickListener(view -> changeUserName());
        lvDaftarKamus = findViewById(R.id.lv_kamus);
        txNoData = findViewById(R.id.tx_nodata);
        txUsername = findViewById(R.id.tx_user_name);
        txUsername.setText(SharedPreferenceUtility.getUserName(this));

    }

    private void setupListview() {
        adapter = new DaftarKamusAdapter(this, daftarKamus);
        lvDaftarKamus.setAdapter(adapter);
    }

    private void loadDataKamus() {
        daftarKamus = SharedPreferenceUtility.getAllKamus(this);
    }
    private void refreshListView() {
        adapter.clear();
        loadDataKamus();
        adapter.addAll(daftarKamus);
    }

    private void bukaFormTambahKamus() {
        Intent intent = new Intent(this, FormKamusActivity.class);
        startActivity(intent);
    }

    private void changeUserName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ganti nama");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferenceUtility.saveUserName(getApplicationContext(),input.getText().toString());
                Toast.makeText(getApplicationContext(),"Nama user berhasil disimpan",Toast.LENGTH_SHORT).show();
                txUsername.setText(SharedPreferenceUtility.getUserName(getApplicationContext()));
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }
}