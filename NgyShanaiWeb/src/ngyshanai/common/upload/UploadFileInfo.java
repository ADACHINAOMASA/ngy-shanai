package ngyshanai.common.upload;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nis.framework.dictionary.InputModel;

@InputModel
public class UploadFileInfo {
	
	private String fileName;		// ファイル名
	private String filePath;		// ファイルパス
	private byte[] fileBytes;		//　ファイル情報
	
	private String torokushaCd;		// 登録者コード
	private Timestamp torokuYmdhms;	// 登録日時
	private String koshinshaCd;		// 更新者コード
	private Timestamp koshinYmdhms;	// 更新日時
	private BigDecimal version;		// バージョン
	
	public UploadFileInfo() {}

	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getFileBytes() {
		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes = fileBytes;
	}
	
	public String getTorokushaCd() {
		return torokushaCd;
	}

	public void setTorokushaCd(String torokushaCd) {
		this.torokushaCd = torokushaCd;
	}

	public Timestamp getTorokuYmdhms() {
		return torokuYmdhms;
	}

	public void setTorokuYmdhms(Timestamp torokuYmdhms) {
		this.torokuYmdhms = torokuYmdhms;
	}

	public String getKoshinshaCd() {
		return koshinshaCd;
	}

	public void setKoshinshaCd(String koshinshaCd) {
		this.koshinshaCd = koshinshaCd;
	}

	public Timestamp getKoshinYmdhms() {
		return koshinYmdhms;
	}

	public void setKoshinYmdhms(Timestamp koshinYmdhms) {
		this.koshinYmdhms = koshinYmdhms;
	}

	public BigDecimal getVersion() {
		return version;
	}

	public void setVersion(BigDecimal version) {
		this.version = version;
	}

}

