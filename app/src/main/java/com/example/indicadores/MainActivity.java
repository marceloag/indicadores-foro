package com.example.indicadores;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);
        TextView ufValue = findViewById(R.id.valorUf);
        // Crea la petición
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "https://mindicador.cl/api", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("uf")) {
                                JSONObject ufObject = response.getJSONObject("uf");
                                // Obtiene el valor de la UF
                                Double valorUF = ufObject.getDouble("valor");
                                // Actualiza el TextView con el valor obtenido de la API
                                ufValue.setText(String.valueOf(valorUF));
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.getMessage());
                    }
                });
        queue.add(request);
    }
}