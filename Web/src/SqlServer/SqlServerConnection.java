package SqlServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlServerConnection 
{
	private Connection conn=null;
	private Statement stmt=null;
	public ResultSet rset=null;
	
	public void ConnectsqlServer(String database,String user,String passwd)
	{
		String url="jdbc:sqlserver://localhost:1433;DatabaseName = ������������ϵͳ";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,user,passwd);
			System.out.println("�������ݿ�������ɹ���");
			stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("�������ݿ������ʧ�ܣ�");
		}
	}
	
	public ResultSet query(String sql) throws SQLException
	{
		if(sql==null||sql.equals(""))
		{
			System.out.println("�޲�ѯ��䣡");
			return null;
		}
		else{System.out.println(sql);}
		try
		{
			rset=stmt.executeQuery(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rset;
	}
	
	public void update(String sql) throws SQLException
	{
		int i;
		if(sql==null||sql.equals(""))
			System.out.println("�޸�����䣡");
		i=stmt.executeUpdate(sql);
		System.out.println("Ӱ�������Ϊ��"+i);
	}
	
	public ResultSet excute(String sql) throws SQLException
	{
		boolean t;
		t=stmt.execute(sql);
		if(t==true)
		{
			rset=stmt.getResultSet();
			System.out.println("��ѯ�ɹ���");
			return rset;
		}
		else
		{
			int i=stmt.getUpdateCount();
			System.out.println("���µļ�¼���ǣ�"+i);
			return null;
		}
	}
	
	public void ClosesqlServer() throws SQLException
	{
		if(stmt!=null)
		{
			rset.close();
			rset=null;
		}
		if(stmt!=null)
		{
			stmt.close();
			stmt=null;
		}
		if(conn!=null)
		{
			conn.close();
			conn=null;
		}
	}
	
	public boolean excuteSql(String sql) throws SQLException
	{
		if(sql==null||sql.equals(""))
		{
			System.out.println("�޲�ѯ��䣡");
			return false;
		}
		else
		{
			System.out.println(sql);
		}
		try
		{
			stmt.execute(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

