package com.ft_hangouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        else
        {
            //okok
        }
        setContentView(R.layout.complet_contact);

        final ContactManager db = new ContactManager(this);
        ArrayList<HashMap<String, String>> userList = db.GetUserByUserId(MainActivity.IDCONTACT + 1);

        String sum = "";
        for (HashMap<String, String> hash : userList)
        {
            for (String current : hash.values())
            {
                sum = sum + current + "<#>";
            }
        }
        String[] arr = sum.split("<#>");
        final TextView tv1 = (TextView)findViewById( R.id.CName );
        final TextView tv2 = (TextView)findViewById( R.id.CLastName );
        final TextView tv3 = (TextView)findViewById( R.id.CPhone );
        final TextView tv4 = (TextView)findViewById( R.id.CEmail );
        final TextView tv5 = (TextView)findViewById( R.id.CAdress );
        tv1.setText( arr[0] );
        tv2.setText( arr[1] );
        tv3.setText( arr[2] );
        tv4.setText( arr[3] );
        tv5.setText( arr[4] );
        /*final Button button = findViewById(R.id.buttonS);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                db.DeleteUser(MainActivity.IDCONTACT + 1);
                Intent intent = new Intent(CompletContact.this, MainActivity.class);
                startActivity(intent);

            }
        });*/
        //Pour supprimer ATTENTION, probleme d'id quand on supprime puis recrée un Contact, a voir ;/
    }
}
