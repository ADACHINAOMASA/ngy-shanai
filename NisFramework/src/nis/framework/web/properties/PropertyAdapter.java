package nis.framework.web.properties;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * �v���p�e�B�A�_�v�^
 */
public abstract class PropertyAdapter {

	/**
	 * �v���p�e�B�I�u�W�F�N�g
	 */
	private Properties properties = new Properties();

	/**
	 * �v���p�e�B���̂�Ԃ�
	 * @return �v���p�e�B����
	 */
	protected abstract String getPropertyName();

	/**
	 * �R���X�g���N�^
	 */
	public PropertyAdapter() {
		this(PropertyAdapter.class.getClassLoader());
	}

	/**
	 * �R���X�g���N�^
	 */
	public PropertyAdapter(ClassLoader loader) {
		try {
			properties.load(loader.getResourceAsStream(getPropertyName()));
		}
		catch (IOException x) {
		}
	}

	/**
	 * �v���p�e�B�l��Ԃ�
	 * @param keyName �v���p�e�B�L�[
	 * @return �v���p�e�B�l
	 */
	public String getString(String keyName) {
		return properties.getProperty(keyName);
	}

	/**
	 * �v���p�e�B�l��Ԃ�
	 * @param keyName �v���p�e�B�L�[
	 * @return �v���p�e�B�l
	 */
	public BigDecimal getBigDecimal(String keyName) {
		return new BigDecimal(properties.getProperty(keyName));
	}

}
