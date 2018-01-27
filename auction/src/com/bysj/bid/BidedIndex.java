package com.bysj.bid;

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
import com.bysj.login.LoginApp;
import com.bysj.util.HttpUtil;

public class BidedIndex extends Activity 
{
	private ListView listView;
	String myName="";
	String Name="";
	String Desc="";
	String Price="";
	String myBidPrice;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bidedindex);
		listView=(ListView) findViewById(R.id.listview_bidedindex);
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		
		//���巢�������URL
		String url=HttpUtil.BASE_URL+"bided";
		String loginusername=LoginApp.usernameinfo;
		Map<String, String> map=new HashMap<String, String>();
		map.put("loginusername", loginusername);
		try {
				JSONArray jsonArray=new JSONArray(HttpUtil.postRequest(url,map));
				System.out.println(jsonArray);
				String[] myname=new String[jsonArray.length()];
				String[] name=new String[jsonArray.length()];
				String[] desc=new String[jsonArray.length()];
				String[] price=new String[jsonArray.length()];
				String[] mybidprice=new String[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject obj = jsonArray.getJSONObject(i);
					myName=obj.getString("�ҵ�����");
					myname[i]=myName;
					Name=obj.getString("��Ʒ����");
					name[i]=Name;
					Desc=obj.getString("��Ʒ����");
					desc[i]=Desc;
					Price=obj.getString("��Ʒ��ʼ��");
					price[i]=Price;
					myBidPrice=obj.getString("�ҵľ����");
					mybidprice[i]=myBidPrice;
					
				}
				System.out.println(myname.length);
				for (int i = 0; i < myname.length; i++) 
				{
					Map<String, Object> listItem = new HashMap<String, Object>();
					listItem.put("myname", "�ҵ�����:"+myname[i]);
					listItem.put("name", "��Ʒ����:"+name[i]);
					listItem.put("desc", "��Ʒ����:"+desc[i]);
					listItem.put("price","��Ʒ��ʼ�ۣ�:"+price[i]);
					listItem.put("mybidprice", "�ҵľ���ۣ�:"+mybidprice[i]);
					listItems.add(listItem);
				}
	
				// ����һ��SimpleAdapter
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.showbided, new String[] {"myname","name", "desc","price",
						"mybidprice"},
						new int[] {R.id.bided_myname,R.id.bided_name, R.id.bided_desc,R.id.bided_price,
						R.id.bided_mybidprice});
				
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
			intent.setClass(BidedIndex.this, Bid.class);
			startActivity(intent);
			BidedIndex.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
