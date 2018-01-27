package com.bysj.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bysj.auction.MainActivity;
import com.bysj.auction.R;
import com.bysj.bid.Bid;
import com.bysj.login.LoginApp;
import com.bysj.sale.Sale;
import com.bysj.time.TimeInfo;
import com.bysj.util.High_price;

public class Function extends Activity implements OnItemClickListener
{
	private ListView listView;
	private ArrayAdapter<String> array_adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function);
		listView=(ListView) findViewById(R.id.listview);
		/**
		 * 1���½�һ������������
		 * 2��������������������Դ
		 * 3����ͼ����������
		 */
		String[] items=getResources().getStringArray(R.array.auction_list);
		array_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		listView.setAdapter(array_adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) 
	{
		// TODO Auto-generated method stub
		
		//����һ����Ʒ��ѯ�࣬ʵ���л�ҳ��
		if(position==0)
		{
			Intent intent=new Intent();
			intent.setClass(this, Index.class);
			startActivity(intent);
			Function.this.finish();
		}
		
		if(position==1)
		{
			if(LoginApp.flag==1)
			{
				Intent intent=new Intent();
				intent.setClass(this, Sale.class);
				startActivity(intent);
				Function.this.finish();
			}
			else
			{
				DisplayToast("����û�е�¼��ϵͳ�������ϵͳ��¼��");
				Intent intent=new Intent();
				intent.setClass(this, LoginApp.class);
				startActivity(intent);
				Function.this.finish();
			}
		}
		
		if(position==2)
		{
			if(LoginApp.flag==1)
			{
				Intent intent=new Intent();
				intent.setClass(this, Bid.class);
				startActivity(intent);
				Function.this.finish();
			}
			else
			{
				DisplayToast("����û�е�¼��ϵͳ�������ϵͳ��¼��");
				Intent intent=new Intent();
				intent.setClass(this, LoginApp.class);
				startActivity(intent);
				Function.this.finish();
			}
		}
		
		if(position==3)
		{
			if(LoginApp.flag==1)
			{
				Intent intent=new Intent();
				intent.setClass(this, High_price.class);
				startActivity(intent);
				Function.this.finish();
			}
			else
			{
				DisplayToast("����û�е�¼��ϵͳ�������ϵͳ��¼��");
				Intent intent=new Intent();
				intent.setClass(this, LoginApp.class);
				startActivity(intent);
				Function.this.finish();
			}
		}
		
		if(position==4)
		{
			if(LoginApp.flag==1)
			{
				Intent intent=new Intent();
				intent.setClass(this, IndexInfo.class);
				startActivity(intent);
				Function.this.finish();
			}
			else
			{
				DisplayToast("����û�е�¼��ϵͳ�������ϵͳ��¼��");
				Intent intent=new Intent();
				intent.setClass(this, LoginApp.class);
				startActivity(intent);
				Function.this.finish();
			}
		}
		
		if(position==5)
		{
			if(LoginApp.flag==1)
			{
				Intent intent=new Intent();
				intent.setClass(this, TimeInfo.class);
				startActivity(intent);
				Function.this.finish();
			}
			else
			{
				DisplayToast("����û�е�¼��ϵͳ�������ϵͳ��¼��");
				Intent intent=new Intent();
				intent.setClass(this, LoginApp.class);
				startActivity(intent);
				Function.this.finish();
			}
		}
	}
	
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Function.this, MainActivity.class);
			startActivity(intent);
			Function.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

