package com.example.mygrocerylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ListDetailActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextContent;
    private ImageView imageViewSave, imageViewDelete;
    private DBHelper dbHelper;
    private String listId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        imageViewSave = findViewById(R.id.imageViewSave);
        imageViewDelete = findViewById(R.id.imageViewDelete);
        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        listId = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");

        editTextTitle.setText(title);
        editTextContent.setText(content);

        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = editTextTitle.getText().toString();
                String newContent = editTextContent.getText().toString();
                dbHelper.updateList(listId, newTitle, newContent);
                Toast.makeText(ListDetailActivity.this, "Your shopping list has been updated", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteList(listId);
                Toast.makeText(ListDetailActivity.this, "Your shopping list has been deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
