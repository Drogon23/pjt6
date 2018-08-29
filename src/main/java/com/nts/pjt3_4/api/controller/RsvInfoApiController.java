package com.nts.pjt3_4.api.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.pjt3_4.dto.RsvDto;
import com.nts.pjt3_4.dto.RsvInfoDto;
import com.nts.pjt3_4.dto.RsvInfoPriceDto;
import com.nts.pjt3_4.dto.RsvInfoWithPriceDto;
import com.nts.pjt3_4.service.RsvInfoPriceService;
import com.nts.pjt3_4.service.RsvInfoService;

@RestController
@RequestMapping("/api/reservationInfos")
public class RsvInfoApiController {

	@Autowired
	private RsvInfoService rsvInfoService;
	@Autowired
	private RsvInfoPriceService rsvInfoPriceService;

	@GetMapping
	public Map<String, Object> rsvInfo(@RequestParam(name = "reservationEmail") String rsvEmail) {
		Map<String, Object> map = new LinkedHashMap<>();
		List<RsvInfoDto> rsvInfoList = rsvInfoService.getRsvInfosByEmail(rsvEmail);
		for (RsvInfoDto rsvInfo : rsvInfoList) {
			rsvInfo.setSumPrice(rsvInfoService.sumPrice(rsvInfo.getId()));
		}
		map.put("items", rsvInfoList);
		map.put("size", rsvInfoList.size());
		return map;
	}

	@PostMapping
	public RsvInfoWithPriceDto addRsv(@RequestBody RsvDto rsv) {
		if (rsvInfoService.isValidRsv(rsv)) {
			int rsvId = rsvInfoService.addRsvInfo(rsv);
			RsvInfoWithPriceDto rsvInfo = rsvInfoService.getReservationInfo(rsvId);
			List<RsvInfoPriceDto> rsvInfoPrices = rsvInfoPriceService.getRsvInfoPrices(rsvId);
			rsvInfo.setPrices(rsvInfoPrices);
			return rsvInfo;
		}
		return new RsvInfoWithPriceDto();
	}

	@DeleteMapping("/{rsvId}")
	public String cancelRsv(@PathVariable(name = "rsvId") int rsvId) {
		int isCanceled = rsvInfoService.cancelRsv(rsvId);
		if (isCanceled == 1) {
			return "success";
		} else {
			return "fail";
		}
	}

}
