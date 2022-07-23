package com.example.validatelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class MainActivity extends AppCompatActivity {
    CheckBox checkBox;
    EditText Name,Mobile,Email,Password,ConfirmPassword;
    Button Submit;
    MaterialAlertDialogBuilder materialAlertDialogBuilder;
    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        Name=findViewById(R.id.name);
        Mobile=findViewById(R.id.mobile);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        ConfirmPassword=findViewById(R.id.confirm_password);
        checkBox=findViewById(R.id.checkbox);
        Submit=findViewById(R.id.submit);

        awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.name,
                RegexTemplate.NOT_EMPTY,R.string.invalid_name);

        awesomeValidation.addValidation(this,R.id. mobile,
                "[5-9]{1}[0-9]{9}$",R.string.invalid_mobile);

        awesomeValidation.addValidation(this,R.id.email,
                Patterns.EMAIL_ADDRESS,R.string.invalid_email);

        awesomeValidation.addValidation(this,R.id.password,
                ".{8,}",R.string.invalid_password);

        awesomeValidation.addValidation(this,R.id.confirm_password,
                R.id.password,R.string.invalid_confirm_password);

        materialAlertDialogBuilder=new MaterialAlertDialogBuilder(this);

        Submit.setEnabled(false);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nAmE = Name.getText().toString();
                String mObIlE = Mobile.getText().toString();
                String eMaIl = Email.getText().toString();
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(),
                            "Form Validate Successfully...", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Validation Failed", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("NAME", nAmE);
                intent.putExtra("MOBILE", mObIlE);
                intent.putExtra("EMAIL", eMaIl);
                startActivity(intent);

            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b && awesomeValidation.validate()) {
                    materialAlertDialogBuilder.setTitle("Terms And Conditions");
                    materialAlertDialogBuilder.setMessage("Accept all Terms and Conditions");
                    materialAlertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Submit.setEnabled(true);
                            dialog.dismiss();
                        }
                    });

                    materialAlertDialogBuilder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            checkBox.setChecked(false);
                        }
                    });

                    materialAlertDialogBuilder.show();
                } else if (awesomeValidation.validate() == false) {
                    Submit.setEnabled(false);
                } else {
                    Submit.setEnabled(false);
                }
            }
        });

        /* Submit.setOnClickListener(new View.OnClickListener() {
 @Override
 public void onClick(View v) {
 }
 });*/
    }
}


