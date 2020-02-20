package nis.framework.svf;

import java.io.IOException;
import java.io.OutputStream;

import jp.co.fit.vfreport.Svf;
import jp.co.fit.vfreport.SvfrClient;

public abstract class SvfAdapter implements Svf {

	protected	SvfrClient svf = null;

	public SvfAdapter() throws IOException{
		svf = new SvfrClient();
	}

	public SvfAdapter(String hostName) throws IOException{
		svf = new SvfrClient(hostName);
	}

	public SvfAdapter(String hostName, int port) throws IOException{
		svf = new SvfrClient(hostName, port);
	}


	@Override
	public int VrAbortDoc() throws IOException {
		return svf.VrAbortDoc();
	}

	@Override
	public int VrAdjust(int arg0, int arg1) throws IOException {
		return svf.VrAdjust( arg0, arg1);
	}

	@Override
	public int VrAdjustd(String arg0, int arg1, int arg2) throws IOException {
		return svf.VrAdjustd( arg0, arg1, arg2);
	}

	@Override
	public int VrAdjustd2(int arg0, int arg1) throws IOException {
		return svf.VrAdjustd2( arg0, arg1);
	}

	@Override
	public int VrAdjustm(String arg0, int arg1, int arg2) throws IOException {
		return svf.VrAdjustm( arg0,  arg1,  arg2);
	}

	@Override
	public int VrAdjustm2(int arg0, int arg1) throws IOException {
		return svf.VrAdjustm2( arg0,  arg1);
	}

	@Override
	public int VrAttribute(String arg0, String arg1) throws IOException {
		return svf.VrAttribute(arg0, arg1) ;
	}

	@Override
	public int VrAttributen(String arg0, int arg1, String arg2)
			throws IOException {
		return svf.VrAttributen( arg0,  arg1,  arg2);
	}

	@Override
	public int VrComout(String arg0) throws IOException {
		return svf.VrComout(arg0);
	}

	@Override
	public int VrCondition(String arg0, int arg1) throws IOException {
		return svf.VrCondition(arg0, arg1);
	}

	@Override
	public int VrCopy(int arg0) throws IOException {
		return svf.VrCopy(arg0);
	}

	@Override
	public int VrDebugPrint2(String arg0, int arg1) throws IOException {
		return svf.VrDebugPrint2(arg0, arg1);
	}

	@Override
	public int VrEnablePreview(int arg0, int arg1) throws IOException {
		return svf.VrEnablePreview(arg0, arg1);
	}

	@Override
	public int VrEnablePrintBTN(int arg0) throws IOException {
		return svf.VrEnablePrintBTN(arg0);
	}

	@Override
	public int VrEndDoc() throws IOException {
		return svf.VrEndDoc();
	}

	@Override
	public int VrEndPage() throws IOException {
		return svf.VrEndPage();
	}

	@Override
	public int VrEndRecord() throws IOException {
		return svf.VrEndRecord();
	}

	@Override
	public int VrEndSendFax() throws IOException {
		return svf.VrEndSendFax();
	}

	@Override
	public int VrExecQuery() throws IOException {
		return svf.VrExecQuery();
	}

	@Override
	public int VrFeed(int arg0, int arg1) throws IOException {
		return svf.VrFeed(arg0, arg1);
	}

	@Override
	public int VrGetAttribute(String arg0, String arg1, StringBuffer arg2)
			throws IOException {
		return svf.VrGetAttribute(arg0, arg1, arg2);
	}

	@Override
	public int VrGetManageNum() throws IOException {
		return svf.VrGetManageNum();
	}

	@Override
	public int VrGetPID() throws IOException {
		return svf.VrGetPID();
	}

	@Override
	public int VrGetPrintFlag(StringBuffer arg0) throws IOException {
		return svf.VrGetPrintFlag(arg0);
	}

	@Override
	public String VrGetSpoolFileName() {
		return svf.VrGetSpoolFileName();
	}

	@Override
	public int VrGetStatus() throws IOException {
		return svf.VrGetStatus();
	}

	@Override
	public int VrInit() throws IOException {
		return svf.VrInit();
	}

