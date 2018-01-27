package loginServlet;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/android/login")
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		System.out.println("hello,world!get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		System.out.println("hello,world!post");
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		System.out.println("POST:"+user+":"+pass);
		try 
		{
			int UserId=check(user,pass);
			if(UserId>0)
			{
				request.getSession(true).setAttribute("UserId", UserId);
			}
			try
			{
				//����֤��userId��װ��JSONObject
				JSONObject jsonObject=new JSONObject().put("UserId", UserId);
				
				//�����Ӧ
				response.getWriter().println(jsonObject.toString());
			}
			catch(org.json.JSONException ex)
			{
				ex.printStackTrace();
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int check(String suser,String spass) throws Exception
	{
		String u_name=null,u_pass=null;
		SqlServerConnection mysqlserver=new SqlServerConnection();
		mysqlserver.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		String sql1="select * from SalerInfo where �û���='"+suser+"'";
		String sql2="select * from BiderInfo where �û���='"+suser+"'";
		ResultSet rset=mysqlserver.query(sql1);
		while(rset.next())
		{
			u_name=rset.getString("�û���");
			u_pass=rset.getString("����");
		}
		if(suser.trim().equals(u_name)&&spass.trim().equals(u_pass))
		{
			mysqlserver.ClosesqlServer();
			System.out.println("�����û���¼�ɹ���");
			return 1;
		}
		else
		{
			rset=mysqlserver.query(sql2);
			while(rset.next())
			{
				u_name=rset.getString("�û���");
				u_pass=rset.getString("����");
			}
			if(suser.trim().equals(u_name)&&spass.trim().equals(u_pass))
			{
				mysqlserver.ClosesqlServer();
				System.out.println("�����û���¼�ɹ���");
				return 1;
			}
			else
			{
				System.out.println("��ͨ�û���¼ʧ�ܣ����Ժ����ԣ�");
				return 0;
			}
		}
	}
}
