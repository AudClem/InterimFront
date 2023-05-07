package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;


public class CustomBaseAdapter extends BaseAdapter {

    Context contex;
    List<String> listN;
    List<Integer> imageN;
    LayoutInflater inflater;

    public CustomBaseAdapter(Context ctx, List<String> list, List<Integer> image){
        this.contex = ctx;
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        EditText edtxt = (EditText) convertView.findViewById(R.id.text);
        ImageView imgv = (ImageView) convertView.findViewById(R.id.icon);
        edtxt.setHint(listN.get(i));
        imgv.setImageResource(imageN.get(i));
        return convertView;
    }

}
