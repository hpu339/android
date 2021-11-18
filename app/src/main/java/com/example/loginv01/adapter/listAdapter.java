package com.example.loginv01.adapter;

//自定义适配器

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.loginv01.R;
import com.example.loginv01.entity.list_data;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends BaseAdapter {

    //列表数据
    private List<list_data> pdata = new ArrayList<list_data>();

    //上下文
    private Context context;

    //构造方法
    public listAdapter(List<list_data> pdata,Context context)
    {
        this.context = context;
        this.pdata = pdata;

    }

    @Override

    //获取列表项个数
    public int getCount() {
        return pdata.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    //返回itemid，
    public long getItemId(int position) {
        return position;    //返回数据项在列表中的索引
    }

    //优化一下ListView
    //定义一个viewhold静态类
    static class ViewHolder{
        //定义属性，对应的是列表项数据
        public TextView title,time;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //定义一个ViewHold对象
        ViewHolder holder;
        //判断convertView是否为空，对应的列表项
        if (convertView == null)
        {
            //新建
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.bookitem,parent,false);

            holder.title = (TextView) convertView.findViewById(R.id.item_title);
            holder.time = (TextView) convertView.findViewById(R.id.item_time);

            convertView.setTag(holder);
        }
        else
        {
            //复用列表项
            holder = (ViewHolder) convertView.getTag();

        }
        //设置列表项数据
        holder.title.setText(pdata.get(position).getTitle());
        holder.time.setText(pdata.get(position).getTime());


        return convertView;
    }
}
