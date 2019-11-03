package org.izv.pgc.onpostexecutehebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncActivity extends AppCompatActivity {
    private Button btLanzar;
    private TextView tvTexto;

    private Boolean started = false;
    private Hebra hebra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);
        initComponents();
        initEvents();
    }

    private void initComponents() {
        btLanzar = findViewById(R.id.btLanzarAsync);
        tvTexto = findViewById(R.id.tvTextoAsync);

    }


    private void initEvents() {
        btLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hebra = new Hebra(20);
                if(!started) {
                    started = true;
                    hebra.execute();
                }
            }
        });
    }

    class Hebra extends AsyncTask<Integer, Integer, Boolean> {
        private int contador;

        public Hebra() {
            //CONSTRUCTOR - codigo que se ejecuta al crear la instancia
            this(20);
        }

        public Hebra(int vi) {
            //CONSTRUCTOR2 - codigo que se ejecuta al crear la instancia con parametro
            contador = vi;
        }


        @Override
        protected Boolean doInBackground(Integer... integers) {

            int timer = 1000;

            for (int countDown = 20; countDown >= 0; countDown--) {
                try {
                    Thread.sleep(timer);
                    publishProgress(countDown);
                } catch (InterruptedException ex) {
                    break;
                }
                if(isCancelled()) {
                    break;
                }
            }

            return true;

            }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            for(Integer i: values) {
                tvTexto.setText("" + i);
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Intent intent = new Intent(AsyncActivity.this, SegundaActividad.class);
            startActivity(intent);
        }
    }
}
