package com.example.tv_subscription_app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tv_subscription_app.Channel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class Channel{
    public String name;
    public double cost;

    public Channel(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

public class SelectChannelActivity extends AppCompatActivity {

    HashMap<String, List<Channel>> channels;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_channel);
        setUp();
        ArrayList<Channel> selected = new ArrayList<>();
        ArrayList<String> fromIntent = getIntent().getStringArrayListExtra("Selected");

        Button proceed = findViewById(R.id.proceed);
        proceed.setOnClickListener(v->{
            finish();
            startActivity(new Intent(this, ShowCostActivity.class));
        });

        for(String k: fromIntent){
            Log.d("Shreyash: K ", k);
            for(Channel i: channels.get(k)){
                Log.d("Shreyash: ", i.name);
            }
            selected.addAll(channels.get(k));
        }
        Channel[] selectedArray = new Channel[selected.size()];
        int index = 0;
        for(Channel k: selected){
            selectedArray[index] = k;
            index++;
        }
        Log.d("SHREYASH: ", Arrays.toString(selectedArray));
        ChannelAdapter channelAdapter = new ChannelAdapter(this, R.layout.channel_item, selectedArray);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(channelAdapter);
        Common.chosen = new HashSet<>();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Channel channel = selectedArray[i];
            Log.d("ClickedUI", channel.name);
            if(Common.chosen.contains(channel)){
                Common.chosen.remove(channel);
                Common.cost -= channel.cost;
                view.setBackgroundColor(Color.parseColor("#C90A0B0C"));
            } else {
                Common.chosen.add(channel);
                view.setBackgroundColor(Color.parseColor("#DFDF1E"));
                Common.cost += channel.cost;
            }
            print();
        });
//        Iterator hmIterator = channels.entrySet().iterator();
//
//        while(hmIterator.hasNext()){
//            Map.Entry me = (Map.Entry) hmIterator.next();
//            StringBuilder stringBuilder = new StringBuilder();
//            List<String> k = (List<String>) me.getValue();
//            stringBuilder.append("channels.putIfAbsent(\"" + (String)me.getKey() + " \", Arrays.asList(");
//            Random r = new Random();
//            for(String i: k){
//                stringBuilder.append("new Channel(" + i + ", " + r.nextInt(100 ) + "), ");
//            }
//            stringBuilder.append(")");
//            Log.d("Shreyash: ", stringBuilder.toString());
//        }
    }

    public void print(){
        for (Channel k : Common.chosen) {
            Log.d("Clicked SET: ", k.name);
        }
    }

    @SuppressLint("NewApi")
    public void setUp(){
        channels = new HashMap<>();
//        channels.putIfAbsent("Entertainment", Arrays.asList("UTV", "Sony Marathi", "Set Max", "Star plus"));
//        channels.putIfAbsent("News", Arrays.asList("Zee news", "Republic Bharat", "India TV", "News Nation", "News 18 India"));
//        channels.putIfAbsent("Comedy", Arrays.asList("Sahar one", "Sony TV", "Sony SAB"));
//        channels.putIfAbsent("Infotainment", Arrays.asList("National Geographic", "Discovery", "Sony BBC Earth", "History TV18"));
        channels.putIfAbsent("Infotainment", Arrays.asList(new Channel("National Geographic", 28), new Channel("Discovery", 99), new Channel("Sony BBC Earth", 49), new Channel("History TV18", 54)));
        channels.putIfAbsent("Entertainment", Arrays.asList(new Channel("UTV", 25), new Channel("Sony Marathi", 93), new Channel("Set Max", 83), new Channel("Star plus", 41)));
        channels.putIfAbsent("News", Arrays.asList(new Channel("Zee news", 0.5), new Channel("Republic Bharat", 41), new Channel("India TV", 0), new Channel("News Nation", 50), new Channel("News 18 India", 38)));
        channels.putIfAbsent("Comedy", Arrays.asList(new Channel("Sahar one", 58), new Channel("Sony TV", 44), new Channel("Sony SAB", 49)));

    }
}