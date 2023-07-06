package com.example.belajarsqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMhsActivity extends AppCompatActivity {

    MhsAdapter mhsAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmhs);

        //ListView lvnama =(ListView) findViewById(R.id.lvnama);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<MhsModel> mhsList = getIntent().getExtras().getParcelableArrayList("mhsList");

        mhsAdapter = new MhsAdapter(mhsList, new MhsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ArrayList<MhsModel> mhsList, int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListMhsActivity.this);
                dialog.setTitle("Pilihan");
                dialog.setItems(new CharSequence[]{"Hapus", "Edit"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        DBHelper db = new DBHelper(getApplicationContext());
                        MhsModel mm = mhsList.get(position);

                        switch (item){
                            case 0:
                                boolean stts = db.hapus(mm.getId());

                                if(stts) {
                                    mhsAdapter.removeItem(position);
                                    Toast.makeText(getApplicationContext(), "data berhasil dihapus", Toast.LENGTH_SHORT).show();

                                }
                                break;
                            case 1:
                                Intent intent_main = new Intent(ListMhsActivity.this, MainActivity.class);
                                intent_main.putExtra("mhsData", mm);

                                startActivity(intent_main);
                                break;
                        }
                    }
                });
                dialog.create().show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);

        FloatingActionButton fabtambah = findViewById(R.id.fabtambah);
        fabtambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMhsActivity.this, MainActivity.class));
            }
        });

 //       if(mhsList.isEmpty()){
  //          mhsList.add("Empty");
//        }

//        ArrayAdapter<String> ad_nama = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mhsList);
//
//        lvnama.setAdapter(ad_nama);

    }
}