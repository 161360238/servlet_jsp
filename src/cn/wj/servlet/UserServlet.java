package cn.wj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.wj.bean.User;
import cn.wj.service.UserService;
import cn.wj.service.Impl.UserServiceImpl;

public class UserServlet extends HttpServlet {

	private UserService userService = new UserServiceImpl();

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		String oper = request.getParameter("oper");
		if (oper.equals("login")) {  //登录
 			loginController(request, response);
		}else if(oper.equals("out")){  //登出
			loginoutController(request,response);
		}else if(oper.equals("pwd")){   //修改密码
			userChangePwd(request,response);
		}else if(oper.equals("show")){
			showAllUser(request,response);
		}
	}

	//查看所有用户信息
	private void showAllUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> users=new ArrayList<User>();
		users=userService.showAllUserServer();
		request.setAttribute("lu", users);
		request.getRequestDispatcher("/user/showUser.jsp").forward(request, response);
	}


	private void userChangePwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取信息
		String newPwd=request.getParameter("newPwd");
		User user=(User)request.getSession().getAttribute("user");
		int uid=user.getUid();
		//调用service层，处理
			int index=userService.userChangePwdService(newPwd,uid);
			if(index>0){
				request.getSession().setAttribute("pwd", true);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
	}


	/**
	 * 实现登出的servlet
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void loginoutController(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			HttpSession session=request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	/**
	 * 实现登录功能的servlet
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void loginController(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		User user = userService.loginService(uname, pwd);
		
		if (user != null) {
			HttpSession session=request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath()+"/main/main.jsp");
			return ;
		}else{
			request.setAttribute("flag", 0);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return ;
		}

	}

	
}
