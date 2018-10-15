package com.example.shmuel.myapplication;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    String EmailHolder, WingHolder;
    TextView Email, wingT;
    Button LogOUT ;
    ListView list;
    private SQLiteHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        wingT = (TextView) findViewById(R.id.wingtext);
        Email = (TextView)findViewById(R.id.textView1);
        LogOUT = (Button)findViewById(R.id.button1);
        list = (ListView) findViewById(R.id.listView1);
        Intent intent = getIntent();

        EmailHolder = intent.getStringExtra(MainActivity.UserEmail);


       // Email.setText(Email.getText().toString()+ EmailHolder);
        db = new SQLiteHelper(this);
        getWingNumber();
        getAllTasks();
        getWorkerName();
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

                Toast.makeText(DashboardActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void getWingNumber()
    {
        String wing;
        Cursor cursor = db.getWing(EmailHolder);
        if (cursor != null)
            cursor.moveToFirst();
        wing = cursor.getString(0);
        WingHolder = wing;
       // wingT.setText(wing);
    }
    private void getWorkerName()
    {
        String name;
        Cursor cursor = db.getWname(EmailHolder);
        if (cursor != null)
            cursor.moveToFirst();
        name = cursor.getString(0);
        //WingHolder = wing;
        Email.setText(Email.getText().toString()+ name);
    }

    private void getAllTasks()
    {
        Cursor cursor = db.getTasks(WingHolder);
        List<Task> tasks = new ArrayList<Task>();
        Task t = null;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                t = new Task();
                t.setName(cursor.getString(cursor.getColumnIndex("name")));
                t.setText(cursor.getString(cursor.getColumnIndex("task")));
                tasks.add(t);
            } while (cursor.moveToNext());
        }

        //ArrayAdapter adpater = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, tasks);
        //list.setAdapter(adpater);

        ArrayList<Task> arrayTasks = new ArrayList<Task>(tasks);
        MyCustomAdapter c_adapter = new MyCustomAdapter(arrayTasks, this);
        list.setAdapter(c_adapter);
    }

}