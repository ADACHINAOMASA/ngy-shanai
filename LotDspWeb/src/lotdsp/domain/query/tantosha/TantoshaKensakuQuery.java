package lotdsp.domain.query.tantosha;

import java.util.Map;

import lotdsp.common.AppConst;
import lotdsp.common.msg.tantosha.TantoshaInfo;
import lotdsp.common.msg.tantosha.TantoshaKensakuInfo;
import nis.framework.sql.NisQuery;
import nis.framework.util.bean.BeanUtil;

public class TantoshaKensakuQuery extends NisQuery<TantoshaInfo> {

	public TantoshaKensakuQuery(TantoshaKensakuInfo info){
		setParameters(info);
	}

	private void setParameters(TantoshaKensakuInfo info) {
		addLikeParameter("tantosha.tantosha_cd LIKE ? ", info.getTantoshaCd(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.tantosha_mei LIKE ? ", info.getTantoshaMei(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.tantosha_kana LIKE ? ", info.getTantoshaKana(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.jigyosho_cd LIKE ? ", info.getJigyoshoCd(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.bumon_cd LIKE ? ", info.getBumonCd(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.shiyotanmatsu1 LIKE ? ", info.getShiyotanmatsu1(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.shiyotanmatsu2 LIKE ? ", info.getShiyotanmatsu2(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.shiyotanmatsu3 LIKE ? ", info.getShiyotanmatsu3(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.juchu_kengen LIKE ? ", info.getJuchuKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.kobai_kengen LIKE ? ", info.getKobaiKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.uriage_kengen LIKE ? ", info.getUriageKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.zaikoKengen LIKE ? ", info.getZaikoKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.urikake_kengen LIKE ? ", info.getUrikakeKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.kyotsu_kengen LIKE ? ", info.getKyotsuKengen(), LIKE_MODE.STARTS_WITH);
		addLikeParameter("tantosha.biko LIKE ? ", info.getBiko(), LIKE_MODE.STARTS_WITH);
		
		addParameter("tantosha.SHONIN_KENGEN = ? ", info.getShoninKengen());
	}
	
	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("    tantosha.tantosha_cd AS tantoshaCd ");			// ??????????????????
		sql.append("  , tantosha.tantosha_mei AS tantoshaMei ");		// ????????????
		sql.append("  , tantosha.tantosha_kana AS tantoshaKana ");		// ???????????????
		sql.append("  , tantosha.jigyosho_cd AS jigyoshoCd ");			// ??????????????????
		sql.append("  , tantosha.bumon_cd AS bumonCd ");				// ???????????????
		sql.append("  , tantosha.password AS password ");				// ???????????????
		sql.append("  , tantosha.shiyotanmatsu1 AS shiyotanmatsu1 ");	// ???????????????
		sql.append("  , tantosha.shiyotanmatsu2 AS shiyotanmatsu2 ");	// ???????????????
		sql.append("  , tantosha.shiyotanmatsu3 AS shiyotanmatsu3 ");	// ???????????????
		sql.append("  , tantosha.juchu_kengen AS juchuKengen ");		// ??????????????????
		sql.append("  , tantosha.kobai_kengen AS kobaiKengen ");		// ??????????????????
		sql.append("  , tantosha.uriage_kengen AS uriageKengen ");		// ??????????????????
		sql.append("  , tantosha.zaiko_kengen AS zaikoKengen ");		// ??????????????????
		sql.append("  , tantosha.urikake_kengen AS urikakeKengen ");	// ??????????????????
		sql.append("  , tantosha.kyotsu_kengen AS kyotsuKengen ");		// ??????????????????
		sql.append("  , tantosha.biko AS biko ");						// ??????
		sql.append("  , tantosha.version AS version ");					// ???????????????
		sql.append("  , tantosha.toroku_userid AS torokuUserid ");		// ????????????????????????
		sql.append("  , tantosha.toroku_ymdhms AS torokuYmdhms ");		// ????????????
		sql.append("  , tantosha.toroku_frontid AS torokuFrontid ");	// ??????????????????ID
		sql.append("  , tantosha.koshin_userid AS koshinUserid ");		// ????????????????????????
		sql.append("  , tantosha.koshin_ymdhms AS koshinYmdhms ");		// ????????????
		sql.append("  , tantosha.koshin_frontid AS koshinFrontid ");	// ??????????????????ID
		sql.append("  , jigyosho.JIGYOSHO_MEI AS jigyoshoMei ");	// ??????????????????ID
		sql.append("  , bumon.BUMON_MEI AS bumonMei ");	// ??????????????????ID
		sql.append(" FROM ");
		sql.append(" NFMTANTOSHA tantosha ");
		sql.append(" left outer join NFMJIGYOSHO jigyosho ");
		sql.append(" on tantosha.jigyosho_cd = jigyosho.jigyosho_cd ");
		sql.append(" left outer join NFMBUMON bumon ");
		sql.append(" on tantosha.bumon_cd = bumon.bumon_cd ");
		sql.append(" WHERE ");
		sql.append("  tantosha.tantosha_cd is not null ");
		sql.append(createParameterString(true));
		sql.append(" ORDER BY ");
		sql.append("  tantosha.tantosha_cd");
		db2FetchFirst(sql, AppConst.KENSAKU_KENSU_MAX);
		
		logger.info(getClass().getSimpleName()+"="+sql.toString());
		return sql.toString();
	}

	@Override
	public TantoshaInfo record(Map<String, Object> record) {
		TantoshaInfo info = BeanUtil.createBean(record, TantoshaInfo.class);
		return info;
	}

}
