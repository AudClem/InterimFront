package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;


public class CustomBaseAdapter extends BaseAdapter {

    Context contex;
    List<String> listN;
    List<Integer> imageN;
    List<String> souslistN;
    List<Integer> sousimageN;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<String> list, List<Integer> image, List<String> souslist, List<Integer> sousimage){
        this.contex = ctx;
        this.listN = list;
        this.imageN = image;
        this.souslistN = souslist;
        this.sousimageN = sousimage;
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

        if (getItem(0)== "First Name" && i==4){
            convertView = inflater.inflate(R.layout.activity_custom_list_view_c, null);
            EditText edtxt = (EditText) convertView.findViewById(R.id.text);
            ImageView imgv = (ImageView) convertView.findViewById(R.id.icon);
            EditText edtxt2 = (EditText) convertView.findViewById(R.id.text2);
            ImageView imgv2 = (ImageView) convertView.findViewById(R.id.icon2);
            edtxt.setHint(souslistN.get(0));
            imgv.setImageResource(sousimageN.get(0));
            edtxt2.setHint(souslistN.get(1));
            imgv2.setImageResource(sousimageN.get(1));
        }
        else{
            convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
            EditText edtxt = (EditText) convertView.findViewById(R.id.text);
            ImageView imgv = (ImageView) convertView.findViewById(R.id.icon);
            edtxt.setHint(listN.get(i));
            imgv.setImageResource(imageN.get(i));
        }

        return convertView;
    }

}
