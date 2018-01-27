package com.bysj.function;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.sale.Sale;
import com.bysj.sale.Sale_delete;
import com.bysj.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Index_mz extends Activity
{
	
	private EditText editText;
	private Button btnmz;
	private ListView listView;
	private ArrayAdapter<String> array_adapter;
	String Name="";
	String Desc="";
	String Price="";
	String Status="";
	String Amount="";
	String Time="";
	String Saler="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index_mz);
		
		editText=(EditText) findViewById(R.id.ed_index_mz);
		btnmz=(Button) findViewById(R.id.btn_indexmz);
		listView=(ListView) findViewById(R.id.listview_indexmz);
		
		btnmz.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				String goodsmz=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("goodsmz", goodsmz);
				String url=HttpUtil.BASE_URL+"goodsname";
				String result;
				try 
				{
					result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONArray jsonArray=new JSONArray(result);
					System.out.println(jsonArray.length());
					if(jsonArray.length()==0)
					{
						DisplayToast("���������Ʒ��ʱ�����������������룡");
					}
					else
					{
						for(int i=0;i<jsonArray.length();i++)
						{
							JSONObject obj = jsonArray.getJSONObject(i);
							Name=obj.getString("��Ʒ����");
							Desc=obj.getString("��Ʒ����");
							Price=obj.getString("��Ʒ��ʼ��");
							Status=obj.getString("��Ʒ״̬");
							Amount=obj.getString("��Ʒ����");
							Time=obj.getString("��Ʒ�ϻ�ʱ��");
							Saler=obj.getString("�������ǳ�");
							String[] info={"��Ʒ����:"+Name,"��Ʒ����:"+Desc,"��Ʒ��ʼ��:"+Price,"��Ʒ״̬:"+Status,
									"��Ʒ����:"+Amount,"��Ʒ�ϻ�ʱ��:"+Time,"�������ǳ�:"+Saler};
							array_adapter=new ArrayAdapter<String>(Index_mz.this, android.R.layout.simple_list_item_1,info);
							listView.setAdapter(array_adapter);
						}
					}
				} 
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Index_mz.this, Index.class);
			startActivity(intent);
			Index_mz.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}
}
