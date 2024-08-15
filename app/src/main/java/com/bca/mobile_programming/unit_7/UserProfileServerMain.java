package com.bca.mobile_programming.unit_7;

import java.util.HashMap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.UserInfo;

public class UserProfileServerMain extends AppCompatActivity {
    ListView mainList;

    EditText idInput;
    EditText nameInput;
    EditText addressInput;

    final String getEndpoint = "http://192.168.0.100/getdata.php";
    final String sendEndpoint = "http://192.168.0.100/setdata.php";

    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.hide();
    }

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_7_user_profile_server);

        idInput = findViewById(R.id.userProfileServerIdInput);
        mainList = findViewById(R.id.userProfileServerListMain);
        nameInput = findViewById(R.id.userProfileServerNameInput);
        addressInput = findViewById(R.id.userProfileServerAddressInput);
        Button insertButton = findViewById(R.id.userProfileServerInsertButton);
        Button refreshButton = findViewById(R.id.userProfileServerRefreshButton);

        getUserData();

        insertButton.setOnClickListener(v -> setUserData());
        refreshButton.setOnClickListener(v -> getUserData());
    }

    private void getUserData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getEndpoint,
                response -> decodeJson(response),
                error -> Log.d("Error getting data", error.toString())
        );

        queue.add(stringRequest);
    }

    private void decodeJson(String response) {
        try {
            ArrayList<UserInfo> data = new ArrayList<>();
            JSONObject result = new JSONObject(response);
            JSONArray array = result.getJSONArray("data");

            for (int i = 0; i < array.length(); i++) {
                JSONObject student = array.getJSONObject(i);

                int id = student.getInt("id");
                String name = student.getString("name");
                String address = student.getString("address");

                UserInfo dataModel = new UserInfo(id, name, address);

                data.add(dataModel);
            }

            UserListItemAdapter adapter = new UserListItemAdapter(UserProfileServerMain.this, data);

            mainList.setAdapter(adapter);
        } catch (Exception e) {
            Log.d("Decode json error", e.toString());
        }
    }

    private void setUserData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, sendEndpoint,
                response -> Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show(),
                error -> Log.d("Error posting data", error.toString())
        ) {
            @Override
            protected HashMap<String, String> getParams() {
                HashMap<String, String> map = new HashMap<>();

                map.put("id", idInput.getText().toString());
                map.put("name", nameInput.getText().toString());
                map.put("address", addressInput.getText().toString());

                return map;
            }
        };

        queue.add(stringRequest);
    }
}
