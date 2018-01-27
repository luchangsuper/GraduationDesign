package com.bysj.manager;

import com.bysj.auction.Manager;
import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.function.Index;
import com.bysj.operate.Chaxunjp;
import com.bysj.operate.Chaxunpm;
import com.bysj.operate.Chaxunsp;
import com.bysj.operate.Shanchujp;
import com.bysj.operate.Shanchupm;
import com.bysj.operate.Shanchusp;
import com.bysj.operate.Tianjiajp;
import com.bysj.operate.Tianjiapm;
import com.bysj.operate.Tianjiasp;
import com.bysj.operate.Xiugaijp;
import com.bysj.operate.Xiugaipm;
import com.bysj.operate.Xiugaisp;
import com.bysj.sale.Sale;
import com.bysj.sale.Sale_add;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ManagerOperate extends Activity implements OnItemClickListener
{
	private ListView listView;
	private ArrayAdapter<String> array_adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.moperate);
		listView=(ListView) findViewById(R.id.moplistview);
		
		/**
		 * 1、新建一个数据适配器
		 * 2、数据适配器加载数据源
		 * 3、视图加载适配器
		 */
		String[] items=getResources().getStringArray(R.array.operate);
		array_adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
		listView.setAdapter(array_adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) 
	{
		// TODO Auto-generated method stub
		if(position==0)
		{
			Intent intent=new Intent();
			intent.setClass(this, Chaxunsp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==1)
		{
			Intent intent=new Intent();
			intent.setClass(this, Tianjiasp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==2)
		{
			Intent intent=new Intent();
			intent.setClass(this, Xiugaisp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==3)
		{
			Intent intent=new Intent();
			intent.setClass(this, Shanchusp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==4)
		{
			Intent intent=new Intent();
			intent.setClass(this, Chaxunpm.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==5)
		{
			Intent intent=new Intent();
			intent.setClass(this, Tianjiapm.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==6)
		{
			Intent intent=new Intent();
			intent.setClass(this, Xiugaipm.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==7)
		{
			Intent intent=new Intent();
			intent.setClass(this, Shanchupm.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==8)
		{
			Intent intent=new Intent();
			intent.setClass(this, Chaxunjp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==9)
		{
			Intent intent=new Intent();
			intent.setClass(this, Tianjiajp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==10)
		{
			Intent intent=new Intent();
			intent.setClass(this, Xiugaijp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
		
		if(position==11)
		{
			Intent intent=new Intent();
			intent.setClass(this, Shanchujp.class);
			startActivity(intent);
			ManagerOperate.this.finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(ManagerOperate.this, Manager.class);
			startActivity(intent);
			ManagerOperate.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
