package com.example.btl_foodapp_2_7.Project.Activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.api.exceptions.ApiException;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.R;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private TextView tvDangky;
    private Button btnDangnhap;
    private EditText username, password;
    private ImageView loginGoogle;
    SQLiteDatabase db;
    LottieAnimationView lottieAnimationView;
    private boolean hasRunOnce = false;
        private static final int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onResume() {
        super.onResume();
//        SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
//        String username = preferences.getString("username", "");
//        if (!username.isEmpty()) {
//            Toast.makeText(LoginActivity.this, "Login da dang nhap ", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(LoginActivity.this, "Login chua dang nhap", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = openOrCreateDatabase("food_app.db", MODE_PRIVATE, null);
        username = findViewById(R.id.inputUsername);
        password = findViewById(R.id.editTextTextPassword);
        loginGoogle = findViewById(R.id.login_google);

        SharedPreferences  prefs = getPreferences(this.MODE_PRIVATE);
        if(prefs.getBoolean("firstRun", false )) {

            DatabaseHelper db2 = new DatabaseHelper(LoginActivity.this);
            db2.recreateDatabase();
            db2.addFood();
            db2.addUser();
            db2.addBuaAn();
            db2.addComment();
            db2.addThongBao();
            String url = "https://64f18dbb0e1e60602d23eb4e.mockapi.io/api/food";
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);
                                    Food food = new Food();
                                    food.setId(jsonObject.getInt("id"));
                                    food.setTenMonAn(jsonObject.getString("food_name"));
                                    food.setDescription(jsonObject.getString("description"));
                                    food.setNguyenLieu(jsonObject.getString("nguyen_lieu"));
                                    food.setCachLam(jsonObject.getString("cach_lam"));
                                    food.setPicUrl(jsonObject.getString("picUrl"));
                                    food.setTime(jsonObject.getString("time"));
                                    food.setIdBuaAn(jsonObject.getInt("buaAnId"));
                                    food.setUserId(jsonObject.getInt("user_id"));
                                    db2.insertFood(food);
                                }
                            } catch (JSONException e) {
                                // Toast.makeText(MainActivity.this, "Lỗi xử lý dữ liệu JSON" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Toast.makeText(MainActivity.this, "Lỗi khi fetch API", Toast.LENGTH_SHORT).show();
                        }
                    });

            Volley.newRequestQueue(this).add(request);
            prefs.edit().putBoolean("firstRun", true).commit();
        }



//        if (!hasRunOnce) {
//            // Đoạn mã sẽ chỉ chạy khi hasRunOnce là false
//            DatabaseHelper db2 = new DatabaseHelper(LoginActivity.this);
//            db2.recreateDatabase();
//            // db.addFood();
//            db2.addUser();
//            db2.addBuaAn();
//            db2.addComment();
//            db2.addThongBao();
//            db2.saveComment("day la comment1", 1, 1);
//

//
//            // Sau khi đã chạy, đặt hasRunOnce thành true để không chạy lại
//            hasRunOnce = true;
//        }


        tvDangky = findViewById(R.id.tvBackLogin);

        mAuth = FirebaseAuth.getInstance();
        tvDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SiginActivity.class));
            }
        });

        //login gg
        loginGoogle.setOnClickListener(view -> signInWithGoogle());


        btnDangnhap = findViewById(R.id.btnDangnhap);
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db2 = new DatabaseHelper(LoginActivity.this);
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String userRole = db2.getUserRoleByUsername(usernameTxt);
                String[] projection = {"id"};
                String selection = "username = ? AND password = ?";
                String[] selectionArgs = {usernameTxt, passwordTxt};
                Cursor cursor = db.query("user", projection, selection, selectionArgs, null, null, null);
//                Cursor res = db.query("user", null,null,null,null,null,null);
//                res.moveToNext();
                if (cursor.getCount() == 0) {
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (userRole != null) {
                        if (userRole.equals("ADMIN")) {
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }
                    SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("username", usernameTxt);
                    editor.putString("password", passwordTxt);

                    editor.apply();

                }
            }
        });

        lottieAnimationView = findViewById(R.id.gif);
        lottieAnimationView.setRepeatCount(LottieDrawable.INFINITE);


        // Cấu hình đăng nhập Google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        lottieAnimationView.animate().setDuration(99999999);

    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Xử lý lỗi
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            Log.d("Idtoken", idToken);
                            FirebaseUser user = mAuth.getInstance().getCurrentUser();
                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            String displayName = user.getDisplayName(); // Lấy tên người dùng
            String email = user.getEmail(); // Lấy địa chỉ email người dùng
            SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", email);

            editor.apply();
        } else {

        }
    }
}

