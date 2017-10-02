package fr.supinternet.slike;


import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    LinearLayout loginLayout;

    EditText username;
    EditText email;
    EditText password;

    EditText email_login;
    EditText password_login;

    Button loginButton;
    Button registerButton;

    View hr;


    TextInputLayout usernameLayout;
    TextInputLayout passwordLayout;
    TextInputLayout emailLayout;


    TextInputLayout passwordLayout_login;
    TextInputLayout emailLayout_login;


    //int i = 0;


    ProgressBar spinner;


    //FIREBASE AUTH
    private FirebaseAuth mAuth;

    //DB
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //FIREBASE Auth
        mAuth = FirebaseAuth.getInstance();


        //DATABASE
        mDatabase = FirebaseDatabase.getInstance().getReference("users");


        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);

        username = (EditText) findViewById(R.id.form_username);
        password = (EditText) findViewById(R.id.form_password);
        email = (EditText) findViewById(R.id.form_email);

        password_login = (EditText) findViewById(R.id.form_password_login);
        email_login = (EditText) findViewById(R.id.form_email_login);

        usernameLayout = (TextInputLayout) findViewById(R.id.layout_username);
        passwordLayout = (TextInputLayout) findViewById(R.id.layout_password);
        emailLayout = (TextInputLayout) findViewById(R.id.layout_email);

        passwordLayout_login = (TextInputLayout) findViewById(R.id.layout_password_login);
        emailLayout_login = (TextInputLayout) findViewById(R.id.layout_email_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        hr = findViewById(R.id.hr);

        spinner = (ProgressBar) findViewById(R.id.progressBar1);


    }


    //sylvain01
    public void loginInput(View v) {
        Log.d("login", "login");

        if (email_login.length() == 0 && password_login.length() == 0) {
            CharSequence text = "Veuillez vos identifiants";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(MainActivity.this, text, duration);
            toast.show();
        } else {
            if (email_login.length() == 0) {
                CharSequence text = "Login : Email vide!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(MainActivity.this, text, duration);
                toast.show();

            } else {
                if (password_login.length() == 0) {
                    CharSequence text = "Login : Password vide !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(MainActivity.this, text, duration);
                    toast.show();
                } else {

                    spinner.setVisibility(View.VISIBLE);

                    username.setVisibility(View.INVISIBLE);
                    email.setVisibility(View.INVISIBLE);
                    password.setVisibility(View.INVISIBLE);


                    loginButton.setVisibility(View.INVISIBLE);
                    registerButton.setVisibility(View.INVISIBLE);

                    hr.setVisibility(View.INVISIBLE);

                    usernameLayout.setVisibility(View.INVISIBLE);
                    emailLayout.setVisibility(View.INVISIBLE);
                    passwordLayout.setVisibility(View.INVISIBLE);

                    passwordLayout_login.setVisibility(View.INVISIBLE);
                    emailLayout_login.setVisibility(View.INVISIBLE);


                    mAuth.signInWithEmailAndPassword(email_login.getText().toString(), password_login.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d("tata", "signInWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        loginLayout.setBackgroundColor(0);

                                        spinner.setVisibility(View.GONE);
                                        username.setVisibility(View.VISIBLE);
                                        email.setVisibility(View.VISIBLE);
                                        password.setVisibility(View.VISIBLE);

                                        loginButton.setVisibility(View.VISIBLE);
                                        registerButton.setVisibility(View.VISIBLE);

                                        usernameLayout.setVisibility(View.VISIBLE);
                                        emailLayout.setVisibility(View.VISIBLE);
                                        passwordLayout.setVisibility(View.VISIBLE);

                                        hr.setVisibility(View.VISIBLE);

                                        usernameLayout.setVisibility(View.VISIBLE);
                                        emailLayout.setVisibility(View.VISIBLE);
                                        passwordLayout.setVisibility(View.VISIBLE);

                                        passwordLayout_login.setVisibility(View.VISIBLE);
                                        emailLayout_login.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                        startActivity(intent);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w("tata", "signInWithEmail:failure", task.getException());
                                        Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    }

                                    // ...
                                }
                            });
                }

            }

        }
    }


    public void registerInput(View v) {

        Context context = getApplicationContext();
        if (password.length() == 0 && username.length() == 0 && email.length() == 0) {
            CharSequence text = "Please enter your data !";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (password.length() == 0) {

                CharSequence text = "Invalid password !";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            } else {
                if (username.length() == 0) {
                    CharSequence text = "Invalid username !";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                } else {
                    if (email.length() == 0) {
                        CharSequence text = "Invalid email !";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else {
                        //loginLayout.setBackgroundColor(R.color.colorPrimaryDark);
                        //loading view + redirect to new activity
                        spinner.setVisibility(View.VISIBLE);

                        username.setVisibility(View.INVISIBLE);
                        email.setVisibility(View.INVISIBLE);
                        password.setVisibility(View.INVISIBLE);


                        loginButton.setVisibility(View.INVISIBLE);
                        registerButton.setVisibility(View.INVISIBLE);

                        hr.setVisibility(View.INVISIBLE);

                        usernameLayout.setVisibility(View.INVISIBLE);
                        emailLayout.setVisibility(View.INVISIBLE);
                        passwordLayout.setVisibility(View.INVISIBLE);

                        passwordLayout_login.setVisibility(View.INVISIBLE);
                        emailLayout_login.setVisibility(View.INVISIBLE);


                        //final Handler handler = new Handler();

                        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            Log.d("toto", "createUserWithEmail:success");
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            //updateUI(user);

                                            User user1 = new User(username.getText().toString(), email.getText().toString());
                                            mDatabase.child(user.getUid()).setValue(user1);

                                            loginLayout.setBackgroundColor(0);

                                            spinner.setVisibility(View.GONE);
                                            username.setVisibility(View.VISIBLE);
                                            email.setVisibility(View.VISIBLE);
                                            password.setVisibility(View.VISIBLE);

                                            loginButton.setVisibility(View.VISIBLE);


                                            usernameLayout.setVisibility(View.VISIBLE);
                                            emailLayout.setVisibility(View.VISIBLE);
                                            passwordLayout.setVisibility(View.VISIBLE);


                                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                            startActivity(intent);
                                        } else {
                                            // If sign in fails, display a message to the user.
                                            Log.w("toto", "createUserWithEmail:failure", task.getException());
                                            Toast.makeText(MainActivity.this, task.getException().getMessage(),
                                                    Toast.LENGTH_LONG).show();
                                            System.out.print(email.getText().toString());
                                            //updateUI(null);
                                            username.setVisibility(View.VISIBLE);
                                            email.setVisibility(View.VISIBLE);
                                            password.setVisibility(View.VISIBLE);

                                            loginButton.setVisibility(View.VISIBLE);


                                            usernameLayout.setVisibility(View.VISIBLE);
                                            emailLayout.setVisibility(View.VISIBLE);
                                            passwordLayout.setVisibility(View.VISIBLE);
                                        }

                                        // ...
                                    }
                                });

                        //spinner.setVisibility(View.GONE);



                    /*
                    t.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            i+=1;

                            if(i == 5){

                                spinner.setVisibility(View.GONE);

                                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                startActivity(intent);

                            }
                        }
                    }, 1000,1000);

*/


                    }
                }


            }
        }
    }
}
