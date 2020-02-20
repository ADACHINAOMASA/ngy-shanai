package nis.framework.web.option;

import java.util.List;

/**
 *
 * <p>
 * オプション生成ファクトリインターフェース<br>
 * getSelectOptionsで生成したオプションがコンテナに追加される
 * </p>
 * @author Kengo-Nagase
 *
 */
public interface SelectOptionsFactory {

	/**
	 * オプションを取得する
	 * @return
	 */
	public List<Option> getSelectOptions();

}
