package com.unc0ded.restapidemo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.unc0ded.restapidemo2.models.Person;
import com.unc0ded.restapidemo2.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter adapter;
    Button formbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formbtn=findViewById(R.id.form_btn);
        formbtn.setOnClickListener(view -> {
            Intent form = new Intent(MainActivity.this, FormActivity.class);
            startActivity(form);
        });

        recyclerView=findViewById(R.id.people_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Call<List<Person>> call= RetrofitClient.getClient().getPeople();
        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if(response.isSuccessful()){
                    ArrayList<Person> list= (ArrayList<Person>)response.body();
                    adapter= new PersonAdapter(list);
                    recyclerView.setAdapter(adapter);
                }
                else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.e("ResponseError",t.getMessage());

            }
        });
    }
}
