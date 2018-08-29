package com.nts.pjt3_4.controller;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvListDto;
import com.nts.pjt3_4.service.RsvInfoService;

@Controller
@RequestMapping("/myreservation")
public class MyReservationController {

	@Autowired
	private RsvInfoService rsvInfoService;

	@GetMapping
	public String checkCookie(@CookieValue(value = "rsvEmail", required = false, defaultValue = "") String rsvEmail,
		ModelMap modelMap) {
		if ("".equals(rsvEmail)) {
			return "bookinglogin";
		} else {
			modelMap.addAttribute("rsvEmail", rsvEmail);
			List<RsvInfoDto> rsvInfoList = rsvInfoService.getRsvInfosByEmail(rsvEmail);
			int totalCount = rsvInfoList.size();
			RsvListDto rsvList = rsvInfoService.categorizeRsv(rsvInfoList);
			modelMap.addAttribute("totalCount", totalCount);
			if (totalCount > 0) {
				modelMap.addAttribute("canceledRsvList", rsvList.getCanceledRsvList());
				modelMap.addAttribute("usedRsvList", rsvList.getUsedRsvList());
				modelMap.addAttribute("notUsedRsvList", rsvList.getNotUsedRsvList());
			} else {
				modelMap.addAttribute("canceledRsvList", Collections.emptyList());
				modelMap.addAttribute("usedRsvList", Collections.emptyList());
				modelMap.addAttribute("notUsedRsvList", Collections.emptyList());
			}
			return "myreservation";
		}
	}

	@PostMapping
	public String myReservationPage(@RequestParam("resrv_email") String rsvEmail, HttpServletResponse response,
		ModelMap modelMap) {

		String regexEmail = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		if (Pattern.matches(regexEmail, rsvEmail)) {
			Cookie cookie = new Cookie("rsvEmail", rsvEmail);
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
			modelMap.addAttribute("rsvEmail", rsvEmail);
		}
		return "redirect:/myreservation";
	}
}
