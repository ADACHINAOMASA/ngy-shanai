package lotdsp.web.service.icasapp;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import lotdsp.application.LogicExecutor;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoHeaderInfo;
import lotdsp.common.msg.hososhiyosho.HosoShiyoshoInfo;
import lotdsp.domain.logic.hososhiyosho.HosoShiyoshoSoapClientLogic;

@Path("/icasapp")
public class IcasAppService {

	@EJB
	private LogicExecutor executor;

	/**
	 * 冷延開始登録情報更新
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/GetKaishi4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo setKaishi4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * 冷延ロット実績登録情報要求
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/GetLotJisseki4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo getLotJisseki4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * 冷延ロット実績登録情報更新
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/SetLotJisseki4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo setLotJisseki4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * 冷延実績種別情報要求
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/GetJissekiKind4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo getJissekiKind4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * 冷延実績種別情報要求
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/GetTeisiJisseki4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo getTeisiJisseki4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * 冷延設備停止実績情報更新
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/SetTeisiJisseki4CM")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo setTeisiJisseki4CM(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * オペレータ登録情報要求
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/GetOperatorData")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo getOperatorData(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * オペレータ登録情報更新
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/SetOperatorData")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo setOperatorData(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

	/**
	 * ロール登録情報更新
	 * 
	 * @param in
	 * @return
	 */
	@POST
	@Path("/SetRollData")
	@Consumes(MediaType.APPLICATION_JSON)
	public HosoShiyoshoInfo setRollData(HosoShiyoshoHeaderInfo in) {

		return executor.execute(HosoShiyoshoSoapClientLogic.class, in);
	}

}
