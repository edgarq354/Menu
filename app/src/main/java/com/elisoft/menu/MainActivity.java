package com.elisoft.menu;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_dialogo,bt_notificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_dialogo=(Button)findViewById(R.id.bt_dialogo);
        bt_notificacion=(Button)findViewById(R.id.bt_notificacion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bt_dialogo.setOnClickListener(this);
        bt_notificacion.setOnClickListener(this);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.it_configuracion) {
            startActivity(new Intent(this,Configuracion.class));
        }else if(id==R.id.it_actualizacion){
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("Actualización");
            dialogo1.setMessage("¿Desea actualizar la aplicación?.");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("ACTULIZAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.elisoft.radiomovilclasico&hl=es");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
            dialogo1.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    dialogo1.cancel();
                }
            });

            dialogo1.show();
        }
        else if(id == R.id.it_salir){
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
            dialogo1.setTitle("Menu App");
            dialogo1.setMessage("¿Desea salir de la aplicación?.");
            dialogo1.setCancelable(false);
            dialogo1.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    finish();
                }
            });
            dialogo1.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogo1, int id) {
                    dialogo1.cancel();
                }
            });

        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_dialogo){
            final AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
            final LayoutInflater inflater = getLayoutInflater();
            final View dialoglayout = inflater.inflate(R.layout.popup_mensaje, null);
            final TextView Tv_titulo = (TextView) dialoglayout.findViewById(R.id.tv_titulo);
            final TextView Tv_mensaje = (TextView) dialoglayout.findViewById(R.id.tv_mensaje);
            final Button Bt_aceptar = (Button) dialoglayout.findViewById(R.id.bt_aceptar);

            dialogo.setView(dialoglayout);
            dialogo.setCancelable(false);
            final AlertDialog finalDialogo =dialogo.create();

            Bt_aceptar.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    finalDialogo.dismiss();
                }
            });

            finalDialogo.show();


        }else if (v.getId() == R.id.bt_notificacion) {
            Intent intent= new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://developer.android.com/index.html"));
            PendingIntent pendingIntent= PendingIntent.getActivity(getApplicationContext()
                    ,0,intent,0);
            Uri sonido= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            //Uri sonido= Uri.parse("android.resource://"+ this.mCtx.getPackageName() + "/" + R.raw.vocina);

            //Construccion de la notificacion;
            long[] pattern = new long[]{100,1000,500,800,100,100};
            // vibracion
            NotificationCompat.Builder builder= new NotificationCompat.Builder(
                    getApplicationContext());
            builder.setSmallIcon(R.mipmap.ic_launcher_round);
            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                    R.mipmap.ic_launcher_round));
            builder.setContentTitle("Academy xperts");
            builder.setContentText("Aprendiendo sobre las notificaciones" );
            builder.setSubText("Nueva notificaciones.");
            builder.setSound(sonido);
            builder.setVibrate(pattern );
            //Enviar la notificacion
            NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(1,builder.build());

        }
    }
}
