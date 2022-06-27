簡単使い方：

	(1) index.html
		<!-- 	common -->
		<script src="app/main/CommonService.js"></script>
		
		<!-- 	direct print -->
		<script src="app/main/directprint/NisDirectPrint.js"></script>
		<script src="app/main/directprint/DirectPrintController.js"></script>
		<script src="app/main/directprint/DirectPrintService.js"></script>
	
	(2)　Java側
	
		1.CommonService.javaが必須
		
		2.直接印刷データについて
			DirectPrintService.js(3)を呼ぶ前に、直接印刷データをセッションに存在しておくこと。
	
		   　　直接印刷データをセッションに保存する例：
		   	    PrintResultMessage printMsg=new PrintResultMessage();
				printMsg.setResult(IOUtil.read("c:/sample.svfwdp"));//直接印刷データ
				printMsg.setPdfName("sample");//拡張子が不要
				printMsg.setResultType(PrintResultMessage.WDP);//直接印刷モード
				
				// PDFまたはWDPをセッションに保持
				// 原則として、WebConst.ObjectKey.PDF_INFORMATIONを利用して、PrintResultMessageオブジェクトを保持すること。
				request.getSession().setAttribute(WebConst.ObjectKey.PDF_INFORMATION,printMsg);
	
    (3) Angular側
	    DirectPrintServiceをinjectし、以下のように呼ぶ。
	    	    
		DirectPrintService.print("sample");　
		or 
		DirectPrintService.print("sample.svfwdp");
	
	
	