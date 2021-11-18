package com.example.loginv01.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginv01.DB_con;
import com.example.loginv01.MainActivity;
import com.example.loginv01.R;
import com.example.loginv01.adapter.listAdapter;
import com.example.loginv01.databinding.FragmentHomeBinding;
import com.example.loginv01.entity.list_data;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    //创建ListView控件
    private ListView mylistview1;
    private ListView lv;
    //创建适配器
    private listAdapter myAdapter; //定义适配器
    //定义数组，用于存储记事本数据
    private String[] titles = {"test1","test2","test3"};
    private String[] times = {"2021.11.01","2021.11.02","2021.11.03"};

    //定义一个列表作为数据源
    private List<list_data> books = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        //创建布局文件
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final ListView mylistview = binding.mylistview;
        //final TextView textView = binding.textHome;
        //mylistview1 = mylistview.findViewById(R.id.mylistview);
        initDataBooks();
        //初始化控件
        mylistview1 = mylistview.findViewById(R.id.mylistview);
        //创建适配器（参数1：数据源，2：上下文）
        //Toast.makeText(getActivity(),"前面"+books.size(),Toast.LENGTH_SHORT).show();
        myAdapter = new listAdapter(books, getActivity());
        //Toast.makeText(getActivity(),"后面"+books.size(),Toast.LENGTH_SHORT).show();
        //books = null;//清空列表防止重复加载
        mylistview.setAdapter(myAdapter);   //加载适配器


//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                initDataBooks();
//
////                textView.setText(s);
//                //初始化控件
//                mylistview1 = mylistview.findViewById(R.id.mylistview);
//                //创建适配器（参数1：数据源，2：上下文）
//                myAdapter = new listAdapter(books, getActivity());
//                mylistview.setAdapter(myAdapter);   //加载适配器
//
//            }
//
//        });
//        //点击监听
//        mylistview1.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
//            {
//                TextView tv = (TextView)view;
//                Toast.makeText(getActivity(), tv.getText()+"", Toast.LENGTH_SHORT).show();
//                //弹窗
//            }
//        });

        return root;
    }

    //初始化数据
    private void initDataBooks()
    {
        books.clear();//全部清除，防止累加
//        for (int i = 0; i< titles.length;i++)
//        {
//            //新建list_data对象，存放标题和时间
//            list_data list = new list_data(titles[i],times[i]);
//            //将数据加入到数据列表中
//            //books.clear();//全部清除，防止累加
//            books.add(list);
//            //Toast.makeText(getActivity(),"内容"+books.get(i),Toast.LENGTH_SHORT).show();
//
//        }
        DB_con.updata(getActivity(),books);

        Toast.makeText(getActivity(),"长度"+books.size(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}