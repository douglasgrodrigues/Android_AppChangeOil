package com.example.changeoil.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.changeoil.R;
import com.example.changeoil.config.ConfiguracaoFirebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private ImageButton buttonRegister;
    private TextInputEditText editData, editKm, editTipo;
    private EditText textKm, textData, textTipo;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        buttonRegister = findViewById(R.id.buttonRegister);
        editData = findViewById(R.id.editData);
        editKm = findViewById(R.id.editKm);
        editTipo = findViewById(R.id.editTipo);
        textData = findViewById(R.id.textData);
        textKm = findViewById(R.id.textKm);
        textTipo = findViewById(R.id.textTipo);


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
                    textKm.setText(kmTroca);
                    textTipo.setText(tipoTroca);

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSair:
                deslogarUsuario();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario() {
        try {
            autenticacao.signOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}