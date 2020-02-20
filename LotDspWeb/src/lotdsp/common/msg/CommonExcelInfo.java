package lotdsp.common.msg;


public class CommonExcelInfo {
	
	private String fileName;
	
	private byte[] data;

	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
