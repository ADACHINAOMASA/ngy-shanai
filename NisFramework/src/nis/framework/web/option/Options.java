package nis.framework.web.option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * <p>
 * オプション群<br>
 * あるキーで纏められたオプションの一群
 * </p>
 * @author Kengo-Nagase
 *
 */
public class Options {

	/**
	 * キー
	 */
	private String key;

	/**
	 * オプション群
	 */
	private List<Option> options = new ArrayList<Option>();

	/**
	 *
	 */
	public Options(){}

	/**
	 *
	 * @param key
	 */
	public Options(String key){
		this(key, new ArrayList<Option>());
	}

	/**
	 *
	 * @param key
	 * @param options
	 */
	public Options(String key, List<Option> options){
		this.key = key;
		addOptions(options);
	}

	/**
	 * オプション追加
	 * @param option
	 */
	public void addOption(Option...option) {
		addOptions(Arrays.asList(option));
	}

	/**
	 * オプション追加
	 * @param options
	 */
	public void addOptions(List<Option> options) {
		this.options.addAll(options);
	}

	/**
	 * オプション取得
	 * @param value
	 * @return
	 */
	public Option getOption(String value) {
		for (Option option : getOptions()) {
			if (option.getValue().equals(value)) {
				return option;
			}
		}
		return null;
	}

	/**
	 * keyを取得します。
	 * @return key
	 */
	public String getKey() {
	    return key;
	}

	/**
	 * keyを設定します。
	 * @param key key
	 */
	public void setKey(String key) {
	    this.key = key;
	}

	/**
	 * optionsを取得します。
	 * @return options
	 */
	public List<Option> getOptions() {
	    return options;
	}

	/**
	 * optionsを設定します。
	 * @param options options
	 */
	public void setOptions(List<Option> options) {
	    this.options = options;
	}

	public boolean containsOption(String value) {
		return getOption(value) != null;
	}

}
