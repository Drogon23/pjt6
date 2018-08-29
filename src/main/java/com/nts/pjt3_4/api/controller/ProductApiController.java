package com.nts.pjt3_4.api.controller;

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

import com.nts.pjt3_4.dto.DisplayInfoImageDto;
import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.dto.ProductPrice;
import com.nts.pjt3_4.dto.RsvUserCmtDto;
import com.nts.pjt3_4.service.DisplayInfoImageService;
import com.nts.pjt3_4.service.ProductImageService;
import com.nts.pjt3_4.service.ProductPriceService;
import com.nts.pjt3_4.service.ProductService;
import com.nts.pjt3_4.service.RsvUserCmtImgService;
import com.nts.pjt3_4.service.RsvUserCmtService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;
	@Autowired
	private RsvUserCmtService rsvUserCmtService;
	@Autowired
	private RsvUserCmtImgService rsvUserCmtImgService;

	@GetMapping
	public Map<String, Object> listProducts(
		@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start) {

		Map<String, Object> map = new LinkedHashMap<>();
		int totalCount = productService.getProductsCountByCategory(categoryId);
		map.put("totalCount", totalCount);
		if (totalCount > 0) {
			List<ProductDto> productsList = productService.getProductsByCategory(start, categoryId);
			int productsCount = productsList.size();
			map.put("productsCount", productsCount);
			map.put("products", productsList);
		}
		return map;
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getOneProduct(@PathVariable(name = "displayInfoId") int displayInfoId) {
		Map<String, Object> map = new LinkedHashMap<>();

		DisplayInfoImageDto displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		ProductDto product = productService.getProduct(displayInfoId);
		ProductImageDto productImage = productImageService.getProductImageByType(product.getId(), "ma");
		List<ProductPrice> productPrices = productPriceService.getProductPrices(product.getId());
		map.put("product", product);
		map.put("productImages", productImage);
		map.put("displayInfoImages", displayInfoImage);

		int commentsCount = rsvUserCmtService.getCount(product.getId());
		if (commentsCount > 0) {
			float avgScore = rsvUserCmtService.getAvgScore(product.getId());
			avgScore = Float.parseFloat(String.format("%.1f", avgScore));
			List<RsvUserCmtDto> comments = rsvUserCmtService.getThreeComments(product.getId(), 0);
			for (RsvUserCmtDto comment : comments) {
				comment.setRsvUserCmtImg(
					rsvUserCmtImgService.getCommentImage(comment.getId()));
			}
			map.put("comments", comments);
			map.put("avgScore", avgScore);
		} else {
			map.put("comments", Collections.emptyMap());
			map.put("avgScore", 0);
		}

		map.put("productPrices", productPrices);

		return map;
	}

}
