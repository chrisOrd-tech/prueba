package com.ingchristopher.tareamascotas;

/**
 * Created by Ing. Christopher on 13/11/2016.
 */

public class Mascota {
    private int foto;
    private String nombre;
    private String raiting;

    public Mascota(int foto, String nombre, String raiting){
        this.foto = foto;
        this.nombre = nombre;
        this.raiting = raiting;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaiting() {
        return raiting;
    }

    public void setRaiting(String raiting) {
        this.raiting = raiting;
    }
}
