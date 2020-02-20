package lotdsp.common.msg.options;

public class GetSimpleSelectOptionsRequestInfo {

	private String table;

	private String value;

	private String label;

	private String order;

	private String where;

	/**
	 * tableを取得します。
	 * @return table
	 */
	public String getTable() {
	    return table;
	}

	/**
	 * tableを設定します。
	 * @param table table
	 */
	public void setTable(String table) {
	    this.table = table;
	}

	/**
	 * valueを取得します。
	 * @return value
	 */
	public String getValue() {
	    return value;
	}

	/**
	 * valueを設定します。
	 * @param value value
	 */
	public void setValue(String value) {
	    this.value = value;
	}

	/**
	 * labelを取得します。
	 * @return label
	 */
	public String getLabel() {
	    return label;
	}

	/**
	 * labelを設定します。
	 * @param label label
	 */
	public void setLabel(String label) {
	    this.label = label;
	}

	/**
	 * orderを取得します。
	 * @return order
	 */
	public String getOrder() {
	    return order;
	}

	/**
	 * orderを設定します。
	 * @param order order
	 */
	public void setOrder(String order) {
	    this.order = order;
	}

	/**
	 * whereを取得します。
	 * @return where
	 */
	public String getWhere() {
	    return where;
	}

	/**
	 * whereを設定します。
	 * @param where where
	 */
	public void setWhere(String where) {
	    this.where = where;
	}

}
