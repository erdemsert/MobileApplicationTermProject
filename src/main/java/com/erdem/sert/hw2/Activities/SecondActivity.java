package com.erdem.sert.hw2.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import com.erdem.sert.hw2.Food.FoodActivity;
import com.erdem.sert.hw2.Food.JSONRecyclerViewAdapter;
import com.erdem.sert.hw2.R;
import com.erdem.sert.hw2.SystemClass.MealSys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class SecondActivity extends AppCompatActivity {
    Spinner spinnerMeal;
    RecyclerView JSONRecyclerViewAdapter;
    boolean selectedItem = false;
    //For Recyclerview
    private ArrayList<mealJson> json = new ArrayList<>();
    // for Json operations
    private JSONArray meals;
    private JSONObject mealJSONObject;
    private RequestQueue mRequestQueue;
    public static final String TAG_MEALS = "meals";
    public static final String TAG_NAME = "name";
    public static final String TAG_CALORI = "calori";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Hiding title bar using code
        getSupportActionBar().hide();
        // Hiding the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        MealSys.prepareMealData();

        spinnerMeal = findViewById(R.id.spinnerMeal);
        JSONRecyclerViewAdapter = findViewById(R.id.recyclerviewJSON);
        mRequestQueue = Volley.newRequestQueue(this);
        new HttpsTrustManager().allowAllSSL();
        // get json from endpoint and fill the array list for adapter
        String MEALS_URL = "https://memolineapp.com/utku/mealsJson.json";
        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(
                Request.Method.DEPRECATED_GET_OR_POST, MEALS_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mealJSONObject = response;

                        Log.d("RESPONSE", response.toString());
                        // Call to AsyncTask
                        new GetMeals().execute();

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Result",
                        "ERROR JSONObject request" + error.toString());

            }
        }) {
        };


        mRequestQueue.getCache().clear();
        // starts the request
        mRequestQueue.add(mJsonObjectRequest);

        CustomSpinnerAdapter customAdapter = new CustomSpinnerAdapter(this, MealSys.getMealList());
        spinnerMeal.setAdapter(customAdapter);

        spinnerMeal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (selectedItem) {
                    Meal meal = MealSys.getMealList().get(i);
                    Intent intent = new Intent(SecondActivity.this, FoodActivity.class);
                    Bundle b = new Bundle();
                    b.putParcelable("selectedMeal", meal);
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    selectedItem = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private class GetMeals extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Getting JSON Array_
                meals = mealJSONObject.getJSONArray(TAG_MEALS);
                // looping through all books
                for (int i = 0; i < meals.length(); i++) {

                    JSONObject jsonObj = meals.getJSONObject(i);
                    String name = jsonObj.getString(TAG_NAME);
                    String calori = jsonObj.getString(TAG_CALORI);

                    mealJson b = new mealJson(name, calori);
                    Log.d("MealJson Object", b.getName() + b.getCalori());
                    json.add(b);
                }
            } catch (JSONException ee) {
                ee.printStackTrace();
            }

            return null;
        }


        // What do you want to do after doInBackground() finishes
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (json != null) {

                //Create RecyclerView
                JSONRecyclerViewAdapter recyclerViewAdapter = new JSONRecyclerViewAdapter(SecondActivity.this, json);
                JSONRecyclerViewAdapter.setAdapter(recyclerViewAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                JSONRecyclerViewAdapter.setLayoutManager(linearLayoutManager);


            } else
                Toast.makeText(SecondActivity.this, "Connection Problem for Json object!", Toast.LENGTH_LONG).show();
        }
    }

    // Thats for solve the certifacete problem. Don't use in real life. Your app will open to sll attacks
    public class HttpsTrustManager implements X509TrustManager {

        private TrustManager[] trustManagers;
        private final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

        @Override
        public void checkClientTrusted(
                java.security.cert.X509Certificate[] x509Certificates, String s)
                throws java.security.cert.CertificateException {

        }

        @Override
        public void checkServerTrusted(
                java.security.cert.X509Certificate[] x509Certificates, String s)
                throws java.security.cert.CertificateException {

        }

        public boolean isClientTrusted(X509Certificate[] chain) {
            return true;
        }

        public boolean isServerTrusted(X509Certificate[] chain) {
            return true;
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return _AcceptedIssuers;
        }

        public void allowAllSSL() {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

            });

            SSLContext context = null;
            if (trustManagers == null) {
                trustManagers = new TrustManager[]{new HttpsTrustManager()};
            }

            try {
                context = SSLContext.getInstance("TLS");
                context.init(null, trustManagers, new SecureRandom());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }

            HttpsURLConnection.setDefaultSSLSocketFactory(context
                    .getSocketFactory());
        }

    }


}