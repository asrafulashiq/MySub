package com.example.mysub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SingleSubjectFragmentForMain extends Fragment{

	private SingleSubject subject;

	public SingleSubjectFragmentForMain(SingleSubject subject) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
	}
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.single_subject_layout_for_main,container, false);
		
		TextView name = (TextView)v.findViewById(R.id.subject_name);
		name.setText(this.subject.getName());
		
		TextView date = (TextView)v.findViewById(R.id.last_update_date);
		//date.append(text);
		
		return v;
		
				
	}

	
}
