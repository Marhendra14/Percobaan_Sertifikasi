package com.example.maehendra14.percobaan_sertifikasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_KEGIATAN = "extra_kegiatan";

    EditText edtNama,edtKeterangan,edtWaktu;
    Button btnSubmit,btnSelesai,btnKembali;

    DatabaseHelper databaseHelper;
    KegiatanModel km;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        edtNama = findViewById(R.id.edtNama);
        edtKeterangan = findViewById(R.id.edtKeterangan);
        edtWaktu = findViewById(R.id.edtWaktu);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSelesai = findViewById(R.id.btnSelesai);
        btnKembali = findViewById(R.id.btnKembali);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        Intent extras = getIntent();

        if(extras.hasExtra(EXTRA_KEGIATAN)){
            btnSubmit.setText("UBAH");
            btnSelesai.setEnabled(true);

            km = (KegiatanModel) extras.getSerializableExtra(EXTRA_KEGIATAN);

            edtNama.setText(km.getNama());
            edtKeterangan.setText(km.getKeterangan());
            edtWaktu.setText(km.getWaktu());

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama,keterangan,waktu;
                    int id;
                    id = km.getId();
                    nama = edtNama.getText().toString();
                    keterangan = edtKeterangan.getText().toString();
                    waktu = edtWaktu.getText().toString();
                    databaseHelper.updateKegiatan(id,nama,keterangan,waktu);
                    Toast.makeText(getApplicationContext(),"Berhasil Mengubah Pengingat",Toast.LENGTH_LONG).show();
                }
            });

            btnSelesai.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id;
                    id = km.getId();
                    databaseHelper.deleteKegiatan(id);

                    Toast.makeText(getApplicationContext(),"Kegiatan Telah Selesai",Toast.LENGTH_LONG).show();
                    finish();
                }
            });
        }else{
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nama,keterangan,waktu;
                    nama = edtNama.getText().toString();
                    keterangan = edtKeterangan.getText().toString();
                    waktu = edtWaktu.getText().toString();
                    databaseHelper.addKegiatan(nama,keterangan,waktu);
                    Toast.makeText(getApplicationContext(),"Berhasil Menambahkan Pengingat Baru",Toast.LENGTH_LONG).show();
                }
            });
        }
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
