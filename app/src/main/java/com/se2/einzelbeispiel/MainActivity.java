package com.se2.einzelbeispiel;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendToServer(View view) {

        String matNr = ((EditText) findViewById(R.id.mat_eingabe)).getText().toString();

        new SendTask().execute(matNr);
    }

    public void calcBin(View view){

        String matNr = ((EditText) findViewById(R.id.mat_eingabe)).getText().toString();

        new CalcTask().execute(matNr);
    }


    public class SendTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... message) {

            return TcpClient.sendMessage(message[0]);

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = findViewById(R.id.server_antwort);
            textView.setText(s);

        }
    }

    public class CalcTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... matNr) {

            int quersumme = 0;
            for (int i = 0; i < matNr[0].length(); i++) {
                quersumme += Character.getNumericValue(matNr[0].charAt(i));
            }

            StringBuilder bin = new StringBuilder();
            if (quersumme <= 0) bin.append(0);

            while(quersumme != 0){
                bin.append(quersumme % 2);
                quersumme /= 2;
            }

            return "Quersumme als Binärzahl: " + bin.reverse().toString();
        }

        @Override
        protected void onPostExecute(String bin) {
            super.onPostExecute(bin);
            TextView textView = findViewById(R.id.server_antwort);
            textView.setText(bin);

        }
    }
}
