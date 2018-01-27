package com.bysj.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.login.LoginApp;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SaledIndex extends Activity
{
	private ListView listView;
	String No="";
	String Name="";
	String Desc="";
	String Price="";
	String LastPrice;
	String Bider="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saledindex);
		listView=(ListView) findViewById(R.id.listview_saledindex);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		
		String loginusername=LoginApp.usernameinfo;
		Map<String, String> map=new HashMap<String, String>();
		map.put("loginusername", loginusername);
		//���巢�������URL
		String url=HttpUtil.BASE_URL+"saled";
		try {
				JSONArray jsonArray=new JSONArray(HttpUtil.postRequest(url,map));
				System.out.println(jsonArray);
				String[] no=new String[jsonArray.length()];
				String[] name=new String[jsonArray.length()];
				String[] desc=new String[jsonArray.length()];
				String[] price=new String[jsonArray.length()];
				String[] lastprice=new String[jsonArray.length()];
				String[] bider=new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					No=obj.getString("��Ʒ���");
					no[i]=No;
					Name=obj.getString("��Ʒ����");
					name[i]=Name;
					Desc=obj.getString("��Ʒ����");
					desc[i]=Desc;
					Price=obj.getString("��Ʒ��ʼ��");
					price[i]=Price;
					LastPrice=obj.getString("��Ʒ���վ����");
					lastprice[i]=LastPrice;
					Bider=obj.getString("����������");
					bider[i]=Bider;
				}
				System.out.println(name.length);
				for (int i = 0; i < no.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("no", "��Ʒ���:"+no[i]);
					listItem.put("name", "��Ʒ����:"+name[i]);
					listItem.put("info", "��Ʒ����:"+desc[i]);
					listItem.put("price","��Ʒ��ʼ�ۣ�:"+price[i]);
					listItem.put("lastprice", "��Ʒ���վ���ۣ�:"+lastprice[i]);
					listItem.put("bider", "����������:"+bider[i]);
					listItems.add(listItem);
				}
	
				// ����һ��SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.showsaled, new String[] {"no","name", "info","price",
						"lastprice","bider"},
						new int[] {R.id.no,R.id.name, R.id.info,R.id.price,R.id.lastprice,
						R.id.salednicheng});
				
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
			intent.setClass(SaledIndex.this, Sale.class);
			startActivity(intent);
			SaledIndex.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
