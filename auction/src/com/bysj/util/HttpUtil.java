package com.bysj.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtil
{
	// ����HttpClient����
	public static HttpClient httpClient = new DefaultHttpClient();
	public static final String BASE_URL ="http://192.168.155.1:8080/Web/android/";
//	public static final String BASE_URL ="http://59.73.145.230:8080/Web/android/";
//	public static final String BASE_URL ="http://172.24.1.211:8080/Web/android/";
//	public static final String BASE_URL ="http://59.73.154.190:8080/Web/android/";
//	public static final String BASE_URL ="http://169.254.89.203:8080/Web/android/";
	/**
	 *
	 * @param url ���������URL
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static synchronized String getRequest(final String url)throws Exception
	{
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>()
				{
					@Override
					public String call() throws Exception
					{
						// ����HttpGet����
						HttpGet get = new HttpGet(url);
						
						// ����GET����
						HttpResponse httpResponse=new DefaultHttpClient().execute(get);
						
						System.out.println(httpResponse.getStatusLine().getStatusCode());
						// ����������ɹ��ط�����Ӧ
						if (httpResponse.getStatusLine().getStatusCode() == 200)
						{
							// ��ȡ��������Ӧ�ַ���
							String result = EntityUtils.toString(httpResponse.getEntity());
							System.out.println(result);
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}

	/**
	 * @param url ���������URL
	 * @param params �������
	 * @return ��������Ӧ�ַ���
	 * @throws Exception
	 */
	public static synchronized String postRequest(final String url, final Map<String ,String> rawParams)throws Exception
	{
		FutureTask<String> task = new FutureTask<String>(
				new Callable<String>()
				{
					@Override
					public String call() throws Exception
					{
						// ����HttpPost����
						HttpPost post = new HttpPost(url);
						// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
						List<NameValuePair> params = new ArrayList<NameValuePair>();
						for(String key : rawParams.keySet())
						{
							//��װ�������
							params.add(new BasicNameValuePair(key, rawParams.get(key)));
						}
						// �����������
						post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
						
						// ����POST����
						HttpResponse httpResponse=new DefaultHttpClient().execute(post);
						// ����������ɹ��ط�����Ӧ
						System.out.println(httpResponse.getStatusLine().getStatusCode());
						if (httpResponse.getStatusLine().getStatusCode() == 200)
						{
							// ��ȡ��������Ӧ�ַ���
							String result = EntityUtils.toString(httpResponse.getEntity());
							return result;
						}
						return null;
					}
				});
		new Thread(task).start();
		return task.get();
	}
}

