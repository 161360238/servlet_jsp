package cn.wj.service;

import java.util.List;

import cn.wj.bean.User;

public interface UserService {
	User loginService(String uname,String pwd);

	int userChangePwdService(String newPwd, int uid);

	List<User> showAllUserServer();

	int userRegService(User u);
}
