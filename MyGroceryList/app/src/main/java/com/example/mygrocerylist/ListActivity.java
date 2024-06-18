package com.example.mygrocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class ListActivity extends AppCompatActivity {
    private ImageView imageViewAdd;
    private ListView listView;
    private DBHelper dbHelper;
    private ListViewAdapter adapter;
    private ArrayList<HashMap<String, String>> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        imageViewAdd = findViewById(R.id.imageViewAdd);
        listView = findViewById(R.id.listView);
        dbHelper = new DBHelper(this);
        shoppingList = dbHelper.getAllLists();

        adapter = new ListViewAdapter(this, shoppingList);
        listView.setAdapter(adapter);

        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> item = shoppingList.get(position);
                Intent intent = new Intent(ListActivity.this, ListDetailActivity.class);
                intent.putExtra("id", item.get("id"));
                intent.putExtra("title", item.get("title"));
                intent.putExtra("content", item.get("content"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shoppingList.clear();
        shoppingList.addAll(dbHelper.getAllLists());
        adapter.notifyDataSetChanged();
    }
}
