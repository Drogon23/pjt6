package com.nts.pjt3_4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.pjt3_4.dto.DisplayInfoImage;
import com.nts.pjt3_4.dto.Product;
import com.nts.pjt3_4.dto.ProductImage;
import com.nts.pjt3_4.service.DisplayInfoImageService;
import com.nts.pjt3_4.service.ProductImageService;
import com.nts.pjt3_4.service.ProductService;

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
	public String productDetail(@RequestParam(name = "id") int displayInfoId, ModelMap modelMap) {

		DisplayInfoImage displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		Product product = productService.getProduct(displayInfoId);
		ProductImage productImage = productImageService.getProductMainImage(product.getId());
		modelMap.addAttribute("displayInfoImage", displayInfoImage);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("productImage", productImage);

		return "detail";
	}
}
