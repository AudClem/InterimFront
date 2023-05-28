package com.example.myapplication;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class BaseAdapterJobSearch extends BaseAdapter {

    Context contxt;
    List<String> listSsTitre;
    List<String> listTitre;
    List<String> listInfo;
    List<Integer> imagePrint;
    List<Integer> imageStar;
    LayoutInflater inflater;

    public BaseAdapterJobSearch(Context ctx, List<String> listsstitre, List<String> listtitre, List<String> listinfo, List<Integer> imageprint, List<Integer> imagestar){
        this.contxt = ctx;
        this.listSsTitre = listsstitre;
        this.listTitre = listtitre;
        this.listInfo = listinfo;
        this.imagePrint = imageprint;
        this.imageStar = imagestar;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return listTitre.size();
    }

    @Override
    public Object getItem(int i) {
        return listTitre.get(i);
    }


    @Override
    public long getItemId(int i) {return 0; }


    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        convertView = inflater.inflate(R.layout.activity_custom_list_jobsearch, null);
        TextView txt1 = convertView.findViewById(R.id.r_sous_titre);
        TextView txt2 = convertView.findViewById(R.id.r_intitule);
        TextView txt3 = convertView.findViewById(R.id.r_lieu);
        ImageView img1 = (ImageView) convertView.findViewById(R.id.r_print_offer);
        ImageView img2 = (ImageView) convertView.findViewById(R.id.r_star_offer);
        txt1.setText(listSsTitre.get(i));
        txt2.setText(listTitre.get(i));
        txt3.setText(listInfo.get(i));
        img1.setImageResource(imagePrint.get(i));
        img2.setImageResource(imageStar.get(i));

        return convertView;
    }

}
