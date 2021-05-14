package com.example.lab10_sql_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_Name = "usersdb";
    private static final String TABLE_Users = "userDetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_DESG = "designation";

    public DbHandler(@Nullable Context context)
    {
        super(context,DB_Name, null, DB_VERSION);
    }

    public void insertUserDetails(String name, String location, String designation)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cvalues = new ContentValues();
        cvalues.put(KEY_NAME, name);
        cvalues.put(KEY_LOC, location);
        cvalues.put(KEY_DESG, designation);

        long newRowId = db.insert(TABLE_Users, null, cvalues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<HashMap<String, String>> userList = new ArrayList<>();

        String query = "SELECT name, location, designation FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext())
        {
            HashMap<String, String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("designation", cursor.getString(cursor.getColumnIndex(KEY_DESG)));
            user.put("location", cursor.getString(cursor.getColumnIndex(KEY_LOC)));

            userList.add(user);
        }
        return userList;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_LOC + " TEXT,"
                + KEY_DESG + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_Users);
        //create tables again
        onCreate(db);
    }
}
