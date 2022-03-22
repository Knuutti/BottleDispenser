package com.example.bottledispenser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    BottleDispenser automaatti = BottleDispenser.getInstance();
    InputStreamReader defaultInputReader = new InputStreamReader(System.in);
    BufferedReader bufferedReader = new BufferedReader(defaultInputReader);
    Scanner bufferedScanner = new Scanner(bufferedReader);
    private Spinner spinner;

    TextView moneyCount;
    TextView console;
    SeekBar moneyBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moneyCount = findViewById(R.id.moneyText);
        moneyBar = findViewById(R.id.moneyBar);
        console = findViewById(R.id.console);
        context = MainActivity.this;

        initializeUI();
    }

    private void initializeUI() {

        spinner = (Spinner) findViewById(R.id.bottleSpinner);

        ArrayList<Bottle> bottleList = automaatti.getBottleArray();

        ArrayAdapter<Bottle> adapter = new ArrayAdapter<Bottle>(getApplicationContext(),  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, bottleList);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

    }

    public void addMoney(View v) {
        automaatti.addMoney(moneyBar.getProgress());
        if (Double.parseDouble(automaatti.getMoney()) > 10) {
            automaatti.setMoney(10);
            console.setText("MAKSIMI RAHAMÄÄRÄ");
        }
        moneyCount.setText(automaatti.getMoney() + " €");
        moneyBar.setProgress(0);
    }

    public void buyBottle(View v) {
        if (automaatti.getBottleArray().size() == 0) {
            console.setText("AUTOMAATTI TYHJÄ");
        }
        else {
            int position = spinner.getSelectedItemPosition();
            automaatti.buyBottle(position);
            moneyCount.setText(automaatti.getMoney() + " €");
            initializeUI();
            console.setText("");
        }
    }

    public void printToFile(View v) {
        String tiedosto = "kuitti.txt";
        String teksti = automaatti.getBillString();
        automaatti.setBillString();

        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(tiedosto, Context.MODE_PRIVATE));
            osw.write(teksti);
            osw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void returnMoney(View v) {
        automaatti.returnMoney();
        moneyCount.setText("0.00 €");
        console.setText("RAHAT PALAUTETTU");
    }
}