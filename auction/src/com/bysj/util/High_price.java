package com.bysj.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bysj.auction.R;
import com.bysj.function.Function;

public class High_price extends Activity
{
	private ListView listView;
	String No="";
	String Name="";
	String Desc="";
	String Highprice="";
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
	List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highprice);
		
		listView=(ListView) findViewById(R.id.listview_highprice);
		
		//定义发送请求的URL
		String url=HttpUtil.BASE_URL+"highprice";
		try {
				JSONArray jsonArray=new JSONArray(HttpUtil.getRequest(url));
				System.out.println(jsonArray);
				String[] no=new String[jsonArray.length()];
				String[] name=new String[jsonArray.length()];
				String[] desc=new String[jsonArray.length()];
				String[] highprice=new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					No=obj.getString("商品编号");
					no[i]=No;
					Name=obj.getString("商品名称");
					name[i]=Name;
					Desc=obj.getString("商品描述");
					desc[i]=Desc;
					Highprice=obj.getString("商品当前最高出价");
					highprice[i]=Highprice;
				}
				System.out.println(name.length);
				for (int i = 0; i < name.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("img", imageIds[i]);
					listItem.put("name", "商品名称:"+name[i]);
					listItem.put("info", "商品描述:"+desc[i]);
					listItem.put("highprice","商品当前最高出价￥:"+highprice[i]);
					listItems.add(listItem);
				}
	
				// 创建一个SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.showhighprice, new String[] { "no","name", "info","highprice","img"},
						new int[] {R.id.highpriceno, R.id.highpricename, R.id.highpriceinfo,R.id.highprice,
						R.id.highpriceimg});
				
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
			intent.setClass(High_price.this, Function.class);
			startActivity(intent);
			High_price.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
