package com.vincent.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private List<Entity> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.rlv);
        adapter = new MainAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(1));//item之间的间隔
        recyclerView.getItemAnimator().setChangeDuration(500);
        recyclerView.getItemAnimator().setMoveDuration(500);
        adapter.setHeadClickListener(new HeadListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"头部",Toast.LENGTH_LONG).show();
            }
        });

        adapter.setOtherListener(new OtherListener() {
            @Override
            public void onItemClick(View view, int position) {
                Entity entity = data.get(position-1);//剪掉头部的位置
                Toast.makeText(MainActivity.this,entity.title,Toast.LENGTH_LONG).show();
            }
        });

        adapter.setButtomListener(new ButtomListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,"尾部",Toast.LENGTH_LONG).show();
            }
        });

        Entity entity1 = new Entity("fsfs","Fsfs");
        Entity entity2 = new Entity("232","Fsfs");
        Entity entity3 = new Entity("sffsfsf","Fsfs");
        Entity entity4 = new Entity("fsfsff","Fsfs");
        Entity entity5 = new Entity("fsffsf","Fsfs");
        Entity entity6 = new Entity("fsaaffsf","Fsfs");
        Entity entity7 = new Entity("fsfsrrf","Fsfs");
        Entity entity8= new Entity("fsfsssf","Fsfs");
        Entity entity9 = new Entity("fsfsssfssf","Fsfs");
        Entity entity10= new Entity("fsffsfsf","Fsfs");

        data.add(entity1);
        data.add(entity2);
        data.add(entity3);
        data.add(entity4);
        data.add(entity5);
        data.add(entity6);
        data.add(entity7);
        data.add(entity8);
        data.add(entity9);
        data.add(entity10);

        adapter.setData(data);
        recyclerView.setAdapter(adapter);

    }
}
