package nis.framework.web.inputmodel;


/**
 * InputModelクラス
 * <p>
 *  このInputModelの名前と元となるクラスを保持し、そのクラスの新インスタンスを取得する為のクラス<br>
 * </p>
 * @author Kengo-Nagase
 *
 */
public class InputModelClass {

	private String name;

	private Class<?> nestedClass;

	InputModelClass(String name, Class<?> nestedClass) {
		this.name = name;
		this.nestedClass = nestedClass;
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * nestedClassを取得します。
	 * @return nestedClass
	 */
	public Class<?> getNestedClass() {
	    return nestedClass;
	}

	/**
	 * 新たなInstanceの生成
	 * @return このInputModelクラスが確保している元クラスの初期値が設定されたインスタンス
	 */
	public Object newInstance() {
		try {
			return InputModelClassInstanceCreator.create(this);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
