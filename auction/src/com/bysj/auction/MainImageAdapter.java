package com.bysj.auction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * ����������������ʵ��
 * @author LuChang
 */
public class MainImageAdapter extends BaseAdapter  
{
	// ����Context
	private Context		mContext;
	// ������������ ��ͼƬԴ
	private Integer[]	mImageIds= 
	{ 
			R.drawable.button1, 
			R.drawable.button2, 
			R.drawable.button3,
			R.drawable.gonggao
	};
   /*���췽��*/
	public MainImageAdapter(Context c)
	{
		mContext = c;
	}

	// ��ȡͼƬ�ĸ���
	@Override
	public int getCount()
	{
		return mImageIds.length;
	}

	// ��ȡͼƬ�ڿ��е�λ��
	@Override
	public Object getItem(int position)
	{
		return position;
	}
	// ��ȡͼƬID
	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ImageView imageView;
		if (convertView == null)
		{
			// ��ImageView������Դ
			imageView = new ImageView(mContext);
			// ���ò��� ͼƬ200��200��ʾ
			imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
			// ������ʾ��������
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
		else
		{
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(mImageIds[position]);		
		return imageView;
	}	
}

