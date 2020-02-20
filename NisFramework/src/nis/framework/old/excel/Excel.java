/*
 * �쐬��: 2007/08/16
 */
package nis.framework.old.excel;

import java.io.IOException;
import java.io.OutputStream;

/**
 * �G�N�Z���C���^�[�t�F�C�X
 */
public interface Excel {

	/**
	 * �X�g���[���o��
	 */
	public void write(OutputStream stream) throws ExcelException, IOException;

	/**
	 * �o�C�g�z���Ԃ�
	 */
	public byte[] toByteArray() throws ExcelException, IOException;

}
