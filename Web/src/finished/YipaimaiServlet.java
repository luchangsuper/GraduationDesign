package finished;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class YipaimaiServlet
 */
@WebServlet("/android/add_yipaimai")
public class YipaimaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YipaimaiServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("paimainame");
		String mingzi=request.getParameter("paimaimz");
		String price=request.getParameter("paimaiprice");
		int transprice=Integer.parseInt(price,10);
		SqlServerConnection mypaimai=new SqlServerConnection();
		mypaimai.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		String str="select * from GoodsInfo where 商品名称="+"'"+name+"'";
		try 
		{
			ResultSet yipaimai=mypaimai.query(str);
			String paimaino="";
			String paimainame="";
			String paimaidesc="";
			String paimaistart="";
			while(yipaimai.next())
			{
				paimaino=yipaimai.getString("商品编号").trim();
				paimainame=yipaimai.getString("商品名称").trim();
				paimaidesc=yipaimai.getString("商品描述").trim();
				paimaistart=yipaimai.getString("商品起始价").trim();
			}
			int transpaimaino=Integer.parseInt(paimaino,10);
			int transpaimaistart=Integer.parseInt(paimaistart,10);
			String paimaisql="insert into SaledGoodsInfo(商品编号,商品名称,商品描述,商品起始价,商品最终竞标价,竞标者姓名)"
			+" values("+transpaimaino+",'"+paimainame+"',"+"'"+paimaidesc+"',"+transpaimaistart
			+","+transprice+","+"'"+mingzi+"')";
			mypaimai.query(paimaisql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
