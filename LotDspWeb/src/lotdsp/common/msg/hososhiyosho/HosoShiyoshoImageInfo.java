package lotdsp.common.msg.hososhiyosho;

import nis.framework.dictionary.InputModel;

@InputModel
public class HosoShiyoshoImageInfo {
	private boolean loaded=false;
	private String pkgSpecNo; 
	private String fileFormat;
	private int rotatedAngle;
	private int resolution;
	private int imgWidth;
	private int imgHeight;
	private int setWidth;
	private int setHeight;
	private int defWidth;
	private int defHeight;
	
	private int seq;
	
	private String imgBaseUrl;//画像ベースURL
	
	private String thumbnailSrc;//サムネル画像ソース
	
	private String imgSrc;//デフォルト画像（表示時利用）
	
	private String imgSrc1;//綺麗な画像
	private String imgSrc2;//粗い画像
	
	private String setImgSrc1;//綺麗な画像
	private String setImgSrc2;//粗い画像
	
	private String defImgSrc1;//綺麗な画像
	private String defImgSrc2;//粗い画像
	
	
	public String getPkgSpecNo() {
		return pkgSpecNo;
	}
	public void setPkgSpecNo(String pkgSpecNo) {
		this.pkgSpecNo = pkgSpecNo;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public int getRotatedAngle() {
		return rotatedAngle;
	}
	public void setRotatedAngle(int rotatedAngle) {
		this.rotatedAngle = rotatedAngle;
	}
	public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) {
		this.resolution = resolution;
	}
	public int getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(int imgWidth) {
		this.imgWidth = imgWidth;
	}
	public int getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(int imgHeight) {
		this.imgHeight = imgHeight;
	}
	public int getSetWidth() {
		return setWidth;
	}
	public void setSetWidth(int setWidth) {
		this.setWidth = setWidth;
	}
	public int getSetHeight() {
		return setHeight;
	}
	public void setSetHeight(int setHeight) {
		this.setHeight = setHeight;
	}
	public int getDefWidth() {
		return defWidth;
	}
	public void setDefWidth(int defWidth) {
		this.defWidth = defWidth;
	}
	public int getDefHeight() {
		return defHeight;
	}
	public void setDefHeight(int defHeight) {
		this.defHeight = defHeight;
	}
	public String getImgBaseUrl() {
		return imgBaseUrl;
	}
	public void setImgBaseUrl(String imgBaseUrl) {
		this.imgBaseUrl = imgBaseUrl;
	}
	public String getThumbnailSrc() {
		return thumbnailSrc;
	}
	public void setThumbnailSrc(String thumbnailSrc) {
		this.thumbnailSrc = thumbnailSrc;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getImgSrc1() {
		return imgSrc1;
	}
	public void setImgSrc1(String imgSrc1) {
		this.imgSrc1 = imgSrc1;
	}
	public String getImgSrc2() {
		return imgSrc2;
	}
	public void setImgSrc2(String imgSrc2) {
		this.imgSrc2 = imgSrc2;
	}
	public String getSetImgSrc1() {
		return setImgSrc1;
	}
	public void setSetImgSrc1(String setImgSrc1) {
		this.setImgSrc1 = setImgSrc1;
	}
	public String getSetImgSrc2() {
		return setImgSrc2;
	}
	public void setSetImgSrc2(String setImgSrc2) {
		this.setImgSrc2 = setImgSrc2;
	}
	public String getDefImgSrc1() {
		return defImgSrc1;
	}
	public void setDefImgSrc1(String defImgSrc1) {
		this.defImgSrc1 = defImgSrc1;
	}
	public String getDefImgSrc2() {
		return defImgSrc2;
	}
	public void setDefImgSrc2(String defImgSrc2) {
		this.defImgSrc2 = defImgSrc2;
	}
	public boolean isLoaded() {
		return loaded;
	}
	public void setLoaded(boolean loaded) {
		this.loaded = loaded;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
		
}

