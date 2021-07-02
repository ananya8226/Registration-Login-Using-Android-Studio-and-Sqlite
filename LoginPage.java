package com.codeanu.vaccinator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    Button button2;
    EditText aadhar_number;
    DBHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        aadhar_number = (EditText) findViewById(R.id.aadhar);

        MyDB = new DBHelper(this);

        button2 = (Button) findViewById(R.id.submit);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aadhar_numberr = aadhar_number.getText().toString();

                if(aadhar_numberr.equals(""))
                {
                    Toast.makeText(LoginPage.this, "Enter Aadhar number", Toast.LENGTH_SHORT).show();
                }
                 else{
                    Boolean result = MyDB.checkaadhar(aadhar_numberr);
                    if(result == false)
                    {
                        Toast.makeText(LoginPage.this, "User does not exists.\n Kindly Register", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), RegisterPage.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(getApplicationContext(), Vaccinatorr.class);
                        startActivity(intent);
                    }
                }

            }
        });

    }

}
