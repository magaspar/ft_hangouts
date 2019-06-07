package com.ft_hangouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

public class ContactManager extends SQLiteOpenHelper
{
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ADDRESS = "adress";
    public ContactManager(Context context)
    {
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_LASTNAME + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_ADDRESS + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        onCreate(db);
    }
    void insertUserDetails(String lastname, String name, String phone, String email, String adress)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_LASTNAME, lastname);
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PHONE, phone);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_ADDRESS, adress);
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }
    public ArrayList<HashMap<String, String>> GetUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT lastname, name, phone, email, adress, id FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("lastname",cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("phone",cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("adress",cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            userList.add(user);
        }
        return  userList;
    }
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT lastname, name, phone, email, adress FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_LASTNAME, KEY_NAME, KEY_PHONE, KEY_EMAIL, KEY_ADDRESS}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("lastname",cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
            user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("phone",cursor.getString(cursor.getColumnIndex(KEY_PHONE)));
            user.put("email",cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.put("adress",cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            userList.add(user);
        }
        return  userList;
    }
    public void DeleteUser(int userid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{String.valueOf(userid)});
        db.close();
    }
    public int UpdateUserDetails(String name, String phone, String lastname, String email, String adress, int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_NAME, name);
        cVals.put(KEY_PHONE, phone);
        cVals.put(KEY_LASTNAME, lastname);
        cVals.put(KEY_EMAIL, email);
        cVals.put(KEY_ADDRESS, adress);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }
}