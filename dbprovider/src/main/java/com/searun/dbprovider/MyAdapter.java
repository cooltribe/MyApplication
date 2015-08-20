package com.searun.dbprovider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 陈玉柱 on 2015/8/18.
 */
public class MyAdapter extends BaseAdapter {
    List<Person> personList;
    Context context;
    LayoutInflater inflater;

    public MyAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_item,parent,false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.tv_phone);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(personList.get(position).getName());
        viewHolder.phone.setText(personList.get(position).getPhone());
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView phone;
    }
}
