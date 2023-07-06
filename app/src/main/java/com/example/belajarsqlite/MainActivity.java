package com.example.belajarsqlite;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MhsModel> mhsList;
    MhsModel mm;
    DBHelper db;
    boolean isEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText ednama = (EditText) findViewById(R.id.ednama);
        EditText ednim = (EditText) findViewById(R.id.ednim);
        EditText ednomerhp = (EditText) findViewById(R.id.ednomerhp);
        Button buttonsave = (Button) findViewById(R.id.buttonsave);

        mhsList = new ArrayList<>();

        isEdit = false;

        Intent intent_main = getIntent();
        if(intent_main.hasExtra("mhsData")){
          mm = intent_main.getExtras().getParcelable("mhsData");
          ednama.setText(mm.getNama());
          ednim.setText(mm.getNim());
          ednomerhp.setText(mm.getNomerhp());


          isEdit = true;

          buttonsave.setBackgroundColor(Color.BLUE);
          buttonsave.setText("Edit");
        }

        db = new DBHelper(getApplicationContext());

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isian_nama = ednama.getText().toString();
                String isian_nim = ednim.getText().toString();
                String isian_nomerhp = ednomerhp.getText().toString();
                int mhslist = Integer.parseInt(mhsList.toString());

                Intent intent_list = new Intent(MainActivity.this, ListMhsActivity.class);


                for(int i=1; i <= mhslist; i++){
                    System.out.print(i+" ");
                }

                if(isian_nama.isEmpty() || isian_nim.isEmpty() || isian_nomerhp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
                }else{
                    //mhsList.add(new MhsModel(-1, isian_nama, isian_nim, isian_nomerhp));


                    boolean stts;

                    if(!isEdit){
                        mm = new MhsModel(-1, isian_nama, isian_nim, isian_nomerhp);
                        stts = db.simpan(mm);

                        ednama.setText("");
                        ednim.setText("");
                        ednomerhp.setText("");

                    }else{
                        mm = new MhsModel(mm.getId(), isian_nama, isian_nim, isian_nomerhp);
                        stts = db.ubah(mm);
                    }

                    if(stts){
                        Toast.makeText(getApplicationContext(), "Data Berhasil Dismpan", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Data Gagal Dismpan", Toast.LENGTH_SHORT).show();
                    }



                    // intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    //startActivity(intent_list);

                }

            }
        });

        Button buttonreview = (Button) findViewById(R.id.buttonreview);
        buttonreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mhsList = db.list();

                if(mhsList.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Data Melebihi Batas", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent_list = new Intent(MainActivity.this, ListMhsActivity.class);
                    intent_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(intent_list);
                }


            }
        });
    }
}