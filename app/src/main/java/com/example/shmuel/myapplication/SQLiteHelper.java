package com.example.shmuel.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="UserTable";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_Email="email";

    public static final String Table_Column_3_Password="password";

    public static final String Table_Column_4_Wing="wing";

    public static final String TABLE_NAME2="TasksTable";

    public static final String TaskTable_Column_ID="id";

    public static final String TaskTable_Column_1_Name="name";

    public static final String TaskTable_Column_2_Task="task";

    public static final String TaskTable_Column_3_Wing="wing";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR, "+Table_Column_4_Wing+")";
        String CREATE_Task_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME2+" ("+TaskTable_Column_ID+" INTEGER PRIMARY KEY, "+TaskTable_Column_1_Name+" VARCHAR, "+TaskTable_Column_2_Task+" VARCHAR, "+TaskTable_Column_3_Wing+")";
        database.execSQL(CREATE_TABLE);
        database.execSQL(CREATE_Task_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        onCreate(db);

    }
    public Cursor getWing(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //email = email.replace("@","\n@");
        String sql = "SELECT " + Table_Column_4_Wing +  " FROM " + TABLE_NAME + " WHERE email = '" + email + "'"  ;
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public Cursor getWname(String email){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        //email = email.replace("@","\n@");
        String sql = "SELECT " + Table_Column_1_Name +  " FROM " + TABLE_NAME + " WHERE email = '" + email + "'"  ;
        return sqLiteDatabase.rawQuery(sql, null);
    }

    public Cursor getTasks(String wing) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT " + TaskTable_Column_2_Task + "," + TaskTable_Column_1_Name +  " FROM " + TABLE_NAME2 + " WHERE wing = '" + wing + "';" ;
        return sqLiteDatabase.rawQuery(sql, null);
    }
    public void deleteRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE "+ TaskTable_Column_2_Task+"='"+value+"'");
        db.close();
    }
}