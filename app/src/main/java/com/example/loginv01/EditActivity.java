package com.example.loginv01;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ImageView back,save;
        /*
            接收传回的数据
         */
        Intent intent1 = getIntent();
        String statue = intent1.getStringExtra("statue");
        Integer id = 0;
        if (statue.equals("1"))
        {
            id = Integer.valueOf(intent1.getStringExtra("id"));
            String title = intent1.getStringExtra("title");
            EditText editText = findViewById(R.id.edit_conect);
            //String Text = DB_con.query()
            editText.setText(DB_con.query(this,title));
            //在编辑栏展现内容
        }
//        EditText editText;
        //返回跳转
        back = findViewById(R.id.back_view);    //查找控件
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, notepadActivity.class);   //创建
                startActivity(intent);
            }
        });

//        editText = findViewById(R.id.edit_conect);
//        String text = editText.getText().toString();
        //保存跳转
        save = findViewById(R.id.save_view);    //查找保存
        Integer finalId = id;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText editText;
                String title;
                editText = findViewById(R.id.edit_conect);
                String text = editText.getText().toString();
                if(text.isEmpty())  //判空
                {
                    Toast toast = Toast.makeText(EditActivity.this,"请输入有效内容",Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
                    toast.show();//弹出提示
                }
                else
                {
                    if(text.length() < 5)
                    {
                        title = text;
                    }
                    else
                    {
                        title = text.substring(0,5);
                    }
                    if (statue.equals("0"))
                    {
                        DB_con.insert(EditActivity.this,title,text);    //数据库没有，直接插入
                    }
                    else
                    {
                        DB_con.edit(EditActivity.this, finalId,text,title);
                        Toast.makeText(EditActivity.this,"finalId:"+finalId,Toast.LENGTH_SHORT).show();
                    }
                    Toast toast = Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT);
                    toast.show();//弹出提示
                    Toast.makeText(EditActivity.this,"状态值："+statue,Toast.LENGTH_SHORT).show();
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置

                    //跳转
                    Intent intent = new Intent(EditActivity.this, notepadActivity.class);   //创建
                    startActivity(intent);
                }

            }
        });

    }
}