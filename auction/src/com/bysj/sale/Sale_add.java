package com.bysj.sale;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bysj.auction.R;
import com.bysj.util.HttpUtil;

public class Sale_add extends Activity
{
	
	private EditText ed_goodsname;
	private EditText ed_goodsdesc;
	private EditText ed_goodsprice;
	private EditText ed_goodsstatus;
	private EditText ed_goodsnumber;
	private EditText ed_goodstime;
	private EditText ed_ID;
	private Button btn_add;
	private Button btn_addcancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addinfo);
		
		ed_goodsname=(EditText) findViewById(R.id.add_edname);
		ed_goodsdesc=(EditText) findViewById(R.id.add_eddesc);
		ed_goodsprice=(EditText) findViewById(R.id.add_edprice);
		ed_goodsstatus=(EditText) findViewById(R.id.add_edstatus);
		ed_goodsnumber=(EditText) findViewById(R.id.add_ednumber);
		ed_goodstime=(EditText) findViewById(R.id.add_edtime);
		ed_ID=(EditText) findViewById(R.id.add_edID);
		btn_add=(Button) findViewById(R.id.btn_add);
		btn_addcancel=(Button) findViewById(R.id.btn_addCancel);
		
		btn_add.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(checkGoodsInfo()){
					DisplayToast("��Ϣ��֤�ɹ���");
					String name=ed_goodsname.getText().toString();
					String desc=ed_goodsdesc.getText().toString();
					String price=ed_goodsprice.getText().toString();
					String status=ed_goodsstatus.getText().toString();
					String number=ed_goodsnumber.getText().toString();
					String time=ed_goodstime.getText().toString();
					String ID=ed_ID.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("Goodsname", name);
					map.put("Goodsdesc", desc);
					map.put("Goodsprice", price);
					map.put("Goodsstatus", status);
					map.put("Goodsnumber", number);
					map.put("Goodstime", time);
					map.put("SalerID", ID);
					String url=HttpUtil.BASE_URL+"add";
					
					//��������,���ѷ�������ӦתΪJSON����
					try 
					{
						String result=HttpUtil.postRequest(url,map);
						System.out.println(result);
						JSONObject jsonObject=new JSONObject(result);
						System.out.println(jsonObject);
						if(jsonObject.getInt("GoodsId")>0)
						{
							DisplayToast("��ϲ�����ѳɹ�������Ʒ��Ϣ��");
						}
						else
						{
							DisplayToast("������Ʒ��Ϣʧ�ܣ����Ժ����ԣ�");
						}
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
			}
		});
		
		btn_addcancel.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				DisplayToast("���Ѿ�ȡ���˷�����Ʒ��Ϣ��");
			}
		});
	}
	
	/**
	 * ������鷽��
	 */
	private boolean checkGoodsInfo()
	{
		if (ed_goodsname.getText() != null && ed_goodsname.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("��Ʒ���Ʋ���Ϊ�գ�");
			return false;
		}
		
		if (ed_goodsstatus.getText() != null && ed_goodsstatus.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("��Ʒ״̬��Ϣ����Ϊ�գ�");
			return false;
		}
		
		if (ed_goodsdesc.getText() != null && ed_goodsdesc.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("��Ʒ��������Ϊ�գ�");
			return false;
		}
		
		if(isNumeric(ed_goodsprice.getText().toString()))
		{
		}
		else
		{
			DisplayToast("��Ʒ��ʼ��ֻ��Ϊ���֣�");
			return false;
		}
		
		if(isNumeric(ed_goodsnumber.getText().toString()))
		{
		}
		else
		{
			DisplayToast("��Ʒ����ֻ��Ϊ���֣�");
			return false;
		}
		
		if (ed_goodstime.getText() != null && ed_goodstime.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("��Ʒ�ϻ�ʱ�䲻��Ϊ�գ�");
			return false;
		}
		
		if (ed_ID.getText() != null && ed_ID.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�������ǳƲ���Ϊ�գ�");
			return false;
		}
		return true;
	}
	
	/**
	 * ������֤
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str) 
	{
		//����������ʽ
		String regNumeric="[0-9]*";
		Pattern pattern = Pattern.compile(regNumeric);
		return pattern.matcher(str).matches();
	}
	
	/* ��ʾToast */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// ����toast��ʾ��λ��
		toast.setGravity(Gravity.TOP, 0, 220);
		// ��ʾ��Toast
		toast.show();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK&&event.getRepeatCount() == 0)
		{
			Intent intent = new Intent();
			intent.setClass(Sale_add.this, Sale.class);
			startActivity(intent);
			Sale_add.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
