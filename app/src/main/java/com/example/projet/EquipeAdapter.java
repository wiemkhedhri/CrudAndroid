package com.example.projet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projet.Database.DatabaseHelper;
import com.example.projet.Database.Equipe;

import java.util.ArrayList;

public class EquipeAdapter extends ArrayAdapter<Equipe> {
    Activity context;
    ArrayList<Equipe> liste;
    DatabaseHelper db;


    public EquipeAdapter( Activity context, ArrayList<Equipe> liste) {
        super(context, R.layout.annonce_item,liste);
        this.context = context;
        this.liste = liste;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View v = inflater.inflate(R.layout.annonce_item, null, true);
        TextView id = v.findViewById(R.id.txtid);
        TextView name = v.findViewById(R.id.txtn);
        TextView coupe = v.findViewById(R.id.txtim);
        TextView continent= v.findViewById(R.id.txtdesH);
        ImageView edit = v.findViewById(R.id.img1);
        ImageView delete = v.findViewById(R.id.img2);
        id.setText(String.valueOf(liste.get(position).getId()));
        name.setText(liste.get(position).getNom());
        coupe.setText(liste.get(position).getDescription());
        continent.setText(liste.get(position).getContinent());
     Equipe s=liste.get(position);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater1=LayoutInflater.from(context);
                View subview=inflater1.inflate(R.layout.add_annonce,null);
                EditText n=subview.findViewById(R.id.edit_name);
                EditText d=subview.findViewById(R.id.edit_desc);
                 EditText img=subview.findViewById(R.id.edit_image);
                n.setText(s.getNom());
                d.setText(s.getDescription());
                img.setText(s.getContinent());
                AlertDialog.Builder a=new AlertDialog.Builder(context);
                a.setView(subview);
                a.create();
                a.setTitle("Edite ");
                a.setPositiveButton("update",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                db=new DatabaseHelper(context);
                                String des=d.getText().toString();
                                String name=n.getText().toString();
                                String imga=img.getText().toString();
                                Equipe e = new Equipe(s.getId(),name,des,imga);

                                db.updateAnnonce(e);
                                context.finish();
                                context.startActivity(context.getIntent());
                            }
                        });
                a.show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db=new DatabaseHelper(context);
                db.removeAnnonce(s.getId());
                context.finish();
                context.startActivity(context.getIntent());
                Toast.makeText(context," Delete",Toast.LENGTH_LONG).show();
            }
        });
        return v; }
    private  void DeletAnnonce(Equipe e){
        db.removeAnnonce(e.getId());context.finish();
        context.startActivity(context.getIntent());
        Toast.makeText(context," delete",Toast.LENGTH_LONG).show();

    }


    }