	@Override
	public int VrInit(String arg0) throws IOException {
		return svf. VrInit(arg0);
	}

	@Override
	public int VrNotUseServer() throws IOException {
		return svf.VrNotUseServer();
	}

	@Override
	public int VrOutputQuery(String arg0) throws IOException {
		return svf.VrOutputQuery(arg0);
	}

	@Override
	public int VrOutputQueryStream(OutputStream arg0) throws IOException {
		return svf.VrOutputQueryStream(arg0);
	}

	@Override
	public int VrPage(String arg0) throws IOException {
		return svf.VrPage(arg0);
	}

	@Override
	public int VrPaperEject() throws IOException {
		return svf.VrPaperEject();
	}

	@Override
	public int VrPreLoadForm(String arg0, int arg1) throws IOException {
		return svf.VrPreLoadForm(arg0, arg1);
	}

	@Override
	public int VrPreviewEndPage() throws IOException {
		return svf.VrPreviewEndPage();
	}

	@Override
	public int VrPreviewStartPage() throws IOException {
		return svf.VrPreviewStartPage();
	}

	@Override
	public int VrPrint() throws IOException {
		return svf.VrPrint();
	}

	@Override
	public int VrQuit() throws IOException {
		return svf.VrQuit();
	}

	@Override
	public int VrReport() throws IOException {
		return svf.VrReport();
	}

	@Override
	public int VrSendFaxPress(String arg0) throws IOException {
		return svf.VrSendFaxPress(arg0);
	}

	@Override
	public int VrSeqOut(String arg0) throws IOException {
		return svf.VrSeqOut(arg0);
	}

	@Override
	public int VrSetCSVFileName(String arg0, int arg1, int arg2)
			throws IOException {
		return svf.VrSetCSVFileName(arg0, arg1, arg2);
	}

	@Override
	public int VrSetCSVFileStream(OutputStream arg0, int arg1, int arg2)
			throws IOException {
		return svf.VrSetCSVFileStream(arg0, arg1, arg2);
	}

	@Override
	public int VrSetCalcMode(int arg0) throws IOException {
		return svf.VrSetCalcMode(arg0);
	}

	@Override
	public int VrSetClientInfo2(String arg0, String arg1) throws IOException {
		return svf.VrSetClientInfo2(arg0, arg1);
	}

	@Override
	public int VrSetComputerName(String arg0) throws IOException {
		return svf.VrSetComputerName(arg0);
	}

	@Override
	public int VrSetDocName2(String arg0) throws IOException {
		return svf.VrSetDocName2(arg0);
	}

	@Override
	public int VrSetDocumentSaveMode(int arg0, int arg1, String arg2, int arg3)
			throws IOException {
		return svf.VrSetDocumentSaveMode( arg0,  arg1,  arg2,  arg3);
	}

	@Override
	public int VrSetDuplex(int arg0, int arg1) throws IOException {
		return svf.VrSetDuplex(arg0, arg1);
	}

	@Override
	public int VrSetForm(String arg0, int arg1) throws IOException {
		return svf.VrSetForm(arg0, arg1);
	}

	@Override
	public int VrSetLocale(String arg0) throws IOException {
		return svf.VrSetLocale(arg0);
	}

	@Override
	public int VrSetMachineKind(int arg0) throws IOException {
		return svf.VrSetMachineKind(arg0);
	}

	@Override
	public int VrSetOutputBin(int arg0) throws IOException {
		return svf.VrSetOutputBin(arg0);
	}

	@Override
	public int VrSetOutputPrinter(String arg0, int arg1) throws IOException {
		return svf.VrSetOutputPrinter(arg0, arg1);
	}

	@Override
	public int VrSetOutputVPrinter(String arg0) throws IOException {
		return svf.VrSetOutputVPrinter(arg0) ;
	}

	@Override
	public int VrSetOutputVPrinter2(String arg0) throws IOException {
		return svf.VrSetOutputVPrinter2(arg0);
	}

	@Override
	public int VrSetPageCount(int arg0, int arg1) throws IOException {
		return svf.VrSetPageCount(arg0, arg1);
	}

