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

public class MembersListAdapter extends ArrayAdapter<ListTeamObject> {


  private static final String TAG = "TeamListAdapter";
    private Context mContext;
    int mResource;

    public MembersListAdapter(@NonNull Context context, int resource, ArrayList<ListTeamObject> al) {
        super(context, resource, al);
        this.mContext = context;
        mResource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String teamname = getItem(position).getTeamname();
        String teammember = getItem(position).getMembername();

        ListTeamObject listTeamObject = new ListTeamObject(teamname, teammember);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

     //   TextView tv_teamname = (TextView) convertView.findViewById(R.id.teamname);
        TextView tv_address = (TextView) convertView.findViewById(R.id.teammember);

      //  tv_teamname.setText(teamname);
        tv_address.setText(teammember);
        return convertView;

    }


    @Override
    public void clear() {
        super.clear();
    }


}



