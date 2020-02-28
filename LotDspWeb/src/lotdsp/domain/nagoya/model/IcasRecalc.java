/*
 * PRSHBMH.java
 *
 * Created on 2007/01/31, 19:51
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package lotdsp.domain.nagoya.model;
import java.math.BigDecimal;
import java.util.List;

import lotdsp.domain.nagoya.bean.IcasBean;
import lotdsp.domain.nagoya.bean.IcasBoxBean;
import lotdsp.domain.nagoya.util.StringUtil;
/**
 *
 * @author Hirohiko-Matsushita
 */
public class IcasRecalc {
    
    /** Creates a new instance of PRSHBMH */
    public IcasRecalc() {
    }
    
    public IcasBean setSbmh(IcasBean bean) {
        String JCD202 = StringUtil.toFixlenStr(StringUtil.nvl(bean.getJCD202()), 2, " ");
        List icasBoxInfo = bean.getIcasBoxInfos();
        // 
        if (JCD202.substring(0,1).equals("4") || JCD202.substring(0,1).equals("5")) {
            //    *****************************************
            //    *   CM BM        MODULE                 *
            //    *****************************************
            for (int i=0; i<icasBoxInfo.size(); i++) {
                IcasBoxBean icasBoxBean = (IcasBoxBean)icasBoxInfo.get(i);
                if (icasBoxBean.getSBSM() != null) {
                    if (icasBoxBean.getSH_SBW() != null) {
                        if (icasBoxBean.getSBK().intValue() != 1) {
                            if (icasBoxBean.getSBK().intValue() != 2) {
                                //未処理
                            } else {
                                if (bean.getTANW().doubleValue() == 0) {
                                    icasBoxBean.setSBMH(new BigDecimal(0));
                                } else {
                                    if (bean.getTANW().doubleValue() != 0.0) {
                                        BigDecimal numerator = new BigDecimal(icasBoxBean.getSH_SBW().doubleValue());
                                        BigDecimal denominator = new BigDecimal(bean.getTANW().doubleValue());
                                        icasBoxBean.setSBMH(numerator.divide(denominator,0,BigDecimal.ROUND_DOWN));
                                    } else {
                                        icasBoxBean.setSBMH(new BigDecimal(0));
                                    }
                                }
                            }
                        } else {
                            if (icasBoxBean.getSBX().doubleValue() == 0.0 || 
                                icasBoxBean.getSBY().doubleValue() == 0.0 || 
                                icasBoxBean.getSBZ().doubleValue() == 0.0 ||
                                bean.getHIJYU().doubleValue() == 0.0) {
                                    icasBoxBean.setSBMH(new BigDecimal(0));
                            } else {
                                BigDecimal numerator = new BigDecimal(icasBoxBean.getSH_SBW().doubleValue());
                                BigDecimal denominator = new BigDecimal(icasBoxBean.getSBX().doubleValue() * 
                                                         icasBoxBean.getSBY().doubleValue() * 
                                                         icasBoxBean.getSBZ().doubleValue() * 
                                                         bean.getHIJYU().doubleValue() / 1000000);
                                BigDecimal denominator_tanju = new BigDecimal(bean.getTANW().doubleValue());
				// 2012-10-18 設備名がKEN(検査)の時の計算処理を変更
				if(icasBoxBean.getSBSM().equals("KEN")){
				    icasBoxBean.setSBMH(numerator.divide(denominator_tanju,0,BigDecimal.ROUND_DOWN));
				}else{
				    icasBoxBean.setSBMH(numerator.divide(denominator,0,BigDecimal.ROUND_DOWN));
				}
                            }
                        }
                    }
                }
                // 2012-10-10 切り捨て処理追加
                //if (icasBoxBean.getJBRMH().doubleValue() == 0) icasBoxBean.setJBRMH(icasBoxBean.getSBMH());
		if(icasBoxBean.getJBRMH()==null || icasBoxBean.getJBRMH().doubleValue() == 0){
		    icasBoxBean.setJBRMH(icasBoxBean.getSBMH());
		}else{
		    icasBoxBean.setJBRMH(icasBoxBean.getJBRMH().divide(BigDecimal.ONE,0,BigDecimal.ROUND_DOWN));
		}
		icasBoxInfo.set(i, icasBoxBean);
            }
        } else {
            //    *****************************************
            //    *   2HI HOT      MODULE                 *
            //    *****************************************
            //    設定値のままのため処理しない
	    for (int i=0; i<icasBoxInfo.size(); i++) {
                IcasBoxBean icasBoxBean = (IcasBoxBean)icasBoxInfo.get(i);
		if(icasBoxBean.getJBRMH().doubleValue() == 0){
		    icasBoxBean.setJBRMH(icasBoxBean.getSBMH());
		}else{
		    icasBoxBean.setJBRMH(icasBoxBean.getJBRMH().divide(BigDecimal.ONE,0,BigDecimal.ROUND_DOWN));
		}
		icasBoxInfo.set(i, icasBoxBean);
	    }
        }
	
        bean.setIcasBoxInfos(icasBoxInfo);
        return bean;
    }
}
