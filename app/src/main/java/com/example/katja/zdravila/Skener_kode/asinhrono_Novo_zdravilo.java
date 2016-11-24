package com.example.katja.zdravila.Skener_kode;

import android.os.AsyncTask;

import com.example.Zdravilo;
import com.example.katja.zdravila.activity_Novo_zdravilo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Katja on 27. 05. 2016.
 */
public class asinhrono_Novo_zdravilo extends AsyncTask<String, String, Zdravilo> {

    private Zdravilo vrni;
    private String url;
    private activity_Novo_zdravilo con;

    public asinhrono_Novo_zdravilo(activity_Novo_zdravilo con1,String URL) {
        url=URL;
        con=con1;
    }

    @Override
    protected Zdravilo doInBackground(String... params) {

        try
        {
            String link = "";
            String poimenovanje = "";
            Integer kolicina = 0;
            String merska_enota = "";
            String oblika = "";
            String namen = "";
            Integer st_tablet = 0;
            String oblika_dolgo = "";
            String namen_dolgo = "";

            Document doc = Jsoup.connect(url).get();
            Element zravilo = doc.getElementsByClass("r").first();
            Element zravilo2 = zravilo.getElementsByAttribute("href").first();
            link = zravilo2.attr("href");
           // System.out.print("POVEZAVA NA ZDRAVILO: " + link + "\n");

            if (link.toLowerCase().contains("www.cbz.si"))
            {
                Document doc2 = Jsoup.connect(link).get();

                Elements ime_zdravil = doc2.getElementsByClass("textbarva01");
                int j = 0;
                for (Element a : ime_zdravil) {
                    String vsi_podatki = a.text();
                    // System.out.print("Vsi podatki: " + vsi_podatki + "\n");
                    if (j == 5) {
                        //Cezera 5 mg filmsko obložene tablete
                        poimenovanje = a.text();
                        // System.out.print("ZDRAVILO"+ poimenovanje +"\n");
                    }
                    if (j == 12) {
                        //filmsko obložena tableta
                        oblika = a.text();
                    }
                    if (j == 13) {
                        // 10 tableta
                        oblika_dolgo = a.text();
                        // System.out.print("OBLIKA"+ oblika_dolgo +"\n");
                    }
                    if (j == 24) {
                        //namen
                        //R ZDRAVILA ZA BOLEZNI DIHAL R06 ANTIHISTAMINIKI ZA SISTEMSKO ZDRAVLJENJE R06A Antihistaminiki za sistemsko zdravljenje R06AE Derivati piperazina R06AE09 levocetirizin
                        namen_dolgo = a.text();
                        //  System.out.print("NAMEN"+ namen_dolgo +"\n");
                    }
                    j++;
                }

                String[] oblika_dolgo_split = oblika_dolgo.split(" ");
                st_tablet = Integer.parseInt(oblika_dolgo_split[0]);
                namen = namen_dolgo;
                /*
                System.out.print("_____________________________________________" + "\n");
                System.out.print("PODATKI O ZDRAVILU" + "\n");
                System.out.print("Poimenovanje: " + poimenovanje + "\n");
                System.out.print("Število tablet: " + st_tablet + "\n");
                System.out.print("Oblika: " + oblika + "\n");
                System.out.print("Namen: " + namen + "\n");
                System.out.print("____________________________________________" + "\n");
                */

                vrni = new Zdravilo(poimenovanje, kolicina, merska_enota, oblika, namen, st_tablet);
            }
            else
            {
                vrni =  new Zdravilo("zdravilo ni bilo najdeno",0,"a","b","c",0);
            }
        }
        catch (Exception e) {
            vrni =  new Zdravilo("zdravilo ni bilo najdeno",0,"a","b","c",0);
            e.printStackTrace();
        }
        return  vrni;
    }

    @Override
    protected void onPostExecute(Zdravilo zdravilo_skenirano) {
        con.napolni(zdravilo_skenirano);
    }


    @Override
    protected void onProgressUpdate(String... text) {

    }

}
