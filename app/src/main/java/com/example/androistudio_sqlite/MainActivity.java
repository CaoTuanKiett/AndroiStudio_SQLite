package com.example.androistudio_sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView idComputer , nameComputer , price , idCategory ;
    Button insert , update , delete , view ;
    Helper helper ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idCategory = findViewById(R.id.idCategory);
        idComputer = findViewById(R.id.idComputer);
        nameComputer = findViewById(R.id.nameComputer);
        price = findViewById(R.id.price);

        insert = findViewById(R.id.button4);
        update = findViewById(R.id.button5);
        delete = findViewById(R.id.button6);
        view = findViewById(R.id.button7);
        helper = new Helper(this);
        Boolean checkInsertCate = helper.insertValueCategory("AVGCOM","May Tinh Loai Trung");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.insertValuecomputer(idComputer.getText().toString() , nameComputer.getText().toString() , price.getText().toString(),"AVGCOM");
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.updateValuecomputer(idComputer.getText().toString() , nameComputer.getText().toString() , price.getText().toString(),"AVGCOM");
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkInsertComputer = helper.deleteValuecomputer(idComputer.getText().toString());
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = helper.getvalueComputer();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No entry", Toast.LENGTH_SHORT).show();
                    return ;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id Computer : " + res.getString(1) + "\n");
                    buffer.append("Name Computer : " + res.getString(1) + "\n");
                    buffer.append("Price Computer : " + res.getString(2) + "\n");
                    buffer.append("Id Category : " + res.getString(3) + "\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Computer");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}