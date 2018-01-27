package com.bysj.sale;

import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.function.Index;
import com.bysj.time.Fabutime_saler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Sale extends Activity
{
	
	private Button add;
	private Button delete;
	private Button update;
	private Button saled;
	private Button time;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale);
		
		add=(Button) findViewById(R.id.sale_add);
		delete=(Button) findViewById(R.id.sale_delete);
		update=(Button) findViewById(R.id.sale_update);
		saled=(Button) findViewById(R.id.saled);
		time=(Button) findViewById(R.id.sale_time);
		
		//为添加商品绑定单击事件监听器
		add.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Sale.this, Sale_add.class);
				startActivity(intent);
				Sale.this.finish();
			}
		});
		
		delete.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Sale.this, Sale_delete.class);
				startActivity(intent);
				Sale.this.finish();
			}
		});
		
		update.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Sale.this, Sale_update.class);
				startActivity(intent);
				Sale.this.finish();
			}
		});
		
		saled.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Sale.this, SaledIndex.class);
				startActivity(intent);
				Sale.this.finish();
			}
		});
		
		time.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(Sale.this, Fabutime_saler.class);
				startActivity(intent);
				Sale.this.finish();
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Sale.this, Function.class);
			startActivity(intent);
			Sale.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
