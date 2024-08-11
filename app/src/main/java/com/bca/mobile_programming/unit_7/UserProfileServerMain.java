package com.bca.mobile_programming.unit_7;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bca.mobile_programming.R;
import com.bca.mobile_programming.unit_1.UserInfo;

public class UserProfileServerMain extends AppCompatActivity {
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

        ListView mainList = findViewById(R.id.userProfileServerListMain);

        Button selectButton = findViewById(R.id.userProfileServerSelectButton);
        Button insertButton = findViewById(R.id.userProfileServerInsertButton);

        EditText idInput = findViewById(R.id.userProfileServerIdInput);
        EditText nameInput = findViewById(R.id.userProfileServerNameInput);
        EditText addressInput = findViewById(R.id.userProfileServerAddressInput);

        try (MyDatabase myDatabase = new MyDatabase(this)) {
            insertButton.setOnClickListener(v -> {
                String name = nameInput.getText().toString();
                String address = addressInput.getText().toString();
                int id = Integer.parseInt(idInput.getText().toString());

                UserInfo data = new UserInfo(id, name, address);

                myDatabase.insertData(data);

                Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_SHORT).show();
            });

            selectButton.setOnClickListener(v -> {
                ArrayList<Integer> idList = new ArrayList<>();
                ArrayList<String> nameList = new ArrayList<>();
                ArrayList<String> addressList = new ArrayList<>();

                Cursor cursor = myDatabase.selectData();

                while (cursor.moveToNext()) {
                    idList.add(cursor.getInt(0));
                    nameList.add(cursor.getString(1));
                    addressList.add(cursor.getString(2));
                }

                UserListItemAdapter adapter = new UserListItemAdapter(UserProfileServerMain.this, idList, nameList, addressList);

                mainList.setAdapter(adapter);
            });
        }
    }
}
