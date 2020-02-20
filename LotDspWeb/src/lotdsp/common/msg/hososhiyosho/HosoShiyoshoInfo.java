package lotdsp.common.msg.hososhiyosho;

import java.util.ArrayList;
import java.util.List;

import nis.framework.dictionary.InputModel;

@InputModel
public class HosoShiyoshoInfo {
	
	
	private List<LotInfo> lots=new ArrayList<LotInfo>();
	
	public List<LotInfo> getLots() {
		return lots;
	}

	public void setLots(List<LotInfo> lots) {
		this.lots = lots;
	}

	private List<HosoShiyoshoImageInfo> images=new ArrayList<HosoShiyoshoImageInfo>();
	
	public List<HosoShiyoshoImageInfo> getImages() {
		return images;
	}

	public void setImages(List<HosoShiyoshoImageInfo> images) {
		this.images = images;
	}

	/**
	 * プロパティ pkgSpecNo の値を保持。
	 */
	private String pkgSpecNo;

	/**
	 * プロパティ pkgSpecNo の取得メソッド。
	 * 
	 * @return プロパティ pkgSpecNo の値。
	 */
	public String getPkgSpecNo() {

		return this.pkgSpecNo;
	}

	/**
	 * プロパティ pkgSpecNo の設定メソッド。
	 * 
	 * @param pkgSpecNo
	 *            プロパティ pkgSpecNo の新しい値。
	 */
	public void setPkgSpecNo(String pkgSpecNo) {

		this.pkgSpecNo = pkgSpecNo;
	}

	/**
	 * プロパティ imageCount の値を保持。
	 */
	private int imageCount;

	/**
	 * プロパティ imageCount の取得メソッド。
	 * 
	 * @return プロパティ imageCount の値。
	 */
	public int getImageCount() {

		return this.imageCount;
	}

	/**
	 * プロパティ imageCount の設定メソッド。
	 * 
	 * @param imageCount
	 *            プロパティ imageCount の新しい値。
	 */
	public void setImageCount(int imageCount) {

		this.imageCount = imageCount;
	}

	/**
	 * プロパティ hitCount の値を保持。
	 */
	private int hitCount;

	/**
	 * プロパティ hitCount の取得メソッド。
	 * 
	 * @return プロパティ hitCount の値。
	 */
	public int getHitCount() {

		return this.hitCount;
	}

	/**
	 * プロパティ hitCount の設定メソッド。
	 * 
	 * @param hitCount
	 *            プロパティ hitCount の新しい値。
	 */
	public void setHitCount(int hitCount) {

		this.hitCount = hitCount;
	}

	/**
	 * プロパティ historyNo の値を保持。
	 */
	private int historyNo;

	/**
	 * プロパティ historyNo の取得メソッド。
	 * 
	 * @return プロパティ historyNo の値。
	 */
	public int getHistoryNo() {

		return this.historyNo;
	}

	/**
	 * プロパティ historyNo の設定メソッド。
	 * 
	 * @param historyNo
	 *            プロパティ historyNo の新しい値。
	 */
	public void setHistoryNo(int historyNo) {

		this.historyNo = historyNo;
	}

	/**
	 * プロパティ modifyCount の値を保持。
	 */
	private int modifyCount;

	/**
	 * プロパティ modifyCount の取得メソッド。
	 * 
	 * @return プロパティ modifyCount の値。
	 */
	public int getModifyCount() {

		return this.modifyCount;
	}

	/**
	 * プロパティ modifyCount の設定メソッド。
	 * 
	 * @param modifyCount
	 *            プロパティ modifyCount の新しい値。
	 */
	public void setModifyCount(int modifyCount) {

		this.modifyCount = modifyCount;
	}

	/**
	 * プロパティ scalingFactor の値を保持。
	 */
	private int scalingFactor = 1;

	/**
	 * プロパティ scalingFactor の取得メソッド。
	 * 
	 * @return プロパティ scalingFactor の値。
	 */
	public int getScalingFactor() {

		return this.scalingFactor;
	}

