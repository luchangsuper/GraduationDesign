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
		myxiugaijp1.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		
		String sql="update BiderInfo set �û���="+"'"+name+"',"+"����="+"'"+pwd+"',"+"�绰="+"'"+phone+"',"
				+"����="+"'"+email+"',"+"����="+"'"+realname+"',"+"��ַ="+"'"+address+"',"+"�ʱ�="+"'"+post+"'"
				+"where �����߱��="+no;
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
				//����֤��GoodsId��װ��JSONObject
				JSONObject jsonObject=new JSONObject().put("BiderId", BiderId);
				
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
}
