package com.nts.pjt3_4.dto;

import java.util.Date;

public class ProductImageDto {
	private int productId;
	private int productImageId;
	private String type;
	private int fileInfold;
	private String fileName;
	private String SaveFileName;
	private String contentType;
	private int deleteFlag;
	private Date createDate;
	private Date modifyDate;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFileInfold() {
		return fileInfold;
	}

	public void setFileInfold(int fileInfold) {
		this.fileInfold = fileInfold;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return SaveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		SaveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProductImage [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
			+ ", fileInfold=" + fileInfold + ", fileName=" + fileName + ", SaveFileName=" + SaveFileName
			+ ", contentType=" + contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}

}
