package com.ft_hangouts;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;

import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;


public class ColorChange extends AppCompatActivity
{
    public static int newcolor;
    @Override
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
        setContentView(R.layout.color_change);

        final Button buttonB = findViewById(R.id.buttonB);
        final Button buttonR = findViewById(R.id.buttonR);
        final Button buttonG = findViewById(R.id.buttonG);
        buttonB.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(ColorChange.this, MainActivity.class);
                newcolor = 1;
                startActivity(intent);
            }
        });
        buttonR.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(ColorChange.this, MainActivity.class);
                newcolor = 2;
                startActivity(intent);
            }
        });
        buttonG.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent intent = new Intent(ColorChange.this, MainActivity.class);
                newcolor = 3;
                startActivity(intent);
            }
        });

    }
}
