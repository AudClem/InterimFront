package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderRecycler extends RecyclerView.ViewHolder {
    ImageView h_affiche, h_etoile;
    TextView h_sous_titre, h_titre, h_salaire, h_lieu;

    public ViewHolderRecycler(@NonNull View itemView) {
        super(itemView);
        h_affiche = itemView.findViewById(R.id.custom_print);
        h_sous_titre = itemView.findViewById(R.id.custom_sous_titre);
        h_titre = itemView.findViewById(R.id.custom_titre);
        h_etoile = itemView.findViewById(R.id.custom_star);
        h_salaire = itemView.findViewById(R.id.custom_salaire);
        h_lieu = itemView.findViewById(R.id.custom_lieu);
    }
}
