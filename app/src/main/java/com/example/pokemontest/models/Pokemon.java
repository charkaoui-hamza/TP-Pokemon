package com.example.pokemontest.models;

public class Pokemon {

    private int number=0;
    private String name;
    private String url;

    public int getNumber() {
        if(this.number==0){
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length-1]);}else{
            return this.number;
        }
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
