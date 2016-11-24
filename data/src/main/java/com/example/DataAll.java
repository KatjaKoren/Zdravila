package com.example; /**
 * Created by Katja on 28. 02. 2016.
 */


import java.util.ArrayList;

public class DataAll {

    private ArrayList<Opomnik> opomniki = new ArrayList<>();
    private ArrayList<Zdravilo> zdravila = new ArrayList<>();
    private ArrayList<Cas> casi = new ArrayList<>();

    public DataAll() {

        opomniki = new ArrayList<Opomnik>();
        zdravila = new ArrayList<Zdravilo>();
        casi = new ArrayList<Cas>();
    }

    public void dodaj(Opomnik o){
        opomniki.add(o);
    }
    public void dodaj(Zdravilo z){zdravila.add(z);}
    public void dodaj(Cas c){casi.add(c);}


    @Override
    public String toString() {
        return "com.example.DataAll{" +
                "zdravila=" + zdravila +
                ", opomniki=" + opomniki +
                '}';
    }

    public Zdravilo getZdravilo(int i) {
        return zdravila.get(i);
    }

    public ArrayList<Zdravilo> getListZdravil() {
        return zdravila;
    }

    public ArrayList<Opomnik> isci(String iskani_niz) {
        ArrayList<Opomnik> najdeni = new ArrayList<>();
        for (int i=0; i<opomniki.size(); i++) {
            if (opomniki.get(i).jeIzbran(iskani_niz)) najdeni.add(opomniki.get(i));
        }
        return najdeni;
    }



    public static DataAll getSeznamZdravilData() {
        DataAll all = new DataAll();

        all.dodaj(new Zdravilo("Ospen", 1000, "mg", "filmsko oblozene tablete", "antibiotik", 30));
        all.dodaj(new Zdravilo("Naklofen duo", 75, "mg", "kapsule",  "protivnetno zdravilo", 20 ));
        all.dodaj(new Zdravilo("Plivit", 10, "ml", "kapljice",  "vitamin D", 40));
        all.dodaj(new Zdravilo("Amoksiklav", 875, "mg", "filsko oblozene tablete",  "antibiotik", 45));
        all.dodaj(new Zdravilo("Cancor Cor", 25, "mg","filmsko oblozene tablete", "zdravilo za bolezni srca in ozilja", 47));
        all.dodaj(new Zdravilo("Ospen", 1000, "mg", "filmsko oblozene tablete", "antibiotik", 30));
        all.dodaj(new Zdravilo("Naklofen duo", 75, "mg", "kapsule",  "protivnetno zdravilo", 20 ));
        all.dodaj(new Zdravilo("Plivit", 10, "ml", "kapljice",  "vitamin D", 40));
        all.dodaj(new Zdravilo("Amoksiklav", 875, "mg", "filsko oblozene tablete",  "antibiotik", 45));
        all.dodaj(new Zdravilo("Cancor Cor", 25, "mg","filmsko oblozene tablete", "zdravilo za bolezni srca in ozilja", 47));
        all.dodaj(new Zdravilo("Naklofen duo", 75, "mg", "kapsule",  "protivnetno zdravilo", 20 ));
        all.dodaj(new Zdravilo("Plivit", 10, "ml", "kapljice",  "vitamin D", 40));
        all.dodaj(new Zdravilo("Amoksiklav", 875, "mg", "filsko oblozene tablete",  "antibiotik", 45));
        all.dodaj(new Zdravilo("Cancor Cor", 25, "mg","filmsko oblozene tablete", "zdravilo za bolezni srca in ozilja", 47));

        all.dodaj(new Cas(2,3,2016,17,50));
        all.dodaj(new Cas(10,3,2016,14,00));
        all.dodaj(new Cas(12,3,2016,9,30));
        all.dodaj(new Cas(15,3,2016,21,45));
        all.dodaj(new Cas(17,3,2016,13,10));

        all.dodaj(new Opomnik(all.zdravila.get(0), all.casi.get(0)));
        all.dodaj(new Opomnik(all.zdravila.get(1), all.casi.get(1)));
        all.dodaj(new Opomnik(all.zdravila.get(2), all.casi.get(2)));
        all.dodaj(new Opomnik(all.zdravila.get(3), all.casi.get(3)));
        all.dodaj(new Opomnik(all.zdravila.get(4), all.casi.get(4)));


        return all;

    }
}
