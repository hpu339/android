package com.example.loginv01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB_con {

    //数据库con数据表查询，返回数组
    static String[] query(Activity activity, String Title)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getReadableDatabase();


        String result_con = "";
        String result_tim = "";
        String[] arr = new String[2];  //创建长度为2的数组
        List<String> list = Arrays.asList(arr);
        List<String> titleList = new ArrayList<String>();
        // 定义新集合

        if(db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from con",null);
            while(cursor.moveToNext())
            {
                @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                if(Title.equals(title))
                {
                    @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                    @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
//                    Toast toast = Toast.makeText(MainActivity.this,"密码为："+pw,Toast.LENGTH_SHORT);
//                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
//                    toast.show();//弹出提示
                    result_con = content;  //返回内容
                    result_tim = time;
                    break;
                }
                else
                {
//                    Toast toast = Toast.makeText(MainActivity.this,"免密失败，请先注册账号",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
//                    toast.show();//弹出提示
                    result_con = "error";
                }

                //Log.d("嗯嗯","query: _id:"+_id + "accout"+ ac);
            }

            cursor.close();
        }
        db.close();

        list.add(result_con);
        list.add(result_tim);
        titleList.addAll(list); //将集合中的数据添加到新集合中
        String[] newArr = titleList.toArray(new String[titleList.size()]);  //将新集合转换成数组
        return newArr;
    }

    //插入函数
    static String insert(Activity activity,String title,String content)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取系统时间
        Date currentTime = Calendar.getInstance().getTime();

        if(db.isOpen())
        {
            String sql = "insert into con" + "(title,time,content)values('" + title + "','" + currentTime + "','"+ content + "')";
            //String sql = "insert into ac_pw(accout) values('张三')";
            db.execSQL(sql);
        }
        db.close();
        return "ok";
    }
}
