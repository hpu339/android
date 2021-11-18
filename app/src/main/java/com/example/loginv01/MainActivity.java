package com.example.loginv01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView registe;
    private Button login;
    private EditText ac,pw;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //更改状态栏背景颜色
        Change_color.translucentStatusBar(MainActivity.this,false);
        //改变状态栏字体颜色
        Change_color.setAndroidNativeLightStatusBar(MainActivity.this,true);
        //Change_color.setStatusBarColor(MainActivity.this,);
        //创建数据库
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();

        registe = (TextView) findViewById(R.id.tv_register);    //查找控件
        registe.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, regeisteActiivity.class);   //创建
                startActivity(intent);
            }
        });

        login = (Button) findViewById(R.id.btn_login);
        ac = (EditText) findViewById(R.id.login_account);

        pw = (EditText) findViewById(R.id.login_pw);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String accout = ac.getText().toString().trim();
                String password = pw.getText().toString().trim();
                if(accout.equals(""))   //账号为空
                {
                    Toast toast = Toast.makeText(MainActivity.this,"请输入账号",Toast.LENGTH_SHORT);
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
                    toast.show();//弹出提示
                }
                else if (password.equals(""))   //免密登录
                {
                    String pw = DB_ac.query(MainActivity.this,accout);
                    //Toast toast1 = Toast.makeText(MainActivity.this,"text"+pw,Toast.LENGTH_SHORT);
                    //通过数据库查找密码
                    Toast toast;//弹出提示
                        //设置位置
                    if(pw.equals(""))   //没有查询到密码返回空
                    {
                        toast = Toast.makeText(MainActivity.this, "免密登录失败，请注册账号", Toast.LENGTH_SHORT);
                    }
                    else    //免密登录成功
                    {
                        //successful
                        toast = Toast.makeText(MainActivity.this, "免密登录成功！", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(MainActivity.this, notepadActivity.class);   //创建
                        startActivity(intent);
                    }
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
                    toast.show();//弹出提示
                }
                else    //有账号有密码
                {
                    String pw = DB_ac.query(MainActivity.this,accout);
                    Toast toast;
                    if(password.equals(pw)) //密码匹配成功
                    {
                        //successf
                        toast = Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(MainActivity.this, notepadActivity.class);   //创建
                        startActivity(intent);
                    }
                    else        //密码或账号错误
                    {
                        toast = Toast.makeText(MainActivity.this, "账号或密码错误，请重新输入", Toast.LENGTH_SHORT);
                    }
                    toast.show();
                }
            }
        });

    }

//    //数据库查找函数
//    public String query(String accout)
//    {
//        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
//        //datebase 文件的创建靠下面这句话
//        SQLiteDatabase db = helper.getReadableDatabase();
//        String result = "0";
//        if(db.isOpen())
//        {
//            Cursor cursor = db.rawQuery("select * from ac_pw",null);
//            while(cursor.moveToNext())
//            {
//                @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex("_id"));
//                @SuppressLint("Range") String ac = cursor.getString(cursor.getColumnIndex("accout"));
//                if(ac.equals(accout))
//                {
//                    @SuppressLint("Range") String pw = cursor.getString(cursor.getColumnIndex("password"));
////                    Toast toast = Toast.makeText(MainActivity.this,"密码为："+pw,Toast.LENGTH_SHORT);
////                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
////                    toast.show();//弹出提示
//                    result = pw;  //返回密码
//                    break;
//                }
//                else
//                {
////                    Toast toast = Toast.makeText(MainActivity.this,"免密失败，请先注册账号",Toast.LENGTH_SHORT);
////                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
////                    toast.show();//弹出提示
//                    result = "0";
//                }
//
//                //Log.d("嗯嗯","query: _id:"+_id + "accout"+ ac);
//            }
//
//            cursor.close();
//        }
//        db.close();
//        return result;
//    }

//    static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
//        Window window = activity.getWindow();
//        //添加Flag把状态栏设为可绘制模式
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        if (hideStatusBarBackground) {
//            //如果为全透明模式，取消设置Window半透明的Flag
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //设置状态栏为透明
//            window.setStatusBarColor(Color.TRANSPARENT);
//            //设置window的状态栏不可见
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//        } else {
//            //如果为半透明模式，添加设置Window半透明的Flag
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //设置系统状态栏处于可见状态
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
//        }
////        //view不根据系统窗口来调整自己的布局
//        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            ViewCompat.setFitsSystemWindows(mChildView, false);
//            ViewCompat.requestApplyInsets(mChildView);
//        }
//    }

//    public void query(View view)
//    {
//        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(this);
//        //datebase 文件的创建靠下面这句话
//        SQLiteDatabase db = helper.getReadableDatabase();
//
//        if(db.isOpen())     //数据库打开
//        {
//            getString()
//            db.rawQuery("select")
//        }
//
//    }
}