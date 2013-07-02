package com.commics.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CommicsStatusActivity extends Activity{

	private EditText etStatus;
	private TextView tvShow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status_view);
		
		etStatus = (EditText) findViewById(R.id.et_status);
		tvShow = (TextView) findViewById(R.id.tv_show);
	}
	
	public void onButtonClicked(View v)
	{
		String s = etStatus.getText().toString();
		tvShow.setText(s);
		
	}
	
	
}
