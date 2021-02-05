package com.example.pokemontest;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemontest.Common.Common;
import com.example.pokemontest.Interface.ItemClickListener;
import com.example.pokemontest.models.PokeapiRequest;
import com.example.pokemontest.models.Pokemon;
import com.example.pokemontest.pokeapi.PokeapiService;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListPokemonAdapter  extends RecyclerView.Adapter<ListPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;



    public ListPokemonAdapter(Context context){
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Pokemon p = dataset.get(position);
        holder.nbTextView.setText(p.getName());

        Glide.with(context)
                .load("https://cdn.traction.one/pokedex/pokemon/"+ p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        //Event
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onclick(View view, int position) {
                //Toast.makeText(context,"clik pokemon" + p.getName(), Toast.LENGTH_LONG);

                //Log.e("Common","" + p.getName());

                Intent intent=new Intent(view.getContext(),PokemonDetails.class);
                intent.putExtra("id",p.getNumber());
                intent.putExtra("name",p.getName());
                context.startActivity(intent);
            }



        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addListPokemon(ArrayList<Pokemon> listPokemon) {
        dataset.addAll(listPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView fotoImageView;
        private TextView nbTextView;

        private ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }


        public ViewHolder(View itemView){
            super(itemView);

            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nbTextView = (TextView) itemView.findViewById(R.id.nbTextView);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view)
        {
            itemClickListener.onclick(view,getAdapterPosition());
        }
    }

}
