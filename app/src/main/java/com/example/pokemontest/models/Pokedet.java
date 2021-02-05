package com.example.pokemontest.models;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pokedet {
    @SerializedName("number")
    private String number;
    @SerializedName("name")
    private String name;
    @SerializedName("types")
    private List<String> types = null;
    @SerializedName("height")
    private String height;
    @SerializedName("weight")
    private String weight;
    @SerializedName("description")
    private String description;
    @SerializedName("family")
    private Family family;

    @Override
    public String toString() {
        return "Pokemon{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", types=" + types +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", description='" + description + '\'' +
                ", family=" + family +
                ", sprite='" + sprite + '\'' +
                '}';
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    @SerializedName("sprite")
    private String sprite;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }



    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

