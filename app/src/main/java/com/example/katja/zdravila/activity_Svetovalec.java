package com.example.katja.zdravila;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import weka.core.Attribute;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class activity_Svetovalec extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svetovalec);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        NumberPicker npvrocina = (NumberPicker) findViewById(R.id.numberPicker2);
        npvrocina.setMaxValue(43);
        npvrocina.setMinValue(30);

        RadioGroup rgkaselj = (RadioGroup) findViewById(R.id.rg_kaselj);
        int st_kaselj = rgkaselj.getCheckedRadioButtonId();

        RadioGroup rgvnetogrlo = (RadioGroup) findViewById(R.id.rg_grlo);
        int st_grlo = rgvnetogrlo.getCheckedRadioButtonId();

        RadioGroup rgnos = (RadioGroup) findViewById(R.id.rg_izcedek);
        int st_nos = rgnos.getCheckedRadioButtonId();

        RadioGroup rgbolecine = (RadioGroup) findViewById(R.id.rg_bolecine);
        int st_bolecine = rgbolecine.getCheckedRadioButtonId();

        RadioGroup rgglavobol = (RadioGroup) findViewById(R.id.rg_glavobol);
        int st_glavobol = rgglavobol.getCheckedRadioButtonId();

        RadioGroup rgmrzlica = (RadioGroup) findViewById(R.id.rg_mrzlica);
        int st_mrzlica = rgmrzlica.getCheckedRadioButtonId();

        RadioGroup rgprsnikos = (RadioGroup) findViewById(R.id.rg_prsni_kos);
        int st_prsnikos = rgprsnikos.getCheckedRadioButtonId();

        RadioGroup rgutrujenost = (RadioGroup) findViewById(R.id.rg_utrujenost);
        int st_utrujenost = rgutrujenost.getCheckedRadioButtonId();

        RadioGroup rgvrtoglavica = (RadioGroup) findViewById(R.id.rg_vrtoglavica);
        int st_vrtoglavica = rgvrtoglavica.getCheckedRadioButtonId();

        RadioGroup rgzasoplost = (RadioGroup) findViewById(R.id.rg_zasoplost);
        int st_zasoplost = rgzasoplost.getCheckedRadioButtonId();

        RadioGroup rgpotenje = (RadioGroup) findViewById(R.id.rg_potenje);
        int st_potenje = rgpotenje.getCheckedRadioButtonId();

        RadioGroup rgslabost = (RadioGroup) findViewById(R.id.rg_slabost);
        int st_slabost = rgslabost.getCheckedRadioButtonId();

        RadioGroup rgizpuscaji = (RadioGroup) findViewById(R.id.rg_izpuscaji);
        int st_izpuscaji = rgizpuscaji.getCheckedRadioButtonId();

        RadioGroup rgsrbeceoci= (RadioGroup) findViewById(R.id.rg_srbece_oci);
        int st_srbeceoci = rgsrbeceoci.getCheckedRadioButtonId();

        //Deklariranje atributov

        Attribute Vrocina = new Attribute("Vrocina");
        Attribute Kaselj = new Attribute("Kaselj");
        Attribute VnetoGrlo = new Attribute("Vneto grlo");
        Attribute IzcedekNos = new Attribute("Izcedek iz nosu ali zamasen nos");
        Attribute Bolecine = new Attribute("Bolecine");
        Attribute Glavobol = new Attribute("Glavobol");
        Attribute Mrzlica = new Attribute("Mrzlica");
        Attribute Kihanje = new Attribute("Kihanje");
        Attribute PrsniKos = new Attribute("Bolecina v prsnem kosu");
        Attribute Utrujenost = new Attribute("Utrujenost");
        Attribute Vrtoglavica = new Attribute("Vrtoglavica");
        Attribute Zasoplost = new Attribute("Zasoplost");
        Attribute Potenje = new Attribute("Potenje");
        Attribute Slabost = new Attribute("Slabost");
        Attribute Izpuscaji = new Attribute("Izpuscaji");
        Attribute SrbeceOci = new Attribute("Srbece oci");


        FastVector fvkaselj = new FastVector(3);
        fvkaselj.addElement("ne");
        fvkaselj.addElement("moker");
        fvkaselj.addElement("suh");

        FastVector fvgrlo = new FastVector(2);
        fvgrlo.addElement("da");
        fvgrlo.addElement("ne");

        FastVector fvizcedeknos = new FastVector(2);
        fvizcedeknos.addElement("da");
        fvizcedeknos.addElement("ne");

        FastVector fvbolecine = new FastVector(3);
        fvgrlo.addElement("brez");
        fvgrlo.addElement("blaga");
        fvgrlo.addElement("mocnejsa");

        FastVector fvglavobol = new FastVector(3);
        fvglavobol.addElement("brez");
        fvglavobol.addElement("blaga");
        fvglavobol.addElement("mocnejsa");

        FastVector fvmrzlica = new FastVector(2);
        fvmrzlica.addElement("da");
        fvmrzlica.addElement("ne");

        FastVector fvkihanje = new FastVector(2);
        fvkihanje.addElement("da");
        fvkihanje.addElement("ne");

        FastVector fvprsnikos = new FastVector(2);
        fvprsnikos.addElement("da");
        fvprsnikos.addElement("ne");

        FastVector fvutrujenost = new FastVector(2);
        fvutrujenost.addElement("da");
        fvutrujenost.addElement("ne");

        FastVector fvvrtoglavica = new FastVector(2);
        fvvrtoglavica.addElement("da");
        fvvrtoglavica.addElement("ne");

        FastVector fvzasoplost = new FastVector(2);
        fvzasoplost.addElement("da");
        fvzasoplost.addElement("ne");

        FastVector fvpotenje = new FastVector(2);
        fvpotenje.addElement("da");
        fvpotenje.addElement("ne");

        FastVector fvslabost = new FastVector(2);
        fvslabost.addElement("da");
        fvslabost.addElement("ne");

        FastVector fvizpuscaji = new FastVector(2);
        fvizpuscaji.addElement("da");
        fvizpuscaji.addElement("ne");

        FastVector fvsrbeceoci = new FastVector(2);
        fvsrbeceoci.addElement("da");
        fvsrbeceoci.addElement("ne");


        FastVector fvWekaAtributi = new FastVector(16);
        fvWekaAtributi.addElement(Vrocina);
        fvWekaAtributi.addElement(Kaselj);
        fvWekaAtributi.addElement(VnetoGrlo);
        fvWekaAtributi.addElement(IzcedekNos);
        fvWekaAtributi.addElement(Bolecine);
        fvWekaAtributi.addElement(Glavobol);
        fvWekaAtributi.addElement(Mrzlica);
        fvWekaAtributi.addElement(Kihanje);
        fvWekaAtributi.addElement(PrsniKos);
        fvWekaAtributi.addElement(Utrujenost);
        fvWekaAtributi.addElement(Vrtoglavica);
        fvWekaAtributi.addElement(Zasoplost);
        fvWekaAtributi.addElement(Potenje);
        fvWekaAtributi.addElement(Slabost);
        fvWekaAtributi.addElement(Izpuscaji);

        // Določanje instance, ki jo želim napovedati
        Instances dataset = new Instances("bolezen", fvWekaAtributi, 0);

        String strvrocina = (npvrocina.toString());

        String strkaselj;
        if(st_kaselj == 0 )
           strkaselj = "ne";
        if(st_kaselj == 1)
            strkaselj = "moker";
        if(st_kaselj == 2)
            strkaselj = "suh";

        String strgrlo;
        if(st_grlo == 0 )
            strgrlo = "da";
        if(st_grlo== 1)
            strgrlo = "ne";

        String strizcedek_nos;
        if(st_grlo == 0 )
            strizcedek_nos = "da";
        if(st_grlo== 1)
            strizcedek_nos = "ne";

        String strbolecine;
        if(st_bolecine == 0 )
            strbolecine = "brez";
        if(st_bolecine == 1)
            strbolecine = "blaga";
        if(st_bolecine == 2)
           strbolecine = "mocnejsa";

        String strglavobol;
        if(st_glavobol == 0 )
            strglavobol = "brez";
        if(st_glavobol == 1)
            strglavobol ="rahel";
        if(st_glavobol == 2)
            strglavobol = "mocnejsi";

        String strmrzlica;
        if(st_grlo == 0 )
            strmrzlica = "da";
        if(st_grlo== 1)
            strmrzlica = "ne";

        String strkihanje;
        if(st_grlo == 0 )
            strkihanje = "da";
        if(st_grlo== 1)
            strkihanje = "ne";

        String strprsnikos;
        if(st_grlo == 0 )
            strprsnikos = "da";
        if(st_grlo== 1)
            strprsnikos = "ne";

        String strutrujenost;
        if(st_grlo == 0 )
            strutrujenost = "da";
        if(st_grlo== 1)
            strutrujenost = "ne";

        String strvrtoglavica;
        if(st_grlo == 0 )
            strvrtoglavica = "da";
        if(st_grlo== 1)
            strvrtoglavica = "ne";

        String strzasoplost;
        if(st_grlo == 0 )
            strzasoplost = "da";
        if(st_grlo== 1)
            strzasoplost = "ne";

        String strpotenje;
        if(st_grlo == 0 )
            strpotenje = "da";
        if(st_grlo== 1)
            strpotenje = "ne";

        String strslabost;
        if(st_grlo == 0 )
            strslabost = "da";
        if(st_grlo== 1)
            strslabost = "ne";

        String strizpuscaji;
        if(st_grlo == 0 )
            strizpuscaji = "da";
        if(st_grlo== 1)
            strizpuscaji = "ne";

        String strsrbeceoci;
        if(st_grlo == 0 )
            strsrbeceoci = "da";
        if(st_grlo== 1)
            strsrbeceoci = "ne";

        Log.e("VROCINA VRNE:", String.valueOf(npvrocina));
        Log.e("RADIO BUTTON VRNE:", String.valueOf(st_kaselj));
    }



}