	/**
	 * プロパティ scalingFactor の設定メソッド。
	 * 
	 * @param scalingFactor
	 *            プロパティ scalingFactor の新しい値。
	 */
	public void setScalingFactor(int scalingFactor) {

		this.scalingFactor = scalingFactor;
	}

	/**
	 * プロパティ viewWidth の値を保持。
	 */
	private int viewWidth = 833;

	/**
	 * プロパティ viewWidth の取得メソッド。
	 * 
	 * @return プロパティ viewWidth の値。
	 */
	public int getViewWidth() {

		return this.viewWidth;
	}

	/**
	 * プロパティ viewWidth の設定メソッド。
	 * 
	 * @param viewWidth
	 *            プロパティ viewWidth の新しい値。
	 */
	public void setViewWidth(int viewWidth) {

		this.viewWidth = viewWidth;
	}

	/**
	 * プロパティ viewHeight の値を保持。
	 */
	private int viewHeight = 529;

	/**
	 * プロパティ viewHeight の取得メソッド。
	 * 
	 * @return プロパティ viewHeight の値。
	 */
	public int getViewHeight() {

		return this.viewHeight;
	}

	/**
	 * プロパティ viewHeight の設定メソッド。
	 * 
	 * @param viewHeight
	 *            プロパティ viewHeight の新しい値。
	 */
	public void setViewHeight(int viewHeight) {

		this.viewHeight = viewHeight;
	}

	/**
	 * プロパティ infoMessage の値を保持。
	 */
	private String infoMessage;

	/**
	 * プロパティ infoMessage の取得メソッド。
	 * 
	 * @return プロパティ infoMessage の値。
	 */
	public java.lang.String getInfoMessage() {

		return this.infoMessage;
	}

	/**
	 * プロパティ infoMessage の設定メソッド。
	 * 
	 * @param infoMessage
	 *            プロパティ infoMessage の新しい値。
	 */
	public void setInfoMessage(java.lang.String infoMessage) {

		this.infoMessage = infoMessage;
	}

	/**
	 * プロパティ issueDate の値を保持。
	 */
	private String issueDate;

	/**
	 * プロパティ issueDate の取得メソッド。
	 * 
	 * @return プロパティ issueDate の値。
	 */
	public String getIssueDate() {

		return this.issueDate;
	}

	/**
	 * プロパティ issueDate の設定メソッド。
	 * 
	 * @param issueDate
	 *            プロパティ issueDate の新しい値。
	 */
	public void setIssueDate(String issueDate) {

		this.issueDate = issueDate;
	}

	/**
	 * プロパティ fileFormat の値を保持。
	 */
	private String fileFormat;

	/**
	 * プロパティ fileFormat の取得メソッド。
	 * 
	 * @return プロパティ fileFormat の値。
	 */
	public String getFileFormat() {

		return this.fileFormat;
	}

	/**
	 * プロパティ fileFormat の設定メソッド。
	 * 
	 * @param fileFormat
	 *            プロパティ fileFormat の新しい値。
	 */
	public void setFileFormat(String fileFormat) {

		this.fileFormat = fileFormat;
	}

	/**
	 * プロパティ rotatedAngle の値を保持。
	 */
	private String rotatedAngle;

	/**
	 * プロパティ rotatedAngle の取得メソッド。
	 * 
	 * @return プロパティ rotatedAngle の値。
	 */
	public String getRotatedAngle() {

		return this.rotatedAngle;
	}

	/**
	 * プロパティ rotatedAngle の設定メソッド。
	 * 
	 * @param rotatedAngle
	 *            プロパティ rotatedAngle の新しい値。
	 */
	public void setRotatedAngle(String rotatedAngle) {

		this.rotatedAngle = rotatedAngle;
	}

	/**
	 * プロパティ resolution の値を保持。
	 */
	private String resolution;

	/**
	 * プロパティ resolution の取得メソッド。
	 * 
	 * @return プロパティ resolution の値。
	 */
	public String getResolution() {

		return this.resolution;
	}

	/**
	 * プロパティ resolution の設定メソッド。
	 * 
	 * @param resolution
	 *            プロパティ resolution の新しい値。
	 */
	public void setResolution(String resolution) {

		this.resolution = resolution;
	}

