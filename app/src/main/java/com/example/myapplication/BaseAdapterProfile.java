package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class BaseAdapterProfile extends BaseAdapter {

    Context contxt;
    List<String> listN;
    List<Integer> imageN;
    LayoutInflater inflater;

    public BaseAdapterProfile(Context ctx, List<String> list, List<Integer> image){
        this.contxt = ctx;
        this.listN = list;
        this.imageN = image;
        inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return listN.size();
    }

    @Override
    public Object getItem(int i) {
        return listN.get(i);
    }

    @Override
    public long getItemId(int i) {return 0; }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        convertView = inflater.inflate(R.layout.activity_list_view_profile, null);
        TextView txt = convertView.findViewById(R.id.text);
        ImageView imgv = (ImageView) convertView.findViewById(R.id.icon);
        txt.setText(listN.get(i));
        imgv.setImageResource(imageN.get(i));

        return convertView;
    }

}
