/*
 * �쐬��: 2007/08/16
 */
package nis.framework.old.excel;

import java.io.IOException;
import java.io.OutputStream;

import nis.framework.web.properties.PropertyAdapter;


/**
 * �G�N�Z���̃x�[�X�N���X
 */
public abstract class ExcelBase implements Excel {

	protected static final PropertyAdapter adapter = new PropertyAdapter() {
		protected String getPropertyName() {
			return "excel.properties";
		}
	};

	/**
	 * �f�[�^
	 */
	private byte[] binData;

	/**
	 * �X�g���[���o��
	 */
	public void write(OutputStream stream) throws ExcelException, IOException {
		createData();
		if (binData != null) {
			stream.write(binData);
		}
	}

	/**
	 * �o�C�g�z���Ԃ�
	 */
	public byte[] toByteArray() throws ExcelException {
		createData();
		return binData;
	}

	/**
	 * �G�N�Z�����쐬����
	 */
	private void createData() throws ExcelException {
		if (binData == null) {
			binData = executeCreate();
		}
	}

	/**
	 * �G�N�Z�����쐬����
	 */
	protected abstract byte[] executeCreate() throws ExcelException;

}
