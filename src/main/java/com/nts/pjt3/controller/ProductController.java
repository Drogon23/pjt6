package com.nts.pjt3.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3.dto.DisplayInfoImage;
import com.nts.pjt3.dto.Product;
import com.nts.pjt3.dto.ProductImage;
import com.nts.pjt3.dto.ProductPrice;
import com.nts.pjt3.service.DisplayInfoImageService;
import com.nts.pjt3.service.ProductImageService;
import com.nts.pjt3.service.ProductPriceService;
import com.nts.pjt3.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

	private static final int ALL = 0;
	@Autowired
	ProductService productService;
	@Autowired
	ProductImageService productImageService;
	@Autowired
	ProductPriceService productPriceService;
	@Autowired
	DisplayInfoImageService displayInfoImageService;

	@GetMapping
	public Map<String, Object> listProducts(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		Map<String, Object> map = new LinkedHashMap<>();

		if (categoryId == ALL) {
			int totalCount = productService.getAllProductsCount();
			List<Product> productsList = productService.getAllProducts(start);
			int productsCount = productsList.size();
			map.put("totalCount", totalCount);
			map.put("productsCount", productsCount);
			map.put("products", productsList);
		} else {
			int totalCount = productService.getProductsCountByCategory(categoryId);
			List<Product> productsList = productService.getProductsByCategory(start, categoryId);
			int productsCount = productsList.size();
			map.put("totalCount", totalCount);
			map.put("productsCount", productsCount);
			map.put("products", productsList);
		}

		return map;
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getOneProduct(@PathVariable(name = "displayInfoId") int displayInfoId) {

		DisplayInfoImage displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		Product product = productService.getProduct(displayInfoId);
		ProductImage productImage = productImageService.getProductImage(product.getId());
		List<ProductPrice> productPrices = productPriceService.getProductPrices(product.getId());

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("product", product);
		map.put("productImages", productImage);
		map.put("displayInfoImages", displayInfoImage);
		//TODO 나중에 comments, avgScore 구현시 수정
		map.put("comments", Collections.EMPTY_MAP);
		map.put("avgScore", 0);
		map.put("productPrices", productPrices);

		return map;
	}

}
