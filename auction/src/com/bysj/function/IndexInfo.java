package com.bysj.function;

import com.bysj.auction.MainActivity;
import com.bysj.auction.R;
import com.bysj.auction.Start;
import com.bysj.operate.Xiugaijp;
import com.bysj.operate.Xiugaipm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IndexInfo extends Activity
{
	private Button btn_chaxunpm;
	private Button btn_xiugaipm;
	private Button btn_chaxunjp;
	private Button btn_xiugaijp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indexinfo);
		
		btn_chaxunpm=(Button) findViewById(R.id.pminfo_index);
		btn_xiugaipm=(Button) findViewById(R.id.pminfo_update);
		btn_chaxunjp=(Button) findViewById(R.id.jpinfo_index);
		btn_xiugaijp=(Button) findViewById(R.id.jpinfo_update);
		
		btn_chaxunpm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IndexInfo.this, IndexInfo_pm.class);
				startActivity(intent);
				IndexInfo.this.finish();
			}
		});
		
		btn_xiugaipm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IndexInfo.this, Editpaimai.class);
				startActivity(intent);
				IndexInfo.this.finish();
			}
		});
		
		btn_chaxunjp.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IndexInfo.this, IndexInfo_jp.class);
				startActivity(intent);
				IndexInfo.this.finish();
			}
		});
		
		btn_xiugaijp.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(IndexInfo.this, Editjingpai.class);
				startActivity(intent);
				IndexInfo.this.finish();
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
			intent.setClass(IndexInfo.this, Function.class);
			startActivity(intent);
			IndexInfo.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
