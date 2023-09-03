package com.example.btl_foodapp_2_7.Project.Model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

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
    protected static final String COLUMN_EMAIL = "email";
    protected static final String COLUMN_USER_ROLE = "user_role";

    protected static final String COLUMN_USER_ID_FK = "user_id"; // Tên cột khóa ngoại trong bảng "food"


    protected static final String TABLE_BUA_AN = "Bua_An";
    protected static final String COLUMN_BUA_AN_ID = "id";
    protected static final String COLUMN_TEN_BUA_AN = "tenBuaAn";
    protected static final String COLUMN_IMAGE_BUA_AN = "anhBuaAn";

    protected static final String TABLE_SAVED_FOOD = "MonAn_DaLuu";
    protected static final String COLUMN_SAVED_FOOD_ID = "id";
    protected static final String COLUMN_FOOD_ID_FK = "id_food";


    private static final String TABLE_COMMENT = "comment";

    // Các cột trong bảng "comment"
    private static final String COLUMN_ID_COMMENT = "id";
    private static final String COLUMN_NOI_DUNG = "noi_dung";
    private static final String COLUMN_ID_FOOD_FK_COMMENT = "id_food";
    private static final String COLUMN_ID_USER_FK_COMMENT = "id_user";


    private static final String TABLE_THONG_BAO = "thong_bao";

    // Các cột trong bảng "thong_bao"
    private static final String COLUMN_ID_THONG_BAO = "id";
    private static final String COLUMN_NOI_DUNG_THONG_BAO = "noi_dung_thong_bao";
    private static final String COLUMN_THOI_GIAN_THONG_BAO = "thoi_gian";

    private static final String COLUMN_ID_USER_FK_THONG_BAO = "id_user";



    // Câu truy vấn tạo bảng BUAN
    private static final String CREATE_TABLE_BUA_AN = "CREATE TABLE " + TABLE_BUA_AN + " (" +
            COLUMN_BUA_AN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TEN_BUA_AN + " TEXT NOT NULL,"+
            COLUMN_IMAGE_BUA_AN + " TEXT NOT NULL);" ;

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
            COLUMN_PASSWORD + " text not null, " +
            COLUMN_EMAIL + " text, " +
            COLUMN_USER_ROLE + " text not null);";

    private static final String CREATE_TABLE_SAVED_FOOD = "create table " + TABLE_SAVED_FOOD + "(" +
            COLUMN_SAVED_FOOD_ID + " integer primary key autoincrement, " +
            COLUMN_USER_ID_FK + " integer references " + TABLE_USER + "(" + COLUMN_USER_ID + "), " +
            COLUMN_FOOD_ID_FK + " integer references " + TABLE_FOOD + "(" + COLUMN_FOOD_ID + ")); ";


    private static final String CREATE_TABLE_COMMENT = "CREATE TABLE " + TABLE_COMMENT + " (" +
            COLUMN_ID_COMMENT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOI_DUNG + " TEXT NOT NULL, " +
            COLUMN_ID_FOOD_FK_COMMENT + " INTEGER, " +
            COLUMN_ID_USER_FK_COMMENT + " INTEGER, " +
            "FOREIGN KEY (" + COLUMN_ID_FOOD_FK_COMMENT + ") REFERENCES food(id), " +
            "FOREIGN KEY (" + COLUMN_ID_USER_FK_COMMENT + ") REFERENCES user(id));";


    private static final String CREATE_TABLE_THONG_BAO = "CREATE TABLE " + TABLE_THONG_BAO + " (" +
            COLUMN_ID_THONG_BAO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOI_DUNG_THONG_BAO + " TEXT NOT NULL, " +
            COLUMN_THOI_GIAN_THONG_BAO + " TEXT, " +
            COLUMN_ID_USER_FK_THONG_BAO + " INTEGER, " +
            "FOREIGN KEY (" + COLUMN_ID_USER_FK_THONG_BAO + ") REFERENCES user(id));";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_FOOD);
        database.execSQL(CREATE_TABLE_USER);
        database.execSQL(CREATE_TABLE_BUA_AN);
        database.execSQL(CREATE_TABLE_SAVED_FOOD);
        database.execSQL(CREATE_TABLE_COMMENT);
        database.execSQL(CREATE_TABLE_THONG_BAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUA_AN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVED_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_BAO);
        onCreate(db);
    }


        @SuppressLint("Range")
        public Food getFoodById(int foodId) {
            SQLiteDatabase db = this.getReadableDatabase();
            Food food = null;

            String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_FOOD_ID + " = ?";
            String[] selectionArgs = {String.valueOf(foodId)};
            Cursor cursor = db.rawQuery(query, selectionArgs);
            if (cursor.moveToFirst()) {
                food = new Food();
                food.setTenMonAn(cursor.getString(cursor.getColumnIndex(COLUMN_NAME_FOOD)));
                food.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                food.setPicUrl(cursor.getString(cursor.getColumnIndex(COLUMN_PIC_URL)));
                food.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
                food.setLuotDanhGia(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_DANH_GIA)));
                food.setLuotTim(cursor.getInt(cursor.getColumnIndex(COLUMN_LUOT_TIM)));
                food.setNgayDang(cursor.getString(cursor.getColumnIndex(COLUMN_NGAY_DANG)));
                food.setIdBuaAn(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_BUA_AN_ID_FK))));
                food.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID_FK))));
                // Lấy tên tác giả
                String userId = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID_FK));
                String tenTacGia = getUserNameById(Integer.parseInt(userId));
                food.setTenTacGia(tenTacGia);
            }
            cursor.close();
            db.close();

            return food;
        }
    public void addFood(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> food = new ArrayList<>();
//        ContentValues cv1 = createContentValuesFood("Ga ", "Nướng rất ngon", "Ga", "Bỏ vào nồi nướng", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg","1 tiếng", 100, 50, "17/8/2023", 1,  1);
//        ContentValues cv2 = createContentValuesFood("Vịt ", "Nướng rất ngon", "Bò", "Bỏ vào nồi chiên", "https://cdn.tgdd.vn/Files/2021/07/28/1371483/bi-quyet-lam-mon-vit-nuong-van-dinh-thom-ngon-nuc-mui-ca-nha-deu-me-202201030905519106.jpg","30 phút",100, 50, "18/7/2023", 1,1);
////        ContentValues cv3 = createContentValuesFood("Ga Ga", "Nướng rất ngon", "Lợn", "Bỏ vào nồi chiên", "https://afamilycdn.com/150157425591193600/2021/7/28/cach-lam-thit-heo-chien-nuoc-mam-ngon-1-2-1024x576-16274651688601825681620.jpg","30 phút",100, 50, "18/9/20323", 1,2);
//        food.add(cv1);
//        food.add(cv2);
//        food.add(cv3);
        food.forEach(f ->{
           db.insert(TABLE_FOOD, null, f);
        });
    }

    public void addThongBao(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> thongBao = new ArrayList<>();

        ContentValues cv1 = createContentValuesThongBao("Ban vua cos thong bao.", 1, "24/03/2002");
        ContentValues cv2 = createContentValuesThongBao("Ban vua cos thong bao.", 1, "24/03/2002");
        ContentValues cv3 = createContentValuesThongBao("Ban vua cos thong bao.", 1, "24/03/2002");
        ContentValues cv4 = createContentValuesThongBao("Ban vua cos thong bao.", 1, "24/03/2002");
        thongBao.add(cv1);
        thongBao.add(cv2);
        thongBao.add(cv3);
        thongBao.add(cv4);
        thongBao.forEach(f ->{
            db.insert(TABLE_THONG_BAO, null, f);
        });

    }

    @SuppressLint("Range")
    public User getUserById(int userId) {
        User user = null;
        SQLiteDatabase db = this.getReadableDatabase();

        // Tạo câu truy vấn SQL
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COLUMN_USER_ID + " = ?";

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        // Kiểm tra xem dữ liệu có tồn tại không
        if (cursor.moveToFirst()) {

            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String userName = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            String role = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE));
            // Tạo đối tượng User từ dữ liệu CSDL
            user = new User(name,userName, email, password, role);
        }

        // Đóng kết nối CSDL và trả về người dùng (hoặc null nếu không tìm thấy)
        cursor.close();
        db.close();
        return user;
    }
    public void addUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> user = new ArrayList<>();

        ContentValues cv1 = createContentValuesUser("Đức Hoàng", "admin", "1","hoangbrato@gmail.com", "ADMIN");
        ContentValues cv2 = createContentValuesUser("Minh Hoàng ", "user", "1", "minhhoang2401@gmail.com", "USER");
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
        ContentValues cv1 = createContentValuesBuaAn("Bữa sáng", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg");
        ContentValues cv2 = createContentValuesBuaAn("Bữa trưa", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg");
        ContentValues cv3 = createContentValuesBuaAn("Bữa tối", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg");
        ContentValues cv4 = createContentValuesBuaAn("Bữa Phụ", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg");
        ContentValues cv5 = createContentValuesBuaAn("Ăn Vặt", "https://cdn.tgdd.vn/2020/12/CookProduct/2-1200x676-1.jpg");


        buaAn.add(cv1);
        buaAn.add(cv2);
        buaAn.add(cv3);
        buaAn.add(cv4);
        buaAn.add(cv5);
        buaAn.forEach(f ->{
            db.insert(TABLE_BUA_AN, null, f);
        });
    }
    public void updateLuotTim(int foodId, int newLuotTim) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LUOT_TIM, newLuotTim);

        // Xác định điều kiện cho câu lệnh UPDATE
        String whereClause = COLUMN_FOOD_ID + " = ?";
        String[] whereArgs = { String.valueOf(foodId) };

        // Thực hiện câu lệnh UPDATE
        int rowsUpdated = db.update(TABLE_FOOD, values, whereClause, whereArgs);

        db.close();

        if (rowsUpdated > 0) {
            // Cập nhật thành công
        } else {
            // Không tìm thấy dòng nào cần cập nhật hoặc có lỗi xảy ra
        }
    }
    public void addComment(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<ContentValues> buaAn = new ArrayList<>();
        ContentValues cv1 = createContentValuesComment("Ngon quá đi", 1, 1);
        ContentValues cv2 = createContentValuesComment("Ngon thật á", 1, 1);
        ContentValues cv3 = createContentValuesComment("Ngon quá đi mất", 1, 1);

        buaAn.add(cv1);
        buaAn.add(cv2);
        buaAn.add(cv3);
        buaAn.forEach(f ->{
            db.insert(TABLE_COMMENT, null, f);
        });
    }


    @SuppressLint("Range")
    public ArrayList<BuaAn> getAllBuaAn() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<BuaAn> buaAnList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_BUA_AN;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                BuaAn buaAn = new BuaAn();
                buaAn.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_BUA_AN_ID)));
                buaAn.setTenBuaAn(cursor.getString(cursor.getColumnIndex(COLUMN_TEN_BUA_AN)));
                buaAn.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_BUA_AN)));
                buaAnList.add(buaAn);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return buaAnList;
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
    public List<Notification> getAllThongBao() {
        List<Notification> notificationList = new ArrayList<>();

        // Viết truy vấn SQL để lấy thông báo từ bảng thong_bao
        String selectQuery = "SELECT * FROM thong_bao";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Duyệt qua các dòng kết quả và thêm chúng vào danh sách notificationList
        if (cursor.moveToFirst()) {
            do {
                Notification notification = new Notification();

                notification.setText_noti(cursor.getString(cursor.getColumnIndex(COLUMN_NOI_DUNG_THONG_BAO)));
                notification.setTime_noti(cursor.getString(cursor.getColumnIndex(COLUMN_THOI_GIAN_THONG_BAO)));

                // Điền thêm các trường thông báo khác nếu cần và áp dụng setter cho chúng

                notificationList.add(notification);
            } while (cursor.moveToNext());
        }

        // Đóng kết nối và trả về danh sách thông báo
        cursor.close();
        db.close();
        return notificationList;
    }


    @SuppressLint("Range")
    public ArrayList<Food> getFoodsByBuaAnId(int buaAnId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Food> foodList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " + COLUMN_BUA_AN_ID_FK + " = ?";
        String[] selectionArgs = {String.valueOf(buaAnId)};
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
    private ContentValues createContentValuesThongBao(String noiDung, int idUser, String thoiGian){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOI_DUNG_THONG_BAO, noiDung);
        cv.put(COLUMN_ID_USER_FK_THONG_BAO, idUser);
        cv.put(COLUMN_THOI_GIAN_THONG_BAO, thoiGian);



        return cv;
    }
    private ContentValues createContentValuesComment(String noiDung, int idUser, int idFood){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NOI_DUNG, noiDung);
        cv.put(COLUMN_ID_USER_FK_COMMENT, idUser);
        cv.put(COLUMN_ID_FOOD_FK_COMMENT, idFood);

        return cv;
    }
    private ContentValues createContentValuesUser(String name, String userName, String password, String email, String userRole){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_USERNAME, userName);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_USER_ROLE, userRole);
        return cv;
    }
    private ContentValues createContentValuesBuaAn(String ten, String image){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TEN_BUA_AN, ten);
        cv.put(COLUMN_IMAGE_BUA_AN, image);
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONG_BAO);


        // Tạo lại các bảng
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_BUA_AN);
        db.execSQL(CREATE_TABLE_SAVED_FOOD);
        db.execSQL(CREATE_TABLE_COMMENT);
        db.execSQL(CREATE_TABLE_THONG_BAO);


        db.close();
    }

    @SuppressLint("Range")
    public MonAn_DaLuu getSaveFoodByIds(int idFood, int idUser) {
        SQLiteDatabase db = this.getReadableDatabase();
        MonAn_DaLuu saveFood = null;

        String query = "SELECT * FROM " + TABLE_SAVED_FOOD + " WHERE " + COLUMN_FOOD_ID_FK + " = ? AND " + COLUMN_USER_ID_FK + " = ?";
        String[] selectionArgs = {String.valueOf(idFood), String.valueOf(idUser)};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            saveFood = new MonAn_DaLuu();
            // Đọc các thông tin về SaveFood từ cursor và gán cho đối tượng SaveFood
            saveFood.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_SAVED_FOOD_ID)));
            saveFood.setIdMonAn(cursor.getInt(cursor.getColumnIndex(COLUMN_FOOD_ID_FK)));
            saveFood.setIdUser(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID_FK)));
            // Đọc các thông tin khác về SaveFood và gán cho đối tượng SaveFood
            // ...

        }

        cursor.close();
        db.close();

        return saveFood;
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



    public void saveComment(String noiDung, int userId, int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOI_DUNG,  noiDung);
        values.put(COLUMN_ID_FOOD_FK_COMMENT,  foodId);
        values.put(COLUMN_ID_USER_FK_COMMENT, userId);
        db.insert(TABLE_COMMENT, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<User_Comment> getCommentsByFoodId(int foodId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User_Comment> comments = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_COMMENT + " WHERE " + COLUMN_ID_FOOD_FK_COMMENT + " = ?";
        String[] selectionArgs = {String.valueOf(foodId)};

        Cursor cursor = db.rawQuery(query, selectionArgs);

        if (cursor.moveToFirst()) {
            do {
                User_Comment comment = new User_Comment();
                comment.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_COMMENT)));
                comment.setNoiDung(cursor.getString(cursor.getColumnIndex(COLUMN_NOI_DUNG)));
                comment.setIdFood(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_FOOD_FK_COMMENT)));
                comment.setIdUser(cursor.getInt(cursor.getColumnIndex(COLUMN_ID_USER_FK_COMMENT)));

                comments.add(comment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return comments;
    }

    public void unsaveFood(int userId, int foodId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_USER_ID_FK + " = ? AND " + COLUMN_FOOD_ID_FK + " = ?";
        String[] whereArgs = {String.valueOf(userId), String.valueOf(foodId)};

        db.delete(TABLE_SAVED_FOOD, whereClause, whereArgs);
        db.close();
    }

    public boolean checkIfFoodIsSaved(int userId, int foodId) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean isSaved = false;

        try {
            String[] columns = {COLUMN_SAVED_FOOD_ID};
            String selection = COLUMN_USER_ID_FK + " = ? AND " + COLUMN_FOOD_ID_FK + " = ?";
            String[] selectionArgs = {String.valueOf(userId), String.valueOf(foodId)};

            Cursor cursor = db.query(TABLE_SAVED_FOOD, columns, selection, selectionArgs, null, null, null);

            if (cursor != null) {
                isSaved = cursor.getCount() > 0;
                cursor.close();
            }
        } catch (SQLiteException e) {
            // Xử lý lỗi nếu có
            e.printStackTrace();
        } finally {
            db.close(); // Đảm bảo đóng cơ sở dữ liệu sau khi sử dụng
        }

        return isSaved;
    }

    public void saveThongBao(ThongBao thongBao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOI_DUNG_THONG_BAO, thongBao.getNoiDung());
        values.put(COLUMN_ID_USER_FK_THONG_BAO, thongBao.getId_admin());

        db.insert(TABLE_THONG_BAO, null, values);

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

    @SuppressLint("Range")
    public String getUserRoleByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {COLUMN_USER_ROLE};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = db.query(TABLE_USER, projection, selection, selectionArgs, null, null, null);
        String userRole = null;

        if (cursor.moveToFirst()) {
            userRole = cursor.getString(cursor.getColumnIndex(COLUMN_USER_ROLE));
        }

        cursor.close();
        db.close();

        return userRole;
    }

    public boolean updateUserInfo(int userId, String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Thêm thông tin mới vào ContentValues
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);

        // Xác định điều kiện cập nhật: ID của người dùng
        String whereClause = COLUMN_USER_ID + " = ?";
        String[] whereArgs = {String.valueOf(userId)};

        // Thực hiện cập nhật và kiểm tra xem có thành công hay không
        int numRowsUpdated = db.update(TABLE_USER, values, whereClause, whereArgs);

        // Đóng kết nối CSDL
        db.close();

        // Trả về true nếu có ít nhất một hàng được cập nhật, ngược lại trả về false
        return numRowsUpdated > 0;
    }




}
