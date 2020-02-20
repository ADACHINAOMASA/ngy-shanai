package nis.framework.auth;

public class Authority {

	private String name = "";

	public Authority(){
	}

	public Authority(String name){
		this.name = name;
	}

	public Authority(Enum<?> name) {
		this.name = name.name();
	}

	/**
	 * nameを取得します。
	 * @return name
	 */
	public String getName() {
	    return name;
	}

	/**
	 * nameを設定します。
	 * @param name name
	 */
	public void setName(String name) {
	    this.name = name;
	}


}
