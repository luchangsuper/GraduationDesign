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
 * Servlet implementation class IndexjingpaiServlet
 */
@WebServlet("/android/index_jp")
public class IndexjingpaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexjingpaiServlet() {
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
		String jpusername=request.getParameter("username");
		SqlServerConnection jingpai_info=new SqlServerConnection();
		jingpai_info.ConnectsqlServer("������������ϵͳ", "sa", "luchang");
		String str="select * from BiderInfo where �û���="+"'"+jpusername+"'";
		
		try 
		{
			ResultSet infojingpai=jingpai_info.query(str);
			JSONArray array = new JSONArray();
			// ��ȡ����  
			ResultSetMetaData metaData = infojingpai.getMetaData();  
			int columnCount = metaData.getColumnCount(); 
			
			while(infojingpai.next())
			{
				JSONObject jsonObject = new JSONObject();
				// ����ÿһ��  
				for (int i = 1; i <= columnCount; i++) 
				{
					String columnName =metaData.getColumnLabel(i);  
					String value = infojingpai.getString(columnName);  
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
