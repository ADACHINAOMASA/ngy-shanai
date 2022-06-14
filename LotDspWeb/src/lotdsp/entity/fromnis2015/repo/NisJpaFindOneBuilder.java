package lotdsp.entity.fromnis2015.repo;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * JpaFindOneテンプレートビルダ
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindOneBuilder {

	/**
	 * JPQL本文
	 */
	private String jpql;

	/**
	 * パラメータ一覧
	 */
	private Map<String, Object> parameters = new HashMap<>();

	/**
	 * セーフモード
	 */
	private boolean safe = true;

	/**
	 * 組み立て
	 * @return 生成結果
	 */
	public NisJpaFindOne build() {
		Preconditions.checkNotNull(jpql, "JPQLがセットされていません。");
		return new NisJpaFindOne(jpql, parameters, safe);
	}

	/**
	 * JPQL本文のセット
	 * @param jpql JPQL本文
	 * @return this
	 */
	public NisJpaFindOneBuilder jpql(String jpql) {
		this.jpql = jpql;
		return this;
	}

	/**
	 * パラメータ一覧のセット
	 * @param parameters パラメータ一覧
	 * @return this
	 */
	public NisJpaFindOneBuilder parameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	/**
	 * パラメータの追加
	 * @param name パラメータ名
	 * @param val 値
	 * @return this
	 */
	public NisJpaFindOneBuilder parameter(String name, Object val) {
		parameters.put(name, val);
		return this;
	}

	/**
	 * safeモードの設定<br>
	 * safeモードを有効にすると、論理削除されたエンティティを除外する。<br>
	 * デフォルトは有効。<br>
	 * @param safe true = セーフモード
	 * @return this
	 */
	public NisJpaFindOneBuilder safe(boolean safe) {
		this.safe = safe;
		return this;
	}

	/**
	 * safeモードに
	 * @return this
	 */
	public NisJpaFindOneBuilder safe() {
		this.safe = true;
		return this;
	}

	/**
	 * safeモードを無効に
	 * @return this
	 */
	public NisJpaFindOneBuilder unsafe() {
		this.safe = false;
		return this;
	}

}
