package ngyshanai.domain.query.tantosha;

import java.util.Map;

import ngyshanai.common.AppConst;
import ngyshanai.common.msg.tantosha.TantoshaInfo;
import ngyshanai.common.msg.tantosha.TantoshaKensakuInfo;
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
		sql.append("    tantosha.tantosha_cd AS tantoshaCd ");			// 担当者コード
		sql.append("  , tantosha.tantosha_mei AS tantoshaMei ");		// 担当者名
		sql.append("  , tantosha.tantosha_kana AS tantoshaKana ");		// 担当者カナ
		sql.append("  , tantosha.jigyosho_cd AS jigyoshoCd ");			// 事業所コード
		sql.append("  , tantosha.bumon_cd AS bumonCd ");				// 部門コード
		sql.append("  , tantosha.password AS password ");				// パスワード
		sql.append("  , tantosha.shiyotanmatsu1 AS shiyotanmatsu1 ");	// 使用端末１
		sql.append("  , tantosha.shiyotanmatsu2 AS shiyotanmatsu2 ");	// 使用端末２
		sql.append("  , tantosha.shiyotanmatsu3 AS shiyotanmatsu3 ");	// 使用端末３
		sql.append("  , tantosha.juchu_kengen AS juchuKengen ");		// 受注管理権限
		sql.append("  , tantosha.kobai_kengen AS kobaiKengen ");		// 仕入管理権限
		sql.append("  , tantosha.uriage_kengen AS uriageKengen ");		// 売上管理権限
		sql.append("  , tantosha.zaiko_kengen AS zaikoKengen ");		// 在庫管理権限
		sql.append("  , tantosha.urikake_kengen AS urikakeKengen ");	// 売掛管理権限
		sql.append("  , tantosha.kyotsu_kengen AS kyotsuKengen ");		// 共通管理権限
		sql.append("  , tantosha.biko AS biko ");						// 備考
		sql.append("  , tantosha.version AS version ");					// バージョン
		sql.append("  , tantosha.toroku_userid AS torokuUserid ");		// 登録ユーザーＩＤ
		sql.append("  , tantosha.toroku_ymdhms AS torokuYmdhms ");		// 登録日時
		sql.append("  , tantosha.toroku_frontid AS torokuFrontid ");	// 登録フロントID
		sql.append("  , tantosha.koshin_userid AS koshinUserid ");		// 更新ユーザーＩＤ
		sql.append("  , tantosha.koshin_ymdhms AS koshinYmdhms ");		// 更新日時
		sql.append("  , tantosha.koshin_frontid AS koshinFrontid ");	// 更新フロントID
		sql.append("  , jigyosho.JIGYOSHO_MEI AS jigyoshoMei ");	// 更新フロントID
		sql.append("  , bumon.BUMON_MEI AS bumonMei ");	// 更新フロントID
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
