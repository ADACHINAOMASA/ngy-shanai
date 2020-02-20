package nis.framework.web.inputmodel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import nis.framework.dictionary.DefaultValue;
import nis.framework.dictionary.InputModel;
import nis.framework.type.Hiduke;
import nis.framework.type.Nengetsu;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;


/**
 * InputModelクラスインスタンスクリエイタ
 * <p>
 *  InputModelクラスより新たなインスタンスを生成する<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
class InputModelClassInstanceCreator {

	/**
	 * 生成
	 * @param modelClass
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	public static Object create(InputModelClass modelClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
		return create(modelClass.getNestedClass());
	}

	/**
	 * 生成
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	private static Object create(Class<?> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
		Object instance = clazz.newInstance();
		for (Field field : clazz.getDeclaredFields()) {
			BeanUtils.setProperty(instance, field.getName(), getDefaultValue(field));
		}
		return instance;
	}

	/**
	 * 初期値取得
	 * そのプロパティの型から対応する初期値を取得または生成する
	 * DefaultValueアノテーションが指定されている場合はそこから作る
	 * @param field
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 */
	private static Object getDefaultValue(Field field) throws IllegalAccessException, InstantiationException, InvocationTargetException {
		// TODO:切り離し 初期値の設定
		Class<?> fieldClass = field.getType();
		if (String.class.isAssignableFrom(fieldClass)) {
			return getStringDefaultValue(field);
		}
		else if (BigDecimal.class.isAssignableFrom(fieldClass)) {
			return getBigDecimalDefaultValue(field);
		}
		else if (Date.class.isAssignableFrom(fieldClass)) {
			return getDateDefaultValue(field);
		}
		else if (Timestamp.class.isAssignableFrom(fieldClass)) {
			return getDateDefaultValue(field);
		}
		else if (Hiduke.class.isAssignableFrom(fieldClass)) {
			return new Hiduke(getDateDefaultValue(field));
		}
		else if (Nengetsu.class.isAssignableFrom(fieldClass)) {
			return new Nengetsu(getDateDefaultValue(field));
		}
		else if (Collection.class.isAssignableFrom(fieldClass)) {
			// TODO:総称型についてどうするか考える
			// TODO:初期サイズ
			return new ArrayList<Object>();
		}
		else if (fieldClass.isAnnotationPresent(InputModel.class)) {
			return create(fieldClass);
		}
		if (field.isAnnotationPresent(DefaultValue.class)) {
			throw new IllegalArgumentException("初期値セットに対応していない型:" + fieldClass.getName());
		}
		return null;
	}

	/**
	 * 空文字またはDefaultValueで指定された文字列
	 * @param field
	 * @return
	 */
	private static String getStringDefaultValue(Field field) {
		if (field.isAnnotationPresent(DefaultValue.class)) {
			return field.getAnnotation(DefaultValue.class).value();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 0またはDefaultValueされた文字列を数値に変換
	 * @param field
	 * @return
	 */
	private static BigDecimal getBigDecimalDefaultValue(Field field) {
		if (field.isAnnotationPresent(DefaultValue.class)) {
			return NumberUtils.createBigDecimal(field.getAnnotation(DefaultValue.class).value());
		}
		return BigDecimal.ZERO;
	}

	/**
	 * nullまたはDefaultValueで指定された文字列を日付に変換
	 * @param field
	 * @return
	 */
	private static Date getDateDefaultValue(Field field) {
		if (field.isAnnotationPresent(DefaultValue.class)) {
			String val = field.getAnnotation(DefaultValue.class).value();
			if ("current_date".equals(val)) {
				return new Date(System.currentTimeMillis());
			}
			try {
				return DateUtils.parseDate(val, "yyyy/MM/dd", "yyyy-MM-dd", "yyyyMMdd");
			} catch (ParseException e) {
				throw new IllegalArgumentException(e);
			}
		}
		return null;
	}

}
