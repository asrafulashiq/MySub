package com.example.mysub;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataOpenHelper extends SQLiteOpenHelper{
	
	

	private static final String DATABASE_NAME = "mysub.db";
	private static final int DATABASE_VERSION = 2;
	private static final String NEW_SUBJECT = "new_subject";
	private static final String DATABASE_TABLE = "my_subject";
	private SQLiteDatabase dataBase;
	private static final String SUBJECT = "subject";
	private static final String DATE = "date";

	public Context context;
	//public int subjectCount=0;
	
	public DataOpenHelper(Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		this.context = context;
		dataBase = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase dataBase) {
		// TODO Auto-generated method stub
		this.dataBase = dataBase;
		//this.dataBase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);

		dataBase.execSQL( 
				"create table if not exists my_subject(id integer primary key,"+
		SUBJECT+" text)"
				
				);
		
		
	}
	
	public long addNewSubject(String sub){
		//this.dataBase = this.getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put(SUBJECT, sub);
		
		
		return this.dataBase.insert(DATABASE_TABLE, null, content);
		
	}
	
	public int  deleteSubject(String sub){
		
		/*this.dataBase.execSQL("DELETE FROM "+DATABASE_TABLE
				+ " WHERE "+SUBJECT+" = '"+sub+"'"
				
				);
		*/
		
		return this.dataBase.delete(DATABASE_TABLE,SUBJECT+"='"+sub+"'", null);
		
		
	}
	
	public void deleteAllSubject(){
		
		ArrayList<String> subs = this.retrieveAllSubjects();
		
		if(!subs.isEmpty()){
			
			for(String s:subs){
				this.deleteSubject(s);
			}
			
		}
	}
	
	
	public ArrayList<String> retrieveAllSubjects(){
		
		Cursor cursor = this.getAllSubjectName();
		ArrayList<String> subjects = new ArrayList<String>();
		
		if(cursor.moveToFirst()){
			do{
				subjects.add(cursor.getString(1));
				
			}while(cursor.moveToNext());
		}
		
		if(!cursor.isClosed()){
			cursor.close();
		}
		
		return subjects;
			
	}
	
	private Cursor getAllSubjectName(){
		//this.dataBase = this.getReadableDatabase();

		return  this.dataBase.rawQuery("select * from "+DATABASE_TABLE, null);
	}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase dataBase, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		this.dataBase.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
		onCreate(dataBase);
	}

}
