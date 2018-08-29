package com.nts.pjt3_4.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.dto.ProductPrice;
import com.nts.pjt3_4.service.ProductImageService;
import com.nts.pjt3_4.service.ProductPriceService;
import com.nts.pjt3_4.service.ProductService;

@Controller
@RequestMapping(path = "/reserve")
public class ReserveController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPriceService productPriceService;
	private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.M.d");

	@GetMapping("/{displayInfoId}")
	public String reservePage(@PathVariable(name = "displayInfoId") int displayInfoId, ModelMap modelMap) {

		ProductDto product = productService.getProduct(displayInfoId);
		ProductImageDto productImage = productImageService.getProductImageByType(product.getId(), "ma");
		List<ProductPrice> productPrices = productPriceService.getProductPrices(product.getId());
		LocalDate currentDate = LocalDate.now();
		Random random = new Random();
		currentDate = currentDate.plusDays(random.nextInt(5) + 1);
		String nowString = currentDate.format(dateTimeFormatter);

		modelMap.addAttribute("currentDate", nowString);
		modelMap.addAttribute("product", product);
		modelMap.addAttribute("displayInfoId", displayInfoId);
		modelMap.addAttribute("productImage", productImage);
		modelMap.addAttribute("productPrices", productPrices);

		return "reserve";
	}

}
