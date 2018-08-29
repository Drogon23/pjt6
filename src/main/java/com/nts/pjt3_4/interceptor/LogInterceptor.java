package com.nts.pjt3_4.interceptor;

import java.time.LocalDateTime;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.debug("request URL : {}, reqeust Time : {}, client IP : {}", request.getRequestURL(), LocalDateTime.now(),
			request.getRemoteAddr());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		if (response.isCommitted() == false) {
			String rsvEmail = null;
			boolean find = false;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("rsvEmail".equals(cookie.getName())) {
						find = true;
						rsvEmail = cookie.getValue();
					}
				}
			}
			if (find == false) {
				rsvEmail = ""; 
			}
			ModelMap modelMap = modelAndView.getModelMap();
			modelMap.addAttribute("rsvEmail", rsvEmail);
		}
	}
}
