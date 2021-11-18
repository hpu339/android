package com.example.loginv01;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DB_ac {
    //数据库账户信息表查找函数
    //如果没有查询到则返回“0”，否则返回对应的密码
    static String query(Activity activity, String accout)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getReadableDatabase();
        String result = "0";
        if(db.isOpen())
        {
            Cursor cursor = db.rawQuery("select * from ac_pw",null);
            while(cursor.moveToNext())
            {
                //@SuppressLint("Range") int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                @SuppressLint("Range") String ac = cursor.getString(cursor.getColumnIndex("accout"));
                if(ac.equals(accout))
                {
//                    Toast toast2 = Toast.makeText(activity,"进入"+ac,Toast.LENGTH_SHORT);
//                    toast2.show();
                    @SuppressLint("Range") String pw = cursor.getString(cursor.getColumnIndex("password"));
                    //Toast toast = Toast.makeText(activity,"密码为："+pw,Toast.LENGTH_SHORT);
//                    Toast toast = Toast.makeText(activity,"text"+pw,Toast.LENGTH_SHORT);
                    System.out.println(pw);
                    //toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
//                    toast.show();//弹出提示
                    result = pw;  //返回密码
                    break;
                }
                else
                {
//                    Toast toast = Toast.makeText(MainActivity.this,"免密失败，请先注册账号",Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.TOP | Gravity.LEFT, 200, 500);//设置位置
//                    toast.show();//弹出提示
                    result = "";    //没有查询到密码返回空

                }

                //Log.d("嗯嗯","query: _id:"+_id + "accout"+ ac);
            }

            cursor.close();
        }
        db.close();
        return result;
    }

    //插入函数对ac_pw表进行
    static String insert(Activity activity,String accout,String password)
    {
        SQLiteOpenHelper helper = MySqliteOpenHelper.getInstance(activity);
        //datebase 文件的创建靠下面这句话
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen())
        {
            String sql = "insert into ac_pw" + "(accout,password)values('" + accout + "','" + password + "')";
            //String sql = "insert into ac_pw(accout) values('张三')";
            db.execSQL(sql);
        }
        db.close();
        return "ok";
    }
}
