package lotdsp.domain.logic.login.checker;

import java.util.Map;

import lotdsp.common.msg.user.UserInfo;
import nis.framework.sql.NisQuery;

public class LoginCheckQuery extends NisQuery<UserInfo>{

	private String userCd;
	private String password;
	
	public LoginCheckQuery(String userCd, String password) {
		this.userCd = userCd;
		this.password = password;
		
		this.setParameters();
	}
	
	private void setParameters() {
		addParameter("　u.USER_CD = ? ", userCd);
		addParameter("　u.PASSWORD = ? ", password);
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
	
	// 同一レコードの存在チェックにしか使わないのでプロパティの値をセットする必要はない
		@Override
		public UserInfo record(Map<String, Object> record) {
			UserInfo info = new UserInfo();
			
			return info;
		}
}
