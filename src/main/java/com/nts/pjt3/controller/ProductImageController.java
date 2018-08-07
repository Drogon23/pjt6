package com.nts.pjt3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.service.ProductImageService;

@Controller
@RequestMapping(path = "/productImages")
public class ProductImageController {
	@Autowired
	private ProductImageService productImageService;

	@GetMapping("/{productId}")
	public RedirectView productImageName(@PathVariable(name = "productId") int productId) {

		ProductImage productImage = productImageService.getProductThImage(productId);
		String imgPath = "http://localhost:8080/" + productImage.getSaveFileName();
		return new RedirectView(imgPath);
	}
}
