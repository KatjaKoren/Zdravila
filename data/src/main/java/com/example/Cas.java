package com.example;

/**
 * Created by Katja on 28. 02. 2016.
 */
public class Cas {
    private int dan;
    private int mesec;
    private int leto;
    private int ura;
    private int minuta;

    public Cas(int dan, int mesec, int leto, int ura, int minuta) {
        this.dan = dan;
        this.mesec = mesec;
        this.leto = leto;
        this.ura = ura;
        this.minuta = minuta;
    }

    @Override
    public String toString() {
        return "com.example.Cas jemanja: " + dan + "." + mesec + "." + leto + " " + ura + ":" + minuta;
    }
}
