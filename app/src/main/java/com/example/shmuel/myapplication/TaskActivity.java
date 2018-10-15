package com.example.shmuel.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TaskActivity extends AppCompatActivity {


    EditText Name, Task ;

    Button sendT;
    String NameHolder, TaskHolder, wingType;
    Boolean EditTextEmptyHolder;
    Spinner wingSpinner;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task);



    sendT = (Button)findViewById(R.id.buttonTask);
    wingSpinner = (Spinner) findViewById(R.id.SpinnerWingsType);
    Task = (EditText)findViewById(R.id.editTask);
    Name = (EditText)findViewById(R.id.editName);

    sqLiteHelper = new SQLiteHelper(this);


        sendT.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            SQLiteDataBaseBuild();


            SQLiteTableBuild();


            CheckEditTextStatus();



            EmptyEditTextAfterDataInsert();


        }
    });

}


    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }


    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME2 + "(" + SQLiteHelper.TaskTable_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.TaskTable_Column_2_Task  + " VARCHAR ," + SQLiteHelper.TaskTable_Column_3_Wing + " VARCHAR);");

    }


    public void InsertDataIntoSQLiteDatabase(){


        if(EditTextEmptyHolder == true)
        {


            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME2+" (name,task,wing) VALUES('"+NameHolder+"', '"+TaskHolder+"' , '"+ wingType +"');";

            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);


            sqLiteDatabaseObj.close();


            Toast.makeText(TaskActivity.this,"Task Add Successfully", Toast.LENGTH_LONG).show();

        }

        else {


            Toast.makeText(TaskActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }


    public void EmptyEditTextAfterDataInsert(){

        Name.getText().clear();

        Task.getText().clear();


    }


    public void CheckEditTextStatus(){


        NameHolder = Name.getText().toString() ;
        TaskHolder = Task.getText().toString();
        wingType = wingSpinner.getSelectedItem().toString();
        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(TaskHolder) ){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
        InsertDataIntoSQLiteDatabase();
    }




}

