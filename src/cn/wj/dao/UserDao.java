package cn.wj.dao;

import java.util.List;

import cn.wj.bean.User;

public interface UserDao {
	/*
	 * 根据用户名密码去数据库查是否有这个人
	 */
	User checkUserLoginDao(String uname,String pwd);

	int userChangePwdDao(String newPwd, int uid);

	List<User> showAllUserDao();

	int userRegDao(User u);

}
