package org.izv.pgc.onpostexecutehebra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class HebraActivity extends AppCompatActivity  {

        private static final String TAG = "TIMER:HEBRA";
        private Hebra hebra = null;
        private TextView tvCountDown;


        class Hebra extends Thread {

            private volatile boolean activo = true;

            public boolean isActivo() {
                return activo;
            }

            public Hebra detener() {
                return setActivo(false);
            }

            public Hebra setActivo(boolean activo) {
                this.activo = activo;
                return this;
            }


            @Override
            public void run() {
                final Timer timer = new Timer();

                TimerTask task = new TimerTask() {
                    int tic = 0;
                    int countDown = 20;

                    @Override
                    public void run() {

                        if (tic % 1 == 0) {
                            countDown = Integer.parseInt(tvCountDown.getText().toString());
                            countDown = countDown - 1;
                            tvCountDown.post(new Runnable() {
                                @Override
                                public void run() {
                                    //Esto es la hebra UI PRINCIPAL
                                    tvCountDown.setText("" + countDown);
                                    if (countDown == 0) {
                                        detener();
                                        timer.cancel();
                                        if (!isActivo()) {
                                            pararHebra();
                                            Intent intent = new Intent(HebraActivity.this, SegundaActividad.class);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });

                        }
                        tic++;

                    }
                };

                // Lanzamos la tarea cada 1000ms
                timer.schedule(task, 0, 1000);



            }
        }

        private void initComponents() {
            Button btLanzar;
            btLanzar = findViewById(R.id.btLanzar);
            tvCountDown = findViewById(R.id.tvTexto);
            btLanzar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lanzarHebra();
                }
            });
        }


        private void lanzarHebra() {
            if(hebra == null) {
                hebra = new Hebra();
                hebra.start();
            }
        }

        private void pararHebra() {
            if (hebra != null) {
                hebra.interrupt();
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hebra);
            initComponents();
        }
    }
