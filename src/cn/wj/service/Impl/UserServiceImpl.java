package cn.wj.service.Impl;

import java.util.List;

import cn.wj.bean.User;
import cn.wj.dao.UserDao;
import cn.wj.dao.impl.UserDaoImpl;
import cn.wj.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();
	
	public User loginService(String uname, String pwd) {
		User u=userDao.checkUserLoginDao(uname, pwd);
		return u;
	}

	@Override
	public int userChangePwdService(String newPwd, int uid) {
		int index=userDao.userChangePwdDao(newPwd,uid);
		return index;
	}

	//显示所有用户信息
	public List<User> showAllUserServer() {
		// TODO Auto-generated method stub
		return userDao.showAllUserDao();
	}

}
