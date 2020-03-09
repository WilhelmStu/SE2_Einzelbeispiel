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
}
