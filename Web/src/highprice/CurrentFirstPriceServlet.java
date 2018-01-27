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
 * Servlet implementation class CurrentFirstPriceServlet
 */
@WebServlet("/android/currentfirstprice")
public class CurrentFirstPriceServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrentFirstPriceServlet() 
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
		String currentfirstname=request.getParameter("currentfirstname");
		String currentfirstprice=request.getParameter("currentfirstprice");
		
		SqlServerConnection mycurrent=new SqlServerConnection();
		mycurrent.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		
		String sql="update GoodsInfo set 商品起始价="+"'"+currentfirstprice+"' where 商品名称="+"'"+currentfirstname+"'";
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
