package com.example.mysub;

import java.util.ArrayList;

import android.content.Context;

public class DataManager {
	
	public DataOpenHelper mainDataBase;
	
	private int subjectCount;
	
	ArrayList<SingleSubjectDataOpenHelper> subjects;

	private Context context;
	
	public DataManager(Context context){
		
		this.context = context;
		this.mainDataBase = new DataOpenHelper(context);
		this.subjects = new ArrayList<SingleSubjectDataOpenHelper>();
		
		getSingleSubjectData();
		
	}

	private void getSingleSubjectData() {
		// TODO Auto-generated method stub
		ArrayList<String> subs = this.getAllSubjects();
		
		for(String sub : subs){
			
			this.subjects.add( new SingleSubjectDataOpenHelper(context, sub) );
			
		}
		
	}

	public ArrayList<String> getAllSubjects() {
		// TODO Auto-generated method stub
		return this.mainDataBase.retrieveAllSubjects();
	}

	public int addNewSubject(String string) {
		// TODO Auto-generated method stub
		int r =  (int)this.mainDataBase.addNewSubject(string);
		
		
		
		return r;
	}
	
	private void createSingleSubjectData(String name){
		
		new SingleSubjectDataOpenHelper(context,name);
		
	}
	
	
	
	public int getSubjectCount(){
		return this.mainDataBase.retrieveAllSubjects().size();
	}
	
	public int deleteSubject(String s){
		
		return this.mainDataBase.deleteSubject(s);
	}
	
	public void deleteAll(){
		this.mainDataBase.deleteAllSubject();
	}

}