	@Override
	public int VrSetPreviewAndSpool(int arg0) throws IOException {
		return svf.VrSetPreviewAndSpool(arg0);
	}

	@Override
	public int VrSetPrinter(String arg0, String arg1) throws IOException {
		return svf.VrSetPrinter(arg0, arg1);
	}

	@Override
	public int VrSetPrinterGroup(String arg0) throws IOException {
		return svf.VrSetPrinterGroup(arg0);
	}

	@Override
	public int VrSetPrinterKind(int arg0) throws IOException {
		return svf.VrSetPrinterKind(arg0);
	}

	@Override
	public int VrSetPunch(int arg0) throws IOException {
		return svf.VrSetPunch(arg0);
	}

	@Override
	public int VrSetQuery(String arg0, String arg1, int arg2)
			throws IOException {
		return svf.VrSetQuery(arg0, arg1, arg2);
	}

	@Override
	public int VrSetServerName(String arg0) throws IOException {
		return svf.VrSetServerName(arg0);
	}

	@Override
	public int VrSetSort(int arg0) throws IOException {
		return svf.VrSetSort(arg0);
	}

	@Override
	public int VrSetSpoolFileName2(String arg0) throws IOException {
		return svf.VrSetSpoolFileName2(arg0);
	}

	@Override
	public int VrSetSpoolFileStream(OutputStream arg0) throws IOException {
		return svf.VrSetSpoolFileStream(arg0);
	}

	@Override
	public int VrSetSpoolMode(int arg0) throws IOException {
		return svf.VrSetSpoolMode(arg0);
	}

	@Override
	public int VrSetStaple(int arg0) throws IOException {
		return svf.VrSetStaple( arg0);
	}

	@Override
	public int VrSetTray(int arg0) throws IOException {
		return svf.VrSetTray(arg0);
	}

	@Override
	public int VrSetUseReportDirector(int arg0) throws IOException {
		return svf.VrSetUseReportDirector(arg0);
	}

	@Override
	public int VrSetUseSaveMode2() throws IOException {
		return svf.VrSetUseSaveMode2();
	}

	@Override
	public int VrSetUserName(String arg0) throws IOException {
		return svf.VrSetUserName(arg0);
	}

	@Override
	public int VrStack() throws IOException {
		return svf.VrStack();
	}

	@Override
	public int VrStackOut() throws IOException {
		return svf.VrStackOut();
	}

	@Override
	public int VrStartSendFax(String arg0, String arg1) throws IOException {
		return svf.VrStartSendFax( arg0,  arg1);
	}

	@Override
	public int VrTest() throws IOException {
		return svf.VrTest();
	}

	@Override
	public int VrTestPalette() throws IOException {
		return svf.VrTestPalette();
	}

	@Override
	public int VriOut(String arg0, int arg1) throws IOException {
		return svf.VriOut( arg0,  arg1);
	}

	@Override
	public int VriOutn(String arg0, int arg1, int arg2) throws IOException {
		return svf.VriOutn( arg0,  arg1,  arg2);
	}

	@Override
	public int VrlOut(String arg0, long arg1) throws IOException {
		return svf.VrlOut( arg0,  arg1);
	}

	@Override
	public int VrlOutn(String arg0, int arg1, long arg2) throws IOException {
		return svf.VrlOutn( arg0,  arg1,  arg2);
	}

	@Override
	public int VrrOut(String arg0, double arg1) throws IOException {
		return svf.VrrOut( arg0,  arg1) ;
	}

	@Override
	public int VrrOutn(String arg0, int arg1, double arg2) throws IOException {
		return svf.VrrOutn( arg0,  arg1,  arg2);
	}

	@Override
	public int VrsOut(String arg0, String arg1) throws IOException {
		return svf.VrsOut(arg0, arg1);
	}

	@Override
	public int VrsOutn(String arg0, int arg1, String arg2) throws IOException {
		return svf.VrsOutn( arg0,  arg1,  arg2) ;
	}

	@Override
	public void close() {
		svf.close();
	}

}
