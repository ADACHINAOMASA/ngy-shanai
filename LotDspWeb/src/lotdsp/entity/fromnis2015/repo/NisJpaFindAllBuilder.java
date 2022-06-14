package lotdsp.entity.fromnis2015.repo;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * JPAFindAllテンプレートビルダ
 * @author Kengo-Nagase
 *
 */
public class NisJpaFindAllBuilder {

	/**
	 * JPQL本文
	 */
	private String jpql;

	/**
	 * パラメータ
	 */
	private Map<String, Object> parameters = new HashMap<>();

	/**
	 * 最大件数
	 */
	private int maxResult = -1;

	/**
	 * セーフサーチ
	 */
	private boolean safe = true;

	/**
	 * ビルド実行
	 * @return 生成結果
	 */
	public NisJpaFindAll build() {
		Preconditions.checkNotNull(jpql, "JPQLがセットされていません。");
		return new NisJpaFindAll(jpql, parameters, maxResult, safe);
	}

	/**
	 * JPQL本文のセット
	 * @param jpql JPQL本文
	 * @return this
	 */
	public NisJpaFindAllBuilder jpql(String jpql) {
		this.jpql = jpql;
		return this;
	}

	/**
	 * パラメータマップのセット
	 * @param parameters パラメータマップ
	 * @return this
	 */
	public NisJpaFindAllBuilder parameters(Map<String, Object> parameters) {
		this.parameters = parameters;
		return this;
	}

	/**
	 * パラメータの追加
	 * @param name パラメータ名
	 * @param val 値
	 * @return this
	 */
	public NisJpaFindAllBuilder parameter(String name, Object val) {
		parameters.put(name, val);
		return this;
	}

	/**
	 * 最大件数のセット<br>
	 * safeをonにしている場合、検索後に削除済みが除去されるので、最大件数と一致しない可能性がある。<br>
	 * 問題になる場合は、safeをfalseにしてクエリ内で直接削除フラグを条件に加える事
	 * @param maxResult 最大件数
	 * @return this
	 */
	public NisJpaFindAllBuilder maxResult(int maxResult) {
		this.maxResult = maxResult;
		return this;
	}

	/**
	 * safeモードの設定<br>
	 * safeモードを有効にすると、論理削除されたエンティティを除外する。<br>
	 * デフォルトは有効。<br>
	 * 除外は検索後に行われるので注意が必要(maxResultも参照)。
	 * @param safe true = セーフモード
	 * @return this
	 */
	public NisJpaFindAllBuilder safe(boolean safe) {
		this.safe = safe;
		return this;
	}

	/**
	 * safeモードに
	 * @return this
	 */
	public NisJpaFindAllBuilder safe() {
		this.safe = true;
		return this;
	}

	/**
	 * safeモードを無効に
	 * @return this
	 */
	public NisJpaFindAllBuilder unsafe() {
		this.safe = false;
		return this;
	}

}
