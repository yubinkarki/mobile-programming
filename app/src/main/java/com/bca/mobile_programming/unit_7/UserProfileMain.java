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

public class UserProfileMain extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();

        ActionBar bar = getSupportActionBar();
        if (bar != null) bar.hide();
    }

    @Override
    protected void onCreate(@Nullable Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.unit_7_user_profile);

        ListView mainList = findViewById(R.id.userProfileListMain);

        Button selectButton = findViewById(R.id.userProfileSelectButton);
        Button insertButton = findViewById(R.id.userProfileInsertButton);
        Button updateButton = findViewById(R.id.userProfileUpdateButton);
        Button deleteButton = findViewById(R.id.userProfileDeleteButton);

        EditText idInput = findViewById(R.id.userProfileIdInput);
        EditText nameInput = findViewById(R.id.userProfileNameInput);
        EditText addressInput = findViewById(R.id.userProfileAddressInput);

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
                ArrayList<UserInfo> userInfoList = new ArrayList<>();

                Cursor cursor = myDatabase.selectData();

                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String address = cursor.getString(2);
                    userInfoList.add(new UserInfo(id, name, address));
                }

                UserListItemAdapter adapter = new UserListItemAdapter(UserProfileMain.this, userInfoList);

                mainList.setAdapter(adapter);
            });

            updateButton.setOnClickListener(v -> {
                String name = nameInput.getText().toString();
                String address = addressInput.getText().toString();
                int id = Integer.parseInt(idInput.getText().toString());

                UserInfo data = new UserInfo(id, name, address);

                myDatabase.updateData(data);

                Toast.makeText(getApplicationContext(), "Updated successfully", Toast.LENGTH_SHORT).show();
            });

            deleteButton.setOnClickListener(v -> {
                String id = idInput.getText().toString();

                myDatabase.deleteData(id);

                Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
            });
        }
    }
}
