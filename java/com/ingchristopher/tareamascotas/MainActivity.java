package com.ingchristopher.tareamascotas;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpPager();
        setSupportActionBar(toolbar);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment>fragments = new ArrayList<>();

        fragments.add(new DatosContacto.RecyclerviewFragment());
        fragments.add(new ActivityDos.Perfil());
        return fragments;
    }

    private void setUpPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                irDatosContacto();
                break;
            case R.id.mAcercaDe:
                irDatosDesarrollador();
                break;
            case R.id.actionView:
                irActivityDos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void irDatosDesarrollador(){
        Intent i = new Intent(this,DatosDesarrollador.class);
        startActivity(i);
    }

    public void irDatosContacto(){
        Intent intent1 = new Intent(this,DatosContacto.class);
        startActivity(intent1);
    }

    public void irActivityDos(){
        Intent intent2 = new Intent(this,ActivityDos.class);
        startActivity(intent2);
    }

}
