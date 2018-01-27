package com.bysj.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.function.Index;
import com.bysj.function.Index_mz;
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Chaxunsp extends Activity
{
	private ListView listView;
	private Button btn_mz;
	String Name="";
	String Desc="";
	String Status="";
	String Price="";
	
	private int[] imageIds = new int[] 
			{ 
				R.drawable.ding, 
				R.drawable.wan, 
				R.drawable.ping,
				R.drawable.qinghua,
				R.drawable.fo,
				R.drawable.ruyi,
				R.drawable.mei,
				R.drawable.falangcai,
				R.drawable.chahu
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		listView=(ListView) findViewById(R.id.listview_index);
		btn_mz=(Button) findViewById(R.id.index_mingzi);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		btn_mz.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Chaxunsp.this, Chaxunsp_mz.class);
				startActivity(intent);
				Chaxunsp.this.finish();
			}
		});
		
		//定义发送请求的URL
		String url=HttpUtil.BASE_URL+"index";
		try {
				JSONArray jsonArray=new JSONArray(HttpUtil.getRequest(url));
				System.out.println(jsonArray);
				
				String[] name=new String[jsonArray.length()];
				String[] desc=new String[jsonArray.length()];
				String[] status=new String[jsonArray.length()];
				String[] price=new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					Name=obj.getString("商品名称");
					name[i]=Name;
					Desc=obj.getString("商品描述");
					desc[i]=Desc;
					Status=obj.getString("商品状态");
					status[i]=Status;
					Price=obj.getString("商品起始价");
					price[i]=Price;
				}
				System.out.println(name.length);
				for (int i = 0; i < name.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("img", imageIds[i]);
					listItem.put("name", "商品名称:"+name[i]);
					listItem.put("info", "商品描述:"+desc[i]);
					listItem.put("status", "商品状态:"+status[i]);
					listItem.put("price","￥:"+price[i]);
					listItems.add(listItem);
				}
	
				// 创建一个SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.show, new String[] { "img","name", "info","status","price" },
						new int[] {R.id.img, R.id.name, R.id.info,R.id.status,R.id.price });
				
				// 为ListView设置Adapter
				listView.setAdapter(simpleAdapter);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Chaxunsp.this, ManagerOperate.class);
			startActivity(intent);
			Chaxunsp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
