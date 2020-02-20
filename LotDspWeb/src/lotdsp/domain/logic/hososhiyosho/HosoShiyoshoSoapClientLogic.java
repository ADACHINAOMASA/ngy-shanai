package lotdsp.domain.logic.hososhiyosho;

import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import jp.co.nikkeikin.nis.nagoya.common.XmlDataModel;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoHeaderInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoInfo;
import lotdsp.common.msg.hososhiyosho.LotInfo;
import lotdsp.domain.logic.hososhiyosho.test.CommonMessage;
import lotdsp.domain.logic.hososhiyosho.test.CommonSchOperator;
import lotdsp.domain.logic.hososhiyosho.test.SessionBean1;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;
import nis.framework.util.StringUtil;

public class HosoShiyoshoSoapClientLogic {

	@Inject
	protected ServiceContext svContext;

	@Logic
	public HosoShiyoshoInfo execute(HosoShiyoshoHeaderInfo in) {

		SessionBean1 sb1 = new SessionBean1();
		
		//SchAppSoapClient sb1 = new SchAppSoapClient();

		HosoShiyoshoInfo out = new HosoShiyoshoInfo();
		out.setLots(new ArrayList<LotInfo>());

		try {
			String setsubi = in.getPkgSpecNo();
			if(StringUtil.hasNoValue(setsubi)) {
				setsubi="L-1";
			}
			
			javax.xml.rpc.holders.StringHolder schList = new javax.xml.rpc.holders.StringHolder();

			int result = sb1.getSchData4CM(sb1.getAuthInfString(), setsubi, schList);

			Map<?, ?> map = CommonMessage.getSoapMessage(setsubi, result);

			if (map.get("msgStr") == null) {
				XmlDataModel xmlModel = CommonSchOperator.getXmlDataModel(sb1, schList);

				Iterator<?> it = xmlModel.iterator();
				while (it.hasNext()) {
					Map<?, ?> m = (Map<?, ?>) it.next();
					System.out.println(getString(m.toString()));

					LotInfo lotInfo = new LotInfo();

					lotInfo.setRoll(getString(m, "TEISIJPNAME"));
					lotInfo.setLotNo(getString(m, "LTNO"));
					lotInfo.setZaishitsu(getString(m, "LTA"));

					out.getLots().add(lotInfo);

				}

			} else {
				sb1.setErrorMessage(map.get("msgStr").toString());
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return out;
	}

	protected String getString(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("UTF-8"), "UTF-8");
	}

	protected String getString(Map<?, ?> map, String key) throws UnsupportedEncodingException {
		String str = ((String) map.get(key));
		if (StringUtil.hasNoValue(str)) {
			return "";
		}
		return new String(str.getBytes("UTF-8"), "UTF-8");
	}

}
