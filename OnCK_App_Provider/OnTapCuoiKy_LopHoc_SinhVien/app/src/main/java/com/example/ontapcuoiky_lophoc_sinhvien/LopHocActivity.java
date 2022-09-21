package com.example.ontapcuoiky_lophoc_sinhvien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LopHocActivity extends AppCompatActivity {
    String uri="content://com.example.ontapcuoiky_lophoc_sinhvien/LopHoc";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lop_hoc);

        EditText etMaLop=findViewById(R.id.etMaLop);
        EditText etTenLop=findViewById(R.id.etTenLop);

        //Thoát
        Button btThoat=findViewById(R.id.btThoatLP);
        btThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //Insert
        Button btSave=findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int maLop=Integer.valueOf(etMaLop.getText().toString());
                String tenLop=etTenLop.getText().toString();
                LopHoc lopHoc=new LopHoc(maLop,tenLop);

                ContentValues values=new ContentValues();
                values.put("malop",lopHoc.getMaLop());
                values.put("tenlop",lopHoc.getTenLop());
                Uri lophoc=Uri.parse(uri);
                Log.d("lophoc", lophoc.toString());
                Uri insert_uri=getContentResolver().insert(lophoc,values);
                Toast.makeText(getApplicationContext(),"Lưu thành công",Toast.LENGTH_SHORT).show();
            }
        });
        //Select
        GridView gridViewLopHocs = findViewById(R.id.gridview_LH);
        Button btn_Select = findViewById(R.id.button_Select);
        btn_Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> string_list = new ArrayList<>();
                Uri lophoc = Uri.parse(uri);
                Cursor cursor = getContentResolver().query(lophoc, null, null,null,"malop");//sap xep theo name
                if(cursor != null){
                    cursor.moveToFirst();
                    do{
                        string_list.add(cursor.getInt(0)+"");//id
                        string_list.add(cursor.getString(1));
                    }while(cursor.moveToNext());
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(LopHocActivity.this,
                            android.R.layout.simple_list_item_1, string_list);
                    gridViewLopHocs.setAdapter(adapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Không có kết quả", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Search
        Button btSearch=findViewById(R.id.btTimKiem);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<LopHoc> lopHocs=new ArrayList<>();
                Uri lophoc=Uri.parse("content://com.example.ontapcuoiky_lophoc_sinhvien/LopHoc");
                Cursor cursor=getContentResolver().query(lophoc,null,null,null,"malop");
                if(cursor!=null){
                    cursor.moveToNext();
                    while (!cursor.isAfterLast()){
                        lopHocs.add(new LopHoc(cursor.getInt(0),cursor.getString(1)));
                        cursor.moveToNext();
                    }
                }
            }
        });

        //Delete
        Button btDelete=findViewById(R.id.btDelete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri lophoc=Uri.parse("content://com.example.ontapcuoiky_lophoc_sinhvien/LopHoc");
                int maLop=Integer.valueOf(etMaLop.getText().toString());
                int del=getContentResolver().delete(lophoc,"malop=?",new String[]{etMaLop.getText().toString()});
                if(del>0){
                    Toast.makeText(getApplicationContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Update
        Button btUpdate=findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri lophoc=Uri.parse("content://com.example.ontapthicuoiky1/LopHoc");
                ContentValues values=new ContentValues();
                values.put("malop",etMaLop.getText().toString());
                values.put("tenlop",etTenLop.getText().toString());
                int update=getContentResolver().update(lophoc,values,"malop=?",new String[]{etMaLop.getText().toString()});
                if(update>0){
                    Toast.makeText(getApplicationContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}