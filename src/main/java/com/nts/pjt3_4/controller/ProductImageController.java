package com.nts.pjt3_4.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.service.ProductImageService;

@Controller
@RequestMapping(path = "/productImages")
public class ProductImageController {

	@Autowired
	private ServletContext context;
	@Autowired
	private ProductImageService productImageService;
	private static final String ETC_Type = "et";

	@ResponseBody
	@GetMapping(value = "/{productId}/{type}", produces = "image/*")
	public byte[] getProductImage(@PathVariable(name = "productId") int productId,
		@PathVariable(name = "type") String type) throws IOException {
		ProductImageDto productThImage = productImageService.getProductImageByType(productId, type);
		InputStream in = context.getResourceAsStream("/WEB-INF/" + productThImage.getSaveFileName());
		return IOUtils.toByteArray(in);
	}

	@ResponseBody
	@GetMapping("/etc/{productId}")
	public String getProductEtcImage(@PathVariable(name = "productId") int productId) {
		ProductImageDto productEtcImage = productImageService.getProductImageByType(productId, ETC_Type);
		if (productEtcImage != null) {
			return "success";
		} else {
			return "fail";
		}
	}

}
