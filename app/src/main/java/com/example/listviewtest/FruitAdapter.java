package com.example.listviewtest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit>{

    private int resourceId;
    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    //当前的适配器会让我们的ListView运行效率很低，
    //因为我们要不断的创建新的View，不断的创建新的ImageView和TextView对象
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Fruit fruit = getItem(position);
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
//
//        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
//        TextView textView = (TextView)view.findViewById(R.id.textView);
//
//        imageView.setImageResource(fruit.getImageId());
//        textView.setText(fruit.getName());
//        return view;
//    }



    //为此我们提供了一种新的方法，
    //我们用convertView 缓冲已经创建好的view
    //用ViewHolder对创建好的控件实例进行缓冲

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position);
        View view;
        ViewHolder viewHolder;

        //getView方法中有一个convertView参数，这个参数用于将之前加载好的布局进行缓冲
        //现在我们在getView方法中对convertView进行判断
        //如果convertView为null的话，则使用LayoutInflater去加载布局
        //如果convertView不为null的话，则使用convertView去加载布局

        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView)view.findViewById(R.id.imageView);
            viewHolder.textView = (TextView)view.findViewById(R.id.textView);

            //将ViewHolder存储在View中
            view.setTag(viewHolder);
        }
        else{
            view = convertView;
            //重新获取viewolderH
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.imageView.setImageResource(fruit.getImageId());
        viewHolder.textView.setText(fruit.getName());

        return view;
    }

    //新增一个内部类ViewHolder，用于对控件实例进行缓冲
    class ViewHolder{
        ImageView imageView;
        TextView textView;
    }
}
