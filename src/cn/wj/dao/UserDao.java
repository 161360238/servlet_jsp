package cn.wj.dao;

import java.util.List;

import cn.wj.bean.User;

public interface UserDao {
	/*
	 * �����û�������ȥ���ݿ���Ƿ��������
	 */
	User checkUserLoginDao(String uname,String pwd);

	int userChangePwdDao(String newPwd, int uid);

	List<User> showAllUserDao();

	int userRegDao(User u);

}
