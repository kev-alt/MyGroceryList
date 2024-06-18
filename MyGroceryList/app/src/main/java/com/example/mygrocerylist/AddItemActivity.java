package com.example.mygrocerylist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    EditText editTextTitle;
    EditText editTextContent;
    ImageView imageViewSave;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        imageViewSave = findViewById(R.id.imageViewSave);
        dbHelper = new DBHelper(this);

        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString();
                String content = editTextContent.getText().toString();
                if (title.isEmpty() || content.isEmpty()) {
                    makeToast("Please fill in all fields.");
                } else {
                    saveItem(title, content);
                    makeToast("Your shopping list has been saved.");
                    Intent intent = new Intent(AddItemActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void saveItem(String title, String content) {
        dbHelper.insertList(title, content);
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

