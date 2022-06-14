package lotdsp.entity.fromnis2015.repo;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

/**
 * JpaFindNativeテンプレートビルダ
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindNativeBuilder {

	/**
	 * SQL本文
	 */
	private String sql;

	/**
	 * パラメータ
	 */
	private List<Object> parameters = new ArrayList<>();

	/**
	 * 最大件数
	 */
	private int maxResult = -1;

	/**
	 * 組み立て
	 * @return 生成結果
	 */
	public NisJpaFindNative build() {
		Preconditions.checkNotNull(sql, "SQLがセットされていません。");
		return new NisJpaFindNative(sql, parameters, maxResult);
	}

	/**
	 * SQL本文のセット
	 * @param sql SQL
	 * @return this
	 */
	public NisJpaFindNativeBuilder sql(String sql) {
		this.sql = sql;
		return this;
	}

	/**
	 * パラメータリストの設定
	 * @param parameters パラメータリスト
	 * @return this
	 */
	public NisJpaFindNativeBuilder parameters(List<Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	/**
	 * パラメータの追加
	 * @param val パラメータ
	 * @return this
	 */
	public NisJpaFindNativeBuilder parameter(Object val) {
		parameters.add(val);
		return this;
	}

	/**
	 * 最大件数の設定
	 * @param maxResult 最大件数
	 * @return this
	 */
	public NisJpaFindNativeBuilder maxResult(int maxResult) {
		this.maxResult = maxResult;
		return this;
	}

}