	/**
	 * プロパティ imgWidth の値を保持。
	 */
	private String imgWidth;

	/**
	 * プロパティ imgWidth の取得メソッド。
	 * 
	 * @return プロパティ imgWidth の値。
	 */
	public String getImgWidth() {

		return this.imgWidth;
	}

	/**
	 * プロパティ imgWidth の設定メソッド。
	 * 
	 * @param imgWidth
	 *            プロパティ imgWidth の新しい値。
	 */
	public void setImgWidth(String imgWidth) {

		this.imgWidth = imgWidth;
	}

	/**
	 * プロパティ imgHeight の値を保持。
	 */
	private String imgHeight;

	/**
	 * プロパティ imgHeight の取得メソッド。
	 * 
	 * @return プロパティ imgHeight の値。
	 */
	public String getImgHeight() {

		return this.imgHeight;
	}

	/**
	 * プロパティ imgHeight の設定メソッド。
	 * 
	 * @param imgHeight
	 *            プロパティ imgHeight の新しい値。
	 */
	public void setImgHeight(String imgHeight) {

		this.imgHeight = imgHeight;
	}

	/**
	 * プロパティ setWidth の値を保持。
	 */
	private String setWidth;

	/**
	 * プロパティ setWidth の取得メソッド。
	 * 
	 * @return プロパティ setWidth の値。
	 */
	public String getSetWidth() {

		return this.setWidth;
	}

	/**
	 * プロパティ setWidth の設定メソッド。
	 * 
	 * @param setWidth
	 *            プロパティ setWidth の新しい値。
	 */
	public void setSetWidth(String setWidth) {

		this.setWidth = setWidth;
	}

	/**
	 * プロパティ setHeight の値を保持。
	 */
	private String setHeight;

	/**
	 * プロパティ setHeight の取得メソッド。
	 * 
	 * @return プロパティ setHeight の値。
	 */
	public String getSetHeight() {

		return this.setHeight;
	}

	/**
	 * プロパティ setHeight の設定メソッド。
	 * 
	 * @param setHeight
	 *            プロパティ setHeight の新しい値。
	 */
	public void setSetHeight(String setHeight) {

		this.setHeight = setHeight;
	}

	/**
	 * プロパティ returnMessage の値を保持。
	 */
	private String returnMessage;

	/**
	 * プロパティ returnMessage の取得メソッド。
	 * 
	 * @return プロパティ returnMessage の値。
	 */
	public String getReturnMessage() {

		return this.returnMessage;
	}

	/**
	 * プロパティ returnMessage の設定メソッド。
	 * 
	 * @param returnMessage
	 *            プロパティ returnMessage の新しい値。
	 */
	public void setReturnMessage(String returnMessage) {

		this.returnMessage = returnMessage;
	}

	/**
	 * プロパティ defWidth の値を保持。
	 */
	private String defWidth;

	/**
	 * プロパティ defWidth の取得メソッド。
	 * 
	 * @return プロパティ defWidth の値。
	 */
	public String getDefWidth() {

		return this.defWidth;
	}

	/**
	 * プロパティ defWidth の設定メソッド。
	 * 
	 * @param defWidth
	 *            プロパティ defWidth の新しい値。
	 */
	public void setDefWidth(String defWidth) {

		this.defWidth = defWidth;
	}

	/**
	 * プロパティ defHeight の値を保持。
	 */
	private String defHeight;

	/**
	 * プロパティ defHeight の取得メソッド。
	 * 
	 * @return プロパティ defHeight の値。
	 */
	public String getDefHeight() {

		return this.defHeight;
	}

	/**
	 * プロパティ defHeight の設定メソッド。
	 * 
	 * @param defHeight
	 *            プロパティ defHeight の新しい値。
	 */
	public void setDefHeight(String defHeight) {

		this.defHeight = defHeight;
	}

	
	private List<String> comments=new ArrayList<String>();

	public List<String> getComments() {
		return comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	
	private String getTime;

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}
		
}
