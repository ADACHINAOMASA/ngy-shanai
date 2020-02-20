package nis.framework.ejb.logic.checker;

import nis.framework.ejb.logic.CheckLogic;

/**
 * ロジック用デフォルトチェッカー
 * <p>
 *  空で返すだけのチェッカー<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
public class DefaultLogicChecker {

	@CheckLogic
	public boolean pre() {
		return true;
	}

	@CheckLogic
	public boolean post(Object obj) {
		return true;
	}

}
