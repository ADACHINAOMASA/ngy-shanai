package lotdsp.domain.logic.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import lotdsp.common.lotdsp.AuthenticationInfo;
import lotdsp.domain.nagoya.logic.XmlMsg;
import lotdsp.domain.nagoya.logic.XmlReader;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;

public class IPAuthenticateLogic {
	//表示モードの設定
    public static final String ICAS_MODE  = "0";
    public static final String STAFF_MODE = "1";
    public static final String BOTH_MODE  = "2";
    public static final String OUT_MODE   = "3";

    public static final String SITE_STAFF  = "0";
    public static final String SITE_WORKS  = "1";

    public static final String TAB_ICAS = "0";
    public static final String TAB_PROGRESS = "1";
    public static final String TAB_MANUFACTURING = "2";
    public static final String TAB_TEST = "3";
    public static final String TAB_QUALITY = "4";
	public static final String TAB_CF = "5";

	@Inject
	protected ServiceContext svContext;

	@Logic
	public AuthenticationInfo execute(AuthenticationInfo in) {
		AuthenticationInfo out = new AuthenticationInfo();
        String ltno = null;
        String knno = null;
        String cyno = null;
        String linkkey = null;
        String mode = null;
        String tab = null;
        String site = null;



		// 許可対象のIPのリスト
		List ipList = new ArrayList();
		// XMLテスト(対象のIPアドレスを取得する)
		try{
		  //XMLファイルを読み込む
		  XmlReader reader = new XmlReader();
		  ipList = reader.domRead("C:\\Tools\\iplist.xml");
		}
		catch (Exception e){
		  System.err.println(e.getMessage());
		  System.err.println("XMLファイルの読み込みに失敗しました");
	      e.printStackTrace();
		}

		//modeの設定
		if (in.getMode() == null) {
			// IPアドレスの取得
			// リストからマッチするか確認
			mode = getMode(ipList, in.getIp());
			if(mode == null) {
			  mode = OUT_MODE;
			}
			System.out.println("@接続IP:" + in.getIp()  + ",モード:" + mode);
		}

	    if (mode != null) {
	  	  if (!mode.equals(in.getMode())) {
	  		in.setMode(mode);
	  	  }
	    }

	  	//tabの設定
	  	tab = in.getTab();
	  	if(tab == null) {
	  		tab = TAB_PROGRESS;
	  	}else {
	  		tab = tab.trim().toUpperCase();
	  	}
	  	if (tab != null) {
	  		if (!tab.equals(in.getTab())) {
	  			in.setTab(tab);
	  		}
	  	}

	  	//siteの設定
	  	site = in.getSite();
	  	if(site == null) {
	  		site = "0";
	  	}else {
	  		site = site.trim().toUpperCase();
	  	}
	  	if (site != null) {
	  		if (!site.equals(in.getSite())) {
	  			in.setSite(site);
	  		}
	  	}

	  	//linkkey,ltno,knno,cynoの設定
	  	if(in.getLinkkey() != null) {
	  		linkkey = in.getLinkkey().trim().toUpperCase();
	  	}

	  	if(in.getLtno() != null) {
	  		ltno = in.getLtno().trim().toUpperCase();
	  	}else {
	  		in.setLtno("");
	  	}

	  	if(in.getKnno() != null) {
	  		knno = in.getKnno().trim().toUpperCase();
	  	}else {
	  		in.setKnno("");
	  	}

	  	if(in.getCyno() != null) {
	  		cyno = in.getCyno().trim().toUpperCase();
	  	}else {
	  		in.setCyno("");
	  	}

	  	Map map = new HashMap();
	  	if (linkkey != null) {

	  	}



		return in;
	}

	   /**
		* MODEを返す
	    */
		public String getMode(List adminList, String ip){
		  for(int i = 0; i < adminList.size(); i++ ){
			XmlMsg msg = (XmlMsg) adminList.get(i);
			if(ip.matches(msg.getIp())){
			  return msg.getMode();
			}
		  }
		  return null;
		}

}
