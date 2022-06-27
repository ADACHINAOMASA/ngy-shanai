package ngyshanai.common.excel;

import java.io.IOException;
import java.io.OutputStream;

public interface Excel {

	public void write(OutputStream stream) throws ExcelException, IOException;

	public byte[] toByteArray() throws ExcelException, IOException;

}
