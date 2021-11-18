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
//        EditText editText;

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
        save = findViewById(R.id.save_view);    //查找保存
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText editText;
                String title,status;
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
                    DB_con.insert(EditActivity.this,title,text);
                    Toast toast = Toast.makeText(EditActivity.this,"保存成功",Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
                    toast.show();//弹出提示

                    //跳转
                    Intent intent = new Intent(EditActivity.this, notepadActivity.class);   //创建
                    startActivity(intent);
                }

            }
        });

    }
}