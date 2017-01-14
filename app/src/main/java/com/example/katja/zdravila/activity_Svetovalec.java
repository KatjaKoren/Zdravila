package com.example.katja.zdravila;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import weka.core.Attribute;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

import static java.lang.Integer.valueOf;

public class activity_Svetovalec extends AppCompatActivity {


    RadioButton rbvnetogrlo;
    RadioButton rbkaselj;
    RadioButton rbnos;
    RadioButton rbbolecine;
    RadioButton rbglavobol;
    RadioButton rbmrzlica;
    RadioButton rbprsnikos;
    RadioButton rbutrujenost;
    RadioButton rbvrtoglavica;
    RadioButton rbzasoplost;
    RadioButton rbpotenje;
    RadioButton rbslabost;
    RadioButton rbizpuscaji;
    RadioButton rbsrbeceoci;
    RadioButton rbkihanje;

    RadioGroup rgkaselj;
    RadioGroup rgvnetogrlo;
    RadioGroup rgnos;
    RadioGroup rgbolecine;
    RadioGroup rgglavobol;
    RadioGroup rgmrzlica;
    RadioGroup rgprsnikos;
    RadioGroup rgutrujenost;
    RadioGroup rgvrtoglavica;
    RadioGroup rgzasoplost;
    RadioGroup rgpotenje;
    RadioGroup rgslabost;
    RadioGroup rgizpuscaji;
    RadioGroup rgsrbeceoci;
    RadioGroup rgkihanje;
    NumberPicker npvrocina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svetovalec);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btn = (Button)findViewById(R.id.btn_Potrdi);

        npvrocina = (NumberPicker) findViewById(R.id.npTemperatura);
        npvrocina.setMaxValue(43);
        npvrocina.setMinValue(30);
        npvrocina.setValue(36);


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    svetovalec_weka();
                }
                catch (Exception e){
                    System.out.println("Jebi ga "+e.toString());
                }
            }
        });

        rgkaselj = (RadioGroup)findViewById(R.id.rg_kaselj);
        rgvnetogrlo = (RadioGroup) findViewById(R.id.rg_grlo);
        rgnos = (RadioGroup) findViewById(R.id.rg_izcedek);
        rgbolecine = (RadioGroup) findViewById(R.id.rg_bolecine);
        rgglavobol = (RadioGroup) findViewById(R.id.rg_glavobol);
        rgmrzlica = (RadioGroup) findViewById(R.id.rg_mrzlica);
        rgprsnikos = (RadioGroup) findViewById(R.id.rg_prsni_kos);
        rgutrujenost = (RadioGroup) findViewById(R.id.rg_utrujenost);
        rgvrtoglavica = (RadioGroup) findViewById(R.id.rg_vrtoglavica);
        rgzasoplost = (RadioGroup) findViewById(R.id.rg_zasoplost);
        rgpotenje = (RadioGroup) findViewById(R.id.rg_potenje);
        rgslabost = (RadioGroup) findViewById(R.id.rg_slabost);
        rgizpuscaji = (RadioGroup) findViewById(R.id.rg_izpuscaji);
        rgsrbeceoci= (RadioGroup) findViewById(R.id.rg_srbece_oci);
        rgkihanje = (RadioGroup) findViewById(R.id.rg_kihanje);
    }

    public void svetovalec_weka() throws Exception{

        BufferedReader breader = null;
       //Uri wekafile = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.symptom_check);

        File desc  = new File(this.getExternalFilesDir("zdravila_data"),"symptom_check.arff");
        FileReader reader = new FileReader(desc);
        breader = new BufferedReader(reader);

        Instances train = new Instances(breader); // naredimo instance za trenirat
        train.setClassIndex(train.numAttributes() - 1); // določimo razred za treniranje

        breader.close();

        IBk klasifikator = new IBk();
        klasifikator.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(klasifikator, train, 10, new Random(1));

        int st_kaselj=rgkaselj.getCheckedRadioButtonId();
        rbkaselj=(RadioButton)findViewById(st_kaselj);
        String strkaselj = rbkaselj.getText().toString();

        int st_nos = rgnos.getCheckedRadioButtonId();
        rbnos=(RadioButton)findViewById(st_nos);
        String strizcedek_nos = rbnos.getText().toString();

        int st_grlo = rgvnetogrlo.getCheckedRadioButtonId();
        rbvnetogrlo=(RadioButton)findViewById(st_grlo);
        String strgrlo = rbvnetogrlo.getText().toString();

        int st_bolecine = rgbolecine.getCheckedRadioButtonId();
        rbbolecine=(RadioButton)findViewById(st_bolecine);
        String strbolecine = rbbolecine.getText().toString();

        int st_glavobol = rgglavobol.getCheckedRadioButtonId();
        rbglavobol=(RadioButton)findViewById(st_glavobol);
        String strglavobol = rbglavobol.getText().toString();

        int st_mrzlica = rgmrzlica.getCheckedRadioButtonId();
        rbmrzlica=(RadioButton)findViewById(st_mrzlica);
        String strmrzlica = rbmrzlica.getText().toString();

        int st_prsnikos = rgprsnikos.getCheckedRadioButtonId();
        rbprsnikos=(RadioButton)findViewById(st_prsnikos);
        String strprsnikos= rbprsnikos.getText().toString();

        int st_utrujenost = rgutrujenost.getCheckedRadioButtonId();
        rbutrujenost=(RadioButton)findViewById(st_utrujenost);
        String strutrujenost = rbutrujenost.getText().toString();

        int st_vrtoglavica = rgvrtoglavica.getCheckedRadioButtonId();
        rbvrtoglavica=(RadioButton)findViewById(st_vrtoglavica);
        String strvrtoglavica = rbvrtoglavica.getText().toString();

        int st_zasoplost = rgzasoplost.getCheckedRadioButtonId();
        rbzasoplost=(RadioButton)findViewById(st_zasoplost);
        String strzasoplost = rbzasoplost.getText().toString();

        int st_potenje = rgpotenje.getCheckedRadioButtonId();
        rbpotenje=(RadioButton)findViewById(st_potenje);
        String strpotenje = rbpotenje.getText().toString();

        int st_slabost = rgslabost.getCheckedRadioButtonId();
        rbslabost=(RadioButton)findViewById(st_slabost);
        String strslabost = rbslabost.getText().toString();

        int st_izpuscaji = rgizpuscaji.getCheckedRadioButtonId();
        rbizpuscaji=(RadioButton)findViewById(st_izpuscaji);
        String strizpuscaji = rbizpuscaji.getText().toString();

        int st_srbeceoci = rgsrbeceoci.getCheckedRadioButtonId();
        rbsrbeceoci=(RadioButton)findViewById(st_srbeceoci);
        String strsrbeceoci = rbsrbeceoci.getText().toString();

        int st_kihanje = rgkihanje.getCheckedRadioButtonId();
        rbkihanje=(RadioButton)findViewById(st_kihanje);
        String strkihanje = rbkihanje.getText().toString();

        Integer strvrocina = npvrocina.getValue();
        
        //Deklariranje atributov

        Attribute Vrocina = new Attribute("Vrocina");

        FastVector fvkaselj = new FastVector(3);
        fvkaselj.addElement("ne");
        fvkaselj.addElement("moker");
        fvkaselj.addElement("suh");
        Attribute Kaselj = new Attribute("Kaselj",fvkaselj);

        FastVector fvgrlo = new FastVector(2);
        fvgrlo.addElement("da");
        fvgrlo.addElement("ne");
        Attribute VnetoGrlo = new Attribute("Vneto grlo", fvgrlo);

        FastVector fvizcedeknos = new FastVector(2);
        fvizcedeknos.addElement("da");
        fvizcedeknos.addElement("ne");
        Attribute IzcedekNos = new Attribute("Izcedek iz nosu ali zamasen nos", fvizcedeknos);

        FastVector fvbolecine = new FastVector(3);
        fvbolecine.addElement("brez");
        fvbolecine.addElement("blaga");
        fvbolecine.addElement("mocnejsa");
        Attribute Bolecine = new Attribute("Bolecine", fvbolecine);

        FastVector fvglavobol = new FastVector(3);
        fvglavobol.addElement("brez");
        fvglavobol.addElement("rahel");
        fvglavobol.addElement("mocnejsi");
        Attribute Glavobol = new Attribute("Glavobol", fvglavobol);

        FastVector fvmrzlica = new FastVector(2);
        fvmrzlica.addElement("da");
        fvmrzlica.addElement("ne");
        Attribute Mrzlica = new Attribute("Mrzlica", fvmrzlica);

        FastVector fvkihanje = new FastVector(2);
        fvkihanje.addElement("da");
        fvkihanje.addElement("ne");
        Attribute Kihanje = new Attribute("Kihanje", fvkihanje);

        FastVector fvprsnikos = new FastVector(2);
        fvprsnikos.addElement("da");
        fvprsnikos.addElement("ne");
        Attribute PrsniKos = new Attribute("Bolecina v prsnem kosu",fvprsnikos);

        FastVector fvutrujenost = new FastVector(2);
        fvutrujenost.addElement("da");
        fvutrujenost.addElement("ne");
        Attribute Utrujenost = new Attribute("Utrujenost", fvutrujenost);

        FastVector fvvrtoglavica = new FastVector(2);
        fvvrtoglavica.addElement("da");
        fvvrtoglavica.addElement("ne");
        Attribute Vrtoglavica = new Attribute("Vrtoglavica", fvvrtoglavica);


        FastVector fvzasoplost = new FastVector(2);
        fvzasoplost.addElement("da");
        fvzasoplost.addElement("ne");
        Attribute Zasoplost = new Attribute("Zasoplost", fvzasoplost);


        FastVector fvpotenje = new FastVector(2);
        fvpotenje.addElement("da");
        fvpotenje.addElement("ne");
        Attribute Potenje = new Attribute("Potenje", fvpotenje);


        FastVector fvslabost = new FastVector(2);
        fvslabost.addElement("da");
        fvslabost.addElement("ne");
        Attribute Slabost = new Attribute("Slabost", fvslabost);


        FastVector fvizpuscaji = new FastVector(2);
        fvizpuscaji.addElement("da");
        fvizpuscaji.addElement("ne");
        Attribute Izpuscaji = new Attribute("Izpuscaji", fvizpuscaji);


        FastVector fvsrbeceoci = new FastVector(2);
        fvsrbeceoci.addElement("da");
        fvsrbeceoci.addElement("ne");
        Attribute SrbeceOci = new Attribute("Srbece oci", fvsrbeceoci);


        // KLAS KI GA ŽELIM NAPOVEDATI
        weka.core.FastVector fvBolezen = new FastVector(3);
        fvBolezen.addElement("prehlad");
        fvBolezen.addElement("gripa");
        fvBolezen.addElement("angina");
        fvBolezen.addElement("pljucnica");
        fvBolezen.addElement("alergija");
        Attribute Bolezen = new Attribute("bolezen",fvBolezen);


        FastVector fvWekaAtributi = new FastVector(17);
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
        fvWekaAtributi.addElement(SrbeceOci);
        fvWekaAtributi.addElement(Bolezen);

        // Določanje instance, ki jo želim napovedati
        Instances dataset = new Instances("bolezen", fvWekaAtributi, 0);

        Instance instance = new DenseInstance(16);
        instance.setValue(Vrocina,strvrocina);
        instance.setValue(Kaselj, strkaselj);
        instance.setValue(VnetoGrlo, strgrlo);
        instance.setValue(IzcedekNos, strizcedek_nos);
        instance.setValue(Bolecine,strbolecine);
        instance.setValue(Glavobol,strglavobol);
        instance.setValue(Mrzlica, strmrzlica);
        instance.setValue(Kihanje, strkihanje);
        instance.setValue(PrsniKos,strprsnikos);
        instance.setValue(Utrujenost, strutrujenost);
        instance.setValue(Vrtoglavica, strvrtoglavica);
        instance.setValue(Zasoplost,strzasoplost);
        instance.setValue(Potenje, strpotenje);
        instance.setValue(Slabost, strslabost);
        instance.setValue(Izpuscaji, strizpuscaji);
        instance.setValue(SrbeceOci, strsrbeceoci);

        dataset.add(instance);
        dataset.setClassIndex(dataset.numAttributes()-1);

        double rezultat = klasifikator.classifyInstance(dataset.instance(0));
        String[] strRezultat = new String[]{"prehlad","gripa","angina","pljučnica","alergija"};
        String strrezultat2 = new String(strRezultat[(int)rezultat]);
        String strStavek = new String();
        strStavek ="Verjetno imate " + strrezultat2.toString() + ".";

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        //toast.makeText(activity_Svetovalec.this,strRezultat[(int)rezultat].toString(),Toast.LENGTH_LONG).show();
        toast.makeText(activity_Svetovalec.this,strStavek,Toast.LENGTH_LONG).show();

    }
}
