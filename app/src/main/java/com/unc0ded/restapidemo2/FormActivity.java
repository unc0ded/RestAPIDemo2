package com.unc0ded.restapidemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unc0ded.restapidemo2.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    EditText nameET, ageET, locLatET, locLongET;
    Button send;
    String name, age, lat, longTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nameET=findViewById(R.id.name_et);
        ageET=findViewById(R.id.age_et);
        locLatET=findViewById(R.id.lat_et);
        locLongET=findViewById(R.id.long_et);
        send=findViewById(R.id.send_btn);

        send.setOnClickListener((v) -> {
            name = nameET.getText().toString().trim();
            age = ageET.getText().toString().trim();
            lat = locLatET.getText().toString().trim();
            longTxt = locLongET.getText().toString().trim();

            if(name.isEmpty()) nameET.setError("Invalid entry");
            else nameET.setError(null);
            if(age.isEmpty()) ageET.setError("Invalid entry");
            else ageET.setError(null);
            if(lat.isEmpty()) locLatET.setError("Invalid entry");
            else locLatET.setError(null);
            if(longTxt.isEmpty()) locLongET.setError("Invalid entry");
            else locLongET.setError(null);

            if(nameET.getError()==null && ageET.getError()==null && locLatET.getError()==null && locLongET.getError()==null)
                send(name,age,lat,longTxt);
        });
    }

    private void send(String name, String age, String lat, String longTxt) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("age", age);
        map.put("foundLost", "NotFound");
        map.put("lastLocationLat", lat);
        map.put("lastLocationLong", longTxt);

        Call<Void> call = RetrofitClient.getClient().sendPerson(map);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(),"Person Added", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
