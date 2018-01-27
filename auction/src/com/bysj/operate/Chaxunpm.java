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
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Chaxunpm extends Activity
{
	private ListView listView;
	String No="";
	String Name="";
	String Pwd="";
	String Email="";
	String Phone="";
	String Post="";
	String Realname="";
	String Address="";
	String Credit="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chaxunpm);
		listView=(ListView) findViewById(R.id.listview_chaxunpm);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		
		//定义发送请求的URL
		String url=HttpUtil.BASE_URL+"chaxunpm";
		try {
				JSONArray jsonArray=new JSONArray(HttpUtil.getRequest(url));
				System.out.println(jsonArray);
				String[] no=new String[jsonArray.length()];
				String[] name=new String[jsonArray.length()];
				String[] pwd=new String[jsonArray.length()];
				String[] phone=new String[jsonArray.length()];
				String[] email=new String[jsonArray.length()];
				String[] realname=new String[jsonArray.length()];
				String[] address=new String[jsonArray.length()];
				String[] post=new String[jsonArray.length()];
				String[] credit=new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					No=obj.getString("拍卖者编号");
					no[i]=No;
					
					Name=obj.getString("用户名");
					name[i]=Name;
					
					Pwd=obj.getString("密码");
					pwd[i]=Pwd;
					
					Phone=obj.getString("电话");
					phone[i]=Phone;
					
					Email=obj.getString("邮箱");
					email[i]=Email;
					
					Realname=obj.getString("姓名");
					realname[i]=Realname;
					
					Address=obj.getString("地址");
					address[i]=Address;
					
					Post=obj.getString("邮编");
					post[i]=Post;
					
					Credit=obj.getString("信用度");
					credit[i]=Credit;
				}
				System.out.println(no.length);
				for (int i = 0; i < no.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("no", "拍卖者编号:"+no[i]);
					listItem.put("name", "用户名:"+name[i]);
					listItem.put("pwd","密码:"+pwd[i]);
					listItem.put("phone","电话:"+phone[i]);
					listItem.put("email","邮箱:"+email[i]);
					listItem.put("realname","姓名:"+realname[i]);
					listItem.put("address","地址:"+address[i]);
					listItem.put("post","邮编:"+post[i]);
					listItem.put("credit","信用度:"+credit[i]);
					listItems.add(listItem);
				}
	
				// 创建一个SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.showchaxunpm, new String[] { "no","name", "pwd","phone","email","realname",
						"address","post","credit"},
						new int[] {R.id.chaxunpm_no, R.id.chaxunpm_name, R.id.chaxunpm_pwd,R.id.chaxunpm_phone,
						R.id.chaxunpm_email,R.id.chaxunpm_realname,R.id.chaxunpm_address,R.id.chaxunpm_post,
						R.id.chaxunpm_credit});
				
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
			intent.setClass(Chaxunpm.this, ManagerOperate.class);
			startActivity(intent);
			Chaxunpm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
