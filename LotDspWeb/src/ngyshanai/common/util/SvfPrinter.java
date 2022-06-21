package ngyshanai.common.util;

import java.io.IOException;

import nis.framework.svf.Print;
import nis.framework.svf.PrintCreator;
import nis.framework.svf.PrintException;

/**
 * @author Leang-Say
 */
public class SvfPrinter {

	private PrintCreator printCreator = null;

	public SvfPrinter() {
		printCreator = new PrintCreator();
	}

	public void add(Print print) {
		this.printCreator.addPrint(print);
	}

	public byte[] printPdf() throws PrintException {
		printCreator.executePdfPrint();
		return printCreator.getResult();
	}

	public void printPdf(String outputFilePath) throws PrintException, IOException {
		printCreator.executePdfPrint();
		IOUtil.write(printCreator.getResult(), outputFilePath);
	}

	public byte[] printDirect() throws PrintException {
		printCreator.executeEmfPrint();
		return printCreator.getResult();
	}

	public void printDirect(String outputFilePath) throws PrintException, IOException {
		printCreator.executeEmfPrint();
		IOUtil.write(printCreator.getResult(), outputFilePath);
	}

	public byte[] getPrintResult() {
		return printCreator.getResult();
	}
	
}
