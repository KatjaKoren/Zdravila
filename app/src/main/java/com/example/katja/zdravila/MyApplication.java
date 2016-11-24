package com.example.katja.zdravila;

import android.app.Application;
import android.os.Environment;
import com.example.DataAll;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Katja on 29. 05. 2016.
 */
public class MyApplication extends Application {

    private DataAll podatki = new DataAll();

    public DataAll getPodatki() {
        return podatki;
    }

    public void setAll(DataAll podatki1) {
        this.podatki = podatki1;
    }

    public static final String DATA_MAP = "zdravila_data";
    public static final String FILE_NAME = "zdravila.json";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean save() {

        return save(getPodatki(), FILE_NAME);
    }

    public boolean load() {
        DataAll tmp = load(FILE_NAME);
        if (tmp != null)
            setAll(tmp);
        else
            return false;
        return true;
    }

    public boolean save(DataAll a, String filename) {
        if (isExternalStorageWritable()) {
            File file = new File(this.getExternalFilesDir(DATA_MAP), "" + filename);
            try {
                long start = System.currentTimeMillis();
                System.out.println("Save " + file.getAbsolutePath() + " " + file.getName());
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                PrintWriter pw = new PrintWriter(file);
                String sss = gson.toJson(a);
                System.out.println("Save time gson:" + (double) (System.currentTimeMillis() - start) / 1000);
                pw.println(sss);
                pw.close();
                System.out.println("Save time s:" + (double) (System.currentTimeMillis() - start) / 1000);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Error save! (FileNotFoundException)");
            } catch (Exception e) {
                System.out.println("Error save! (IOException)" + e);
            }
        } else {
            System.out.println(this.getClass().getCanonicalName() + " NOT Writable");
        }
        return false;
    }

    public DataAll load(String name) {
        if (isExternalStorageReadable()) {
            try {
                File file = new File(this.getExternalFilesDir(DATA_MAP), "" + name);
                System.out.println("Load " + file.getAbsolutePath() + " " + file.getName());
                FileInputStream fstream = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fstream);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuffer sb = new StringBuffer();
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    sb.append(strLine).append('\n');
                }
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                DataAll a = gson.fromJson(sb.toString(), DataAll.class);
                if (a == null) {
                    System.out.println("Error: fromJson Format error");
                } else {
                    System.out.println(a.toString());
                }
                return a;
            } catch (IOException e) {
                System.out.println("Error load " + e.toString());
            }
        }
        System.out.println("ExternalStorageAvailable is not avaliable");
        return null;
    }
}
