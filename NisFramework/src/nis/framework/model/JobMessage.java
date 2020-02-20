/*
 * 作成日: 2003/10/08
 */
package nis.framework.model;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 * 業務固有メッセージの基底クラス。
 * @author Toshiyuki-Ichikawa
 */
public abstract class JobMessage implements Serializable, Cloneable {

	private static final long serialVersionUID = -5645950095960142572L;

	private final static int REFLEXIVE_SIZE = 3;

	/**
	 * クローン機能の基本サポート。
	 * DeepCopyをサポートする場合はサブクラス側で調整する。
	 */
	public Object clone() {
		try {
			return super.clone();
		}
		catch (CloneNotSupportedException x) {
			throw new InternalError(x.getMessage());
		}
	}

// トレース関連

	/**
	 * カスタマイズなしの toString を呼び出す。
	 */
	protected final String toStringDefault() {
		return super.toString();
	}

	/**
	 * オブジェクトの文字列表現を返す。
	 */
	public String toString() {
		return toString(1);
	}

	/**
	 * 各プロパティ値を列挙する。
	 * トレース量が膨大になる場合は、サブクラスでカスタマイズしてください。
	 * @param indent インデントレベル
	 * @return 文字列表現
	 */
	protected String toString(int indent) {
		StringBuffer buf = new StringBuffer(toStringDefault());

		// 各プロパティの値を取得
		Map<String, Object> map = new TreeMap<String, Object>();
		try {
			PropertyDescriptor[] props = Introspector.getBeanInfo(getClass()).getPropertyDescriptors();
			for (int n = 0; n < props.length; n++) {
				String name = props[n].getName();
				if (name.equals("class")) { // getClass()は無視
					continue;
				}
				if (props[n].getReadMethod() == null) {
				}
				else {
					Object value = props[n].getReadMethod().invoke(this, new Object[0]);
					map.put(name, value);
				}
			}
		}
		catch (IntrospectionException x) {
		}
		catch (InvocationTargetException x) {
		}
		catch (IllegalAccessException x) {
		}

		// プロパティ表示文字列の作成
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			String name = iter.next();
			Object value = map.get(name);

			// コレクションの場合は再帰呼び出し
			if (value instanceof Collection) {
				int reflexiveSize = getCollectionReflexiveSize();

				// 再帰呼び出ししない
				if (reflexiveSize == 0) {
					writeBuffer(buf, indent, name + " - " + ((Collection<?>) value).size() + "件");
				}
				// 件数分だけ再帰呼び出し
				else {
					Iterator<?> subIter = ((Collection<?>) value).iterator();
					int count = 0;
					while (subIter.hasNext()) {
						JobMessage subModel = (JobMessage) subIter.next();
						writeBuffer(buf, indent, name + " - " + subModel.toString(indent + 1));
						count++;

						if (reflexiveSize != -1 && reflexiveSize == count) {
							break;
						}
					}
					if (subIter.hasNext()) {
						writeBuffer(buf, indent, name + " - And more .. (全" + ((Collection<?>) value).size() + "件)");
					}
				}
				continue;
			}

			// JobModelの場合は再帰呼び出し
			if (value instanceof JobMessage) {
				JobMessage subModel = (JobMessage) value;
				writeBuffer(buf, indent, name + " - " + subModel.toString(indent + 1));
				continue;
			}

			writeBuffer(buf, indent, name + " - " + value);
		}

		return buf.toString();
	}

	/**
	 * コレクションの中身を再帰呼び出しして書き出す回数
	 * デフォルト-1は制限なしで再帰呼び出しを行う
	 * オーバーライドして0を返せば、コレクションを再帰呼び出しして文字列表現しない。
	 * オーバーライドして3を返せば、3回だけコレクションを再帰呼び出しして文字列表現する。さらに全件数を出力する。
	 * @return 再帰呼び出しする回数
	 */
	protected int getCollectionReflexiveSize() {
		return REFLEXIVE_SIZE;
	}

	/**
	 * 引数のバッファにインデント付きの文字列を書き込む。
	 * @param buf 文字列のバッファ
	 * @param indent インデントレベル
	 * @param value 値
	 */
	protected static void writeBuffer(StringBuffer buf, int indent, Object value) {
		buf.append("\r\n");
		for (int n = 0; n < indent; n++) {
			buf.append("\t");
		}
		buf.append(value);
	}
}
