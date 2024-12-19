package com.suii.userapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class vieww extends AppCompatActivity {
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_vieww);
        t1 = findViewById(R.id.tex);
        callApi();
    }

    private void callApi() {
        String Apiurl = "https://jsonplaceholder.typicode.com/users";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                Apiurl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        StringBuilder result = new StringBuilder();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject user = response.getJSONObject(i);

                                String name = user.getString("name");
                                String username = user.getString("username");
                                String email = user.getString("email");

                                JSONObject address = user.getJSONObject("address");
                                String street = address.getString("street");
                                String suite = address.getString("suite");
                                String city = address.getString("city");
                                String zipcode = address.getString("zipcode");

                                JSONObject geo = address.getJSONObject("geo");
                                String lat = geo.getString("lat");
                                String lng = geo.getString("lng");

                                String phone = user.getString("phone");
                                String website = user.getString("website");

                                JSONObject company = user.getJSONObject("company");
                                String companyName = company.getString("name");
                                String catchPhrase = company.getString("catchPhrase");
                                String bs = company.getString("bs");

                                // Append user details to the result
                                result.append("Name: ").append(name).append("\n");
                                result.append("Username: ").append(username).append("\n");
                                result.append("Email: ").append(email).append("\n");
                                result.append("Address: ").append(street).append(", ").append(suite).append(", ")
                                        .append(city).append(" - ").append(zipcode).append("\n");
                                result.append("Geo: Lat ").append(lat).append(", Lng ").append(lng).append("\n");
                                result.append("Phone: ").append(phone).append("\n");
                                result.append("Website: ").append(website).append("\n");
                                result.append("Company: ").append(companyName).append("\n");
                                result.append("Catch Phrase: ").append(catchPhrase).append("\n");
                                result.append("Business: ").append(bs).append("\n");
                                result.append("............................................\n");
                            }

                            t1.setText(result.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error parsing JSON", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred", Toast.LENGTH_LONG).show();
                    }
                }
        );

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }
}
