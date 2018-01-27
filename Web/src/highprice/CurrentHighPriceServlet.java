package highprice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class CurrentHighPriceServlet
 */
@WebServlet("/android/currenthighprice")
public class CurrentHighPriceServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentHighPriceServlet() 
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
		String currentname=request.getParameter("currentname");
		String currenthighprice=request.getParameter("currenthighprice");
		
		SqlServerConnection mycurrent=new SqlServerConnection();
		mycurrent.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		
		String sql="update Highprice set 商品当前最高出价="+"'"+currenthighprice+"' where 商品名称="+"'"+currentname+"'";
		try 
		{
			mycurrent.query(sql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
