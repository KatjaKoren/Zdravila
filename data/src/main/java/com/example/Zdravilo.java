package com.example;


/**
 * Created by Katja on 28. 02. 2016.
 */
public class Zdravilo {

    private String poimenovanje;
    private Integer kolicina;
    private String merska_enota;
    private String oblika;
    private String namen;
    private Integer st_tablet;

    public Zdravilo(String poimenovanje, Integer kolicina ,String merska_enota, String oblika, String namen, Integer st_tablet) {
        this.poimenovanje = poimenovanje;
        this.kolicina = kolicina;
        this.merska_enota = merska_enota;
        this.oblika = oblika;
        this.namen = namen;
        this.st_tablet = st_tablet;
    }


    @Override
    public String toString() {
        return "com.example.Zdravilo: " + poimenovanje + " " + kolicina + " " + merska_enota +" / " + oblika + " / " + namen +" " + st_tablet;
    }

    public String getPoimenovanje() {
        return poimenovanje;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setPoimenovanje(String poimenovanje) {
        this.poimenovanje = poimenovanje;
    }

    public String getNamen() {
        return namen;
    }

    public void setNamen(String namen) {
        this.namen = namen;
    }

    public String getOblika() {
        return oblika;
    }

    public void setOblika(String oblika) {
        this.oblika = oblika;
    }

    public String getMerska_enota() {
        return merska_enota;
    }

    public void setMerska_enota(String merska_enota) {
        this.merska_enota = merska_enota;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Integer getSt_tablet() {
        return st_tablet;
    }

    public void setSt_tablet(Integer st_tablet) {
        this.st_tablet = st_tablet;
    }
}
