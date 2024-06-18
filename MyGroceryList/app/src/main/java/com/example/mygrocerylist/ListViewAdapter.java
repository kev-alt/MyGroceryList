package com.example.mygrocerylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private DBHelper dbHelper;

    public ListViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.data = data;
        this.dbHelper = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView title = convertView.findViewById(R.id.list_item_title);
        ImageView delete = convertView.findViewById(R.id.list_item_delete);

        final HashMap<String, String> item = data.get(position);
        title.setText(item.get("title"));

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteList(item.get("id"));
                data.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Your shopping list has been deleted", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
