package saleServlet;

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
 * Servlet implementation class SaleServlet
 */
@WebServlet("/android/add")
public class SaleServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleServlet() 
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
		String name=request.getParameter("Goodsname");
		String desc=request.getParameter("Goodsdesc");
		String price=request.getParameter("Goodsprice");
		String status=request.getParameter("Goodsstatus");
		String number=request.getParameter("Goodsnumber");
		String time=request.getParameter("Goodstime");
		String ID=request.getParameter("SalerID");
		
		int rows=0;
		SqlServerConnection myadd=new SqlServerConnection();
		myadd.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		String str="select * from GoodsInfo";
		try 
		{
			ResultSet sct=myadd.query(str);
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
		
		String sql="insert into GoodsInfo(商品编号,商品名称,商品描述,商品起始价,商品状态,商品数量,商品上货时间,拍卖者昵称)"
		+" values('"+(rows+1)+"','"+name+"',"+"'"+desc+"',"+"'"+price+"',"+"'"+status+"',"+"'"+number+"',"+"'"+time+"',"+"'"+ID+"')";
		try 
		{
			myadd.query(sql);
			int GoodsId=rows;
			if(GoodsId>0)
			{
				request.getSession(true).setAttribute("GoodsId", GoodsId);
			}
			try
			{
				//把验证的GoodsId封装成JSONObject
				JSONObject jsonObject=new JSONObject().put("GoodsId", GoodsId);
				
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
