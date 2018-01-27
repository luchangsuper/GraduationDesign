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
 * Servlet implementation class update_1
 */
@WebServlet("/android/update_1")
public class update_1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update_1() {
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
		String no=request.getParameter("Goodsno");
		String name=request.getParameter("Goodsname");
		String desc=request.getParameter("Goodsdesc");
		String price=request.getParameter("Goodsprice");
		String status=request.getParameter("Goodsstatus");
		String number=request.getParameter("Goodsnumber");
		String time=request.getParameter("Goodstime");
		String ID=request.getParameter("SalerID");
		
		SqlServerConnection myupdate1=new SqlServerConnection();
		myupdate1.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		
		String sql="update GoodsInfo set ��Ʒ����="+"'"+name+"',"+"��Ʒ����="+"'"+desc+"',"+"��Ʒ��ʼ��="+"'"+price+"',"
				+"��Ʒ״̬="+"'"+status+"',"+"��Ʒ����="+"'"+number+"',"+"��Ʒ�ϻ�ʱ��="+"'"+time+"',"+"�������ǳ�="+"'"+ID+"' where ��Ʒ���="+no;
		try 
		{
			myupdate1.query(sql);
			int GoodsId=Integer.parseInt(no,10);
			if(GoodsId>0)
			{
				request.getSession(true).setAttribute("GoodsId", GoodsId);
			}
			try
			{
				//����֤��GoodsId��װ��JSONObject
				JSONObject jsonObject=new JSONObject().put("GoodsId", GoodsId);
				
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
