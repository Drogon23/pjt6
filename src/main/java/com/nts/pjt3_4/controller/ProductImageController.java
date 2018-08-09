package com.nts.pjt3_4.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.nts.pjt3_4.dto.ProductImage;
import com.nts.pjt3_4.service.ProductImageService;

@Controller
@RequestMapping(path = "/productImages")
public class ProductImageController {
	@Autowired
	private ProductImageService productImageService;

	@GetMapping("/{productId}/ma")
	public RedirectView getProductThImage(@PathVariable(name = "productId") int productId) {
		ProductImage productThImage = productImageService.getProductThImage(productId);
		String imgPath = "http://localhost:8080/" + productThImage.getSaveFileName();
		return new RedirectView(imgPath);
	}

	@ResponseBody
	@GetMapping("/{productId}/etc")
	public Map<String, Object> getProductEtcImage(@PathVariable(name = "productId") int productId) {
		List<ProductImage> productEtcImageList = productImageService.getProductEtcImage(productId);
		Map<String, Object> map = new HashMap<>();
		map.put("productEtcImageList", productEtcImageList);
		return map;
	}
}
