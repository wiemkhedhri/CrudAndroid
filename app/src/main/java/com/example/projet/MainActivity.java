package com.example.projet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projet.Database.DatabaseHelper;
import com.example.projet.Database.Equipe;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private ListView listView;
private EquipeAdapter adapter;
private DatabaseHelper db;
private ArrayList<Equipe> listequipe;
FloatingActionButton b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_view);
        db=new DatabaseHelper(this);
        int nb=db.getAnnonceCount();
        if (nb>0) {
            listequipe=db.getAllAnnonce();
            EquipeAdapter adapter=new EquipeAdapter(this,listequipe);
            adapter.notifyDataSetChanged();
            listView.setAdapter(adapter);
            db.close();

        }
        else {
            Toast.makeText(MainActivity.this,"Empty data base",Toast.LENGTH_LONG).show();
        }


        b=findViewById(R.id.btn_add);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater=LayoutInflater.from(MainActivity.this);
                View viewadd=inflater.inflate(R.layout.add_annonce,null);

                EditText n=viewadd.findViewById(R.id.edit_name);
                EditText im=viewadd.findViewById(R.id.edit_image);
                EditText des=viewadd.findViewById(R.id.edit_desc);
                AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);
                a.setTitle("Add new Stade");
                a.setView(viewadd);
                a.create();
                a.setPositiveButton("Add ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name=n.getText().toString();
                        String image=im.getText().toString();
String description=des.getText().toString();
                        if ( TextUtils.isEmpty(name)){
                            Toast.makeText(MainActivity.this,"check input values",Toast.LENGTH_LONG).show();

                        }else{
                            Equipe e = new Equipe(name,image,description);

                            db.addAnnonce(e);
                            finish();
                            startActivity(getIntent());
                        }
                    }
                });

                a.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this,"Task cancelled",Toast.LENGTH_LONG).show();
                    }
                });
                a.show();
            }
        });



    }
}