package epicodus.com.findyourcongressperson.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import epicodus.com.findyourcongressperson.R;
import epicodus.com.findyourcongressperson.adapters.CongressAdapter;
import epicodus.com.findyourcongressperson.models.CongressPerson;
import epicodus.com.findyourcongressperson.util.DownloadTask;


public class CongressActivity extends ListActivity {
    public static ArrayList<CongressPerson> mCongressPeople;
    private CongressAdapter mCongressAdapter;

    String mZipcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congress);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        mZipcode = extras.getString("zipcode");
        mCongressPeople = new ArrayList<CongressPerson>();

        findCongresspeople(mZipcode);
    }

    private void findCongresspeople(String zipcode) {
        try {
            DownloadTask task = new DownloadTask();
            String url = "http://congress.api.sunlightfoundation.com//legislators/locate?zip=" + zipcode + "&apikey=8b15bc61bd5e415199c4c1ef1f76ad25";
            Runnable updateAdapter = new Runnable() {
                @Override
                public void run() {
                    mCongressAdapter = new CongressAdapter(CongressActivity.this, mCongressPeople);
                    setListAdapter(mCongressAdapter);
                }
            };
            updateAdapter.run();
            task.execute(url);

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
