package com.grimmslaw.pokemon.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {

    public static final Gson GSON_BUILDER = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

}
