package ngyshanai.entity.oldframework;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import nis.framework.jpa.EmContext;
import nis.framework.properties.AppProperties;

public abstract class EntityAbstract<U> implements Serializable {

	private static final long serialVersionUID = 1L;
	private EntityManager em = null;
	private static final String JNDI_NAME = AppProperties.getDataSourceJndiDefaultName();

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public EntityManager getEntityManager() {
		try {
			if (em == null) {
				em = EmContext.get();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return em;
	}

	/**
	 * entityの永続化（DBMSとの関連付けnew→persistとするとキー重複は事前にチェックしておくこと。コミット時にエラーになる）
	 */
	public void persist() {
		getEntityManager().persist(this);
	};

	/**
	 * entityの関連属性をリフレッシュ（属性のキー値を変更した場合に利用。flushする必要があるのでコストが高い？）
	 * フラッシュで一旦DBに反映し、リフレッシュでDBの内容をリロードという感じ属性を再セットする、
	 * 属性のキー値を入れ替えただけでは属性側は置き換わらない。
	 * refreshはDBから内容と取り直すということなので、通常flushと同時に使用する。
	 */
	public void relationRefresh() {
		flush();
		getEntityManager().refresh(this);
	};

	public void remove() {
		getEntityManager().remove(this);
	}

	/**
	 * JPAキャッシュを全クリアー　関連系のアノテーションを使用している場合、業務ロジックに問題ないにも関わらず以下のエラーがでた場合、
	 * 適切な場所にこのメソッドを追加する。
	 */
	public void cacheClear() {
		flush();
		getEntityManager().clear();
	}

	/**
	 * ぬるぽ避けダミーエンティティが混在する場合、実際存在しているかどうかをチェックする
	 * referで取得したものが実際に存在するかチェックできる。
	 * 厳密にはアクセサーのexistの利用を推奨
	 */
	private boolean exist() {
		return getEntityManager().contains(this);
	}

	public boolean notExist() {
		return !exist();
	}

	/**
	 * フラッシュ。通常エンティティマネージャレベルだが、あるエンティティの更新を終えた時に、フラッシュするのが自然なので、この位置に作成。
	 */
	public void flush() {
		getEntityManager().flush();
	}

	protected void control(UpdateInfo info) {
		throw new UnsupportedOperationException("このメソッドは継承クラスでオーバーライドして下さい。");
	};

	/**
	 * このEntityの更新
	 *
	 * @param updater 項目転送元インスタンス(アップデータを実装したクラス)
	 * @param updaterIntf 転送項目を定義したクラス
	 * @param info 更新者情報
	 */
	protected void updateEntity(U updaterImpl, UpdateInfo info) {
		updateByUpdater(updaterImpl, getUpdaterClass());
		control(info);
	}

	/**
	 * 項目転送<br>
	 * <br>
	 * 　　「アップデータを使用した更新」の別名
	 *
	 * ignoresは一部の項目を転送対象外に指定したい時に利用する
	 */
	protected void komokuTenso(Object srcObject, Class<?> beanClassOrIntf, String... ignores) {
		updateByUpdater(srcObject, beanClassOrIntf, ignores);
	}

	/**
	 * アップデータを使用した更新<br>
	 * <br>
	 * 　　自身に項目転送元インスタンスの内容を転送します。
	 *
	 * @param updater 項目転送元インスタンス
	 * @param updaterIntf 転送項目を定義したクラス
	 */
	private void updateByUpdater(Object updater, Class<?> updaterIntf, String... ignores) {
		// Updaterクラスの属性をすべて転送する
		PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(updaterIntf);
		for (PropertyDescriptor propertyDescriptor : props) {
			if (isYukoProperty(propertyDescriptor.getName(), ignores)) {
				try {
					Method readMethod = PropertyUtils.getReadMethod(PropertyUtils.getPropertyDescriptor(updater, propertyDescriptor.getName()));
					Object value = readMethod.invoke(updater, new Object[0]);
					setValue(this, propertyDescriptor.getName(), value);
				}
				catch (Exception e) {
					e.printStackTrace();
					throw new UnsupportedOperationException(propertyDescriptor.getName() + ":プロパティー転送に失敗しました。");
				}
			}
		}
	}

	/**
	 * 有効なプロパティかどうかの判定。無効なものが見つかったら追加していく。
	 */
	protected boolean isYukoProperty(String propertyName, String... ignores) {
		if (ignores.length > 0) {
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i].equals(propertyName)) {
					return false; // 無視する属性にヒット
				}
			}
		}
		return !"class".equals(propertyName);
	}

	/**
	 * 名前指定で値をセットする
	 */
	protected void setValue(Object dstInst, String propertyName, Object value) {
		try {
			Method writeMethod = PropertyUtils.getWriteMethod(PropertyUtils.getPropertyDescriptor(dstInst, propertyName));
			writeMethod.invoke(dstInst, new Object[] { value });
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(propertyName + ":プロパティー設定に失敗しました。");
		}
	}

	/**
	 * 属性取出<br>
	 * <br>
	 * 　　自身の属性を目的のオブジェクトに転送する。
	 *
	 * @param dstObj 転送先インスタンス
	 * @param updaterIntf 転送項目を定義したクラス 取り敢えずアップデータのみの制約を付ける
	 */
	public void takeOutAttr(Object dstInst, Class<U> updaterIntf, String... ignores) {
		// Updaterクラスの属性をすべて転送する
		PropertyDescriptor[] props = PropertyUtils.getPropertyDescriptors(updaterIntf);
		for (PropertyDescriptor propertyDescriptor : props) {
			if (isYukoProperty(propertyDescriptor.getName(), ignores)) {
				try {
					Method readMethod = PropertyUtils.getReadMethod(PropertyUtils.getPropertyDescriptor(this, propertyDescriptor.getName()));
					Object value = readMethod.invoke(this, new Object[0]);
					setValue(dstInst, propertyDescriptor.getName(), value);
				}
				catch (Exception e) {
					e.printStackTrace();
					throw new UnsupportedOperationException(propertyDescriptor.getName() + ":プロパティー転送に失敗しました。");
				}
			}
		}
	}

	/**
	 * Updaterクラスを返す
	 */
	protected Class<U> getUpdaterClass() {
		throw new UnsupportedOperationException("このメソッドは継承クラスでオーバーライドして下さい。");
	}

}
