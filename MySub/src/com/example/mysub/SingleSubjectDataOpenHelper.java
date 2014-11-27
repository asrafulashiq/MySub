package com.example.mysub;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SingleSubjectDataOpenHelper extends SQLiteOpenHelper{

	
	
	private static final int VERSION = 0;
	private static final String CT_TABLE = "ct";
	private static final String MARK = "mark";
	private static final String DATE = "date";
	private String subject_name;
	private SQLiteDatabase dataBase;

	public SingleSubjectDataOpenHelper(Context context, String name) {
		super(context, name, null, VERSION);
		// TODO Auto-generated constructor stub
		this.subject_name = name.toUpperCase();
		this.dataBase = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated methoqd stub
		this.dataBase=db;
		
		String CREATE_CT = "CREATE TABLE IF NOT EXISTS "+CT_TABLE
				+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+MARK+" REAL)";
		
		String DATE_INFO = "CREATE TABLE IF NOT EXISTS "+DATE
				+" INTEGER";
		
		this.dataBase.execSQL(CREATE_CT);
		this.dataBase.execSQL(DATE_INFO);
	  
	}
	
	
	public ArrayList<Double> getCtMarks(){
		
		Cursor cursor = this.dataBase.rawQuery("SELECT "+CT_TABLE+"."+MARK+" FROM "+CT_TABLE, null);
	
		ArrayList<Double> marks = new ArrayList<Double>();
		
		if(cursor.moveToFirst()){
			
			do {
				marks.add(cursor.getDouble(1));
				
			} while (cursor.moveToNext());
			
		}
		
		return marks;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
	}
	
	

	
}
