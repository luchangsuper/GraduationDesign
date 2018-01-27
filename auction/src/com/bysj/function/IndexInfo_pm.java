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

public class IndexInfo_pm extends Activity 
{
	private EditText editText_pm;
	private ListView listView_pm;
	String pm_No="";
	String pm_Name="";
	String pm_Phone="";
	String pm_Email="";
	String pm_Realname="";
	String pm_Address="";
	String pm_Post="";
	String pm_Credit="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paimai_index);
		
		editText_pm=(EditText) findViewById(R.id.ed_paimai_index);
		listView_pm=(ListView) findViewById(R.id.listview_paimai_index);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		String paimainame=LoginApp.usernameinfo;
		editText_pm.setText(paimainame);
		
		String username=editText_pm.getText().toString().trim();
		Map<String, String> map=new HashMap<String, String>();
		map.put("username", username);
		String url=HttpUtil.BASE_URL+"index_pm";
		String result;
		try 
		{
			result = HttpUtil.postRequest(url, map);
			System.out.println(result);
			JSONArray jsonArray=new JSONArray(result);
			String[] pm_no=new String[jsonArray.length()];
			String[] pm_name=new String[jsonArray.length()];
			String[] pm_phone=new String[jsonArray.length()];
			String[] pm_email=new String[jsonArray.length()];
			String[] pm_realname=new String[jsonArray.length()];
			String[] pm_address=new String[jsonArray.length()];
			String[] pm_post=new String[jsonArray.length()];
			String[] pm_credit=new String[jsonArray.length()];
			
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject obj = jsonArray.getJSONObject(i);
				pm_No=obj.getString("拍卖者编号");
				pm_no[i]=pm_No;
				
				pm_Name=obj.getString("用户名");
				pm_name[i]=pm_Name;
				
				pm_Phone=obj.getString("电话");
				pm_phone[i]=pm_Phone;
				
				pm_Email=obj.getString("邮箱");
				pm_email[i]=pm_Email;
				
				pm_Realname=obj.getString("姓名");
				pm_realname[i]=pm_Realname;
				
				pm_Address=obj.getString("地址");
				pm_address[i]=pm_Address;
				
				pm_Post=obj.getString("邮编");
				pm_post[i]=pm_Post;
				
				pm_Credit=obj.getString("信用度");
				pm_credit[i]=pm_Credit;
			}
			System.out.println(pm_no.length);
		
			for (int i = 0; i < pm_no.length; i++) 
			{
				Map<String, Object> listItem = new HashMap<String, Object>();
				listItem.put("pm_no", "拍卖者编号:"+pm_no[i]);
				listItem.put("pm_name", "用户名:"+pm_name[i]);
				listItem.put("pm_phone", "电话:"+pm_phone[i]);
				listItem.put("pm_email","邮箱:"+pm_email[i]);
				listItem.put("pm_realname", "我的姓名:"+pm_realname[i]);
				listItem.put("pm_address", "家庭住址:"+pm_address[i]);
				listItem.put("pm_post", "邮编:"+pm_post[i]);
				listItem.put("pm_credit","信用度:"+pm_credit[i]);
				listItems.add(listItem);
			}

			// 创建一个SimpleAdapter
			SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
					R.layout.show_paimai_info, new String[] { "pm_no","pm_name", "pm_phone","pm_email",
					"pm_realname","pm_address","pm_post","pm_credit"},
					new int[] {R.id.paimai_bh, R.id.paimai_yhm, R.id.paimai_dh,R.id.paimai_yx,
					R.id.paimai_xm,R.id.paimai_dz,R.id.paimai_yb,R.id.paimai_xyd });
			
			// 为ListView设置Adapter
			listView_pm.setAdapter(simpleAdapter);
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
			intent.setClass(IndexInfo_pm.this, IndexInfo.class);
			startActivity(intent);
			IndexInfo_pm.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
