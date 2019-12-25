package com.example.maehendra14.percobaan_sertifikasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnTambah;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<KegiatanModel> listKegiatan;

    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv);
        btnTambah = findViewById(R.id.btnTambah);

        databaseHelper = new DatabaseHelper(getApplicationContext());
//        databaseHelper.addKegiatan("A","a","A");

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        listKegiatan = databaseHelper.getKegiatan();
        mAdapter = new RvKegiatanAdapter(listKegiatan);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                i.putExtra(DetailActivity.EXTRA_KEGIATAN,listKegiatan.get(position));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),DetailActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listKegiatan = databaseHelper.getKegiatan();
        mAdapter = new RvKegiatanAdapter(listKegiatan);
        mRecyclerView.setAdapter(mAdapter);
    }
}
