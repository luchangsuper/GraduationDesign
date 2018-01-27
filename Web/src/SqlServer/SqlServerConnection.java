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
		String url="jdbc:sqlserver://localhost:1433;DatabaseName = 网上拍卖管理系统";
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			Connection conn=DriverManager.getConnection(url,user,passwd);
			System.out.println("连接数据库服务器成功！");
			stmt=conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("连接数据库服务器失败！");
		}
	}
	
	public ResultSet query(String sql) throws SQLException
	{
		if(sql==null||sql.equals(""))
		{
			System.out.println("无查询语句！");
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
			System.out.println("无更新语句！");
		i=stmt.executeUpdate(sql);
		System.out.println("影响的行数为："+i);
	}
	
	public ResultSet excute(String sql) throws SQLException
	{
		boolean t;
		t=stmt.execute(sql);
		if(t==true)
		{
			rset=stmt.getResultSet();
			System.out.println("查询成功！");
			return rset;
		}
		else
		{
			int i=stmt.getUpdateCount();
			System.out.println("更新的记录数是："+i);
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
			System.out.println("无查询语句！");
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

