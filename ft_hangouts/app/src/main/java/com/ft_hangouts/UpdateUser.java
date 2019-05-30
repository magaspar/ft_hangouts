package com.ft_hangouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateUser extends AppCompatActivity
{
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (ColorChange.newcolor == 1)
        {
            getTheme().applyStyle(R.style.OverlayPrimaryColorBlue, true);
        }
        else if (ColorChange.newcolor == 2)
        {
            getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
        }
        else if (ColorChange.newcolor == 3)
        {
            getTheme().applyStyle(R.style.OverlayPrimaryColorGreen, true);
        }
        else
        {
            //okok
        }
        setContentView(R.layout.update_user);

        final ContactManager db = new ContactManager(this);
        ArrayList<HashMap<String, String>> userList = db.GetUserByUserId(MainActivity.IDCONTACT);
        String sum = "";
        for (HashMap<String, String> hash : userList)
        {
            for (String current : hash.values())
            {
                sum = sum + current + "<#>";
            }
        }
        final String[] arr = sum.split("<#>");
        final EditText tv1 = findViewById( R.id.CName );
        final EditText tv2 = findViewById( R.id.CLastName );
        final EditText tv3 = findViewById( R.id.CPhone );
        final EditText tv4 = findViewById( R.id.CEmail );
        final EditText tv5 = findViewById( R.id.CAdress );
        tv1.setText( arr[0] );
        tv2.setText( arr[1] );
        tv3.setText( arr[2] );
        tv4.setText( arr[3] );
        tv5.setText( arr[4] );

        final Button buttonM = findViewById(R.id.buttonM);
        buttonM.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                db.UpdateUserDetails(tv1.getText().toString(), tv2.getText().toString(), tv3.getText().toString(), tv4.getText().toString(), tv5.getText().toString(), MainActivity.IDCONTACT);
                Intent intent = new Intent(UpdateUser.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}