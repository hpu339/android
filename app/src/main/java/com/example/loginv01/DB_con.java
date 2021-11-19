package com.example.loginv01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Gravity;
import android.widget.Toast;

import com.example.loginv01.entity.list_data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DB_con {

    //数据库con数据表查询，通过title查询其内容
    static String query(Activity activity, String Title) {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getReadableDatabase();


        String result_con = "";
//        String result_tim = "";
//        String[] arr = new String[2];  //创建长度为2的数组
//        List<String> list = Arrays.asList(arr);
//        List<String> titleList = new ArrayList<String>();
//        // 定义新集合

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from con", null);
            while (cursor.moveToNext()) {
                //移动浮标
                @SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                if (Title.equals(title)) {
                    @SuppressLint("Range") String content = cursor.getString(cursor.getColumnIndex("content"));
                    //@SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
//                    Toast toast = Toast.makeText(MainActivity.this,"密码为："+pw,Toast.LENGTH_SHORT);
//                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
//                    toast.show();//弹出提示
                    result_con = content;  //返回内容
//                    result_tim = time;
                    break;
                } else {

                    result_con = "error";
                }

                //Log.d("嗯嗯","query: _id:"+_id + "accout"+ ac);
            }

            cursor.close();
        }
        db.close();
        return result_con;
    }

//        list.add(result_con);
//        list.add(result_tim);
//        titleList.addAll(list); //将集合中的数据添加到新集合中
//        String[] newArr = titleList.toArray(new String[titleList.size()]);  //将新集合转换成数组
//        return newArr;


    //数据库表con的修改
    static void edit(Activity activity,Integer _id,String content,String title)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getReadableDatabase();
        //对应列表
        String id = String.valueOf(_id);
        if (db.isOpen())
        {
            String sql1 = "update con set content= '"+content+"' where _id="+id;
            db.execSQL(sql1);
            String sql2 = "update con set title= '"+title+"' where _id="+id;
            db.execSQL(sql2);
            Toast.makeText(activity,"修改成功",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(activity,"修改失败",Toast.LENGTH_SHORT).show();
        }
    }

    //数据库库con表遍历
    public static void updata(Activity activity,List<list_data> books)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getReadableDatabase();
        //List<list_data> books = new ArrayList<>();

        if (db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from con",null);
            while(cursor.moveToNext())
            {
                @SuppressLint("Range") String time = cursor.getString(cursor.getColumnIndex("time"));
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
                list_data list = new list_data(title,time);
                books.add(list);
            }
        }
        else
        {
            Toast.makeText(activity,"列表更新函数出现错误",Toast.LENGTH_SHORT).show();
        }

    }

    //插入函数
    static String insert(Activity activity,String title,String content)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取系统时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        //对时间格式化
        Date currentTime = new Date(System.currentTimeMillis());
        if(db.isOpen())
        {
            String sql = "insert into con" + "(title,time,content)values('" + title + "','" + simpleDateFormat.format(currentTime) + "','"+ content + "')";
            //String sql = "insert into ac_pw(accout) values('张三')";
            db.execSQL(sql);
        }
        db.close();
        return "ok";
    }


}
