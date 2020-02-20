package nis.framework.web.inputmodel;

import nis.framework.dictionary.InputModel;

import org.apache.commons.lang3.StringUtils;

/**
 * InputModelクリエイタ
 * <p>
 *  InputModelアノテーションが設定されたクラスからInputModelClassオブジェクトを作る<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
public class InputModelCreator {

	/**
	 * 生成
	 * @param clazz InputModel
	 * @return
	 */
	public static InputModelClass create(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(InputModel.class)) {
			throw new IllegalArgumentException("InputModelでないクラス");
		}
		String name = clazz.getAnnotation(InputModel.class).value();
		if (StringUtils.isEmpty(name)) {
			name = clazz.getSimpleName();
		}
		return new InputModelClass(name, clazz);
	}

}
