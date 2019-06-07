package com.ft_hangouts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    public static int IDCONTACT = 0;
    public static String currentDateandTime = "";
    boolean shouldExecuteOnResume;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);

        }
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
        shouldExecuteOnResume = false;
        setContentView(R.layout.activity_main);


        ContactManager db = new ContactManager(this);
        ArrayList<HashMap<String, String>> userList = db.GetUsers();
        ListView lv = (ListView) findViewById(R.id.user_list);
        ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList, R.layout.list_row,new String[]{"lastname","name","phone", "id"}, new int[]{R.id.lastname, R.id.name, R.id.phone, R.id.ids});
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView tv = view.findViewById(R.id.ids);
                IDCONTACT = Integer.parseInt(tv.getText().toString());
                Intent i1 = new Intent( MainActivity.this, CompletContact.class );
                startActivity(i1);
            }
        });

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, GetInfo.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_color, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, ColorChange.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if(shouldExecuteOnResume)
        {
            Toast.makeText(getApplicationContext(), currentDateandTime, Toast.LENGTH_LONG).show();
        } else {
            shouldExecuteOnResume = true;
        }

    }

    @Override
    protected void onPause() {

        super.onPause();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        currentDateandTime = sdf.format(new Date());

    }
}