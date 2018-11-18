package cn.wj.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

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
		}else if(oper.equals("show")){  //显示所有用户功能
			showAllUser(request,response);
		}else if(oper.equals("reg")){  //用户注册功能
			userReg(request,response);
		}
	}

	
	//用户注册功能实现
	//注册用户
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//获取请求信息
			String uname=req.getParameter("uname");
			String pwd=req.getParameter("pwd");
			String sex=req.getParameter("sex");
			int age=req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
			String birth=req.getParameter("birth");
			String[] bs=null;
			if(birth!=""){
				bs=birth.split("/");
				birth=bs[2]+"-"+bs[0]+"-"+bs[1];
			}
			System.out.println(uname+":"+pwd+":"+sex+":"+age+":"+birth);
			User u=new User(0, uname, pwd, sex, age, birth);
		//处理请求信息
			//调用业务层处理
			int index=userService.userRegService(u);
		//响应处理结果
			if(index>0){
				//获取session
				HttpSession hs=req.getSession();
				hs.setAttribute("flag", "2");
				//重定向
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
		
	}

	//查看所有用户信息
	private void showAllUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<User> users=new ArrayList<User>();
		users=userService.showAllUserServer();
		request.setAttribute("lu", users);
		if(users!=null){
			request.getRequestDispatcher("/user/showUser.jsp").forward(request, response);
		}
	}

	//修改密码
	private void userChangePwd(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//获取信息
		String newPwd=request.getParameter("newPwd");
		User user=(User)request.getSession().getAttribute("user");
		int uid=user.getUid();
		//调用service层，处理
			int index=userService.userChangePwdService(newPwd,uid);
			if(index>0){
				request.getSession().setAttribute("flag", 1);
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
