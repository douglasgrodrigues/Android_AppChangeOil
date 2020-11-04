package com.example.changeoil.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.changeoil.R;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private ImageButton buttonRegister;
    private TextInputEditText editData, editKm, editTipo;
    private EditText textKm, textData;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonRegister = findViewById(R.id.buttonRegister);
        editData = findViewById(R.id.editData);
        editKm = findViewById(R.id.editKm);
        editTipo = findViewById(R.id.editTipo);
        textData = findViewById(R.id.textData);
        textKm = findViewById(R.id.textKm);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (editData.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Preencha a data", Toast.LENGTH_LONG).show();

                } else {
                    String dataTroca = editData.getText().toString();
                    String kmTroca = editKm.getText().toString();
                    String tipoTroca = editTipo.getText().toString();
                    editor.putString("data", dataTroca);
                    editor.putString("km", kmTroca);
                    editor.putString("tipo", tipoTroca);
                    editor.commit();

                    textData.setText(dataTroca);
                    textKm.setText(tipoTroca);


                }
            }
        });

    }




}