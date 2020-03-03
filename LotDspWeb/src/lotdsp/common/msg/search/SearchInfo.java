package lotdsp.common.msg.search;

import nis.framework.dictionary.InputModel;

import nis.framework.dictionary.Zenhankaku;
import nis.framework.dictionary.Zenhankaku.ZenhankakuElm;

@InputModel
public class SearchInfo {

	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String lotNo;  // ロットNo

	@Zenhankaku(ZenhankakuElm.HANKAKU)
	private String kensaNo;  // 検査No

	private int nowPage;

	public SearchInfo() {
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}
	
	public String getKensaNo() {
		return kensaNo;
	}

	public void setKensaNo(String kensaNo) {
		this.kensaNo = kensaNo;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	@Override
	public String toString() {
		return org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString(this);
	}
}

