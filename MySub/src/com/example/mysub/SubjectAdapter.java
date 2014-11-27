package com.example.mysub;

import java.util.ArrayList;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SubjectAdapter extends BaseAdapter{
	

	private ArrayList<SingleSubject> subjects;
	
	
	
	public  SubjectAdapter() {
		// TODO Auto-generated constructor stub
		this.subjects = new ArrayList<SingleSubject>();
	}
	
	public void addSubject(String name,int imgId){
		if(!this.containSubject(name))
			this.subjects.add(new SingleSubject(name, imgId));
	}
	
	public boolean containSubject(String s){
		for(SingleSubject single:subjects ){
			if( s.compareToIgnoreCase(single.getName())==0 )
				return true;
		}
		return false;
	}
	
	public void addSubjects(String[] names){
		
		for(String s:names){
			this.addSubject(s, -1);
		}
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return subjects.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return this.subjects.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(view==null){
			
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.subject_item_layout, parent, false);
			
		}
		
		SingleSubject sub = this.subjects.get(index);
		
		TextView text = (TextView)view.findViewById(R.id.textView);
		text.setText(sub.getName());
		
		ImageView im = (ImageView)view.findViewById(R.id.imageView);
		if( sub.getImgId()!= -1 ){
			
			im.setBackgroundResource(sub.getImgId());
			
		}
		
		
		return view;
	}

}
