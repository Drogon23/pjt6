package com.nts.pjt3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3.dto.DisplayInfoImage;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.service.DisplayInfoImageService;
import com.nts.pjt3.service.ProductImageService;
import com.nts.pjt3.service.ProductService;

@Controller
@RequestMapping(path = "/detail")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@GetMapping
	public String productDetail(
		@RequestParam(name = "id") int displayInfoId,
		ModelMap modelMap) {
		Product product = productService.getProduct(displayInfoId);
		ProductImage productImage = productImageService.getProductMainImage(product.getId());
		DisplayInfoImage displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("productImage", productImage);
		modelMap.addAttribute("displayInfoImage", displayInfoImage);
		
		return "detail";
	}
}
