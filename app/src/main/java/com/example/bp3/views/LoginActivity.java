package com.example.bp3.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.bp3.MainActivity;
import com.example.bp3.R;
import com.example.bp3.service.models.Account;
import com.example.bp3.service.models.Bedrijf;
import com.example.bp3.service.models.Docent;
import com.example.bp3.service.models.Student;
import com.example.bp3.service.repository.RestApiHelper;

import java.util.Arrays;
/**
 * @author Thom van den Hoven
 */

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        RadioButton rdbStudent = (RadioButton) findViewById(R.id.rdbStudent);
        RadioButton rdbDocent = (RadioButton) findViewById(R.id.rdbDocent);
        RadioButton rdbBedrijf = (RadioButton) findViewById(R.id.rdbBedrijf);
        TextView lblError = (TextView) findViewById(R.id.lblError);
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener((v) -> {
            /*@Override
            public void onClick(View view) {
                attemptLogin();
            }*/

            ////////////////TEST ACCOUNTS////////////////////////////
            //Verwijder de comments en zet het onderstaande blok in comments om de login
            //te vervangen met de test accounts.
            //Als de test accounts zijn ingeschakelt maakt het niet uit wat je invult, zolang je
            //de juiste radiobutton aan hebt geklikt log je in als student, docent of bedrijf.

            if (rdbStudent.isChecked()) {
                //Test Student ophalen
                RestApiHelper teststudentJSON = RestApiHelper
                        .prepareQuery("student")
                        .klasse(Student.class)
                        .parameters(Arrays.asList("jklaas@student.avans.nl"))
                        .build();
                teststudentJSON.getObject(jo -> {
                    Student student = (Student) teststudentJSON.toPOJO(jo);
                    Account.currentUser = student;
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                }, error -> Log.d("Error", error.toString()));
            }else if (rdbDocent.isChecked()) {
                //Test Docent ophalen
                RestApiHelper testdocentJSON = RestApiHelper
                        .prepareQuery("docent")
                        .klasse(Docent.class)
                        .parameters(Arrays.asList("BKatwijk@avans.nl"))
                        .build();
                testdocentJSON.getObject(jo -> {
                    Docent docent = (Docent) testdocentJSON.toPOJO(jo);
                    Account.currentUser = docent;
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                }, error -> Log.d("Error", error.toString()));
            } else if (rdbBedrijf.isChecked()) {
                //Test Bedrijf ophalen
                RestApiHelper testbedrijfJSON = RestApiHelper
                        .prepareQuery("bedrijf")
                        .klasse(Bedrijf.class)
                        .parameters(Arrays.asList("ict@community.nl"))
                        .build();
                testbedrijfJSON.getObject(jo -> {
                    Bedrijf bedrijf = (Bedrijf) testbedrijfJSON.toPOJO(jo);
                    Account.currentUser = bedrijf;
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
            }, error -> Log.d("Error", error.toString()));
            }
            /////////////////////////////////////////////////////

            //Zet dit onderdeel in comments als je de test accounts aan hebt staan
//            if (rdbStudent.isChecked()){
//                RestApiHelper studentJSON = RestApiHelper
//                        .prepareQuery("student")
//                        .klasse(Student.class)
//                        .parameters(Arrays.asList(mEmailView.getText().toString()))
//                        .build();
//                studentJSON.getObject(jo -> {
//                    Student student = (Student) studentJSON.toPOJO(jo);
//                    toMain(student);
//                }, error -> Log.d("Error", error.toString()));
//
//            } else if (rdbDocent.isChecked()){
//                RestApiHelper docentJSON = RestApiHelper
//                        .prepareQuery("docent")
//                        .klasse(Docent.class)
//                        .parameters(Arrays.asList(mEmailView.getText().toString()))
//                        .build();
//                docentJSON.getObject(jo -> {
//                    Docent docent = (Docent) docentJSON.toPOJO(jo);
//                    toMain(docent);
//                }, error -> Log.d("Error", error.toString()));
//
//            } else if (rdbBedrijf.isChecked()){
//                RestApiHelper bedrijfJSON = RestApiHelper
//                        .prepareQuery("bedrijf")
//                        .klasse(Bedrijf.class)
//                        .parameters(Arrays.asList(mEmailView.getText().toString()))
//                        .build();
//                bedrijfJSON.getObject(jo -> {
//                    Bedrijf bedrijf = (Bedrijf) bedrijfJSON.toPOJO(jo);
//                    toMain(bedrijf);
//                }, error -> Log.d("Error", error.toString()));
//
//            }
            /////////////////////////////////////////////////

        });

        Button btnAanmaken = (Button) findViewById(R.id.btnMaakAccount);
        btnAanmaken.setOnClickListener((v) -> {
            Intent i = new Intent(v.getContext(), AanmakenAccount.class);
            startActivity(i);
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


    }

    private void toMain(Account account){
        //Check de ingevulde waardes en ga naar het hoofdmenu als deze correct zijn
        TextView lblError = (TextView) findViewById(R.id.lblError);
        if (account == null){
            lblError.setText(getString(R.string.wrong_email));
            return;
        }
        if (account.getWachtwoord() == mPasswordView.getText().toString())
        {
            Account.currentUser = account;
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            lblError.setText(getString(R.string.wrong_pass));
        }
    }

    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}
