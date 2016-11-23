package com.ingchristopher.tareamascotas;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityDos extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dos);

        listaMascotas = (RecyclerView)findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        inicializarMascotas();
        inicializarAdaptador();

    }

    public void inicializarMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro1,"Tasha","5"));
        mascotas.add(new Mascota(R.drawable.perro2,"Reina","2"));
        mascotas.add(new Mascota(R.drawable.perro3,"Pocky","1"));
        mascotas.add(new Mascota(R.drawable.perro4,"Manchis","3"));
        mascotas.add(new Mascota(R.drawable.perro5,"Pitty","9"));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas);
        listaMascotas.setAdapter(adaptador);
    }

    /**
     * A simple {@link Fragment} subclass.
     */
    public static class Perfil extends Fragment {

        private ArrayList<MiMascota> miMascota;
        private RecyclerView fotosMascota;
        MiMascotaAdaptador adaptador;


        public Perfil() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            miMascota = new ArrayList<MiMascota>();
            View v = inflater.inflate(R.layout.fragment_perfil, container, false);
            fotosMascota = (RecyclerView)v.findViewById(R.id.rvMiMascota);

            GridLayoutManager glm = new GridLayoutManager(getActivity(),2);
            glm.setOrientation(GridLayoutManager.HORIZONTAL);

            fotosMascota.setLayoutManager(glm);
            inicializarMascotas();
            inicializarAdaptador();

            return v;
        }

        public void inicializarMascotas(){
            miMascota = new ArrayList<MiMascota>();

            miMascota.add(new MiMascota(R.drawable.perro1,"5"));
            miMascota.add(new MiMascota(R.drawable.perro1,"2"));
            miMascota.add(new MiMascota(R.drawable.perro1,"1"));
            miMascota.add(new MiMascota(R.drawable.perro1,"3"));
            miMascota.add(new MiMascota(R.drawable.perro1,"9"));

        }

        public void inicializarAdaptador(){
            adaptador = new MiMascotaAdaptador(miMascota,getActivity());
            fotosMascota.setAdapter(adaptador);
        }
    }

    /**
     * Created by Ing. Christopher on 13/11/2016.
     */

    public static class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
        public MascotaAdaptador(ArrayList<Mascota> mascotas){
            this.mascotas = mascotas;
        }

        ArrayList<Mascota>mascotas;
        Activity activity;

        @Override
        public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = (LayoutInflater.from(parent.getContext())).inflate(R.layout.cardview_mascota,parent,false);

            return new MascotaViewHolder(v);
        }

        public static class MascotaViewHolder extends RecyclerView.ViewHolder{
            private ImageView imgMascota;
            private TextView tvNombreMascota;
            private TextView tvRaiting;
            private ImageButton btnLike;

            public MascotaViewHolder(View itemView) {
                super(itemView);
                imgMascota          = (ImageView)itemView.findViewById(R.id.imgMascota);
                tvNombreMascota     = (TextView)itemView.findViewById(R.id.tvNombreMascota);
                tvRaiting           = (TextView)itemView.findViewById(R.id.tvRaiting);
                btnLike             = (ImageButton)itemView.findViewById(R.id.btnLike);


            }
        }

        @Override
        public void onBindViewHolder(final MascotaViewHolder holder, int position) {
            final Mascota mascota = mascotas.get(position);
            holder.imgMascota.setImageResource(mascota.getFoto());
            holder.tvNombreMascota.setText(mascota.getNombre());
            holder.tvRaiting.setText(mascota.getRaiting());

            /*holder.btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });*/
        }

        @Override
        public int getItemCount() {
            return mascotas.size();
        }

    }
}
