package com.kodulf.picasookhttpretrofitdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 15-12-29.
 */
public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> items;

    public ItemAdapter(Context context) {
        this.context = context;
        items=new ArrayList<Item>();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<Item> list){
        items.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        Item item = items.get(position);
        ViewHolder holder = (ViewHolder)convertView.getTag();
        holder.name.setText(item.getUserName());
        holder.content.setText(item.getContent());
        Log.d("151229MY", "postion="+position+" Name="+item.getUserName()+" content="+item.getContent());

        return convertView;
    }

    private static class ViewHolder{
        private ImageView icon;
        private ImageView image;
        private TextView content;
        private TextView name;
        public ViewHolder(View itemView){
            icon =(ImageView)itemView.findViewById(R.id.user_icon);
            image =(ImageView)itemView.findViewById(R.id.image);
            content =(TextView)itemView.findViewById(R.id.content);
            name=(TextView)itemView.findViewById(R.id.user_name);
        }
    }
}
