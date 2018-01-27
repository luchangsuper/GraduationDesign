package operateServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class XiugaijpServlet
 */
@WebServlet("/android/xiugaijp_1")
public class XiugaijpServlet_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XiugaijpServlet_1() {
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
		String no=request.getParameter("Biderno");
		String name=request.getParameter("Bidername");
		String pwd=request.getParameter("Biderpwd");
		String phone=request.getParameter("Biderphone");
		String email=request.getParameter("Bideremail");
		String realname=request.getParameter("Biderrealname");
		String address=request.getParameter("Bideraddress");
		String post=request.getParameter("Biderpost");
		
		SqlServerConnection myxiugaijp1=new SqlServerConnection();
		myxiugaijp1.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		
		String sql="update BiderInfo set 用户名="+"'"+name+"',"+"密码="+"'"+pwd+"',"+"电话="+"'"+phone+"',"
				+"邮箱="+"'"+email+"',"+"姓名="+"'"+realname+"',"+"地址="+"'"+address+"',"+"邮编="+"'"+post+"'"
				+"where 竞标者编号="+no;
		try 
		{
			myxiugaijp1.query(sql);
			int BiderId=Integer.parseInt(no,10);
			if(BiderId>0)
			{
				request.getSession(true).setAttribute("BiderId", BiderId);
			}
			try
			{
				//把验证的GoodsId封装成JSONObject
				JSONObject jsonObject=new JSONObject().put("BiderId", BiderId);
				
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
