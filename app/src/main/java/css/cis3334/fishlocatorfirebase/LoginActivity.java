package css.cis3334.fishlocatorfirebase;

/**
 * Created by Amanda Nichols 4/22/2018
 */

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    /**
     * Declare EditText, Button variables
     */
    private EditText etEmail;
    private EditText etPassword;
    private Button buttonLogin;
    private Button buttonCreateLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize variables
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonCreateLogin = (Button) findViewById(R.id.buttonCreateLogin);
        mAuth = FirebaseAuth.getInstance(); //declare object for Firebase

        /**
         * Set up the Sign In button
         * User will enter email and password
         * When button is clicked, get user input
         */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // when Normal Login button is clicked, get user email and password
                signIn(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

        /**
         * Set up the Create Account button
         * User will enter email and password
         * When button is clicked, get user input
         */
        buttonCreateLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // when Create Account button is clicked, get user email and password
                createAccount(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });
    }

    private void signIn(String email, String password){
        //sign in the recurrent user with email and password previously created.
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "SignIn--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    finish();
                }
            }
        });
    }

    private void createAccount(String email, String password) {
        //create account for new users
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(LoginActivity.this, "createAccount--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    finish();
                }
            }
        });
    }

}
