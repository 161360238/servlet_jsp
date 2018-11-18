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
			// 1����������
			Class.forName("com.mysql.jdbc.Driver");
			// 2����������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3������sql����
			String sql = "select * from t_user where uname=? and pwd=?";
			// 4������sql�������
			ps = conn.prepareStatement(sql);
			// 5����ռλ����ֵ
			ps.setString(1, uname);
			ps.setString(2, pwd);
			// 6��ִ��sql
			rs = ps.executeQuery();
			// 6���������
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
			// 1����������
			Class.forName("com.mysql.jdbc.Driver");
			// 2����������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3������sql����
			String sql = "update t_user set pwd=? where uid=?";
			// 4������sql�������
			ps = conn.prepareStatement(sql);
			// 5����ռλ����ֵ
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			// 6��ִ��sql
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
			// 1����������
			Class.forName("com.mysql.jdbc.Driver");
			// 2����������
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/407", "root", "161360238");
			// 3������sql����
			String sql = "select * from t_user";
			// 4������sql�������
			ps = conn.prepareStatement(sql);

			// 6��ִ��sql
			rs = ps.executeQuery();
			// 6���������
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

	//�û�ע��
	@Override
	public int userRegDao(User u) {
		//����jdbc����
		Connection conn=null;
		PreparedStatement ps=null;
		//��������
		int index=-1;
		try {
			//��������
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ����
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/407","root", "161360238");
			//����SQL����
			String sql="insert into t_user values(default,?,?,?,?,?)";
			//����SQL�������
			ps=conn.prepareStatement(sql);
			//��ռλ����ֵ
			ps.setString(1,u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			//ִ��
			index=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{//�ر���Դ
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
		//���ؽ��
		return index;
	}

}
