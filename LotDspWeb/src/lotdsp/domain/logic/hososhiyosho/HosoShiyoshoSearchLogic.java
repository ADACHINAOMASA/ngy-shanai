package lotdsp.domain.logic.hososhiyosho;

import javax.inject.Inject;

import lotdsp.common.msg.hososhiyosho.HosoShiyoshoHeaderInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoInfo;
import lotdsp.common.util.StringUtil;
import nis.framework.ejb.logic.Logic;
import nis.framework.ejb.logic.ServiceContext;

public class HosoShiyoshoSearchLogic {

	@Inject
	protected ServiceContext svContext;

	@Logic
	public HosoShiyoshoInfo execute(HosoShiyoshoHeaderInfo in) {

		String no = StringUtil.nvl(in.getPkgSpecNo()).trim();
		
		HosoShiyoshoInfo out = new HosoShiyoshoDAO().getPkgSpecData(no,StringUtil.nvl(in.getViewWidth(),"0"),StringUtil.nvl(in.getViewHeight(),"0"),in.isGenba());
		
		if(StringUtil.hasValue(out.getReturnMessage())) {
			//svContext.getAlerts().addDanger(out.getReturnMessage());
		}

		return out;
	}

}
