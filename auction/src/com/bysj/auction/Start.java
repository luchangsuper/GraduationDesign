package com.bysj.auction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Start extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		new Handler().postDelayed(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Start.this, MainActivity.class);
				startActivity(intent);
				Start.this.finish();
			}
		}, 3000);
	}
}
