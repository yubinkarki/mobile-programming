package com.bca.mobile_programming.unit_7;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.bca.mobile_programming.unit_1.UserInfo;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mobile_programming";

    private final String COLUMN_ID = "id";
    private final String USER_TABLE = "user";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_ADDRESS = "address";

    public MyDatabase(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + USER_TABLE + "(id INTEGER PRIMARY KEY, name TEXT, address TEXT)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    public void insertData(UserInfo data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_ID, data.getId());
        contentValues.put(COLUMN_NAME, data.getName());
        contentValues.put(COLUMN_ADDRESS, data.getAddress());

        db.insert(USER_TABLE, null, contentValues);
        db.close();
    }

    public Cursor selectData() {
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(query, null);
    }

    public void updateData(UserInfo data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, data.getName());
        contentValues.put(COLUMN_ADDRESS, data.getAddress());

        db.update(USER_TABLE, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(data.getId())});
        db.close();
    }

    public void deleteData(String id) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            db.delete(USER_TABLE, COLUMN_ID + "=?", new String[]{id});
        }
    }
}
