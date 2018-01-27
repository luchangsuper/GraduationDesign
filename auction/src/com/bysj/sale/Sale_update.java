package com.bysj.sale;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.json.JSONArray;
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

public class Sale_update extends Activity
{
	int flag=0;
	private EditText editText;
	private Button btn_confirm;
	private Button bn_update;
	private Button bn_cancel;
	private EditText ed_goodsname;
	private EditText ed_goodsdesc;
	private EditText ed_goodsprice;
	private EditText ed_goodsstatus;
	private EditText ed_goodsnumber;
	private EditText ed_goodstime;
	private EditText ed_ID;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updateinfo);
		
		editText=(EditText) findViewById(R.id.update_edno);
		btn_confirm=(Button) findViewById(R.id.btnupdate);
		ed_goodsname=(EditText) findViewById(R.id.update_edname);
		ed_goodsdesc=(EditText) findViewById(R.id.update_eddesc);
		ed_goodsprice=(EditText) findViewById(R.id.update_edprice);
		ed_goodsstatus=(EditText) findViewById(R.id.update_edstatus);
		ed_goodsnumber=(EditText) findViewById(R.id.update_ednumber);
		ed_goodstime=(EditText) findViewById(R.id.update_edtime);
		ed_ID=(EditText) findViewById(R.id.update_edID);
		bn_update=(Button) findViewById(R.id.btn_update);
		bn_cancel=(Button) findViewById(R.id.btn_updatecancel);
		
		btn_confirm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				String updateno=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("updateno", updateno);
				String url=HttpUtil.BASE_URL+"update";
				String result;
				try 
				{
					result = HttpUtil.postRequest(url, map);
					System.out.println(result);
					JSONArray jsonArray=new JSONArray(result);
					JSONObject jsonObject=jsonArray.getJSONObject(0);
					String uName=jsonObject.getString("商品名称");
					String uDesc=jsonObject.getString("商品描述");
					String uPrice=jsonObject.getString("商品起始价");
					String uStatus=jsonObject.getString("商品状态");
					String uAmount=jsonObject.getString("商品数量");
					String uTime=jsonObject.getString("商品上货时间");
					String uSaler=jsonObject.getString("拍卖者昵称");
					
					ed_goodsname.setText(uName);
					ed_goodsdesc.setText(uDesc);
					ed_goodsprice.setText(uPrice);
					ed_goodsstatus.setText(uStatus);
					ed_goodsnumber.setText(uAmount);
					ed_goodstime.setText(uTime);
					ed_ID.setText(uSaler);
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
				if(checkGoodsInfo())
				{
					DisplayToast("信息验证成功！");
					String no=editText.getText().toString();
					String name=ed_goodsname.getText().toString();
					String desc=ed_goodsdesc.getText().toString();
					String price=ed_goodsprice.getText().toString();
					String status=ed_goodsstatus.getText().toString();
					String number=ed_goodsnumber.getText().toString();
					String time=ed_goodstime.getText().toString();
					String ID=ed_ID.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("Goodsno", no);
					map.put("Goodsname", name);
					map.put("Goodsdesc", desc);
					map.put("Goodsprice", price);
					map.put("Goodsstatus", status);
					map.put("Goodsnumber", number);
					map.put("Goodstime", time);
					map.put("SalerID", ID);
					String url=HttpUtil.BASE_URL+"update_1";
					
					//发送请求,并把服务器响应转为JSON对象
					try 
					{
						String result=HttpUtil.postRequest(url,map);
						System.out.println(result);
						JSONObject jsonObject=new JSONObject(result);
						System.out.println(jsonObject);
						flag=1;
						if(jsonObject.getInt("GoodsId")>0&&flag==1)
						{
							DisplayToast("恭喜您，已成功修改商品信息！");
						}
						else
						{
							DisplayToast("修改商品信息失败，请稍后重试！");
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
				DisplayToast("您已经选择了取消修改商品信息！");
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
			intent.setClass(Sale_update.this, Sale.class);
			startActivity(intent);
			Sale_update.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	/**
	 * 输入检验方法
	 */
	private boolean checkGoodsInfo()
	{
		if (ed_goodsname.getText() != null && ed_goodsname.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品名称不能为空！");
			return false;
		}
		
		if (ed_goodsstatus.getText() != null && ed_goodsstatus.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品状态不能为空！");
			return false;
		}
		
		if (ed_goodsdesc.getText() != null && ed_goodsdesc.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品描述不能为空！");
			return false;
		}
		
		if(isNumeric(ed_goodsprice.getText().toString()))
		{
		}
		else
		{
			DisplayToast("商品起始价只能为数字！");
			return false;
		}
		
		if(isNumeric(ed_goodsnumber.getText().toString()))
		{
		}
		else
		{
			DisplayToast("商品数量只能为数字！");
			return false;
		}
		
		if (ed_goodstime.getText() != null && ed_goodstime.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("商品上货时间不能为空！");
			return false;
		}
		
		if (ed_ID.getText() != null && ed_ID.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("拍卖者昵称不能为空！");
			return false;
		}
		return true;
	}
	
	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	private boolean isNumeric(String str) 
	{
		//定义正则表达式
		String regNumeric="[0-9]*";
		Pattern pattern = Pattern.compile(regNumeric);
		return pattern.matcher(str).matches();
	}
	
	/* 显示Toast */
	public void DisplayToast(String str) 
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		// 设置toast显示的位置
		toast.setGravity(Gravity.TOP, 0, 220);
		// 显示该Toast
		toast.show();
	}
}
