package com.example.bp3.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.bp3.R;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Opleiding;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.repository.RestApiHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class AanmakenAccount extends AppCompatActivity {

    RadioButton rdbStudent;
    RadioButton rdbDocent;
    RadioButton rdbBedrijf;
    EditText txtEmail;
    EditText txtWachtwoord;
    EditText txtNaam;
    EditText txtTelefoon;
    EditText txtLeerjaar;
    EditText txtContactEmail;
    EditText txtContactTelefoon;
    Spinner spnOnderwijs;
    Spinner spnOpleiding;
    Button btnAanmakenAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aanmaken_account);

        rdbStudent = (RadioButton) findViewById(R.id.rdbStudent);
        rdbDocent = (RadioButton) findViewById(R.id.rdbDocent);
        rdbBedrijf = (RadioButton) findViewById(R.id.rdbBedrijf);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtWachtwoord = (EditText) findViewById(R.id.txtWachtwoord);
        txtNaam = (EditText) findViewById(R.id.txtNaam);
        txtTelefoon = (EditText) findViewById(R.id.txtTelefoon);
        txtLeerjaar = (EditText) findViewById(R.id.txtLeerjaar);
        txtContactEmail = (EditText) findViewById(R.id.txtContactEmail);
        txtContactTelefoon = (EditText) findViewById(R.id.txtContactTelefoon);
        spnOnderwijs = (Spinner) findViewById(R.id.spnOnderwijs);
        spnOpleiding = (Spinner) findViewById(R.id.spnOpleiding);
        btnAanmakenAccount = (Button) findViewById(R.id.btnAanmaken);

        btnAanmakenAccount.setOnClickListener((v) -> {
            if (rdbStudent.isChecked()){

                RestApiHelper opleidingJSON = RestApiHelper
                        .prepareQuery("opleiding")
                        .klasse(Student.class)
                        .parameters(Arrays.asList(spnOpleiding.getSelectedItem().toString()))
                        .build();
                opleidingJSON.getObject(jo -> {
                    Opleiding opleiding = (Opleiding) opleidingJSON.toPOJO(jo);
                    Student student = new Student(txtEmail.getText().toString(), txtWachtwoord.getText().toString(),
                            txtNaam.getText().toString(), txtTelefoon.getText().toString(), Integer.parseInt(txtLeerjaar.getText().toString()), opleiding);
                    maakStudent(student);
                });

            } else if (rdbDocent.isChecked()){
                /*
                RestApiHelper opleidingJSON = RestApiHelper
                        .prepareQuery("opleiding")
                        .klasse(Student.class)
                        .parameters(Arrays.asList(spnOpleiding.getSelectedItem().toString()))
                        .build();
                opleidingJSON.getObject(jo -> {
                    Opleiding opleiding = (Opleiding) opleidingJSON.toPOJO(jo);
                    Docent docent = new Docent(txtEmail.getText().toString(), txtWachtwoord.getText().toString(),
                            txtNaam.getText().toString(), txtTelefoon.getText().toString(), opleiding);
                    maakStudent(docent);
                });
                */
            } else if (rdbBedrijf.isChecked()){
                    Bedrijf bedrijf = new Bedrijf(txtEmail.getText().toString(),
                            txtWachtwoord.getText().toString(), txtNaam.getText().toString(),
                            txtTelefoon.getText().toString(), txtContactEmail.getText().toString(),
                            txtContactTelefoon.getText().toString());
                    maakBedrijf(bedrijf);

            }


        });


    }

    private void maakStudent(Student account){
        RestApiHelper.prepareQuery("student")
                .build()
                .post(account, response -> Log.d("POST", "Het object is toegevoegd aan de database."));
    }

    private void maakDocent(Docent account){
        RestApiHelper.prepareQuery("docent")
                .build()
                .post(account, response -> Log.d("POST", "Het object is toegevoegd aan de database."));
    }
    private void maakBedrijf(Bedrijf account){
        RestApiHelper.prepareQuery("bedrijf")
                .build()
                .post(account, response -> Log.d("POST", "Het object is toegevoegd aan de database."));
    }
}
