package com.bysj.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.function.Function;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class TimeInfo extends Activity
{
	private ListView listView;
	String No="";
	String Name="";
	String Start="";
	String End="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeinfo);
		
		listView=(ListView) findViewById(R.id.listview_timeinfo);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		//���巢�������URL
		String url=HttpUtil.BASE_URL+"time_index";
		try 
		{
			JSONArray jsonArray=new JSONArray(HttpUtil.getRequest(url));
			System.out.println(jsonArray);
			String[] no=new String[jsonArray.length()];
			String[] name=new String[jsonArray.length()];
			String[] start=new String[jsonArray.length()];
			String[] end=new String[jsonArray.length()];
			
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject obj = jsonArray.getJSONObject(i);
				No=obj.getString("��Ʒ���");
				no[i]=No;
				
				Name=obj.getString("��Ʒ����");
				name[i]=Name;
				
				Start=obj.getString("��Ʒ�ϻ�ʱ��");
				start[i]=Start;
				
				End=obj.getString("������ֹʱ��");
				end[i]=End;
			}
			System.out.println(name.length);
			for (int i = 0; i < no.length; i++) 
			{
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("no", "��Ʒ���:"+no[i]);
				listItem.put("name", "��Ʒ����:"+name[i]);
				listItem.put("start", "��Ʒ�ϻ�ʱ��:"+start[i]);
				listItem.put("end","������ֹʱ��:"+end[i]);
				listItems.add(listItem);
			}

			// ����һ��SimpleAdapter
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
					R.layout.showtimeinfo, new String[] {"no","name", "start","end"},
					new int[] {R.id.time_myno,R.id.time_myname, R.id.time_mystart,R.id.time_myend});
			
			// ΪListView����Adapter
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
			intent.setClass(TimeInfo.this, Function.class);
			startActivity(intent);
			TimeInfo.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
