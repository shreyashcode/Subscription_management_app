package com.example.tv_subscription_app;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.time.temporal.Temporal;
import java.util.HashSet;

public class ChannelAdapter extends ArrayAdapter<Channel> {

    public ChannelAdapter(@NonNull Context context, int resource, @NonNull Channel[] channels) {
        super(context, resource, channels);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Channel channel = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.channel_item, null);
            viewHolder = new ViewHolder();
            viewHolder.cName = view.findViewById(R.id.name);
            viewHolder.cCost = view.findViewById(R.id.cost);
            view.setTag(viewHolder); //Store ViewHolder in View
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();// Get ViewHolder again
        }
        if(Common.chosen == null){
            Common.chosen = new HashSet<>();
        }
        if(Common.chosen.contains(channel)){
            view.setBackgroundColor(Color.parseColor("#DFDF1E"));
        } else {
            view.setBackgroundColor(Color.parseColor("#C90A0B0C"));
        }
        print();
        viewHolder.cName.setText(channel.name);
        viewHolder.cCost.setText(channel.cost + "");
//        viewHolder.fruitImage.setImageResource(fruit.getImageId());
//        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder {
        TextView cName;
        TextView cCost;
    }
    public void print(){
        for (Channel k : Common.chosen) {
            Log.d("Clicked SET AD*******:", k.name);
        }
    }
}
