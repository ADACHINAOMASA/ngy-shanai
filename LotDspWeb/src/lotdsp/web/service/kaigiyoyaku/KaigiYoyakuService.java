package lotdsp.web.service.kaigiyoyaku;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.kaigiyoyaku.KaigishitsuInfo;
import lotdsp.common.msg.kaigiyoyaku.YoyakuInfo;
import lotdsp.domain.logic.kaigiyoyaku.KaigiYoyakuLogic;
import lotdsp.domain.logic.kaigiyoyaku.MaishuYoyakuDeleteLogic;
import lotdsp.domain.logic.kaigiyoyaku.MaishuYoyakuLogic;
import lotdsp.domain.logic.kaigiyoyaku.SearchKaigishitsuLogic;
import lotdsp.domain.logic.kaigiyoyaku.YoyakuDeleteLogic;

@Path("/yoyaku")
public class KaigiYoyakuService {

	@EJB
	private LogicExecutor logicExecutor;
	
	@GET
	@Path("/searchkaigishitsu/{hizuke}")
	public List<KaigishitsuInfo> searchkaigishitsu(@PathParam("hizuke") Date hizuke) {
		return logicExecutor.execute(SearchKaigishitsuLogic.class, hizuke);
	}
	
	@POST
	@Path("/save")
	public boolean save(YoyakuInfo info) {
		return logicExecutor.execute(KaigiYoyakuLogic.class, info);
	}
	
	@POST
	@Path("/{maishuEnd}")
	public boolean maishusave(@PathParam("maishuEnd")Date maishuEnd, YoyakuInfo info) {
		return logicExecutor.execute(MaishuYoyakuLogic.class, maishuEnd, info);
	}
	
	@DELETE
	@Path("/{kaigishitsuCd}/{yoyakuDate}/{yoyakuBlockStart}")  
	public boolean delete(@PathParam("kaigishitsuCd") String kaigishitsuCd, @PathParam("yoyakuDate") Date yoyakuDate, @PathParam("yoyakuBlockStart") String yoyakuBlockStart) {
		return logicExecutor.execute(YoyakuDeleteLogic.class, kaigishitsuCd, yoyakuDate, yoyakuBlockStart);
	}
	
	@DELETE
	@Path("/{kaigishitsuCd}/{yoyakuDate}/{yoyakuBlockStart}/{maishuYoyakuId}")  
	public boolean maishudelete(@PathParam("kaigishitsuCd") String kaigishitsuCd, @PathParam("yoyakuDate") Date yoyakuDate, @PathParam("yoyakuBlockStart") String yoyakuBlockStart, @PathParam("maishuYoyakuId")String maishuYoyakuId) {
		return logicExecutor.execute(MaishuYoyakuDeleteLogic.class, kaigishitsuCd, yoyakuDate, yoyakuBlockStart, maishuYoyakuId);
	}
	
}
