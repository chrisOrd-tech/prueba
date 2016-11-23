package com.ingchristopher.tareamascotas;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class DatosContacto extends AppCompatActivity implements OnClickListener {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    EditText reciep, sub, msg;
    String rec, subject, textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);

        context = this;

        Button enviar = (Button)findViewById(R.id.btnEnviarMsj);
        reciep = (EditText)findViewById(R.id.etEmail);
        sub = (EditText)findViewById(R.id.etNombre);
        msg = (EditText)findViewById(R.id.etMensaje);

        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        rec = reciep.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pruebaemail30@gmail.com", "pruebaemail");
            }
        });

        pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("testfrom354@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject("Datos de " + subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
            reciep.setText("");
            msg.setText("");
            sub.setText("");
            Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Created by Ing. Christopher on 20/11/2016.
     */

    public static class RecyclerviewFragment extends android.support.v4.app.Fragment {

        private ArrayList<Mascota> mascotas;
        private RecyclerView listaMascotas;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //return super.onCreateView(inflater, container, savedInstanceState);
            View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

            listaMascotas = (RecyclerView)v.findViewById(R.id.rvMascotas);

            LinearLayoutManager llm = new LinearLayoutManager(getActivity());
            llm.setOrientation(LinearLayoutManager.VERTICAL);

            listaMascotas.setLayoutManager(llm);
            inicializarMascotas();
            inicializarAdaptador();

            return v;
        }

        public void inicializarMascotas(){
            mascotas = new ArrayList<Mascota>();

            mascotas.add(new Mascota(R.drawable.perro1,"Tasha","5"));
            mascotas.add(new Mascota(R.drawable.perro2,"Reina","2"));
            mascotas.add(new Mascota(R.drawable.perro3,"Pocky","1"));
            mascotas.add(new Mascota(R.drawable.perro4,"Manchis","3"));
            mascotas.add(new Mascota(R.drawable.perro5,"Pitty","9"));
            mascotas.add(new Mascota(R.drawable.perro1,"Hachi","2"));
            mascotas.add(new Mascota(R.drawable.perro2,"Pulgas","2"));
            mascotas.add(new Mascota(R.drawable.perro3,"Solovino","7"));
            mascotas.add(new Mascota(R.drawable.perro4,"Spike","8"));
            mascotas.add(new Mascota(R.drawable.perro5,"Trueno","10"));
        }

        public void inicializarAdaptador(){
            ActivityDos.MascotaAdaptador adaptador = new ActivityDos.MascotaAdaptador(mascotas);
            listaMascotas.setAdapter(adaptador);
        }
    }
}
