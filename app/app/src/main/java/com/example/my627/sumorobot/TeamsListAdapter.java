package com.example.my627.sumorobot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
 * Created by my627 on 3/15/2018.
 */

public class TeamsListAdapter extends ArrayAdapter<DataObject> {


  private static final String TAG = "TeamListAdapter";
    private Context mContext;
    int mResource;

    public TeamsListAdapter(@NonNull Context context, int resource, ArrayList<DataObject> al) {
        super(context, resource, al);
        this.mContext = context;
        mResource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String teamname = getItem(position).getTeamname();
        String address = getItem(position).getAddress();
        String city = getItem(position).getCity();
        String state = getItem(position).getState();
        String zip = getItem(position).getZip();


        DataObject dataObject = new DataObject(teamname, address, city, state, zip);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tv_teamname = (TextView) convertView.findViewById(R.id.teamname);
        TextView tv_address = (TextView) convertView.findViewById(R.id.address);
        TextView tv_city = (TextView) convertView.findViewById(R.id.city);
        TextView tv_state = (TextView) convertView.findViewById(R.id.state);
        TextView tv_zip = (TextView) convertView.findViewById(R.id.zip);

        tv_teamname.setText(teamname);
        tv_address.setText(address);
        tv_city.setText(city);
        tv_state.setText(state);
        tv_zip.setText(zip);
        return convertView;

    }


    @Override
    public void clear() {
        super.clear();
    }


}



