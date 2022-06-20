package lotdsp.domain.logic.usertoroku;

import java.util.Map;

import lotdsp.common.msg.user.UserInfo;
import nis.framework.sql.NisQuery;

public class GetUserQuery extends NisQuery<UserInfo>{

	private String userCd;
	
	public GetUserQuery(String userCd) {
		this.userCd = userCd;
		
		this.setParameters();
	}
	
	private void setParameters() {
		addParameter("　u.USER_CD = ? ", userCd);
	}
	
	@Override
	public String getSQL() {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		sql.append("   * ");
		sql.append(" FROM ");
		sql.append("   M_USER2 u  ");
		sql.append(" WHERE ");
		sql.append(" u.USER_CD IS NOT NULL ");
		sql.append(createParameterString(true));

		logger.info(getClass().getSimpleName() + "=" + sql.toString());
		return sql.toString();
	}
	
	// 重複チェックにしか使わないのでプロパティの値をセットする必要はない
	@Override
	public UserInfo record(Map<String, Object> record) {
		UserInfo info = new UserInfo();
		
		return info;
	}
}
