package com.example.mysub;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubjectAdapterForMainList extends BaseAdapter{

	private ArrayList<SingleSubject> subjects;
	
	public SubjectAdapterForMainList() {
		// TODO Auto-generated constructor stub
		
	}

	public  SubjectAdapterForMainList(ArrayList<SingleSubject> subjects) {
		// TODO Auto-generated constructor stub
		this.subjects = subjects;
	}
	
	public void addItem(SingleSubject sub){
		this.subjects.add(sub);
	}
	
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.subjects.size();
	}

	@Override
	public SingleSubject getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.subjects.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int index, View v, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		if(v==null){
			
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			v = inflater.inflate(R.layout.single_subject_for_main_layout, parent, false);	
		}
		
		
		
		TextView txt = (TextView)v.findViewById(R.id.subject_name);
		txt.setText(this.subjects.get(index).getName());
		
		return v;
	}

}
