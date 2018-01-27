package com.bysj.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Chaxunjp extends Activity 
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
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chaxunjp);
		listView=(ListView) findViewById(R.id.listview_chaxunjp);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		
		//���巢�������URL
		String url=HttpUtil.BASE_URL+"chaxunjp";
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
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					No=obj.getString("�����߱��");
					no[i]=No;
					
					Name=obj.getString("�û���");
					name[i]=Name;
					
					Pwd=obj.getString("����");
					pwd[i]=Pwd;
					
					Phone=obj.getString("�绰");
					phone[i]=Phone;
					
					Email=obj.getString("����");
					email[i]=Email;
					
					Realname=obj.getString("����");
					realname[i]=Realname;
					
					Address=obj.getString("��ַ");
					address[i]=Address;
					
					Post=obj.getString("�ʱ�");
					post[i]=Post;
					
				}
				System.out.println(no.length);
				for (int i = 0; i < no.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("no", "�����߱��:"+no[i]);
					listItem.put("name", "�û���:"+name[i]);
					listItem.put("pwd","����:"+pwd[i]);
					listItem.put("phone","�绰:"+phone[i]);
					listItem.put("email","����:"+email[i]);
					listItem.put("realname","����:"+realname[i]);
					listItem.put("address","��ַ:"+address[i]);
					listItem.put("post","�ʱ�:"+post[i]);
					listItems.add(listItem);
				}
	
				// ����һ��SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.showchaxunjp, new String[] { "no","name", "pwd","phone","email","realname",
						"address","post"},
						new int[] {R.id.chaxunjp_no, R.id.chaxunjp_name, R.id.chaxunjp_pwd,R.id.chaxunjp_phone,
						R.id.chaxunjp_email,R.id.chaxunjp_realname,R.id.chaxunjp_address,R.id.chaxunjp_post
						});
				
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
			intent.setClass(Chaxunjp.this, ManagerOperate.class);
			startActivity(intent);
			Chaxunjp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
