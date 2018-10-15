package com.example.shmuel.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class MyCustomAdapter extends BaseAdapter  {
    private ArrayList<Task> list = null;
    //private ArrayAdapter<Task> TList = new ArrayAdapter<Task>;
    private Context context;
    private SQLiteHelper db;



    public MyCustomAdapter(ArrayList<Task> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Record rec = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.customlayout, null);
            rec = new Record();
            rec.tv =  view.findViewById(R.id.textContact);
            rec.btn = view.findViewById(R.id.delete_btn);
            view.setTag(rec);
            db = new SQLiteHelper(context);
        }
        rec = (Record) view.getTag();
        rec.tv.setText(list.get(position).toString());

        //TextView listItemText =   view.findViewById(R.id.textContact);
        //Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);



        rec.btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

               // list.remove(position);
                String t;
                t = list.get(position).getText();
                db.deleteRow(t);
                list.remove(position);
                notifyDataSetChanged();

            }
        });


        return view;
    }

    class Record{
        TextView tv;
        Button btn;
    }
}