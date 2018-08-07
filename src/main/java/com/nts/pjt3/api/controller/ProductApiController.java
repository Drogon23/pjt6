package com.nts.pjt3.api.controller;

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
@RequestMapping(path = "api/products")
public class ProductApiController {

	private static final int ALL = 0;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@GetMapping
	public Map<String, Object> listProducts(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		Map<String, Object> map = new LinkedHashMap<>();

		if (categoryId == ALL) {
			int totalCount = productService.getAllProductsCount();
			if (totalCount > 0) {
				map.put("totalCount", totalCount);
				getProdutsInfo(map, start);
			}
		} else {
			int totalCount = productService.getProductsCountByCategory(categoryId);
			map.put("totalCount", totalCount);
			if (totalCount > 0) {
				getProdutsInfoByCate(map, start, categoryId);
			}
		}
		return map;
	}

	private void getProdutsInfo(Map<String, Object> map, int start) {
		List<Product> productsList = productService.getAllProducts(start);
		int productsCount = productsList.size();
		map.put("productsCount", productsCount);
		map.put("products", productsList);
	}

	private void getProdutsInfoByCate(Map<String, Object> map, int start, int categoryId) {
		List<Product> productsList = productService.getProductsByCategory(start, categoryId);
		int productsCount = productsList.size();
		map.put("productsCount", productsCount);
		map.put("products", productsList);
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getOneProduct(@PathVariable(name = "displayInfoId") int displayInfoId) {
		Map<String, Object> map = new LinkedHashMap<>();

		DisplayInfoImage displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		Product product = productService.getProduct(displayInfoId);
		ProductImage productImage = productImageService.getProductMainImage(product.getId());
		List<ProductPrice> productPrices = productPriceService.getProductPrices(product.getId());

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
