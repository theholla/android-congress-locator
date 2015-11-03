package epicodus.com.findyourcongressperson.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import epicodus.com.findyourcongressperson.R;
import epicodus.com.findyourcongressperson.util.DownloadTask;


public class CongressActivity extends AppCompatActivity {
    String mZipcode;
    @Bind(R.id.txtFirstName) TextView mFirstName;
    @Bind(R.id.txtLastName) TextView mLastName;
    @Bind(R.id.txtParty) TextView mParty;
    @Bind(R.id.txtWebsite) TextView mWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mZipcode = extras.getString("zipcode");

        findCongresspeople(mZipcode);

    }

    private void findCongresspeople(String zipcode) {
        try {
            DownloadTask task = new DownloadTask();
            task.execute("http://congress.api.sunlightfoundation.com//legislators/locate?zip=" + zipcode + "&apikey=8b15bc61bd5e415199c4c1ef1f76ad25");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Could not find congresspeople :(", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_congress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
