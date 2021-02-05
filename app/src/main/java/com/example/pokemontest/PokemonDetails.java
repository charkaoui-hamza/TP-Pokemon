package com.example.pokemontest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemontest.models.Family;
import com.example.pokemontest.models.Pokedet;
import com.example.pokemontest.models.Pokemon;
import com.example.pokemontest.pokeapi.PokeapiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.internal.util.Pow2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonDetails extends AppCompatActivity {

    int id;
    String name;
    ImageView imageView;
    TextView type,taille,poids,description;
    RecyclerView pokeEvolution;
    ListPokemonAdapter listPokemonAdapter;
    Family family;
    Pokedet pokedet;
    List<Pokedet> pokemons;
    final ArrayList<Pokemon> pokees=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        final PokemonDetails view=this;
        id= getIntent().getIntExtra("id",0);
        name=getIntent().getStringExtra("name");
        imageView=(ImageView) findViewById(R.id.imageView);
        type=(TextView) findViewById(R.id.type);
        taille=(TextView) findViewById(R.id.taille);
        poids=(TextView) findViewById(R.id.poids);
        description=(TextView) findViewById(R.id.description);
        pokeEvolution = (RecyclerView) findViewById(R.id.pokeEvolution);
        listPokemonAdapter = new ListPokemonAdapter(this);
        listPokemonAdapter.addListPokemon(pokees);
        pokeEvolution.setLayoutManager(new GridLayoutManager(this, 3));
        pokeEvolution.setAdapter(listPokemonAdapter);
        setTitle(name);

        Glide.with(this)
                .load("https://cdn.traction.one/pokedex/pokemon/"+ id+ ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.glitch.me/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeapiService pokeapiService=retrofit.create(PokeapiService.class);
        Call<List<Pokedet>> call=pokeapiService.getPokemonById(id);

        call.enqueue(new Callback<List<Pokedet>>() {
            @Override
            public void onResponse(Call<List<Pokedet>> call, Response<List<Pokedet>> response) {
                if(response.isSuccessful()){
                    pokemons=response.body();
                    Log.e("pokemon",pokemons.get(0).toString());
                    family=pokemons.get(0).getFamily();
                    taille.setText("Taille : "+pokemons.get(0).getHeight());
                    poids.setText("Poids : "+pokemons.get(0).getWeight());
                    String typeresp="Type : ";
                    for (String type : pokemons.get(0).getTypes()){
                        typeresp=typeresp+type+"\n";
                    }
                    type.setText(typeresp);
                    description.setText(pokemons.get(0).getDescription());
                    getEvolution(family,view);
                }else{
                    Log.e("Response",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Pokedet>> call, Throwable t) {
                Log.e("Failure",t.getMessage());
            }
        });




    }

    private void getEvolution(Family family, final PokemonDetails view) {
        pokees.clear();
        for (String nom : family.getEvolutionLine()){
            if(!nom.toLowerCase().equals(name.toLowerCase())){
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.glitch.me/v1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokeapiService pokeapiService=retrofit.create(PokeapiService.class);
                Call<List<Pokedet>> callPoke=pokeapiService.getPokemonByName(nom);
                callPoke.enqueue(new Callback<List<Pokedet>>() {
                    @Override
                    public void onResponse(Call<List<Pokedet>> call, Response<List<Pokedet>> response) {
                        if(response.isSuccessful()){
                            Log.e("Response",response.message());
                            List<Pokedet> poke=response.body();
                            Pokemon pokem=new Pokemon();
                            pokem.setName(poke.get(0).getName());
                            pokem.setNumber(Integer.parseInt(poke.get(0).getNumber()));
                            pokees.add(pokem);
                            listPokemonAdapter = new ListPokemonAdapter(view);
                            listPokemonAdapter.addListPokemon(pokees);
                            pokeEvolution.setAdapter(listPokemonAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pokedet>> call, Throwable t) {

                    }
                });
                Log.e("size", String.valueOf(pokees.size()));
            }
        }
    }
}