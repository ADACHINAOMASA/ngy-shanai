package lotdsp.domain.nagoya.logic;

/**
 * XMLメッセージ
 */
public class XmlMsg {

  /**
   *  名前
   */
  private String name;

  /**
   * IP
   */
  private String ip;

  /**
   * モード
   */
  private String mode;

  public void setName(String name){
	this.name = name;
  }

  public String getName(){
	return name;
  }

  public void setIp(String ip){
	this.ip = ip;
  }

  public String getIp(){
	return ip;
  }

  public String getMode(){
	return mode;
  }

  public void setMode(String mode){
	this.mode = mode;
  }

}
