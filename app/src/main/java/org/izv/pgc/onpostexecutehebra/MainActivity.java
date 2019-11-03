package org.izv.pgc.onpostexecutehebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btThread, btAsync;


    private void initComponents() {
        btAsync = findViewById(R.id.btAsync);
        btThread = findViewById(R.id.btThread);
        btThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActividadHebra();
            }
        });
        btAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActividadAsync();
            }
        });
    }

    private void irActividadAsync() {
        Intent intent = new Intent(MainActivity.this,AsyncActivity.class);
        startActivity(intent);
    }

    private void irActividadHebra() {
        Intent intent = new Intent(MainActivity.this,HebraActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }
}
