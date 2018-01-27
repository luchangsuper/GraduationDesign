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
 * Servlet implementation class YijingpaiServlet
 */
@WebServlet("/android/add_yijingpai")
public class YijingpaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public YijingpaiServlet() {
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
		String name=request.getParameter("jingpainame");
		String mingzi=request.getParameter("jingpaimz");
		String price=request.getParameter("jingpaiprice");
		int transprice=Integer.parseInt(price,10);
		SqlServerConnection myjingpai=new SqlServerConnection();
		myjingpai.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		String str="select * from GoodsInfo where ��Ʒ����="+"'"+name+"'";
		try 
		{
			ResultSet yijingpai=myjingpai.query(str);
			String jingpainame="";
			String jingpaidesc="";
			String jingpaistart="";
			while(yijingpai.next())
			{
				jingpainame=yijingpai.getString("��Ʒ����").trim();
				jingpaidesc=yijingpai.getString("��Ʒ����").trim();
				jingpaistart=yijingpai.getString("��Ʒ��ʼ��").trim();
			}
			
			int transjingpaistart=Integer.parseInt(jingpaistart,10);
			String jingpaisql="insert into BidedGoodsInfo(�ҵ�����,��Ʒ����,��Ʒ����,��Ʒ��ʼ��,�ҵľ����)"
			+" values("+"'"+mingzi+"',"+"'"+jingpainame+"',"+"'"+jingpaidesc+"',"+transjingpaistart+","
			+transprice+")";
			myjingpai.query(jingpaisql);
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
