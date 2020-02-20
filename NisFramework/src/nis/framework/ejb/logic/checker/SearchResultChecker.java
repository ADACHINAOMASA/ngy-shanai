package nis.framework.ejb.logic.checker;

import java.text.MessageFormat;
import java.util.List;

import javax.inject.Inject;

import nis.framework.ejb.logic.CheckLogic;
import nis.framework.ejb.logic.ServiceContext;


public class SearchResultChecker {

	// TODO:メッセージの外部参照

	@Inject
	private ServiceContext svContext;

	@CheckLogic
	public boolean check(List<?> resultList) {
		assert resultList != null;
		if (resultList.isEmpty()) {
			svContext.getAlerts().addWarning("条件に一致するものが見つかりませんでした。");
		}
		else {
			svContext.getAlerts().addSuccess(MessageFormat.format("検索結果 {0}件", resultList.size()));
		}
		return true;
	}

}
