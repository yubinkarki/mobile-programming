package com.bca.mobile_programming.unit_7;

import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "user";
    private static final String DATABASE_NAME = "mobile_programming";

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

    public void insertData(int id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("address", address);

        db.insert(USER_TABLE, null, contentValues);
        db.close();
    }

    public Cursor selectData() {
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(query, null);
    }

    public void updateData(String id, String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("address", address);

        db.update(USER_TABLE, contentValues, "id=?", new String[]{id});
        db.close();
    }

    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USER_TABLE, "id=?", new String[]{id});
    }
}
