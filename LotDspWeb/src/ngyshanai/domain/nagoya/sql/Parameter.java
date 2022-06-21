/*
 * Parameter.java
 *
 * Created on 2006/10/17, 16:08
 * Copyright Hirohiko-Matsushita
 *
 */

package ngyshanai.domain.nagoya.sql;
import java.math.BigDecimal;
import java.util.Date;

/**
 * SQLパラメータクラス。
 * @author Hirohiko-Matsushita
 */
public class Parameter {

	/**
	 * パラメータ文字列
	 */
	private String parameter;

	/**
	 * パラメータ値
	 */
	private Object value;

	/**
	 * コンストラクタ
	 */
	public Parameter() {
	}

	/**
	 * コンストラクタ
	 * @param parameter パラメータ文字列
	 * @param value パラメータ値
	 */
	public Parameter(String parameter, Object value) {
		this.parameter = parameter;
		if (value instanceof java.sql.Date) {
			this.value = new Date(((java.sql.Date)value).getTime());
		}
		else {
			this.value = value;
		}
	}

	/**
	 * パラメータ文字列を取得する。
	 * @return パラメータ文字列
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * パラメータ文字列を設定する。
	 * @param parameter パラメータ文字列
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * パラメータ値を取得する。
	 * @return パラメータ値
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * パラメータ値を設定する。
	 * @param value パラメータ値
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * パラメータ値が設定されているかどうかを判定する。
	 */
	public boolean hasValue() {
		return value != null;
	}

	/**
	 * パラメータ値が String 型かどうかを判定する。
	 */
	public boolean isString() {
		return isClass(String.class);
	}

	/**
	 * パラメータ値が BigDecimal 型かどうかを判定する。
	 */
	public boolean isBigDecimal() {
		return isClass(BigDecimal.class);
	}

	/**
	 * パラメータ値が java.util.Date 型かどうかを判定する。
	 */
	public boolean isDate() {
		return isClass(Date.class);
	}

	/**
	 * パラメータ値が引数の型かどうかを判定する。
	 */
	private boolean isClass(Class class_) {
		if (hasValue() && value.getClass().equals(class_)) {
			return true;
		}
		return false;
	}
}

