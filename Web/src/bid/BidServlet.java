package bid;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class BidServlet
 */
@WebServlet("/android/bid")
public class BidServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BidServlet() 
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
			
		int tag=0;boolean flag=true;String ctime="";
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String yoursprice=request.getParameter("yoursprice");
		String number=request.getParameter("number");
		String nicheng=request.getParameter("nicheng");
		
		SqlServerConnection mybid=new SqlServerConnection();
		mybid.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		
		String statussql="select ��Ʒ״̬ from GoodsInfo where ��Ʒ����="+"'"+name+"'";
		try 
		{
			ResultSet myrset=mybid.query(statussql);
			while(myrset.next())
			{
				String status=myrset.getString("��Ʒ״̬");
				if(status.equals("������"))
				{
					tag=2;flag=false;
					try 
					{
						JSONObject jsonObject;
						request.getSession(true).setAttribute("tag", tag);
						jsonObject = new JSONObject().put("tag", tag);
						response.getWriter().println(jsonObject.toString());
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String end_timesql="select ������ֹʱ�� from Time where ��Ʒ����="+"'"+name+"'";
		try 
		{
			ResultSet myrset=mybid.query(end_timesql);
			while(myrset.next())
			{
				String end_time=myrset.getString("������ֹʱ��");
				ctime=time();
				System.out.println(ctime);
				System.out.println(end_time);
				if(end_time.compareTo(ctime)<0)
				{
					tag=3;flag=false;
					try 
					{
						JSONObject jsonObject;
						request.getSession(true).setAttribute("tag", tag);
						jsonObject = new JSONObject().put("tag", tag);
						response.getWriter().println(jsonObject.toString());
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql="insert into BidGoodsInfo(������Ʒ����,����۸�,��������,����������)"
				+" values("+"'"+name+"',"+"'"+yoursprice+"',"+"'"+number+"',"+"'"+nicheng+"')";
		try 
		{
			tag=1;int max=0;
			if(tag==1&&flag==true)
			{
				if(mybid.excuteSql(sql))
				{
					String maxsql="select max(����۸�) as ����۸� from BidGoodsInfo where ������Ʒ����="+"'"+name+"'";
					ResultSet maxset=mybid.query(maxsql);
					while(maxset.next())
					{
						max=maxset.getInt("����۸�");
					}
					int your=Integer.parseInt(yoursprice,10);
					if(max==your)
					{
						request.getSession(true).setAttribute("tag", tag);
						try
						{
							//����֤��Id��װ��JSONObject
							JSONObject jsonObject=new JSONObject().put("tag", tag);
							
							//�����Ӧ
							response.getWriter().println(jsonObject.toString());
						}
						catch(org.json.JSONException ex)
						{
							ex.printStackTrace();
						}
					}
					else
					{
						tag=4;
						request.getSession(true).setAttribute("tag", tag);
						try
						{
							//����֤��Id��װ��JSONObject
							JSONObject jsonObject=new JSONObject().put("tag", tag);
							
							//�����Ӧ
							response.getWriter().println(jsonObject.toString());
						}
						catch(org.json.JSONException ex)
						{
							ex.printStackTrace();
						}
					}
				}
				else
				{
					tag=2;
					request.getSession(true).setAttribute("tag", tag);
					try
					{
						//����֤��Id��װ��JSONObject
						JSONObject jsonObject=new JSONObject().put("tag", tag);
						
						//�����Ӧ
						response.getWriter().println(jsonObject.toString());
					}
					catch(org.json.JSONException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String time() 
	{
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}
}
