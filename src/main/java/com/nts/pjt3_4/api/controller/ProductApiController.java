package com.nts.pjt3_4.api.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.DisplayInfoImageDto;
import com.nts.pjt3_4.dto.ProductDto;
import com.nts.pjt3_4.dto.ProductImageDto;
import com.nts.pjt3_4.dto.ProductPriceDto;
import com.nts.pjt3_4.dto.ReservationUserCommentDto;
import com.nts.pjt3_4.service.DisplayInfoImageService;
import com.nts.pjt3_4.service.ProductImageService;
import com.nts.pjt3_4.service.ProductPriceService;
import com.nts.pjt3_4.service.ProductService;
import com.nts.pjt3_4.service.ReservationUserCommentImageService;
import com.nts.pjt3_4.service.ReservationUserCommentService;

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
	@Autowired
	private ReservationUserCommentService reservationUserCommentService;
	@Autowired
	private ReservationUserCommentImageService reservationUserCommentImageService;

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
		List<ProductDto> productsList = productService.getAllProducts(start);
		int productsCount = productsList.size();
		map.put("productsCount", productsCount);
		map.put("products", productsList);
	}

	private void getProdutsInfoByCate(Map<String, Object> map, int start, int categoryId) {
		List<ProductDto> productsList = productService.getProductsByCategory(start, categoryId);
		int productsCount = productsList.size();
		map.put("productsCount", productsCount);
		map.put("products", productsList);
	}

	@GetMapping("/{displayInfoId}")
	public Map<String, Object> getOneProduct(@PathVariable(name = "displayInfoId") int displayInfoId) {
		Map<String, Object> map = new LinkedHashMap<>();

		DisplayInfoImageDto displayInfoImage = displayInfoImageService.getFileInfo(displayInfoId);
		ProductDto product = productService.getProduct(displayInfoId);
		ProductImageDto productImage = productImageService.getProductMainImage(product.getId());
		List<ProductPriceDto> productPrices = productPriceService.getProductPrices(product.getId());
		map.put("product", product);
		map.put("productImages", productImage);
		map.put("displayInfoImages", displayInfoImage);

		int commentsCount = reservationUserCommentService.getCount(product.getId());
		float avgScore = reservationUserCommentService.getAvgScore(product.getId());
		avgScore = Float.parseFloat(String.format("%.2f", avgScore));
		if (commentsCount > 0) {
			List<ReservationUserCommentDto> comments = reservationUserCommentService.getThreeComments(product.getId(), 0);
			comments.forEach(comment -> {
				try {
					comment.setReservationUserCommentImage(
						reservationUserCommentImageService.getCommentImage(comment.getId()));
				} catch (EmptyResultDataAccessException e) {

				}
			});
			map.put("comments", comments);
			map.put("avgScore", avgScore);
		} else {
			map.put("comments", Collections.EMPTY_MAP);
			map.put("avgScore", 0);
		}

		map.put("productPrices", productPrices);

		return map;
	}

}
