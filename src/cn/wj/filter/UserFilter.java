package cn.wj.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserFilter
 */
@WebFilter("/UserFilter")
public class UserFilter implements Filter {

	public UserFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String oper=req.getParameter("oper");
		if (uri.endsWith(".js") || uri.endsWith(".css")     //释放静态资源
				|| uri.endsWith(".html") || uri.endsWith(".jpg")
				|| uri.endsWith(".png") || uri.endsWith(".gif")) {
			chain.doFilter(request, response);
		}else if(uri.contains("login")||uri.contains("index")||uri.contains("reg")||"reg".equals(oper)||"login".equals(oper)){   //释放登录、注册页面
			chain.doFilter(request, response);
		} else {
			HttpSession session = (HttpSession) req.getSession();
			Object obj = session.getAttribute("user");
			if (obj != null) {
				chain.doFilter(request, response);		  //如果已经登录，放行		
			}else{	
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
