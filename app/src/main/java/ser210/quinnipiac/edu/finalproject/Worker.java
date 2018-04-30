package ser210.quinnipiac.edu.finalproject;

import android.app.ListFragment;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

/**
 * Created by Kyle on 3/9/2018.
 */

public class Worker extends AsyncTask<String, String, String> {
    private SearchFragment searchFragment;
    private String json;

    public Worker(SearchFragment search) throws JSONException {
        this.searchFragment = search;
        json = null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            try {
                json = stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(json);
            searchFragment.result(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}