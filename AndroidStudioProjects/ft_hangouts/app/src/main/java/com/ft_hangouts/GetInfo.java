package com.ft_hangouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class GetInfo extends AppCompatActivity
{
    Button   mButton;
    EditText mEditLastName;
    EditText mEditName;
    EditText mEditPhoneNum;
    EditText mEditEmailAdd;
    EditText mEditAdres;
    ContactManager m = new ContactManager(this);
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_info);

        mButton = findViewById(R.id.button);
        mEditLastName = findViewById(R.id.editLastName);
        mEditName = findViewById(R.id.editName);
        mEditPhoneNum = findViewById(R.id.editPhoneNum);
        mEditEmailAdd= findViewById(R.id.editEmailAdd);
        mEditAdres = findViewById(R.id.editAdd);

        final Button button = findViewById(R.id.premier);


        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                String tmpLastName;
                String tmpName;
                String tmpPhoneNum;
                Integer tmpPhone;
                String tmpEmailAdd;
                String tmpAdd;


                tmpLastName = mEditLastName.getText().toString();
                tmpName = mEditName.getText().toString();
                tmpPhoneNum = mEditPhoneNum.getText().toString();
                //tmpPhone = Integer.parseInt(tmpPhoneNum); --> Fait planter si le champs n'es pas rempli -_-'
                tmpEmailAdd = mEditEmailAdd.getText().toString();
                tmpAdd = mEditAdres.getText().toString();


                ContactManager dbHandler = new ContactManager(GetInfo.this);
                dbHandler.insertUserDetails(tmpLastName,tmpName,tmpPhoneNum);

                Intent intent = new Intent(GetInfo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
