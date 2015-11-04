package epicodus.com.findyourcongressperson.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import epicodus.com.findyourcongressperson.R;
import epicodus.com.findyourcongressperson.models.CongressPerson;

public class CongressAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<CongressPerson> mCongressPeople;

    public CongressAdapter(Context context, ArrayList<CongressPerson> congressPeople) {
        mContext = context;
        mCongressPeople = congressPeople;
    }

    @Override
    public int getCount() {
        return mCongressPeople.size();
    }

    @Override
    public Object getItem(int position) {
        return mCongressPeople.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.congressperson, null);
            holder = new ViewHolder();
            holder.firstName = (TextView) convertView.findViewById(R.id.txtFirstName);
            holder.lastName = (TextView) convertView.findViewById(R.id.txtLastName);
            holder.party = (TextView) convertView.findViewById(R.id.txtParty);
            holder.website = (TextView) convertView.findViewById(R.id.txtWebsite);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        Log.v("Your arraylist: ", mCongressPeople.toString());

        CongressPerson congressPerson = mCongressPeople.get(position);

        holder.firstName.setText(congressPerson.getFirstName());
        holder.lastName.setText(congressPerson.getLastName());
        holder.party.setText(congressPerson.getParty());
        holder.website.setText(congressPerson.getWebsite());

        return convertView;
    }

    public static class ViewHolder {
        @Bind(R.id.txtFirstName) TextView firstName;
        @Bind(R.id.txtLastName) TextView lastName;
        @Bind(R.id.txtParty) TextView party;
        @Bind(R.id.txtWebsite) TextView website;
    }
}


