package registerServlet;

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
 * Servlet implementation class Register
 */
@WebServlet("/android/register")
public class RegisterServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() 
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userName=request.getParameter("userName");
		String userPwd=request.getParameter("userPwd");
		String userMobilePhone=request.getParameter("userMobilePhone");
		String userEmail=request.getParameter("userEmail");
		String userRealName=request.getParameter("userRealName");
		String userAddress=request.getParameter("userAddress");
		String userPost=request.getParameter("userPost");
		String userCredit=request.getParameter("userCredit");
		int rows=20160100;
		SqlServerConnection myRegister=new SqlServerConnection();
		myRegister.ConnectsqlServer("网上拍卖管理系统", "sa", "luchang");
		String str="select * from SalerInfo";
		try {
			ResultSet sct=myRegister.query(str);
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
		String sql="insert into SalerInfo(拍卖者编号,用户名,密码,电话,邮箱,姓名,地址,邮编,信用度)"
		+" values('"+(rows+1)+"','"+userName+"',"+"'"+userPwd+"',"+"'"+userMobilePhone+"',"+"'"+userEmail+"',"+"'"+userRealName
		+"',"+"'"+userAddress+"',"+"'"+userPost+"',"+"'"+userCredit+"')";
		try 
		{
			myRegister.query(sql);
			int UserId=rows;
			if(UserId>0)
			{
				request.getSession(true).setAttribute("UserId", UserId);
			}
			try
			{
				//把验证的userId封装成JSONObject
				JSONObject jsonObject=new JSONObject().put("UserId", UserId);
				
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
