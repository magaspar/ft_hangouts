package com.ft_hangouts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletContact extends AppCompatActivity
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
        setContentView(R.layout.complet_contact);

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
        final TextView tv1 = findViewById( R.id.CName );
        final TextView tv2 = findViewById( R.id.CLastName );
        final TextView tv3 = findViewById( R.id.CPhone );
        final TextView tv4 = findViewById( R.id.CEmail );
        final TextView tv5 = findViewById( R.id.CAdress );
        tv1.setText( arr[1] );
        tv2.setText( arr[4] );
        tv3.setText( arr[0] );
        tv4.setText( arr[3] );
        tv5.setText( arr[2] );
        final Button button = findViewById(R.id.buttonS);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                db.DeleteUser(MainActivity.IDCONTACT);
                Intent intent = new Intent(CompletContact.this, MainActivity.class);
                startActivity(intent);

            }
        });
        final Button buttonM = findViewById(R.id.buttonM);
        buttonM.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(CompletContact.this, UpdateUser.class);
                startActivity(intent);
            }
        });
        final Button buttonMessage = findViewById(R.id.buttonMessage);
        buttonMessage.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String number = arr[0];
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });
        final Button buttonCall = findViewById(R.id.buttonCall);
        buttonCall.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String number = arr[0];
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + number));
                startActivity(callIntent);
            }
        });
    }
}
