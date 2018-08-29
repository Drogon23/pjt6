package com.nts.pjt3_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.pjt3_4.dto.DisplayInfoImageDto;
import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.service.DisplayInfoImageService;
import com.nts.pjt3_4.service.ProductService;

@Controller
@RequestMapping(path = "/detail")
public class ProductDetailController {

	@Autowired
	private ProductService productService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@GetMapping("/{displayInfoId}")
	public String productDetail(@PathVariable(name = "displayInfoId") int displayInfoId, ModelMap modelMap) {

		DisplayInfoImageDto displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		ProductDto product = productService.getProduct(displayInfoId);
		modelMap.addAttribute("displayInfoImage", displayInfoImage);
		modelMap.addAttribute("product", product);

		return "detail";
	}
}
