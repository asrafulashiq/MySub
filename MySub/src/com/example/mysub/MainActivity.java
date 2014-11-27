package com.example.mysub;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private static final int SUBJECT_LIST = 1;
	private static final int START = 0;
	private static final int EMPTY = 2;
	SubjectAdapter subjectAdapter;
	ListView subject_list_drawer;
	//public static DataOpenHelper dataOpenHelper;
	android.support.v4.app.FragmentTransaction ft;
	private int fragAttached = 0 ;
	private static DataManager dataManager;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ft.commit();
		
		
		this.subjectAdapter = new SubjectAdapter();
		this.subject_list_drawer = (ListView)this.findViewById(R.id.left_drawer);
		
		this.subject_list_drawer.setAdapter(subjectAdapter);
		//this.dataOpenHelper = new DataOpenHelper(this);
		
		MainActivity.dataManager = new DataManager(this);
		
		//this.dataOpenHelper.addNewSubject("EEE");
		//this.dataOpenHelper.deleteAllSubject();
	}
	

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		this.updateUI();
	}

	
	SubjectListFragment subjectListFragment;
	
	public void updateUI(){
		
		ft = this.getSupportFragmentManager().beginTransaction();

		ArrayList<String> s = this.dataManager.getAllSubjects();  // this.dataOpenHelper.retrieveAllSubjects();
		
		if(this.fragAttached==START){
			subjectListFragment = new SubjectListFragment(this,s);
			
		}
		
		if(!s.isEmpty()){
			
		
			this.subjectAdapter.addSubjects(s.toArray(new String[s.size()]));

			if(this.fragAttached==START){
				
				ft.add(R.id.content_frame, subjectListFragment);
				ft.commit();
				this.fragAttached=SUBJECT_LIST;
				
			}
			
			else if(this.fragAttached==SUBJECT_LIST){
				this.subjectListFragment.updateAdapter(s);
				Toast.makeText(getApplicationContext(), "Updating list", Toast.LENGTH_SHORT).show();
				
			}

			else if(this.fragAttached==EMPTY){
				
				this.subjectListFragment.updateAdapter(s);
				ft.replace(R.id.content_frame, subjectListFragment);
				ft.commit();
			}
		}
		else{
			if(this.fragAttached==START){
				AddSubDialog addFrag = new AddSubDialog();
				ft.add(R.id.content_frame, addFrag);
				ft.commit();
				this.fragAttached=EMPTY;
			}
			else if(this.fragAttached==SUBJECT_LIST){
				ft.replace(R.id.content_frame, new AddSubDialog());
				ft.commit();
				this.fragAttached=EMPTY;
			}
			
		}
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	

	/*public void debugButton(View v){
		
		ArrayList<String> s =  this.dataOpenHelper.retrieveAllSubjects();
		
		if(s.isEmpty()){
			Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_SHORT).show();
		}
		else
		for(String i:s){
			Toast.makeText(this,"Subject : "+i, Toast.LENGTH_SHORT).show();
		}
		
	}*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
	
		
		if(id==R.id.plus){
			
			addSub();
			return true;
			
		}
		
		
		return super.onOptionsItemSelected(item);
	}

	public void addSub() {
		// TODO Auto-generated method stub
		
		final Dialog dialog = new Dialog(this);
		
		dialog.setTitle("Add Subject");
		dialog.setContentView(R.layout.custom_dialog_add_sub);
		
		final EditText e = (EditText)dialog.findViewById(R.id.sub);
		
		Button ok = (Button)dialog.findViewById(R.id.ok);
		Button cancel = (Button)dialog.findViewById(R.id.cancel);
		
		
		ok.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						if(dataManager.addNewSubject( e.getText().toString())==-1){
							Toast.makeText(MainActivity.this, "subject could not be added", Toast.LENGTH_SHORT).show();

						}else{
							Toast.makeText(MainActivity.this, "subject added", Toast.LENGTH_SHORT).show();
							//subjectAdapter.addSubject( e.getText().toString() , -1);
							updateUI();

						}
						
						dialog.dismiss();

					}
				}
				);
		
		cancel.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						dialog.dismiss();
					}
				}
				);
		
		dialog.show();
 		
	}
}
