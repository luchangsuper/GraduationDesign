package time;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class TimeInfoServlet
 */
@WebServlet("/android/fabutime")
public class TimeInfoServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeInfoServlet() 
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String no=request.getParameter("Goodsno");
		String name=request.getParameter("Goodsname");
		String start=request.getParameter("Goods_start_time");
		String end=request.getParameter("Goods_end_time");
		
		SqlServerConnection mytime=new SqlServerConnection();
		mytime.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		int rows=0;
		String str="select * from Time";
		try 
		{
			ResultSet sct=mytime.query(str);
			while(sct.next())
			{
				rows++;
			}
			System.out.println(rows);
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql="insert into Time(商品编号,商品名称,商品上货时间,拍卖截止时间)"
		+" values('"+(rows+1)+"','"+name+"',"+"'"+start+"',"+"'"+end+"')";
		
		try 
		{
			mytime.query(sql);
			int time=rows;
			if(time>0)
			{
				request.getSession(true).setAttribute("timeId", time);
			}
			try
			{
				//把验证的GoodsId封装成JSONObject
				JSONObject jsonObject=new JSONObject().put("time", time);
				
				//输出响应
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
}
