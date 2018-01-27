package operateServlet;

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
 * Servlet implementation class ShanchujpServlet
 */
@WebServlet("/android/shanchujp")
public class ShanchujpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShanchujpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int flag=0,rows=0;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String delno=request.getParameter("delno");
		SqlServerConnection mydel=new SqlServerConnection();
		mydel.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		int no=Integer.parseInt(delno,10);
		String sql="select * from BiderInfo";
		try 
		{
			ResultSet sct=mydel.query(sql);	
			while(sct.next())
			{
				rows++;
			}
			System.out.println(rows);
			rows+=201600;
			if(rows>=no)
			{
				String str="delete from BiderInfo where 竞标者编号="+delno;
				try {
					mydel.excuteSql(str);
					flag=1;
					if(flag==1)
					{
						request.getSession(true).setAttribute("flag", flag);
					}
					try
					{
						//把验证的GoodsId封装成JSONObject
						JSONObject jsonObject=new JSONObject().put("flag", flag);
						
						//输出响应
						response.getWriter().println(jsonObject.toString());
					}
					catch(org.json.JSONException ex)
					{
						ex.printStackTrace();
					}
				} 
				catch (SQLException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				try
				{
					//把验证的GoodsId封装成JSONObject
					JSONObject jsonObject=new JSONObject().put("flag", flag);
					
					//输出响应
					response.getWriter().println(jsonObject.toString());
				}
				catch(org.json.JSONException ex)
				{
					ex.printStackTrace();
				}
			}
		} 
		catch (SQLException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
