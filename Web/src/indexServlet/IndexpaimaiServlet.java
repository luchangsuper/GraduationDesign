package indexServlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import SqlServer.SqlServerConnection;

/**
 * Servlet implementation class IndexpaimaiServlet
 */
@WebServlet("/android/index_pm")
public class IndexpaimaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexpaimaiServlet() {
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
		String pmusername=request.getParameter("username");
		SqlServerConnection paimai_info=new SqlServerConnection();
		paimai_info.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		String str="select * from SalerInfo where �û���="+"'"+pmusername+"'";
		
		try 
		{
			ResultSet infopaimai=paimai_info.query(str);
			JSONArray array = new JSONArray();
			// ��ȡ����  
			ResultSetMetaData metaData = infopaimai.getMetaData();  
			int columnCount = metaData.getColumnCount(); 
			
			while(infopaimai.next())
			{
				JSONObject jsonObject = new JSONObject();
				// ����ÿһ��  
				for (int i = 1; i <= columnCount; i++) 
				{
					String columnName =metaData.getColumnLabel(i);  
					String value = infopaimai.getString(columnName);  
					try 
					{
						//���ø�ʽΪtext/json
			            response.setContentType("text/json");
			            
			            //�����ַ���Ϊ'UTF-8'
			            response.setCharacterEncoding("UTF-8");
						jsonObject.put(columnName, value);
					} 
					catch (JSONException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }
				array.put(jsonObject);
			}
			response.getWriter().println(array.toString());
			System.out.println(array.toString());
			System.out.println("�������Ѿ������Ӧ��");
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
