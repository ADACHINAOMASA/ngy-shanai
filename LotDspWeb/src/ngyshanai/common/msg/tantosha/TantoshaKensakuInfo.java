package ngyshanai.common.msg.tantosha;

import java.math.BigDecimal;
import java.sql.Timestamp;

import nis.framework.dictionary.DefaultValue;
import nis.framework.dictionary.InputModel;
import nis.framework.dictionary.MaxLength;
import nis.framework.dictionary.OmojiKomoji;
import nis.framework.dictionary.OmojiKomoji.OmojiKomojiElm;
import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class TantoshaKensakuInfo { 

	/**
	 * 担当者コード
	 */
	@MaxLength(5)
	@OmojiKomoji(OmojiKomojiElm.KONZAI)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String tantoshaCd;

	/**
	 * 担当者名
	 */
	@MaxLength(16)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String tantoshaMei;

	/**
	 * 担当者カナ
	 */
	@MaxLength(16)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String tantoshaKana;

	/**
	 * 事業所コード
	 */
	@MaxLength(2)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String jigyoshoCd;

	/**
	 * 部門コード
	 */
	@MaxLength(2)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String bumonCd;

	/**
	 * パスワード
	 */
	@MaxLength(20)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String password;

	/**
	 * 社内メールアドレス
	 */
	@MaxLength(80)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String shanaiMailAddr;

	/**
	 * 携帯メールアドレス
	 */
	@MaxLength(80)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String keitaiMailAddr;
	
	/**
	 * 使用端末１
	 */
	@MaxLength(15)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String shiyotanmatsu1;

	/**
	 * 使用端末２
	 */
	@MaxLength(15)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String shiyotanmatsu2;

	/**
	 * 使用端末３
	 */
	@MaxLength(15)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String shiyotanmatsu3;

	/**
	 * 受注管理権限
	 */
	@MaxLength(1)
	@DefaultValue("0")
	private String juchuKengen;

	/**
	 * 仕入管理権限
	 */
	@MaxLength(1)
	@DefaultValue("0")
	private String kobaiKengen;

	/**
	 * 売上管理権限
	 */
	@MaxLength(1)
	@DefaultValue("0")
	private String uriageKengen;

	/**
	 * 在庫管理権限
	 */
	@MaxLength(1)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String zaikoKengen;

	/**
	 * 売掛管理権限
	 */
	@MaxLength(1)
	@DefaultValue("0")
	private String urikakeKengen;

	/**
	 * 共通管理権限
	 */
	@MaxLength(1)
	@DefaultValue("0")
	private String kyotsuKengen;

	/**
	 * 備考
	 */
	@MaxLength(50)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String biko;

	/**
	 * バージョン
	 */
	@MaxLength(5)
	private BigDecimal version;

	/**
	 * 登録ユーザーＩＤ
	 */
	@MaxLength(5)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String torokuUserid;

	/**
	 * 登録日時
	 */
	private Timestamp torokuYmdhms;

	/**
	 * 登録フロントID
	 */
	@MaxLength(30)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String torokuFrontid;

	/**
	 * 更新ユーザーＩＤ
	 */
	@MaxLength(5)
	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String koshinUserid;

	/**
	 * 更新日時
	 */
	private Timestamp koshinYmdhms;

	/**
	 * 更新フロントID
	 */
	@MaxLength(30)
	@Zenhankaku(ZenhankakuElm.KONZAI)
	private String koshinFrontid;

	
	private String shoninKengen;
	
	
	/**
	 * デフォルトコンストラクタ
	 */
	public TantoshaKensakuInfo() {
	}

	/**
	 * 再帰呼び出し回数取得
	 */
	protected int getCollectionReflexiveSize() {
		return 0;
	}

	/**
	 * 担当者コードを取得する
	 * @return 担当者コード
	 */
	public String getTantoshaCd() {
		return tantoshaCd;
	}

	/**
	 * 担当者コードを設定する
	 * @param TantoshaCd 担当者コード
	 */
	public void setTantoshaCd(String tantoshaCd) {
		this.tantoshaCd = tantoshaCd;
	}

	/**
	 * 担当者名を取得する
	 * @return 担当者名
	 */
	public String getTantoshaMei() {
		return tantoshaMei;
	}

	/**
	 * 担当者名を設定する
	 * @param TantoshaMei 担当者名
	 */
	public void setTantoshaMei(String tantoshaMei) {
		this.tantoshaMei = tantoshaMei;
	}

	/**
	 * 担当者カナを取得する
	 * @return 担当者カナ
	 */
	public String getTantoshaKana() {
		return tantoshaKana;
	}

	/**
	 * 担当者カナを設定する
	 * @param TantoshaKana 担当者カナ
	 */
	public void setTantoshaKana(String tantoshaKana) {
		this.tantoshaKana = tantoshaKana;
	}

	/**
	 * 事業所コードを取得する
	 * @return 事業所コード
	 */
	public String getJigyoshoCd() {
		return jigyoshoCd;
	}

	/**
	 * 事業所コードを設定する
	 * @param JigyoshoCd 事業所コード
	 */
	public void setJigyoshoCd(String jigyoshoCd) {
		this.jigyoshoCd = jigyoshoCd;
	}

	/**
	 * 部門コードを取得する
	 * @return 部門コード
	 */
	public String getBumonCd() {
		return bumonCd;
	}

	/**
	 * 部門コードを設定する
	 * @param BumonCd 部門コード
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}

	/**
	 * パスワードを取得する
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定する
	 * @param Password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 社内メールアドレスを取得する
	 * @return 社内メールアドレス
	 */
	public String getShanaiMailAddr() {
		return shanaiMailAddr;
	}

	/**
	 * 社内メールアドレスを設定する
	 * @param ShanaiMailAddr 社内メールアドレス
	 */
	public void setShanaiMailAddr(String shanaiMailAddr) {
		this.shanaiMailAddr = shanaiMailAddr;
	}

	/**
	 * 携帯メールアドレスを取得する
	 * @return 携帯メールアドレス
	 */
	public String getKeitaiMailAddr() {
		return keitaiMailAddr;
	}

	/**
	 * 携帯メールアドレスを設定する
	 * @param KeitaiMailAddr 携帯メールアドレス
	 */
	public void setKeitaiMailAddr(String keitaiMailAddr) {
		this.keitaiMailAddr = keitaiMailAddr;
	}
	
	/**
	 * 使用端末１を取得する
	 * @return 使用端末１
	 */
	public String getShiyotanmatsu1() {
		return shiyotanmatsu1;
	}

	/**
	 * 使用端末１を設定する
	 * @param Shiyotanmatsu1 使用端末１
	 */
	public void setShiyotanmatsu1(String shiyotanmatsu1) {
		this.shiyotanmatsu1 = shiyotanmatsu1;
	}

	/**
	 * 使用端末２を取得する
	 * @return 使用端末２
	 */
	public String getShiyotanmatsu2() {
		return shiyotanmatsu2;
	}

	/**
	 * 使用端末２を設定する
	 * @param Shiyotanmatsu2 使用端末２
	 */
	public void setShiyotanmatsu2(String shiyotanmatsu2) {
		this.shiyotanmatsu2 = shiyotanmatsu2;
	}

	/**
	 * 使用端末３を取得する
	 * @return 使用端末３
	 */
	public String getShiyotanmatsu3() {
		return shiyotanmatsu3;
	}

	/**
	 * 使用端末３を設定する
	 * @param Shiyotanmatsu3 使用端末３
	 */
	public void setShiyotanmatsu3(String shiyotanmatsu3) {
		this.shiyotanmatsu3 = shiyotanmatsu3;
	}

	/**
	 * 受注管理権限を取得する
	 * @return 受注管理権限
	 */
	public String getJuchuKengen() {
		return juchuKengen;
	}

	/**
	 * 受注管理権限を設定する
	 * @param JuchuKengen 受注管理権限
	 */
	public void setJuchuKengen(String juchuKengen) {
		this.juchuKengen = juchuKengen;
	}

	/**
	 * 仕入管理権限を取得する
	 * @return 仕入管理権限
	 */
	public String getKobaiKengen() {
		return kobaiKengen;
	}

	/**
	 * 仕入管理権限を設定する
	 * @param KobaiKengen 仕入管理権限
	 */
	public void setKobaiKengen(String kobaiKengen) {
		this.kobaiKengen = kobaiKengen;
	}

	/**
	 * 売上管理権限を取得する
	 * @return 売上管理権限
	 */
	public String getUriageKengen() {
		return uriageKengen;
	}

	/**
	 * 売上管理権限を設定する
	 * @param UriageKengen 売上管理権限
	 */
	public void setUriageKengen(String uriageKengen) {
		this.uriageKengen = uriageKengen;
	}

	/**
	 * 在庫管理権限を取得する
	 * @return 在庫管理権限
	 */
	public String getZaikoKengen() {
		return zaikoKengen;
	}

	/**
	 * 在庫管理権限を設定する
	 * @param ZaikoKengen 在庫管理権限
	 */
	public void setZaikoKengen(String zaikoKengen) {
		this.zaikoKengen = zaikoKengen;
	}

	/**
	 * 売掛管理権限を取得する
	 * @return 売掛管理権限
	 */
	public String getUrikakeKengen() {
		return urikakeKengen;
	}

	/**
	 * 売掛管理権限を設定する
	 * @param UrikakeKengen 売掛管理権限
	 */
	public void setUrikakeKengen(String urikakeKengen) {
		this.urikakeKengen = urikakeKengen;
	}

	/**
	 * 共通管理権限を取得する
	 * @return 共通管理権限
	 */
	public String getKyotsuKengen() {
		return kyotsuKengen;
	}

	/**
	 * 共通管理権限を設定する
	 * @param KyotsuKengen 共通管理権限
	 */
	public void setKyotsuKengen(String kyotsuKengen) {
		this.kyotsuKengen = kyotsuKengen;
	}

	/**
	 * 備考を取得する
	 * @return 備考
	 */
	public String getBiko() {
		return biko;
	}

	/**
	 * 備考を設定する
	 * @param Biko 備考
	 */
	public void setBiko(String biko) {
		this.biko = biko;
	}

	/**
	 * バージョンを取得する
	 * @return バージョン
	 */
	public BigDecimal getVersion() {
		return version;
	}

	/**
	 * バージョンを設定する
	 * @param Version バージョン
	 */
	public void setVersion(BigDecimal version) {
		this.version = version;
	}

	/**
	 * 登録ユーザーＩＤを取得する
	 * @return 登録ユーザーＩＤ
	 */
	public String getTorokuUserid() {
		return torokuUserid;
	}

	/**
	 * 登録ユーザーＩＤを設定する
	 * @param TorokuUserid 登録ユーザーＩＤ
	 */
	public void setTorokuUserid(String torokuUserid) {
		this.torokuUserid = torokuUserid;
	}

	/**
	 * 登録日時を取得する
	 * @return 登録日時
	 */
	public Timestamp getTorokuYmdhms() {
		return torokuYmdhms;
	}

	/**
	 * 登録日時を設定する
	 * @param TorokuYmdhms 登録日時
	 */
	public void setTorokuYmdhms(Timestamp torokuYmdhms) {
		this.torokuYmdhms = torokuYmdhms;
	}

	/**
	 * 登録フロントIDを取得する
	 * @return 登録フロントID
	 */
	public String getTorokuFrontid() {
		return torokuFrontid;
	}

	/**
	 * 登録フロントIDを設定する
	 * @param TorokuFrontid 登録フロントID
	 */
	public void setTorokuFrontid(String torokuFrontid) {
		this.torokuFrontid = torokuFrontid;
	}

	/**
	 * 更新ユーザーＩＤを取得する
	 * @return 更新ユーザーＩＤ
	 */
	public String getKoshinUserid() {
		return koshinUserid;
	}

	/**
	 * 更新ユーザーＩＤを設定する
	 * @param KoshinUserid 更新ユーザーＩＤ
	 */
	public void setKoshinUserid(String koshinUserid) {
		this.koshinUserid = koshinUserid;
	}

	/**
	 * 更新日時を取得する
	 * @return 更新日時
	 */
	public Timestamp getKoshinYmdhms() {
		return koshinYmdhms;
	}

	/**
	 * 更新日時を設定する
	 * @param KoshinYmdhms 更新日時
	 */
	public void setKoshinYmdhms(Timestamp koshinYmdhms) {
		this.koshinYmdhms = koshinYmdhms;
	}

	/**
	 * 更新フロントIDを取得する
	 * @return 更新フロントID
	 */
	public String getKoshinFrontid() {
		return koshinFrontid;
	}

	/**
	 * 更新フロントIDを設定する
	 * @param KoshinFrontid 更新フロントID
	 */
	public void setKoshinFrontid(String koshinFrontid) {
		this.koshinFrontid = koshinFrontid;
	}

	public String getShoninKengen() {
		return shoninKengen;
	}

	public void setShoninKengen(String shokinKengen) {
		this.shoninKengen = shokinKengen;
	}
	
}
