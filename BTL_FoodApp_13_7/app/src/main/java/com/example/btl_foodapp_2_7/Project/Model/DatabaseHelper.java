package com.example.btl_foodapp_2_7.Project.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "food_app.db";
    protected static final int DATABASE_VERSION = 1;

    // Tên bảng
    protected static final String TABLE_FOOD = "food";
    protected static final String TABLE_USER = "user";

    // Các cột của bảng FOOD
    private static final String COLUMN_FOOD_ID = "id";
    protected static final String COLUMN_NAME_FOOD = "nameFood";
    protected static final String COLUMN_DESCRIPTION = "description";
    protected static final String COLUMN_PIC_URL = "picUrl";
    protected static final String COLUMN_TIME = "time";
    protected static final String COLUMN_LUOT_DANH_GIA = "luotDanhGia";
    protected static final String COLUMN_LUOT_TIM = "luotTim";

    // Các cột của bảng USER
    protected static final String COLUMN_USER_ID = "id";
    protected static final String COLUMN_NAME = "name";
    protected static final String COLUMN_USERNAME = "username";
    protected static final String COLUMN_PASSWORD = "password";

    protected static final String COLUMN_USER_ID_FK = "user_id"; // Tên cột khóa ngoại trong bảng "food"
    // Câu truy vấn tạo bảng FOOD
    private static final String CREATE_TABLE_FOOD = "create table " + TABLE_FOOD + "(" +
            COLUMN_FOOD_ID + " integer primary key autoincrement, " +
            COLUMN_NAME_FOOD + " text not null, " +
            COLUMN_DESCRIPTION + " text not null, " +
            COLUMN_PIC_URL + " text not null, " +
            COLUMN_TIME + " text not null, " +
            COLUMN_LUOT_DANH_GIA + " integer, " +
            COLUMN_LUOT_TIM + " integer,"+
            COLUMN_USER_ID_FK + " integer references " + TABLE_USER + "(" + COLUMN_USER_ID + "));";

    // Câu truy vấn tạo bảng USER
    private static final String CREATE_TABLE_USER = "create table " + TABLE_USER + "(" +
            COLUMN_USER_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_USERNAME + " text not null, " +
            COLUMN_PASSWORD + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_FOOD);
        database.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }


    void addFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> food = new ArrayList<>();
        ContentValues cv1 = createContentValuesFood("Gà ", "Nướng rất ngon", "", "1 tiếng", 100, 50,101);

    }

    private ContentValues createContentValuesFood(String tenMonAn, String description, String picUrl, String time, int luotDanhGia, int luotTim, int userId){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_FOOD, tenMonAn);
        cv.put(COLUMN_DESCRIPTION, description);

        cv.put(COLUMN_PIC_URL, picUrl);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_LUOT_DANH_GIA, luotDanhGia);
        cv.put(COLUMN_LUOT_TIM, luotTim);
        return cv;
    }

}
