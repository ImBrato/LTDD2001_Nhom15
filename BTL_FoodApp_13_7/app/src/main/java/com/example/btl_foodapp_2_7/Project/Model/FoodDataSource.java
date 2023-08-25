package com.example.btl_foodapp_2_7.Project.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class FoodDataSource {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public FoodDataSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertFood(Food food) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME_FOOD, food.getDescription());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION, food.getDescription());
        values.put(DatabaseHelper.COLUMN_PIC_URL, food.getPicUrl());
        values.put(DatabaseHelper.COLUMN_TIME, food.getTime());
        values.put(DatabaseHelper.COLUMN_LUOT_DANH_GIA, food.getLuotDanhGia());
        values.put(DatabaseHelper.COLUMN_LUOT_TIM, food.getLuotTim());
        values.put(DatabaseHelper.COLUMN_USER_ID_FK, food.getUserId());

        return database.insert(DatabaseHelper.TABLE_FOOD, null, values);
    }

    public Cursor getAllFoods() {
        return database.query(DatabaseHelper.TABLE_FOOD, null, null, null, null, null, null);
    }



    // ... Các phương thức truy vấn và tương tác khác

}
