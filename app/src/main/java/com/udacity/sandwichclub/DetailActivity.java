package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        TextView placeoforigin=(TextView)findViewById(R.id.origin_tv);
        TextView description=(TextView)findViewById(R.id.description_tv);
        TextView alsoKnownAs=(TextView)findViewById(R.id.also_known_tv);
        TextView ingredients=(TextView)findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        if ( sandwich.getDescription().isEmpty()||sandwich.getDescription()=="")
            description.setText("no data found");
        description.setText(sandwich.getDescription());
        if ( sandwich.getPlaceOfOrigin().isEmpty()||sandwich.getPlaceOfOrigin()=="")
            placeoforigin.setText("no data found");
        placeoforigin.setText(sandwich.getPlaceOfOrigin());
        if ( sandwich.getAlsoKnownAs().isEmpty())
            alsoKnownAs.setText("no data found");
        for(String also: sandwich.getAlsoKnownAs()){
            alsoKnownAs.append(" - "+also+" .\n");
        }int p=1;
        if ( sandwich.getIngredients().isEmpty())
            ingredients.setText("no data found");
        for(String ing: sandwich.getIngredients()){
            ingredients.append((p++)+" - "+ing+" .\n");
        }
        //populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .placeholder(R.drawable.ic_stat_name)
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

    }
}
