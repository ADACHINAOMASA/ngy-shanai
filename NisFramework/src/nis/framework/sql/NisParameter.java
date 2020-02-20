package nis.framework.sql;

import java.math.BigDecimal;
import java.util.Date;
public class NisParameter {

	/**
	 * �p�����[�^������
	 */
	private String parameter;

	/**
	 * �p�����[�^�l
	 */
	private Object value;

	/**
	 * �R���X�g���N�^
	 */
	public NisParameter() {
	}

	/**
	 * �R���X�g���N�^
	 *
	 * @param parameter �p�����[�^������
	 * @param value �p�����[�^�l
	 */
	public NisParameter(String parameter, Object value) {
		this.parameter = parameter;
		if (value instanceof java.sql.Date) {
			this.value = new Date(((java.sql.Date) value).getTime());
		}
		else {
			this.value = value;
		}
	}

	/**
	 * �p�����[�^��������擾����B
	 *
	 * @return �p�����[�^������
	 */
	public String getParameter() {
		return parameter;
	}

	/**
	 * �p�����[�^�������ݒ肷��B
	 *
	 * @param parameter �p�����[�^������
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	/**
	 * �p�����[�^�l���擾����B
	 *
	 * @return �p�����[�^�l
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * �p�����[�^�l��ݒ肷��B
	 *
	 * @param value �p�����[�^�l
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * �p�����[�^�l���ݒ肳��Ă��邩�ǂ����𔻒肷��B
	 */
	public boolean hasValue() {
		return value != null;
	}

	/**
	 * �p�����[�^�l�� String �^���ǂ����𔻒肷��B
	 */
	public boolean isString() {
		return isClass(String.class);
	}

	/**
	 * �p�����[�^�l�� BigDecimal �^���ǂ����𔻒肷��B
	 */
	public boolean isBigDecimal() {
		return isClass(BigDecimal.class);
	}

	/**
	 * �p�����[�^�l�� java.util.Date �^���ǂ����𔻒肷��B
	 */
	public boolean isDate() {
		return isClass(Date.class);
	}

	/**
	 * �p�����[�^�l����̌^���ǂ����𔻒肷��B
	 */
	private boolean isClass(@SuppressWarnings("rawtypes") Class class_) {
		if (hasValue() && value.getClass().equals(class_)) {
			return true;
		}
		return false;
	}
}
