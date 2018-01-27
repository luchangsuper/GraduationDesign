package saleServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class SaledIndex
 */
@WebServlet("/android/saled")
public class SaledIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaledIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String hanziname="";
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String loginusername=request.getParameter("loginusername");
		SqlServerConnection myIndex=new SqlServerConnection();
		myIndex.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		
		String str1="select * from SalerInfo where �û���="+"'"+loginusername+"'";
		String str2="select * from BiderInfo where �û���="+"'"+loginusername+"'";
		try 
		{
			ResultSet temprset1 = myIndex.query(str1);
			while(temprset1.next())
			{
				hanziname=temprset1.getString("����");
			}
			System.out.println(hanziname);
			if(hanziname.equals(""))
			{
				ResultSet temprset2 = myIndex.query(str2);
				while(temprset2.next())
				{
					hanziname=temprset2.getString("����");
				}
				System.out.println(hanziname);
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		String sql="select * from SaledGoodsInfo where ����������<>"+"'"+hanziname+"'";
		String sql="select * from SaledGoodsInfo";
		ResultSet rset;
		
		try 
		{
			rset = myIndex.query(sql);
			JSONArray array = new JSONArray();
			
			// ��ȡ����  
			ResultSetMetaData metaData = rset.getMetaData();  
			int columnCount = metaData.getColumnCount(); 
			
			while(rset.next())
			{
				JSONObject jsonObject = new JSONObject();
				// ����ÿһ��  
				for (int i = 1; i <= columnCount; i++) 
				{
					String columnName =metaData.getColumnLabel(i);  
					String value = rset.getString(columnName);  
					try 
					{
						//���ø�ʽΪtext/json
			            response.setContentType("text/json");
			            
			            //�����ַ���Ϊ'UTF-8'
			            response.setCharacterEncoding("UTF-8");
						jsonObject.put(columnName, value);
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }
				array.put(jsonObject);
			}
			response.getWriter().println(array.toString());
			System.out.println(array.toString());
			System.out.println("�������Ѿ������Ӧ��");
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
