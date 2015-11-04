package epicodus.com.findyourcongressperson.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import epicodus.com.findyourcongressperson.models.CongressPerson;
import epicodus.com.findyourcongressperson.ui.CongressActivity;

public class DownloadTask extends AsyncTask<String, Void, String> {
    public final String TAG = "NO_CONGRESSPERSON";
    private Context mContext;

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (Exception e) {
            Log.i("ERROR", "Please try again");
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            String congressInfo = jsonObject.getString("results");
            JSONArray congressArray = new JSONArray(congressInfo);

            for (int i = 0; i < congressArray.length(); i++)  {
                JSONObject congressPerson = congressArray.getJSONObject(i);

                String firstName = congressPerson.getString("first_name");
                String lastName = congressPerson.getString("last_name");
                String party = congressPerson.getString("party");
                String website = congressPerson.getString("website");

                CongressPerson myRepresentative = new CongressPerson(firstName, lastName, party, website);

                CongressActivity.mCongressPeople.add(myRepresentative);
                Log.i("My representative is:", myRepresentative.toString());
            }

            Log.i("Congress info", congressInfo);
        } catch (JSONException e){
            Log.i("ERROR", "Please try again");
        }
    }
}