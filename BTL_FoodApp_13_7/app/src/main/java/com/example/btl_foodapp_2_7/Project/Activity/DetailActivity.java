package com.example.btl_foodapp_2_7.Project.Activity;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_foodapp_2_7.Project.Adapter.CategoryListAdapter;
import com.example.btl_foodapp_2_7.Project.Adapter.Comment_Itemts;
import com.example.btl_foodapp_2_7.Project.Adapter.FoodListAdapter;
import com.example.btl_foodapp_2_7.Project.Model.Category;
import com.example.btl_foodapp_2_7.Project.Model.Comments;
import com.example.btl_foodapp_2_7.Project.Model.DatabaseHelper;
import com.example.btl_foodapp_2_7.Project.Model.Food;
import com.example.btl_foodapp_2_7.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterFoodList;
    private RecyclerView recyclerViewFood;

    private RecyclerView recyclerViewCmt;
    private RecyclerView.Adapter adapterCmt;

    RecyclerView homeHorizontalRec, homeVerticalRec;
    List<Category> homeHormodelList;

    private List<Comments> cardCommentList;

    CategoryListAdapter homeHorAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EditText comment = findViewById(R.id.EditnhapComment);
        CheckBox sent = findViewById(R.id.sendComments);

        ArrayList<Food> items = new ArrayList<>();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewFood = findViewById(R.id.view6);
        recyclerViewFood.setLayoutManager(linearLayoutManager);
        int idFood = getIntent().getIntExtra("idBuaAn", 0);
        DatabaseHelper db2 = new DatabaseHelper(DetailActivity.this);
        Food food = new Food();
        food = db2.getFoodById(1);

        SharedPreferences preferences = DetailActivity.this.getSharedPreferences("login", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "");
        int id = db2.getIduserByName(username);
        TextView nameFood, tenTacGia, luotThich, nguyenLieu, buocLam;
        ImageView anhFood;
//        nameFood = findViewById(R.id.textViewNameFood);
//        tenTacGia = findViewById(R.id.tenTacGia);
//        luotThich = findViewById(R.id.luotThich);
//        nguyenLieu = findViewById(R.id.nguyenLieu);
//        buocLam = findViewById(R.id.buocLam);
//        anhFood = findViewById(R.id.imgAnhFood);
//
//        nameFood.setText(food.getTenMonAn());
//        tenTacGia.setText(food.getTenTacGia());
//        luotThich.setText(food.getLuotTim());
//        nguyenLieu.setText(food.getNguyenLieu());
//        buocLam.setText(food.getCachLam());
//        Picasso.get()
//                .load(food.getPicUrl())
//                .placeholder(R.drawable.pic_sushi) // Hình ảnh mặc định
//                .error(R.drawable.background_intro) // Hình ảnh sẽ hiển thị nếu có lỗi
//                .into(anhFood);


        cardCommentList = new ArrayList<>();
        // Khởi tạo và thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.view_cmt);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo adapter và gán cho RecyclerView
        Comment_Itemts adapter = new Comment_Itemts(db2.getCommentsByFoodId(1));
        recyclerView.setAdapter(adapter);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sent.isChecked()) {
                    String commentText = comment.getText().toString().trim();
                    if (!commentText.isEmpty()) {
                        // Lấy dữ liệu từ ô comment và thêm comment vào CSDL
                        db2.saveComment(commentText, id, 1);
                        Toast.makeText(DetailActivity.this,"Thêm thành công", Toast.LENGTH_SHORT).show();
                        Comment_Itemts adapter = new Comment_Itemts(db2.getCommentsByFoodId(1));
                        recyclerView.setAdapter(adapter);
                        comment.setText("");
                        // Sau khi thêm comment, bạn có thể làm gì đó khác nếu cần thiết
                    } else {
                        Toast.makeText(DetailActivity.this,"Không thể thêm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        adapterFoodList= new FoodListAdapter(items);
        recyclerViewFood.setAdapter(adapterFoodList);








//        Comments comment1 = new Comments("Món ăn ngon quá!");
//        cardCommentList.add(comment1);
//
//        Comments comment2 = new Comments("Cảm ơn bạn đã chia sẻ!");
//        cardCommentList.add(comment2);
//
//        Comments comment3 = new Comments("Công thức hay lắm bạn!");
//
//        cardCommentList.add(comment3);

// Thêm các comment khác vào danh sách tương tự

    }


}

