package com.example.pokemontest.models;
import java.util.ArrayList;

public class PokeapiRequest   {

    private ArrayList<Pokemon> results;


    public ArrayList<Pokemon> getResults(){
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
