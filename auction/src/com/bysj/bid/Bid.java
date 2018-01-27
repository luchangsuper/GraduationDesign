package com.bysj.bid;

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
import com.bysj.function.Function;
import com.bysj.util.HttpUtil;

public class Bid extends Activity
{
	private int flag=0;
	private EditText editText;
	private Button btn_confirm;
	private Button btn_bid;
	private Button btn_bidcancel;
	private Button btn_chaxunjp;
	private EditText ed_goodsname;
	private EditText ed_goodsdesc;
	private EditText ed_goodsprice;
	private EditText ed_goodsstatus;
	private EditText ed_goodsnumber;
	private EditText ed_goodstime;
	private EditText ed_ID;
	private EditText ed_yours;
	private EditText ed_nicheng;
	private String qishijia;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bid);
		editText=(EditText) findViewById(R.id.bid_ed_name);
		btn_confirm=(Button) findViewById(R.id.btnbid);
		ed_goodsname=(EditText) findViewById(R.id.bid_edname);
		ed_goodsdesc=(EditText) findViewById(R.id.bid_eddesc);
		ed_goodsprice=(EditText) findViewById(R.id.bid_edprice);
		ed_goodsstatus=(EditText) findViewById(R.id.bid_edstatus);
		ed_goodsnumber=(EditText) findViewById(R.id.bid_ednumber);
		ed_goodstime=(EditText) findViewById(R.id.bid_edtime);
		ed_ID=(EditText) findViewById(R.id.bid_edID);
		ed_yours=(EditText) findViewById(R.id.bid_edyoursprice);
		ed_nicheng=(EditText) findViewById(R.id.bid_ednicheng);
		btn_bid=(Button) findViewById(R.id.btn_bid);
		btn_bidcancel=(Button) findViewById(R.id.btn_bidcancel);
		btn_chaxunjp=(Button) findViewById(R.id.btn_bidchaxunjp);
		
		btn_confirm.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				String bidname=editText.getText().toString().trim();
				Map<String, String> map=new HashMap<String, String>();
				map.put("bidname", bidname);
				String url=HttpUtil.BASE_URL+"findbid";
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
					qishijia=uPrice;
				}
				catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btn_bid.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View arg0) 
			{
				if(checkBid())
				{
					// TODO Auto-generated method stub
					String name=ed_goodsname.getText().toString();
					String yoursprice=ed_yours.getText().toString();
					String number=ed_goodsnumber.getText().toString();
					String nicheng=ed_nicheng.getText().toString();
					
					Map<String,String> map=new HashMap<String,String>();
					map.put("name", name);
					map.put("yoursprice", yoursprice);
					map.put("number", number);
					map.put("nicheng",nicheng);
					String url=HttpUtil.BASE_URL+"bid";
					
					int firstprice=Integer.parseInt(qishijia);
					int bidprice=Integer.parseInt(yoursprice);
					
					if(firstprice<bidprice)
					{
						String result=new String();
						try 
						{
							//发送请求,并把服务器响应转为JSON对象
							result=HttpUtil.postRequest(url,map);
							System.out.println(result);
							JSONObject jsonObject=new JSONObject(result);
							System.out.println(jsonObject);
							flag=1;
							if(jsonObject.getInt("tag")==1&&flag==1)
							{
								DisplayToast("恭喜您，已成功发送竞价！");
								String cfurl=HttpUtil.BASE_URL+"currentfirstprice";
								Map<String,String> currentfirstmap=new HashMap<String,String>();
								currentfirstmap.put("currentfirstname", name);
								currentfirstmap.put("currentfirstprice", yoursprice);
								HttpUtil.postRequest(cfurl,currentfirstmap);
								
								String yipaimaiurl=HttpUtil.BASE_URL+"add_yipaimai";
								Map<String,String> paimaimap=new HashMap<String,String>();
								paimaimap.put("paimainame", name);
								paimaimap.put("paimaiprice", yoursprice);
								paimaimap.put("paimaimz", nicheng);
								HttpUtil.postRequest(yipaimaiurl,paimaimap);
								
								String yijingpaiurl=HttpUtil.BASE_URL+"add_yijingpai";
								Map<String,String> jingpaimap=new HashMap<String,String>();
								jingpaimap.put("jingpainame", name);
								jingpaimap.put("jingpaiprice", yoursprice);
								jingpaimap.put("jingpaimz", nicheng);
								HttpUtil.postRequest(yijingpaiurl,jingpaimap);
								
								String churl=HttpUtil.BASE_URL+"currenthighprice";
								Map<String,String> currentmap=new HashMap<String,String>();
								currentmap.put("currentname", name);
								currentmap.put("currenthighprice", yoursprice);
								HttpUtil.postRequest(churl,currentmap);
							}
							else if(jsonObject.getInt("tag")==2&&flag==1)
							{
								DisplayToast("您竞标的商品已拍卖完毕，请您重新选择其他商品！");
							}
							
							else if(jsonObject.getInt("tag")==3&&flag==1)
							{
								DisplayToast("您要竞拍的商品已过期，不能进行竞拍！");
								String csurl=HttpUtil.BASE_URL+"status";
								Map<String,String> currentstatusmap=new HashMap<String,String>();
								currentstatusmap.put("currentstatusname", name);
								HttpUtil.postRequest(csurl,currentstatusmap);
							}
							
							else if(jsonObject.getInt("tag")==4&&flag==1)
							{
								DisplayToast("由于您出价晚于且低于其他拍客，秒杀还需努力呀！");
							}
							
							else
							{
								DisplayToast("竞标商品失败，请稍后重试！");
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
					
					else
					{
						DisplayToast("您的出价未大于商品起始价，竞标商品失败，请您检查的出价后重试！");
					}
				}
			}
		});
		
		btn_bidcancel.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
				DisplayToast("您已经选择了取消竞标商品！");
			}
		});
		
		btn_chaxunjp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Bid.this, BidedIndex.class);
				startActivity(intent);
				Bid.this.finish();
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
			intent.setClass(Bid.this, Function.class);
			startActivity(intent);
			Bid.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
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
	
	private boolean checkBid()
	{
		if (ed_nicheng.getText() != null && ed_nicheng.getText().length() > 0) 
		{
		} 
		else 
		{
			DisplayToast("竞标者昵称不能为空！");
			return false;
		}
		
		if(isNumeric(ed_yours.getText().toString()))
		{
		}
		else
		{
			DisplayToast("竞标价格只能为数字！");
			return false;
		}
		return true;
	}
	
	private boolean isNumeric(String str) 
	{
		//定义正则表达式
		String regNumeric="[0-9]*";
		Pattern pattern = Pattern.compile(regNumeric);
		return pattern.matcher(str).matches();
	}
}
