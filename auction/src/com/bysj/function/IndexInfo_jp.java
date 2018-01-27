package com.bysj.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bysj.auction.R;
import com.bysj.login.LoginApp;
import com.bysj.util.HttpUtil;

public class IndexInfo_jp extends Activity 
{
	String jp_No="";
	String jp_Name="";
	String jp_Phone="";
	String jp_Email="";
	String jp_Realname="";
	String jp_Address="";
	String jp_Post="";
	private EditText editText_jp;
	private ListView listView_jp;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jingpai_index);
		
		editText_jp=(EditText) findViewById(R.id.ed_jingpai_index);
		listView_jp=(ListView) findViewById(R.id.listview_jingpai_index);
		
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		String jingpainame=LoginApp.usernameinfo;
		editText_jp.setText(jingpainame);
		
		String username=editText_jp.getText().toString().trim();
		Map<String, String> map=new HashMap<String, String>();
		map.put("username", username);
		String url=HttpUtil.BASE_URL+"index_jp";
		String result;
		try 
		{
			result = HttpUtil.postRequest(url, map);
			System.out.println(result);
			JSONArray jsonArray=new JSONArray(result);
			String[] jp_no=new String[jsonArray.length()];
			String[] jp_name=new String[jsonArray.length()];
			String[] jp_phone=new String[jsonArray.length()];
			String[] jp_email=new String[jsonArray.length()];
			String[] jp_realname=new String[jsonArray.length()];
			String[] jp_address=new String[jsonArray.length()];
			String[] jp_post=new String[jsonArray.length()];
			
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject obj = jsonArray.getJSONObject(i);
				jp_No=obj.getString("竞标者编号");
				jp_no[i]=jp_No;
				
				jp_Name=obj.getString("用户名");
				jp_name[i]=jp_Name;
				
				jp_Phone=obj.getString("电话");
				jp_phone[i]=jp_Phone;
				
				jp_Email=obj.getString("邮箱");
				jp_email[i]=jp_Email;
				
				jp_Realname=obj.getString("姓名");
				jp_realname[i]=jp_Realname;
				
				jp_Address=obj.getString("地址");
				jp_address[i]=jp_Address;
				
				jp_Post=obj.getString("邮编");
				jp_post[i]=jp_Post;
			}
			System.out.println(jp_no.length);
		
			for (int i = 0; i < jp_no.length; i++) 
			{
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("jp_no", "竞标者编号:"+jp_no[i]);
				listItem.put("jp_name", "用户名:"+jp_name[i]);
				listItem.put("jp_phone", "电话:"+jp_phone[i]);
				listItem.put("jp_email","邮箱:"+jp_email[i]);
				listItem.put("jp_realname", "我的姓名:"+jp_realname[i]);
				listItem.put("jp_address", "家庭住址:"+jp_address[i]);
				listItem.put("jp_post", "邮编:"+jp_post[i]);
				listItems.add(listItem);
			}

			// 创建一个SimpleAdapter
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
					R.layout.show_jingpai_info, new String[] { "jp_no","jp_name", "jp_phone","jp_email",
					"jp_realname","jp_address","jp_post"},
					new int[] {R.id.jingpai_bh, R.id.jingpai_yhm, R.id.jingpai_dh,R.id.jingpai_yx,
					R.id.jingpai_xm,R.id.jingpai_dz,R.id.jingpai_yb});
			
			// 为ListView设置Adapter
			listView_jp.setAdapter(simpleAdapter);
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
			intent.setClass(IndexInfo_jp.this, IndexInfo.class);
			startActivity(intent);
			IndexInfo_jp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
