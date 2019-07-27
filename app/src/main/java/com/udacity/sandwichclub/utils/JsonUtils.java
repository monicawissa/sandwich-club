package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
//    {"name":{
//              "mainName":\"Bosna\",
//              "alsoKnownAs":["Bosner"]},
//     "placeOfOrigin":"Austria",
//     "description":"Bosna is a spicy Austrian fast food dish, said to have originated in either Salzburg or Linz.
//                      It is now popular all over western Austria and southern Bavaria.",
//     "image":"https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg",
//     "ingredients":["White bread",
//                      "Bratwurst",
//                      "Onions",
//                      "Tomato ketchup",
//                      "Mustard","Curry powder"]}
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject basejsonresponse = new JSONObject(json);
            JSONObject names=basejsonresponse.getJSONObject("name");

                String MainName =names.getString("mainName");
                JSONArray JalsoKnownAs = names.getJSONArray("alsoKnownAs");
                ArrayList<String> ArralsoKnown=new ArrayList<>();
                for(int i=0;i<JalsoKnownAs.length();i++ ){
                    ArralsoKnown.add(JalsoKnownAs.getString(i));
                }

            String placeOfOrigin=basejsonresponse.getString("placeOfOrigin");
            String description=basejsonresponse.getString("description");
            String image=basejsonresponse.getString("image");
            JSONArray Jingredients = basejsonresponse.getJSONArray("ingredients");
            ArrayList<String> Arringredients=new ArrayList<>();
            for (int i=0;i<Jingredients.length();i++){
                Arringredients.add(Jingredients.getString(i));
            }
            return new Sandwich(MainName,ArralsoKnown,placeOfOrigin,description,image,Arringredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
