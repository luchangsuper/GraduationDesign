package com.bysj.operate;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bysj.auction.R;
import com.bysj.manager.ManagerOperate;
import com.bysj.util.HttpUtil;

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

public class Xiugaijp extends Activity 
{
	int flag=0;
	private EditText editText;
	private Button btn_confirm;
	private Button bn_update;
	private Button bn_cancel;
	private EditText ed_bidername;
	private EditText ed_biderpwd;
	private EditText ed_biderphone;
	private EditText ed_bideremail;
	private EditText ed_biderrealname;
	private EditText ed_bideraddress;
	private EditText ed_biderpost;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiugaijp);
		
		editText=(EditText) findViewById(R.id.xiugaijp_edno);
		btn_confirm=(Button) findViewById(R.id.btnxiugaijp);
		ed_bidername=(EditText) findViewById(R.id.xiugaijp_edname);
		ed_biderpwd=(EditText) findViewById(R.id.xiugaijp_edpwd);
		ed_biderphone=(EditText) findViewById(R.id.xiugaijp_edphone);
		ed_bideremail=(EditText) findViewById(R.id.xiugaijp_edemail);
		ed_biderrealname=(EditText) findViewById(R.id.xiugaijp_edrealname);
		ed_bideraddress=(EditText) findViewById(R.id.xiugaijp_edaddress);
		ed_biderpost=(EditText) findViewById(R.id.xiugaijp_edpost);
		bn_update=(Button) findViewById(R.id.btn_xiugaijp);
		bn_cancel=(Button) findViewById(R.id.btn_xiugaijpcancel);
		
		btn_confirm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				String Biderno=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("Biderno", Biderno);
				String url=HttpUtil.BASE_URL+"xiugaijp";
				String result;
				try 
				{
					result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONArray jsonArray=new JSONArray(result);
					JSONObject jsonObject=jsonArray.getJSONObject(0);
					String uName=jsonObject.getString("�û���");
					String uPwd=jsonObject.getString("����");
					String uPhone=jsonObject.getString("�绰");
					String uEmail=jsonObject.getString("����");
					String uRealname=jsonObject.getString("����");
					String uAddress=jsonObject.getString("��ַ");
					String uPost=jsonObject.getString("�ʱ�");
					
					ed_bidername.setText(uName);
					ed_biderpwd.setText(uPwd);
					ed_biderphone.setText(uPhone);
					ed_bideremail.setText(uEmail);
					ed_biderrealname.setText(uRealname);
					ed_bideraddress.setText(uAddress);
					ed_biderpost.setText(uPost);
				}
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		bn_update.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				if(checkSalerInfo()){
					DisplayToast("��Ϣ��֤�ɹ���");
					String biderno=editText.getText().toString();
					String bidername=ed_bidername.getText().toString();
					String biderpwd=ed_biderpwd.getText().toString();
					String biderphone=ed_biderphone.getText().toString();
					String bideremail=ed_bideremail.getText().toString();
					String biderrealname=ed_biderrealname.getText().toString();
					String bideraddress=ed_bideraddress.getText().toString();
					String biderpost=ed_biderpost.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("Biderno", biderno);
					map.put("Bidername", bidername);
					map.put("Biderpwd", biderpwd);
					map.put("Biderphone", biderphone);
					map.put("Bideremail", bideremail);
					map.put("Biderrealname", biderrealname);
					map.put("Bideraddress", bideraddress);
					map.put("Biderpost", biderpost);
					String url=HttpUtil.BASE_URL+"xiugaijp_1";
					
					//��������,���ѷ�������ӦתΪJSON����
					try 
					{
						String result=HttpUtil.postRequest(url,map);
						System.out.println(result);
						JSONObject jsonObject=new JSONObject(result);
						System.out.println(jsonObject);
						flag=1;
						if(jsonObject.getInt("BiderId")>0&&flag==1)
						{
							DisplayToast("��ϲ�����ѳɹ��޸ľ����û���Ϣ��");
						}
						else
						{
							DisplayToast("�޸ľ����û���Ϣʧ�ܣ����Ժ����ԣ�");
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
		
		bn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DisplayToast("���Ѿ�ѡ����ȡ���޸ľ����û���Ϣ��");
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
			intent.setClass(Xiugaijp.this, ManagerOperate.class);
			startActivity(intent);
			Xiugaijp.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * ������鷽��
	 */
	private boolean checkSalerInfo()
	{
		if (ed_bidername.getText() != null && ed_bidername.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�������û�������Ϊ�գ�");
			return false;
		}
		
		if(isNumeric(ed_biderphone.getText().toString()))
		{
		}
		else
		{
			DisplayToast("�����û��绰ֻ��Ϊ���֣�");
			return false;
		}
		
		if(isNumeric(ed_biderpost.getText().toString()))
		{
		}
		else
		{
			DisplayToast("�����û��ʱ�ֻ��Ϊ���֣�");
			return false;
		}
		
		if (ed_biderpwd.getText() != null && ed_biderpwd.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�����û����벻��Ϊ�գ�");
			return false;
		}
		
		if (ed_bideremail.getText() != null && ed_bideremail.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("���������䲻��Ϊ�գ�");
			return false;
		}
		
		if (ed_biderrealname.getText() != null && ed_biderrealname.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("��������ʵ��������Ϊ�գ�");
			return false;
		}
		
		if (ed_bideraddress.getText() != null && ed_bideraddress.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("�����û���ַ����Ϊ�գ�");
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
}
