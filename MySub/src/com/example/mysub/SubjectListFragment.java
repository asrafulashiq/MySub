package com.example.mysub;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SubjectListFragment extends Fragment implements  OnItemLongClickListener,  OnItemClickListener{

	ListView list ;

	ArrayList<SingleSubject> subjects = new ArrayList<SingleSubject>();
	
	SubjectAdapterForMainList adapter ; //= new SubjectAdapterForMainList(subjects)
	private Activity parent;
	
	public SubjectListFragment(){
		
	}
	
	public SubjectListFragment(Activity parent,ArrayList<String> subjects){

		this.parent = parent;
		for(String s:subjects){
			this.subjects.add(new SingleSubject(s, -1));
		}
		
		this.adapter = new SubjectAdapterForMainList(this.subjects);
	}
	
	
	public boolean contain(String s){
		
		for(SingleSubject ss:subjects){
			if(s.compareToIgnoreCase(ss.getName())==0){
				return true;
			}
		}
		
		return false;
		
	}
	
	public void updateAdapter(String s){
		if(!this.contain(s))
			this.adapter.addItem(new SingleSubject(s, -1));
	}
	
	public void updateAdapter(ArrayList<String> sss){
		for(String s:sss){
			this.updateAdapter(s);
		}
	}
	
	
	public void addSubject(String s){
		this.subjects.add(new SingleSubject(s, -1));
	}
	
	public void addSubject(SingleSubject s){
		this.subjects.add(s);
	}
	
	public void addSubject(List<String> ss){
		for(String s:ss){
			this.addSubject(s);
		}
	}
	
	public void addSubject(String[] ss){
		for(String s:ss){
			this.addSubject(s);
		}
	}
	
	public List<String> getSubjectNames(){
		
		List<String> l = new ArrayList<String>();
		for(SingleSubject s:subjects){
			l.add(s.getName());
		}
		return l;
		
	}
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.subject_lists_layout, container, false);
		
		this.list = (ListView)v.findViewById(R.id.subject_list);
		this.list.addHeaderView(new View(this.getActivity()));
		this.list.addFooterView(new View(this.getActivity()));
		
		this.list.setAdapter(adapter);

		this.list.setOnItemClickListener(this);
		
		
		return v;
	}

	
	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View v, int position,
			long id) {
		// TODO Auto-generated method stub
		((ListView)parent).setItemChecked(position,((ListView)parent).isItemChecked(position));
		Toast.makeText(getActivity(),"Clicked at "+position, Toast.LENGTH_SHORT).show();
		
		return true;
	}

	

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		
		// TODO Auto-generated method stub
		
		SingleSubject currentSubject = this.subjects.get(position-1);

		
		((ListView)parent).setItemChecked(position,((ListView)parent).isItemChecked(position));
		Toast.makeText(getActivity(),"short Clicked at "+currentSubject.getName(), Toast.LENGTH_SHORT).show();
		
		SingleSubjectFragmentForMain frag = new SingleSubjectFragmentForMain(currentSubject);
		
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		
		ft.replace(R.id.content_frame, frag);
		
		ft.addToBackStack(null);
		
		ft.commit();
		
	
	}
	
	
	
	
}
