package com.example.mysub;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SingleSubjectDataBase extends SQLiteOpenHelper{

	private static final int VERSION = 1;
	private String subjectName;
	private SQLiteDatabase dataBase;
	private String TABLE_NAME;
	
	public SingleSubjectDataBase(Context context, String name) {
		super(context, name, null, VERSION);
		// TODO Auto-generated constructor stub
		this.subjectName=name;
		this.dataBase = this.getWritableDatabase();
		this.TABLE_NAME = name;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		this.dataBase.execSQL("CREATE TABLE "+TABLE_NAME
		
				);
	}

	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		this.dataBase.execSQL("DROP TABEL IF EXISTS "+TABLE_NAME);
	}

}
