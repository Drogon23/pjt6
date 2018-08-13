package com.nts.pjt3_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.service.ProductService;

@Controller
@RequestMapping(path = "/review")
public class ReviewController {

	@Autowired
	ProductService productService;

	@GetMapping("/{displayInfoId}")
	public String reviewPage(@PathVariable(name = "displayInfoId") int displayInfoId, ModelMap modelMap) {

		ProductDto product = productService.getProduct(displayInfoId);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("displayInfoId", displayInfoId);
		return "review";
	}
}
