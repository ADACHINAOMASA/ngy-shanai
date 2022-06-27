package ngyshanai.common.excel;

import java.io.IOException;
import java.io.OutputStream;

public abstract class ExcelBase implements Excel {

	private byte[] binData;

	public void write(OutputStream stream) throws ExcelException, IOException {
		createData();
		if (binData != null) {
			stream.write(binData);
		}
	}

	public byte[] toByteArray() throws ExcelException {
		createData();
		return binData;
	}

	private void createData() throws ExcelException {
		if (binData == null) {
			binData = executeCreate();
		}
	}

	protected abstract byte[] executeCreate() throws ExcelException;

}
