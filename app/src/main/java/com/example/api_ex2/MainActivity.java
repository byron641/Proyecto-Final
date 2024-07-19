package com.example.api_ex2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonApi pokemonApi = retrofit.create(PokemonApi.class);

        Call<Pokemon> call = pokemonApi.getPokemon("ditto");

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Código: " + response.code());
                    return;
                }
                Pokemon pokemon = response.body();
                if (pokemon != null) {
                    String content = "";
                    content += "ID: " + pokemon.getId() + "\n";
                    content += "Name: " + pokemon.getName() + "\n";

                    textViewResult.setText(content);
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
