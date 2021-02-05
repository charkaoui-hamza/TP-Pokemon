package com.example.pokemontest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokemontest.models.PokeapiRequest;
import com.example.pokemontest.models.Pokemon;
import com.example.pokemontest.pokeapi.PokeapiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonListByGeneration#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonListByGeneration extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static PokemonListByGeneration instance;

    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;

    private RecyclerView recyclerView;
    private ListPokemonAdapter listPokemonAdapter;

    private int offset=0;
    private int limit=0;
    private boolean adapterload;


    public static PokemonListByGeneration getInstance() {
        if (instance == null) {
            instance = new PokemonListByGeneration();
        }
        return instance;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PokemonListByGeneration() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PokemonDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonListByGeneration newInstance(String param1, String param2) {
        PokemonListByGeneration fragment = new PokemonListByGeneration();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        listPokemonAdapter = new ListPokemonAdapter(view.getContext());
        recyclerView.setAdapter(listPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        int mimiposition = getArguments().getInt("Gen");
        //Log.e("miniposition", "miniposition : " + mimiposition);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adapterload = true;
        switch (mimiposition){
            case 1 :
                offset = 0;
                limit = 151;
                getData(0,151);
                break;

            case 2 :
                offset = 151;
                limit = 100;
                getData(151,100);
                break;

            case 3 :
                offset = 251;
                limit = 135;
                getData(251,135);
                break;

            case 4 :
                offset = 386;
                limit = 107;
                getData(386,107);
                break;

            case 5 :
                offset = 493;
                limit = 156;
                getData(493,156);
                break;

            case 6 :
                offset = 649;
                limit = 72;
                getData(649,72);
                break;

            case 7 :
                offset = 721;
                limit = 88;
                getData(721,88);
                break;

            case 8 :
                offset = 809;
                limit = 89;
                getData(809,89);
                break;
        }

        getData(offset,limit);

        return view;
    }

    private void getData(int offset, int limit) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokeapiRequest> pokemonRequestCall = service.ListPokemon(limit, offset);

        pokemonRequestCall.enqueue(new Callback<PokeapiRequest>() {
            @Override
            public void onResponse(Call<PokeapiRequest> call, Response<PokeapiRequest> response) {
                adapterload = true;
                if (response.isSuccessful()) {
                    PokeapiRequest pokemonRequest = response.body();
                    ArrayList<Pokemon> listPokemon = pokemonRequest.getResults();

                    listPokemonAdapter.addListPokemon(listPokemon);
                } else {
                    Log.e(TAG, " onResponse " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<PokeapiRequest> call, Throwable t) {
                adapterload = true;
                Log.e(TAG, "on Failure: " + t.getMessage());
            }
        });
    }
}