package lotdsp.common.lotdsp;

import java.util.List;

import lotdsp.domain.nagoya.bean.CFInfoBean;
import lotdsp.domain.nagoya.bean.CladInfoBean;
import lotdsp.domain.nagoya.bean.IcasBean;
import lotdsp.domain.nagoya.bean.LotDspUserBean;
import lotdsp.domain.nagoya.bean.StaffCommonBean;
import lotdsp.domain.nagoya.bean.StaffManufactureBean;
import lotdsp.domain.nagoya.bean.StaffProgressBean;
import lotdsp.domain.nagoya.bean.StaffQualityBean;
import lotdsp.domain.nagoya.bean.StaffQualityBoxBean;
import lotdsp.domain.nagoya.bean.StaffTestBean;
import nis.framework.dictionary.InputModel;

@InputModel
public class CommonInfo extends AuthenticationInfo {

	private boolean error;

	private String errorMessage;

	private String message;

	private boolean errorFlg;

	private String searchLtno;

	private String searchKnno;

	private String userId;

	private String password;

	private List searchKeyInfos;

	private LotDspUserBean lotDspUserBean;

	private IcasBean icasBean;

	private StaffCommonBean staffCommonBean;

	private StaffProgressBean staffProgressBean;

	private StaffManufactureBean staffManufactureBean;

	private StaffTestBean staffTestBean;

	private StaffQualityBean staffQualityBean;

	private StaffQualityBoxBean qualityDetail;

	private boolean qualityInfoTabRendered;

	private CladInfoBean cladInfoBean;

	private CFInfoBean cFInfoBean;

	private String nextGamen;

	private boolean loginFlg = false;

	private int lotMaximum;

	private int nowPage;

	private String paramCyno;

	private String paramLinkkey;

	private boolean tabSetRendered = true;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isErrorFlg() {
		return errorFlg;
	}

	public void setErrorFlg(boolean errorFlg) {
		this.errorFlg = errorFlg;
	}

	public String getSearchLtno() {
		return searchLtno;
	}

	public void setSearchLtno(String searchLtno) {
		this.searchLtno = searchLtno;
	}

	public String getSearchKnno() {
		return searchKnno;
	}

	public void setSearchKnno(String searchKnno) {
		this.searchKnno = searchKnno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List getSearchKeyInfos() {
		return searchKeyInfos;
	}

	public void setSearchKeyInfos(List searchKeyInfos) {
		this.searchKeyInfos = searchKeyInfos;
	}

	public LotDspUserBean getLotDspUserBean() {
		return lotDspUserBean;
	}

	public void setLotDspUserBean(LotDspUserBean lotDspUserBean) {
		this.lotDspUserBean = lotDspUserBean;
	}

	public IcasBean getIcasBean() {
		return icasBean;
	}

	public void setIcasBean(IcasBean icasBean) {
		this.icasBean = icasBean;
	}

	public StaffCommonBean getStaffCommonBean() {
		return staffCommonBean;
	}

	public void setStaffCommonBean(StaffCommonBean staffCommonBean) {
		this.staffCommonBean = staffCommonBean;
	}

	public StaffProgressBean getStaffProgressBean() {
		return staffProgressBean;
	}

	public void setStaffProgressBean(StaffProgressBean staffProgressBean) {
		this.staffProgressBean = staffProgressBean;
	}

	public StaffManufactureBean getStaffManufactureBean() {
		return staffManufactureBean;
	}

	public void setStaffManufactureBean(StaffManufactureBean staffManufactureBean) {
		this.staffManufactureBean = staffManufactureBean;
	}

	public StaffTestBean getStaffTestBean() {
		return staffTestBean;
	}

	public void setStaffTestBean(StaffTestBean staffTestBean) {
		this.staffTestBean = staffTestBean;
	}

	public StaffQualityBean getStaffQualityBean() {
		return staffQualityBean;
	}

	public void setStaffQualityBean(StaffQualityBean staffQualityBean) {
		this.staffQualityBean = staffQualityBean;
	}

	public StaffQualityBoxBean getQualityDetail() {
		return qualityDetail;
	}

	public void setQualityDetail(StaffQualityBoxBean qualityDetail) {
		this.qualityDetail = qualityDetail;
	}

	public boolean isQualityInfoTabRendered() {
		return qualityInfoTabRendered;
	}

	public void setQualityInfoTabRendered(boolean qualityInfoTabRendered) {
		this.qualityInfoTabRendered = qualityInfoTabRendered;
	}

	public CladInfoBean getCladInfoBean() {
		return cladInfoBean;
	}

	public void setCladInfoBean(CladInfoBean cladInfoBean) {
		this.cladInfoBean = cladInfoBean;
	}

	public CFInfoBean getcFInfoBean() {
		return cFInfoBean;
	}

	public void setcFInfoBean(CFInfoBean cFInfoBean) {
		this.cFInfoBean = cFInfoBean;
	}

	public String getNextGamen() {
		return nextGamen;
	}

	public void setNextGamen(String nextGamen) {
		this.nextGamen = nextGamen;
	}

	public boolean isLoginFlg() {
		return loginFlg;
	}

	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;
	}

	public int getLotMaximum() {
		return lotMaximum;
	}

	public void setLotMaximum(int lotMaximum) {
		this.lotMaximum = lotMaximum;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public String getParamCyno() {
		return paramCyno;
	}

	public void setParamCyno(String paramCyno) {
		this.paramCyno = paramCyno;
	}

	public String getParamLinkkey() {
		return paramLinkkey;
	}

	public void setParamLinkkey(String paramLinkkey) {
		this.paramLinkkey = paramLinkkey;
	}

	public boolean isTabSetRendered() {
		return tabSetRendered;
	}

	public void setTabSetRendered(boolean tabSetRendered) {
		this.tabSetRendered = tabSetRendered;
	}

}


