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
		mypaimai.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		String str="select * from GoodsInfo where ��Ʒ����="+"'"+name+"'";
		try 
		{
			ResultSet yipaimai=mypaimai.query(str);
			String paimaino="";
			String paimainame="";
			String paimaidesc="";
			String paimaistart="";
			while(yipaimai.next())
			{
				paimaino=yipaimai.getString("��Ʒ���").trim();
				paimainame=yipaimai.getString("��Ʒ����").trim();
				paimaidesc=yipaimai.getString("��Ʒ����").trim();
				paimaistart=yipaimai.getString("��Ʒ��ʼ��").trim();
			}
			int transpaimaino=Integer.parseInt(paimaino,10);
			int transpaimaistart=Integer.parseInt(paimaistart,10);
			String paimaisql="insert into SaledGoodsInfo(��Ʒ���,��Ʒ����,��Ʒ����,��Ʒ��ʼ��,��Ʒ���վ����,����������)"
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
