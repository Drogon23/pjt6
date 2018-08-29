package com.nts.pjt3_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.service.ProductService;

@Controller
@RequestMapping(path = "/reviewWrite")
public class ReviewWriteController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{displayInfoId}")
	public String reviewWritePage(@PathVariable(name = "displayInfoId") int displayInfoId,
		@RequestParam("rsvId") int rsvId, ModelMap modelMap) {
		ProductDto product = productService.getProduct(displayInfoId);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("rsvId", rsvId);
		modelMap.addAttribute("displayInfoId", displayInfoId);
		return "reviewWrite";
	}
}
