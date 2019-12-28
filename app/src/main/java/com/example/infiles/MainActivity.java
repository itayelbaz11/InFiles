package com.example.infiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    String st,st2;


    /**
     * @author Itay Elbaz
     * @since 6.7.2003
     * et is the Edit Text object
     * tv is the Text View object
     * st is a temporary String value
     * st2 is the Strings collection
     *
     */
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et =(EditText) findViewById(R.id.et);
        tv =(TextView) findViewById(R.id.tv);

        try {
            FileInputStream fis= openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            st2 = br.readLine();
            tv.setText(st2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method CREATES A MENU
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * This method moves to the credits activity when the button "credits is clicked
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }
        return true;
    }

    /**
     *this method add the changes to the collection and save
     */
    public void save(View view) throws IOException {
        st= et.getText().toString();
        if (!st.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(st2!=null){
                    st2= st2+st;}
                else{st2=st;}
                bw.write(st2);
                bw.close();
                tv.setText(st2);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }}

    }

    /**
     * This method resets all the collection and settings

     */
    public void reset(View view) {
        try {
            FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            st2="";
            bw.write(st2);
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        et.setText("");
        tv.setText("");
    }

    /**
     * This method save and exit the app
     */
    public void exit(View view) {
        st= et.getText().toString();
        if(!st.contentEquals("null")){
            try {
                FileOutputStream fos = openFileOutput("test.txt",MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);
                if(st2!=null){
                    st2= st2+st;}
                else{st2=st;}
                bw.write(st2);
                bw.close();
                tv.setText(st2);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finish();}
    }
}