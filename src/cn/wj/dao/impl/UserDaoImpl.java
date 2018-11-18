package cn.wj.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.wj.bean.User;
import cn.wj.dao.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = null;
		try {
			// 1，加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2，建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3，创建sql命令
			String sql = "select * from t_user where uname=? and pwd=?";
			// 4，创建sql命令对象
			ps = conn.prepareStatement(sql);
			// 5，给占位符赋值
			ps.setString(1, uname);
			ps.setString(2, pwd);
			// 6，执行sql
			rs = ps.executeQuery();
			// 6，遍历结果
			while (rs.next()) {
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return u;
	}

	@Override
	public int userChangePwdDao(String newPwd, int uid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int index = -1;
		User u = null;
		try {
			// 1，加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2，建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3，创建sql命令
			String sql = "update t_user set pwd=? where uid=?";
			// 4，创建sql命令对象
			ps = conn.prepareStatement(sql);
			// 5，给占位符赋值
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			// 6，执行sql
			index = ps.executeUpdate();
			return index;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return index;
	}

	@Override
	public List<User> showAllUserDao() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users=new ArrayList<>();
		try {
			// 1，加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 2，建立连接
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3，创建sql命令
			String sql = "select * from t_user";
			// 4，创建sql命令对象
			ps = conn.prepareStatement(sql);

			// 6，执行sql
			rs = ps.executeQuery();
			// 6，遍历结果
			while (rs.next()) {
				User u = new User();
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				users.add(u);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return users;
	}

	//用户注册
	@Override
	public int userRegDao(User u) {
		//声明jdbc对象
		Connection conn=null;
		PreparedStatement ps=null;
		//声明变量
		int index=-1;
		try {
			//加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取连接
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/407","root", "161360238");
			//创建SQL命令
			String sql="insert into t_user values(default,?,?,?,?,?)";
			//创建SQL命令对象
			ps=conn.prepareStatement(sql);
			//给占位符赋值
			ps.setString(1,u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			//执行
			index=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{//关闭资源
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//返回结果
		return index;
	}

}
