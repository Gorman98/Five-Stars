package ser210.quinnipiac.edu.finalproject;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.provider.DocumentsContract;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

/**
 * Created by Kyle on 3/9/2018.
 */

public class Worker extends AsyncTask<String, String, String> {
    private SearchFragment searchFragment;
    private String json;
    Boolean jsonCheck;
    private Document doc;

    public Worker(SearchFragment search, Boolean jsonCheck) throws JSONException {
        this.searchFragment = search;
        this.jsonCheck = jsonCheck;
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
        if (jsonCheck) {
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();

                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                if(jsonCheck)
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
        } else {
            URL url = null;
            try {
                url = new URL(strings[0]);
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            } catch (Exception e) {
                System.out.println("XML Pasing Excpetion = " + e);
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(jsonCheck) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                searchFragment.jsonResult(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            searchFragment.xmlResult(doc);
        }
    }
}