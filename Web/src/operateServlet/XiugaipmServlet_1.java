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
 * Servlet implementation class XiugaipmServlet
 */
@WebServlet("/android/xiugaipm_1")
public class XiugaipmServlet_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XiugaipmServlet_1() {
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
		String no=request.getParameter("Salerno");
		String name=request.getParameter("Salername");
		String pwd=request.getParameter("Salerpwd");
		String phone=request.getParameter("Salerphone");
		String email=request.getParameter("Saleremail");
		String realname=request.getParameter("Salerrealname");
		String address=request.getParameter("Saleraddress");
		String post=request.getParameter("Salerpost");
		String credit=request.getParameter("Salercredit");
		
		SqlServerConnection myxiugaipm1=new SqlServerConnection();
		myxiugaipm1.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		
		String sql="update SalerInfo set 用户名="+"'"+name+"',"+"密码="+"'"+pwd+"',"+"电话="+"'"+phone+"',"
				+"邮箱="+"'"+email+"',"+"姓名="+"'"+realname+"',"+"地址="+"'"+address+"',"+"邮编="+"'"+post+"',"
				+"信用度="+"'"+credit+"'"+"where 拍卖者编号="+no;
		try 
		{
			myxiugaipm1.query(sql);
			int SalerId=Integer.parseInt(no,10);
			if(SalerId>0)
			{
				request.getSession(true).setAttribute("SalerId", SalerId);
			}
			try
			{
				//把验证的GoodsId封装成JSONObject
				JSONObject jsonObject=new JSONObject().put("SalerId", SalerId);
				
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
