package com.nts.pjt3_4.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.pjt3_4.dao.FileInfoDao;
import com.nts.pjt3_4.dao.RsvUserCmtDao;
import com.nts.pjt3_4.dao.RsvUserCmtImgDao;
import com.nts.pjt3_4.dto.CommentDto;
import com.nts.pjt3_4.dto.FileInfoDto;
import com.nts.pjt3_4.dto.RsvUserCmtDto;
import com.nts.pjt3_4.service.RsvUserCmtService;

@Service
public class RsvUserCmtServiceImpl implements RsvUserCmtService {

	@Autowired
	private RsvUserCmtDao rsvUserCmtDao;
	@Autowired
	private FileInfoDao fileInfoDao;
	@Autowired
	private RsvUserCmtImgDao rsvUserCmtImgDao;
	@Autowired
	private ServletContext context;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss_");
	private static final int BUFFER_SIZE = 1024 * 100; //100KB

	@Override
	public List<RsvUserCmtDto> getThreeComments(int productId, int start) {
		return rsvUserCmtDao.selectAll(productId, start, RsvUserCmtService.LIMIT_THREE);
	}

	@Override
	public List<RsvUserCmtDto> getSixComments(int productId, int start) {
		return rsvUserCmtDao.selectAll(productId, start, RsvUserCmtService.LIMIT_SIX);
	}

	@Override
	public int getCount(int productId) {
		return rsvUserCmtDao.countByProductId(productId);
	}

	@Override
	public float getAvgScore(int productId) {
		return rsvUserCmtDao.avgScore(productId);
	}

	@Override
	@Transactional
	public int addComment(CommentDto comment, FileInfoDto fileInfo) {
		int isSuccess = rsvUserCmtDao.insert(comment);
		if (fileInfo != null) {
			fileInfoDao.insert(fileInfo);
			return rsvUserCmtImgDao.insert(comment.getRsvId(), comment.getId(), fileInfo.getId());
		}
		return isSuccess;
	}

	@Override
	public String savedFileName(CommentDto comment) {
		if (comment.getImg() != null) {
			String currentTimeStamp = dateFormat.format(new Date());
			String saveFileName = comment.getRsvId() + currentTimeStamp + comment.getImg().getOriginalFilename();
			imgFileSave(comment, saveFileName);
			return saveFileName;
		}
		return "";
	}

	public void imgFileSave(CommentDto comment, String saveFileName) {
		File f = new File(
			context.getRealPath("WEB-INF") + File.separator + "commentImg" + File.separator
				+ comment.getProductId());

		if (!f.exists()) {
			f.mkdirs();
		}
		try (
			FileOutputStream fos = new FileOutputStream(
				f.getAbsolutePath() + File.separator + saveFileName);
			InputStream is = comment.getImg().getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			comment.setImg(null);
			throw new RuntimeException("file Save Error");
		}
	}

}
