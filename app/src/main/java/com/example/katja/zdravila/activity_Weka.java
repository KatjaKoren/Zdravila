package com.example.katja.zdravila;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;


public class activity_Weka extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weka);
    }
    public void Weka(View v) throws Exception{
        BufferedReader breader = null;
        File desc  = new File(this.getExternalFilesDir("zdravila_data"),"symptom_check_discret.arff");
        FileReader reader = new FileReader(desc.getAbsolutePath());
        breader = new BufferedReader(reader);

        Instances train = new Instances(breader); // naredimo instance za trenirat
        train.setClassIndex(train.numAttributes() - 1); // določimo razred za treniranje

        breader.close();

        IBk klasifikator = new IBk();
        klasifikator.buildClassifier(train);
        Evaluation eval = new Evaluation(train);
        eval.crossValidateModel(klasifikator, train, 10, new Random(1));

        TextView txt = (TextView) findViewById(R.id.wekatxt);
        txt.setMovementMethod(new ScrollingMovementMethod());
        txt.setText(eval.toSummaryString("\nKlasifikator: IBK 10 fold cross validation\n=====================================\n",true)+"\n"+eval.fMeasure(1)+"  "+eval.precision(1)+"  "+eval.recall(1));
    }

}
