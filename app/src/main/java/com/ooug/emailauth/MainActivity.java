package com.ooug.emailauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtPwd;
    private FirebaseAuth firebaseAuth;
    private Button btnVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail=(EditText)findViewById(R.id.txtEmail);
        txtPwd=(EditText)findViewById(R.id.txtPwd);
        firebaseAuth=FirebaseAuth.getInstance();
        btnVerify=(Button)findViewById(R.id.btnVerify);



        btnVerify.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                btnVerify.setEnabled(false);

                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        btnVerify.setEnabled(true);
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "Verification Sucessful!", Toast.LENGTH_SHORT).show();
                            Intent loginActivity=new Intent(MainActivity.this,Main2Activity.class);
                            startActivity(loginActivity);
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Verification Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
    public void btnReg(View v)
    {
        final ProgressDialog progressDialog=ProgressDialog.show(MainActivity.this,"Please Wait...","verifing",true);
        (firebaseAuth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPwd.getText().toString()))
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();

                if (task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, ".", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, ".", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
