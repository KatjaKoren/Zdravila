package com.example;

/**
 * Created by Katja on 28. 02. 2016.
 */
public class Opomnik {

    Zdravilo z;
    Cas c;

    public Opomnik(Zdravilo z, Cas c) {
        this.z = z;
        this.c = c;
    }

    @Override
    public String toString() {
        return "com.example.Opomnik{" +
                "Zdravila=" + z +
                ", com.example.Cas=" + c +
                '}';
    }

    public boolean jeIzbran(String kljuc) {
        if (z.equals(kljuc)) return true;
        if (c.equals(kljuc)) return true;
        return false;
    }

    public Zdravilo getZ() {
        return z;
    }

    public Cas getC() {
        return c;
    }

    public void setZ(Zdravilo z) {
        this.z = z;
    }

    public void setC(Cas c) {
        this.c = c;
    }
}
