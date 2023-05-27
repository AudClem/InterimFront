package com.example.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CustomAdaptaterRecycler extends RecyclerView.Adapter<ViewHolderRecycler>{
    // ajouté
    private final RecyclerInterface recyclerInterface;
    Context contxt;
    List<ItemOffer> items;

    public CustomAdaptaterRecycler(Context contxt, List<ItemOffer> items, RecyclerInterface recyclerInterface) {
        this.recyclerInterface = recyclerInterface;
        this.contxt = contxt;
        this.items = items;
    }

    // ajouté recyclerInterface
    @NonNull
    @Override
    public ViewHolderRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contxt);
        View view = inflater.inflate(R.layout.activity_custom_offer, parent, false);
        return new ViewHolderRecycler(view, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecycler holder, int position) {
        holder.h_sous_titre.setText(items.get(position).getsous_titre());
        holder.h_titre.setText(items.get(position).gettitre());
        holder.h_salaire.setText(items.get(position).getsalaire());
        holder.h_lieu.setText(items.get(position).getlieu());
        holder.h_affiche.setImageResource(items.get(position).getaffiche());
        holder.h_etoile.setImageResource(items.get(position).getetoile());

        Typeface black = Typeface.create("@font/roboto_black", Typeface.BOLD);
        holder.h_titre.setTypeface(black);
        Typeface roboto = Typeface.create("@font/roboto", Typeface.NORMAL);
        holder.h_sous_titre.setTypeface(roboto);
        holder.h_salaire.setTypeface(roboto);
        holder.h_lieu.setTypeface(roboto);

    }
    // ajouté
    @Override
    public int getItemCount() {
        return items.size();
    }
}

