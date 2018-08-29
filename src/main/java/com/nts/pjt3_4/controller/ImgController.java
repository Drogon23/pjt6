package com.nts.pjt3_4.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/image")
public class ImgController {

	@Autowired
	private ServletContext context;

	@ResponseBody
	@GetMapping(value = "/**", produces = "image/*")
	public byte[] showImage(HttpServletRequest request) throws IOException {
		String fileName = request.getRequestURI();
		fileName = fileName.replace("/image", "");
		InputStream in = context.getResourceAsStream("/WEB-INF" + fileName);
		return IOUtils.toByteArray(in);
	}

}
