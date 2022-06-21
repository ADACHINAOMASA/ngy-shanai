/*
 * PRSHBMH.java
 *
 * Created on 2007/01/31, 19:51
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.model;
import java.math.BigDecimal;
import java.util.List;

import ngyshanai.domain.nagoya.bean.StaffProgressBean;
import ngyshanai.domain.nagoya.bean.StaffProgressBoxBean;
import ngyshanai.domain.nagoya.util.StringUtil;
/**
 *
 * @author Toru-Konishi
 */
public class StaffRecalc {

    /** Creates a new instance of PRSHBMH */
    public StaffRecalc() {
    }

    public StaffProgressBean setSbmh(StaffProgressBean bean) {
	List staffBoxInfo = bean.getStaffProgressBoxInfos();
	if (staffBoxInfo == null || staffBoxInfo.isEmpty()) {
	    return bean;
	}

        String JCD202 = StringUtil.toFixlenStr(StringUtil.nvl(bean.getJCD202()), 2, " ");
	if (JCD202.substring(0,1).equals("4") || JCD202.substring(0,1).equals("5")) {
            //    *****************************************
            //    *   CM BM        MODULE                 *
            //    *****************************************
            for (int i=0; i<staffBoxInfo.size(); i++) {
		StaffProgressBoxBean staffProgressBoxBean = (StaffProgressBoxBean) staffBoxInfo.get(i);
                if (staffProgressBoxBean.getSBSM() != null) {
                    if (staffProgressBoxBean.getSH_SBW() != null) {
                        if (staffProgressBoxBean.getSBK().intValue() != 1) {
                            if (staffProgressBoxBean.getSBK().intValue() != 2) {
                                //未処理
                            } else {
                                if (bean.getTANW().doubleValue() == 0) {
                                    staffProgressBoxBean.setSBMH(new BigDecimal(0));
                                } else {
                                    if (bean.getTANW().doubleValue() != 0.0) {
                                        BigDecimal numerator = new BigDecimal(staffProgressBoxBean.getSH_SBW().doubleValue());
                                        BigDecimal denominator = new BigDecimal(bean.getTANW().doubleValue());
                                        staffProgressBoxBean.setSBMH(numerator.divide(denominator,0,BigDecimal.ROUND_DOWN));
                                    } else {
                                        staffProgressBoxBean.setSBMH(new BigDecimal(0));
                                    }
                                }
                            }
                        } else {
                            if (staffProgressBoxBean.getSBX().doubleValue() == 0.0 ||
                                staffProgressBoxBean.getSBY().doubleValue() == 0.0 ||
                                staffProgressBoxBean.getSBZ().doubleValue() == 0.0 ||
                                bean.getHIJYU().doubleValue() == 0.0) {
                                    staffProgressBoxBean.setSBMH(new BigDecimal(0));
                            } else {
                                BigDecimal numerator = new BigDecimal(staffProgressBoxBean.getSH_SBW().doubleValue());
                                BigDecimal denominator = new BigDecimal(staffProgressBoxBean.getSBX().doubleValue() *
                                                         staffProgressBoxBean.getSBY().doubleValue() *
                                                         staffProgressBoxBean.getSBZ().doubleValue() *
                                                         bean.getHIJYU().doubleValue() / 1000000);
				BigDecimal denominator_tanju = new BigDecimal(bean.getTANW().doubleValue());
                                // 2012-10-18 設備名がKEN(検査)の時の計算処理を変更
				if(staffProgressBoxBean.getSBSM().equals("KEN")){
				    staffProgressBoxBean.setSBMH(numerator.divide(denominator_tanju,0,BigDecimal.ROUND_DOWN));
				}else{
				    staffProgressBoxBean.setSBMH(numerator.divide(denominator,0,BigDecimal.ROUND_DOWN));
				}

                            }
                        }
                    }
                }
                staffBoxInfo.set(i, staffProgressBoxBean);

            }
	} else {
            //    *****************************************
            //    *   2HI HOT      MODULE                 *
            //    *****************************************
            //    設定値のままのため処理しない

        }
        bean.setStaffProgressSBoxInfos(staffBoxInfo);
        return bean;
    }
}
