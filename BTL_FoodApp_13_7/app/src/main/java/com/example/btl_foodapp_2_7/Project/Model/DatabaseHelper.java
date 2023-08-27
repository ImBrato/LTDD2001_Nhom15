package com.example.btl_foodapp_2_7.Project.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    protected static final String DATABASE_NAME = "food_app.db";
    protected static final int DATABASE_VERSION = 3;
//

    // Tên bảng
    protected static final String TABLE_FOOD = "food";
    protected static final String TABLE_USER = "user";

    // Các cột của bảng FOOD
    private static final String COLUMN_FOOD_ID = "id";
    protected static final String COLUMN_NAME_FOOD = "nameFood";
    protected static final String COLUMN_DESCRIPTION = "description";
    protected static final String COLUMN_CACHLAM = "cachLam";
    protected static final String COLUMN_NGUYENLIEU = "nguyenLieu";
    protected static final String COLUMN_PIC_URL = "picUrl";
    protected static final String COLUMN_TIME = "time";
    protected static final String COLUMN_LUOT_DANH_GIA = "luotDanhGia";
    protected static final String COLUMN_LUOT_TIM = "luotTim";
    protected static final String COLUMN_NGAY_DANG = "ngayDang";
    private static final String COLUMN_BUA_AN_ID_FK = "buaAnId"; // Khóa ngoại tham chiếu đến bảng Bua_An

    // Các cột của bảng USER
    protected static final String COLUMN_USER_ID = "id";
    protected static final String COLUMN_NAME = "name";
    protected static final String COLUMN_USERNAME = "username";
    protected static final String COLUMN_PASSWORD = "password";

    protected static final String COLUMN_USER_ID_FK = "user_id"; // Tên cột khóa ngoại trong bảng "food"


    protected static final String TABLE_BUA_AN = "Bua_An";
    protected static final String COLUMN_BUA_AN_ID = "id";
    protected static final String COLUMN_TEN_BUA_AN = "tenBuaAn";

    protected static final String TABLE_SAVED_FOOD = "MonAn_DaLuu";
    protected static final String COLUMN_SAVED_FOOD_ID = "id";
    protected static final String COLUMN_FOOD_ID_FK = "id_food";

    // Câu truy vấn tạo bảng BUAN
    private static final String CREATE_TABLE_BUA_AN = "CREATE TABLE " + TABLE_BUA_AN + " (" +
            COLUMN_BUA_AN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TEN_BUA_AN + " TEXT NOT NULL);";

    // Câu truy vấn tạo bảng FOOD
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + "(" +
            COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_FOOD + " TEXT NOT NULL, " +
            COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
            COLUMN_NGUYENLIEU + " TEXT NOT NULL, " +
            COLUMN_CACHLAM + " TEXT NOT NULL, " +
            COLUMN_PIC_URL + " TEXT NOT NULL, " +
            COLUMN_TIME + " TEXT NOT NULL, " +
            COLUMN_LUOT_DANH_GIA + " INTEGER, " +
            COLUMN_LUOT_TIM + " INTEGER, " +
            COLUMN_NGAY_DANG + " TEXT, " +
            COLUMN_BUA_AN_ID_FK + " INTEGER, " +
            COLUMN_USER_ID_FK + " INTEGER, " +
            "FOREIGN KEY (" + COLUMN_BUA_AN_ID_FK + ") REFERENCES " + TABLE_BUA_AN + "(" + COLUMN_BUA_AN_ID + "), " +
            "FOREIGN KEY (" + COLUMN_USER_ID_FK + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + "));";

    // Câu truy vấn tạo bảng USER
    private static final String CREATE_TABLE_USER = "create table " + TABLE_USER + "(" +
            COLUMN_USER_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_USERNAME + " text not null, " +
            COLUMN_PASSWORD + " text not null);";

    private static final String CREATE_TABLE_SAVED_FOOD = "create table " + TABLE_SAVED_FOOD + "(" +
            COLUMN_SAVED_FOOD_ID + " integer primary key autoincrement, " +
            COLUMN_USER_ID_FK + " integer references " + TABLE_USER + "(" + COLUMN_USER_ID + "), " +
            COLUMN_FOOD_ID_FK + " integer references " + TABLE_FOOD + "(" + COLUMN_FOOD_ID + ")); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_FOOD);
        database.execSQL(CREATE_TABLE_USER);
        database.execSQL(CREATE_TABLE_BUA_AN);
        database.execSQL(CREATE_TABLE_SAVED_FOOD);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUA_AN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_FOOD);
        onCreate(db);
    }


    public void addFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> food = new ArrayList<>();
        ContentValues cv1 = createContentValuesFood("Gà ", "Nướng rất ngon", "Ga", "Bỏ vào nồi nướng", "","1 tiếng", 100, 50, "17/8/2023", 1,  1);
        ContentValues cv2 = createContentValuesFood("Vịt ", "Nướng rất ngon", "Bò", "Bỏ vào nồi chiên", "","30 phút",100, 50, "18/7/20323", 1,1);
        food.add(cv1);
        food.add(cv2);
        food.forEach(f ->{
           db.insert(TABLE_FOOD, null, f);
        });
    }
    public void addUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> user = new ArrayList<>();
        ContentValues cv1 = createContentValuesUser("Đức Hoàng", "admin", "1");
        ContentValues cv2 = createContentValuesUser("Minh Hoàng ", "user", "1");
        user.add(cv1);
        user.add(cv2);
        user.forEach(f ->{
            db.insert(TABLE_USER, null, f);
        });
    }
    public long insertFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_FOOD, food.getTenMonAn());
        values.put(COLUMN_DESCRIPTION, food.getDescription());
        values.put(COLUMN_NGUYENLIEU, food.getNguyenLieu());
        values.put(COLUMN_CACHLAM, food.getCachLam());
        values.put(COLUMN_PIC_URL, food.getPicUrl());
        values.put(COLUMN_TIME, food.getTime());
        values.put(COLUMN_LUOT_DANH_GIA, food.getLuotDanhGia());
        values.put(COLUMN_LUOT_TIM, food.getLuotTim());
        values.put(COLUMN_USER_ID_FK, food.getUserId());
        values.put(COLUMN_NGAY_DANG, food.getNgayDang());
        values.put(COLUMN_BUA_AN_ID_FK, food.getIdBuaAn());

        return db.insert(TABLE_FOOD, null, values);
    }
    public void addBuaAn(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> buaAn = new ArrayList<>();
        ContentValues cv1 = createContentValuesBuaAn("Bữa sáng");
        ContentValues cv2 = createContentValuesBuaAn("Bữa trưa");
        ContentValues cv3 = createContentValuesBuaAn("Bữa tối");

        buaAn.add(cv1);
        buaAn.add(cv2);
        buaAn.add(cv3);
        buaAn.forEach(f ->{
            db.insert(TABLE_BUA_AN, null, f);
        });
    }
    @SuppressLint("Range")
    public ArrayList<Food> getAllFoods() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FOOD;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<Food> foodList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setTenMonAn(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FOOD)));
                food.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                food.setPicUrl(cursor.getString(cursor.getColumnIndex(COLUMN_PIC_URL)));
                food.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                food.setLuotDanhGia(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_DANH_GIA)));
                food.setLuotTim(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_TIM)));
                food.setNgayDang(cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_DANG)));
                String id = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID));
                String tenTacGia = getUserNameById(Integer.parseInt(id));
                food.setTenTacGia(tenTacGia);
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return foodList;
    }



    @SuppressLint("Range")
    public String getUserNameById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT name FROM user WHERE id = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        String userName = null;
        if (cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndex("name"));
        }

        cursor.close();
        db.close();

        return userName;
    }
    public ArrayList<String> getAllNameFood() {
        String SQL = "SELECT * FROM Food";
        SQLiteDatabase  query = getReadableDatabase();

        Cursor cursor = query.rawQuery(SQL,null);
        ArrayList<String> nameFood = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            nameFood.add(name);
        }
        return nameFood;
    }

    private ContentValues createContentValuesFood(String tenMonAn, String description, String nguyenLieu, String cachLam, String picUrl, String time, int luotDanhGia, int luotTim, String ngayDang, int idBuaAn, int userId){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME_FOOD, tenMonAn);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_NGUYENLIEU, nguyenLieu);
        cv.put(COLUMN_CACHLAM, cachLam);
        cv.put(COLUMN_PIC_URL, picUrl);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_LUOT_DANH_GIA, luotDanhGia);
        cv.put(COLUMN_LUOT_TIM, luotTim);
        cv.put(COLUMN_NGAY_DANG, ngayDang);
        cv.put(COLUMN_USER_ID_FK, userId);
        cv.put(COLUMN_BUA_AN_ID_FK, idBuaAn);

        return cv;
    }
    private ContentValues createContentValuesUser(String name, String userName, String password){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_USERNAME, userName);
        cv.put(COLUMN_PASSWORD, password);

        return cv;
    }
    private ContentValues createContentValuesBuaAn(String ten){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TEN_BUA_AN, ten);
        return cv;
    }

    public void deleteAllFoods() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FOOD, null, null);
        db.close();
    }
    public void deleteFoodTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.close();
    }
    public void recreateDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUA_AN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_FOOD);


        // Tạo lại các bảng
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BUA_AN);
        db.execSQL(CREATE_TABLE_SAVED_FOOD);


        db.close();
    }

    public void deleteAllUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
    }
    public void saveFood(int userId, int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID_FK, userId);
        values.put(COLUMN_FOOD_ID_FK, foodId);


        db.insert(TABLE_SAVED_FOOD, null, values);
        db.close();
    }

    public void unsaveFood(int userId, int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_USER_ID_FK + " = ? AND " + COLUMN_FOOD_ID + " = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(foodId)};

        db.delete(TABLE_SAVED_FOOD, whereClause, whereArgs);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<Food> getLikedFoodsByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Food> likedFoods = new ArrayList<>();

        String query = "SELECT f.* FROM " + TABLE_FOOD + " f INNER JOIN " + TABLE_SAVED_FOOD +
                " sf ON f." + COLUMN_FOOD_ID + " = sf." + COLUMN_FOOD_ID_FK +
                " WHERE sf." + COLUMN_USER_ID_FK + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setTenMonAn(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FOOD)));
                food.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                food.setPicUrl(cursor.getString(cursor.getColumnIndex(COLUMN_PIC_URL)));
                food.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                food.setLuotDanhGia(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_DANH_GIA)));
                food.setLuotTim(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_TIM)));
                food.setNgayDang(cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_DANG)));

                likedFoods.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return likedFoods;
    }

    @SuppressLint("Range")
    public int getIduserByName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id FROM user WHERE username = ?";
        String[] selectionArgs = {String.valueOf(username)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        int id = 0;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex("id"));
        }
        cursor.close();
        db.close();

        return id;
    }


}
