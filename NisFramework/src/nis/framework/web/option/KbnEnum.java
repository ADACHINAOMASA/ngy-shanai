package nis.framework.web.option;

/**
 *
 * <p>
 * 列挙型区分クラス用インターフェース<br>
 * オプションとして扱いたいものに付与
 * </p>
 * @author Kengo-Nagase
 *
 */
public interface KbnEnum {

	/**
	 * コード取得
	 * @return
	 */
	public String getCode();

	/**
	 * 名称取得
	 * @return
	 */
	public String getName();

}
