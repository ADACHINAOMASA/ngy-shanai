/*
 * IcasMessage.java
 *
 * Created on 2006/12/13, 9:52
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package ngyshanai.domain.nagoya.model;
import java.util.List;

import ngyshanai.domain.nagoya.bean.IcasBean;
import ngyshanai.domain.nagoya.bean.IcasBoxBean;
import ngyshanai.domain.nagoya.util.StringUtil;
        
/**
 *
 * @author Hirohiko-Matsushita
 */
public class IcasMessage {
    
    /** Creates a new instance of IcasMessage */
    public IcasMessage() {
        
    }
    
    String COM_INF = "";
    String WK1_LTAX = "";
    String H146 = "";
    String FUJIPS = "";
    int POS_ALL;
    int POS_BLK;
    int POS_SEL;
    String IQF = "";
    String IPSW = "";
    String ILS = "";
    String IDL = "";
    String CAI = "";
    String ANI = "";
    String ST = "";
    String QF = "";
    String CS = "";
    String GAI = "";
    String VC = "";
    String SS = "";
    String AP = "";
    String LL = "";
    String PSW = "";
    String FS = "";
    String DL = "";
    String LS = "";
    String LS1 = "";
    String LS3 = "";
    String LS4 = "";
    String TLV = "";
    String CAF = "";
    String ANF = "";
    String P = "";
    String MSG_ALL = "";
    String MSG_BLK = "";
    String MSG_SEL = "";
    
    
    String[] PP_TABLE = {
        "00               00",
        "01CM3............00",
        "00               00",
        "03 * ﾌｼﾞPS       06",
        "00               00",
        "05 *H14,6 ﾊｸｼﾞ   04",
        "00               00",
        "00               00",
        "00               00",
        "00               00",
        "10 *FS ｲﾀ PSW,ST 02",
        "11 *FS4 ｲﾀ (A)   04",
        "12 *FS4 ｲﾀ (G)   04",
        "13 *FS ｲﾀ ｿﾉﾀ    04",
        "14 *0.25=> ﾌｲﾝ   02",
        "15 *TLV ｺｲﾙ PS   02",
        "16 *ｺｲﾙ          02",
        "17 *300P         02",
        "18 *ｻ-ｸﾙ         01",
        "19 * ｿ ﾉ ﾀ       01",
        "00               00",
        "21CM1............00",
        "00               00",
        "23 *H14,6 ﾊｸｼﾞ   06",
        "00               00",
        "25  ｿﾉﾀ ﾊｸｼﾞ(ILS)03",
        "26  ｿﾉﾀ ﾊｸｼﾞ     08",
        "27  TLV FS2      04",
        "28  ﾚﾍﾞﾗ-        03",
        "29  VC           02",
        "30  CAF FS4      04",
        "31  TLV FS4(A)   04",
        "32  TLV FS4(G)   04",
        "33  FS4 ｲﾀ (A)   04",
        "34  FS4 ｲﾀ (G)   04",
        "35  BM  FS3      04",
        "36  TLV ｺｲﾙ      02",
        "37  TLV ｺｲﾙ  PS  02",
        "38  LS  ｺｲﾙ      04",
        "00               00",
        "40  0.25=> ﾌｲﾝ   04",
        "00               00",
        "42  GSS ｺｲﾙ      02",
        "00               00",
        "00               00",
        "00               00",
        "46  AP1 AP3      04",
        "47  AP2 AP4      03",
        "48  CS           01",
        "49  P            02",
        "50  300P         01",
        "00               00",
        "00               00",
        "00               00",
        "00               00",
        "00               00",
        "00               00",
        "00               00",
        "58  QF CM        01",
        "59   ｿ ﾉ ﾀ       01",
        "00               00",
        "61ﾌﾟﾚ-ﾄ..........00",
        "00               00",
        "63  2HI          04",
        "00               00",
        "65  2HI QF       01",
        "00               00",
        "00               00",
        "68  HOT          02",
        "00               00",
        "70  HOT QF       01",
        "00               00",
        "00               00",
        "73ALL............00",
        "00               00"        
    };
    
    /**
     *   ﾄｸｼﾕ  (ﾌﾞﾝｻﾝ ﾃﾞ-ﾀ)
     */
    String[] BUNSAN_VALUE_TABLE = {
        "051ANI 3800407",
        "051ANI 4000502",
        "121ANF 1501104",
        "131ANF 2701306",
        "151ANF 2401306",
        "231ANI 3800204",
        "271ANF 2501106",
        "301ANF 2051106",
        "301ANF 2701106",
        "331ANF 2501106",
        "341ANF 2051106",
        "341ANF 2701106",
        "341ANF 2701306",
        "361ANF 2201106",
        "361ANF 2201306",
        "361ANF 2501306",
        "381ANF 2051306",
        "381ANF 2601106",
        "382ANI 2600406",
        "531ANF 2701104"
    };
    
    public String getConvey(IcasBean bean) {
        Convey01(bean);
        Convey02(POS_BLK,POS_SEL);
        //MSG_SEL,MSG_BLK,MSG_ALLの３種類があるがMSG-SELのみ使用
        bean.setCONVNA(MSG_SEL);
        return MSG_SEL;
    }    
    
    /**
     *  ENTRY   'CONVEY01'  USING   PRFL-INF    CONVEY-INF.
     */
    public void Convey01(IcasBean bean) {

        String ltno = StringUtil.toFixlenStr(StringUtil.nvl(bean.getLTNO()), 10, " ");
        String lta = StringUtil.toFixlenStr(StringUtil.nvl(bean.getLTA()), 5, " ");
        String ltb = StringUtil.nvl(bean.getLTB());
        String yotoc = StringUtil.toFixlenStr(StringUtil.nvl(bean.getYOTOC()), 5, " ");
        String jcd201 = StringUtil.nvl(bean.getJCD201().substring(0,1));
        String zaic02 = StringUtil.nvl(bean.getZAIC02());
        
        /*2ST,995 */
        if (lta.substring(1, 5).trim().equals("2ST") || 
            lta.substring(1, 5).trim().equals("995")) {
            WK1_LTAX = lta.substring(1, 5).trim() + " ";
        }
        /*H14,6*/
        if (yotoc.substring(0, 2).trim().equals("E1") &&
           (ltb.trim().equals("H14") || ltb.trim().equals("H16")) &&
           ((lta.trim().equals("2ST") || lta.trim().equals("AF95") || lta.trim().equals("985")) ||
            (lta.substring(1, 4).trim().equals("2ST") || lta.substring(1, 4).trim().equals("STN")) ||
            (lta.substring(0, 3).trim().equals("STN") || lta.substring(0, 3).trim().equals("TNC") || lta.substring(0, 3).trim().equals("TND")))) {
            H146 = "H146";
        }
        
        /*ﾌｼﾞPS*/
        if (yotoc.trim().equals("F741") ||
            yotoc.trim().equals("F734") ||
            yotoc.trim().equals("F731") ||
            yotoc.trim().equals("F729")) {
            FUJIPS = "PS";      
        }

        /**
         *  *ｺ-ｽ     **  ﾁﾕｳｲ ﾁﾕｳｲ ﾁﾕｳｲ  !!  **
         *
         */
        if (H146.equals("") && FUJIPS.equals("")) {
            //PERFORM  YY-SBSM-STA  THRU  YY-SBSM-END
            List list = bean.getIcasBoxInfos();
            for (int i=0;i<bean.getBENO().intValue();i++) {
                IcasBoxBean beanBox = (IcasBoxBean)list.get(i);
                String sbsm = "    ";
                if (beanBox.getSBSM() != null) {
                    sbsm = StringUtil.toFixlenStr(beanBox.getSBSM().toString(), 4, " ");
                }
                double db1 = bean.getLTX().doubleValue();
                double db2 = beanBox.getSBX().doubleValue();
                if (bean.getLTX().doubleValue() != beanBox.getSBX().doubleValue()) {
                    if (!sbsm.equals("")) {
                        if (sbsm.trim().equals("ANI")) ANI = "ANI";
                        if (sbsm.trim().equals("ANI")) ANI = "ANI";
                        if (sbsm.trim().equals("ANI")) ANI = "ANI";
                        if (sbsm.trim().equals("ANI")) ANI = "ANI";
                        if (sbsm.trim().equals("CAI")) CAI = "CAI";
                        if (sbsm.trim().equals("DL") || sbsm.trim().equals("DL1")) IDL = "IDL";
                        if (sbsm.substring(0,2).trim().equals("LS")) ILS = "ILS";
                        if (sbsm.trim().equals("PSW")) IPSW = "IPSW";
                        if (sbsm.substring(0,2).trim().equals("QF")) IQF = "IQF";
                    }
                } else {
                    if (sbsm.trim().equals("P")) {
                        P = "P";
                        if (bean.getP() != null) {
                            if ((bean.getLTX().doubleValue() > new Double(3.5).doubleValue()) && (bean.getP().intValue() == 8)) {
                                P = "300P";
                            }
                        }
                    }
                    if (sbsm.trim().equals("ANF")) ANF = "ANF";
                    if (sbsm.trim().equals("CAF")) CAF = "CAF";
                    if (sbsm.trim().equals("TLV")) TLV = "TLV";
                    if (sbsm.trim().equals("LS1")) {
                        LS = "LS1";
                        LS1 = "LS1";
                    }
                    if (sbsm.trim().equals("LS4")) {
                        LS = "LS4";
                        LS4 = "LS4";
                    }
                    if (sbsm.trim().equals("LS3")) {
                        LS = "LS3";
                        LS3 = "LS3";
                    }
                    if (sbsm.trim().equals("DL") || sbsm.trim().equals("DL1")) DL = "DL";
                    if (sbsm.trim().equals("FS")) FS = sbsm.trim();
                    if (sbsm.trim().equals("PSW")) PSW = "PSW";
                    if (sbsm.trim().equals("LL")) LL = "LL";
                    if (sbsm.substring(0,2).trim().equals("AP")) AP = sbsm.trim();
                    if (sbsm.substring(0,2).trim().equals("SS")) SS = "SS";
                    if (sbsm.substring(0,2).trim().equals("VC")) VC = "VC";
                    if (sbsm.substring(0,1).trim().equals("G")) GAI = sbsm.trim();
                    if (sbsm.trim().equals("CS")) CS = "CS";
                    if (sbsm.substring(0,2).trim().equals("QF")) QF = "QF";
                    if (sbsm.substring(0,2).trim().equals("ST")) ST = "ST";
                }
            }
        }
        
        if (bean.getLTNO().substring(0,1).equals("N")) {
           //PERFORM  CA-NNNN-STA  THRU  CA-NNNN-END
            /**
             *  'N' CM3            *
             */
            POS_BLK = 1;

            //QF CM
            if (QF.equals("QF") || IQF.equals("IQF")) setPosSel(58);
            //ﾌｼﾞPS
            if (FUJIPS.equals("PS")) setPosSel(3);
            //H14,6
            if (H146.equals("H146")) setPosSel(5);
            //TLV ｺｲﾙ PS
            if (jcd201.equals("3") && 
               ((yotoc.trim().compareTo("F711") > 0) && (yotoc.trim().compareTo("F716") < 0)) || 
               (yotoc.trim().equals("F718") || yotoc.trim().equals("F736") || yotoc.trim().equals("F741")) || 
               ((yotoc.trim().compareTo("F722") > 0) && (yotoc.trim().compareTo("F728") < 0)) ||                     
               ((yotoc.trim().compareTo("F728") > 0) && (yotoc.trim().compareTo("F735") < 0)) ||                     
               yotoc.trim().equals("F71B")
               ) setPosSel(15);
            //0.25ｲｶ ﾌｲﾝ
            if (bean.getLTX().doubleValue() < new Double(0.251).doubleValue() && 
               (yotoc.substring(0,3).trim().equals("G21") ||
                yotoc.substring(0,3).trim().equals("G28") ||                    
                yotoc.trim().equals("G293") ||
                yotoc.trim().equals("G294") ||
                yotoc.trim().equals("G281") ||
                yotoc.substring(0,3).trim().equals("F32") ||
                yotoc.trim().equals("R111") ||
                yotoc.trim().equals("R113"))
               ) setPosSel(14);
            //FSｲﾀ PSW,ST
            if ((FS.equals("FS2") || FS.equals("FS2") || FS.equals("FS2")) &&
               jcd201.equals("1") &&
               (PSW.equals("PSW") || ST.equals("ST"))
               ) setPosSel(10);
            //FSｲﾀ
            if (jcd201.equals("1")) {
                if (FS.equals("FS4")) {
                    if (zaic02.substring(1,2).equals("A")) {
                        setPosSel(11);
                    } else {
                        setPosSel(12);
                    }
                } else {
                    setPosSel(13);
                }
            }
            //ｺｲﾙ
            if (jcd201.equals("3")) setPosSel(16);
            //ﾎｲ-ﾙ3P
            if (P.equals("300P")) setPosSel(17);
            //ｻ-ｸﾙ
            if (jcd201.equals("2")) setPosSel(18);
            //ｿﾉﾀ
            setPosSel(19);
        } else if (ltno.substring(0,1).equals("H") || ltno.substring(0,1).equals("R"))  {
            //PERFORM  EA-HHRR-STA  THRU  EA-HHRR-END
            /**
             *  'H','R' ﾌﾟﾚ-ﾄ       *
             */
            POS_BLK = 61;
            if (ltno.substring(0,1).equals("H")) {
                if (QF.equals("QF") || IQF.equals("IQF")) {
                    setPosSel(70);
                } else {
                    setPosSel(68);
                }
            }
            if (ltno.substring(0,1).equals("R")) {
                if (QF.equals("QF") || IQF.equals("IQF")) {
                    setPosSel(65);
                } else {
                    setPosSel(63);
                }
            }            
        } else {
            //PERFORM  DA-LLBB-STA  THRU  DA-LLBB-END
            /**
             *  CM1  'L','B'            *
             */
            POS_BLK = 21;
            
            //QF CM
            if (QF.equals("QF") || IQF.equals("IQF")) setPosSel(58);
            //BM FS3
            if (ltno.substring(0,1).equals("B") || FS.equals("FS3")) setPosSel(35);
            //H14,6
            if (H146.equals("H146")) setPosSel(23);            
            //ｿﾉﾀ ﾊｸ (ILS) ｿﾉﾀ ﾊｸ
            if (yotoc.substring(0,1).equals("E")) {
                if (ILS.equals("ILS")) {
                    setPosSel(25);
                } else {
                    setPosSel(26);
                }
            }
            //LL6,LL7
            if (jcd201.equals("1") && LL.equals("LL")) setPosSel(28);
            //VC
            if (jcd201.equals("1") && VC.equals("VC")) setPosSel(29);
            //TLV-FS2ｲﾀ
            if (jcd201.equals("1") && ((TLV.equals("TLV") && FS.equals("FS2")) || FS.equals("FS2")))  setPosSel(27);
            //CAF FS4
            if (jcd201.equals("1") && CAF.equals("CAF") && FS.equals("FS4")) setPosSel(30);
            //TLV-FS4ｲﾀ
            if (jcd201.equals("1") && TLV.equals("TLV") && FS.equals("FS4")) {
                if (zaic02.trim().equals("A")) {
                    setPosSel(31);
                } else {
                    setPosSel(32);
                }
            }
            //FS4 ｲﾀ
            if (jcd201.equals("1") && FS.equals("FS4")) {
                if (zaic02.trim().equals("A")) {
                    setPosSel(33);
                } else {
                    setPosSel(34);
                }
            }
            //GSS ｺｲﾙ
            if (jcd201.equals("3") && (SS.equals("SS") || GAI.equals("GSS"))) setPosSel(42);
            //TLV ｺｲﾙ PS
            if (jcd201.equals("3") && 
               ((yotoc.trim().compareTo("F711") > 0) && (yotoc.trim().compareTo("F716") < 0)) || 
               (yotoc.trim().equals("F718") || yotoc.trim().equals("F736") || yotoc.trim().equals("F741")) || 
               ((yotoc.trim().compareTo("F722") > 0) && (yotoc.trim().compareTo("F728") < 0)) ||                     
               ((yotoc.trim().compareTo("F728") > 0) && (yotoc.trim().compareTo("F735") < 0)) ||                     
               yotoc.trim().equals("F71B")
               ) setPosSel(37);
            //TLV ｺｲﾙ
            if (jcd201.equals("3") && TLV.equals("TLV")) setPosSel(36);
            //0.25ｲｶ ﾌｲﾝ
            if (bean.getLTX().doubleValue() < new Double(0.251).doubleValue() && 
               (yotoc.substring(0,2).trim().equals("G21") ||
                yotoc.substring(0,2).trim().equals("G28") ||
                yotoc.substring(0,2).trim().equals("F32") ||
                yotoc.trim().equals("R111"))                   
               ) setPosSel(40);
            //LS  ｺｲﾙ 05/08 LS2->LS4
            if (bean.getJCD201() != null) {
                if ((LS.equals("LS1") || LS.equals("LS4") || LS.equals("LS3")) && bean.getJCD201().equals("3")) setPosSel(38);
            }
            //AP1 AP3
            if (AP.equals("AP1") || AP.equals("AP3")) setPosSel(46);
            //AP2 AP4
            if (AP.equals("AP2") || AP.equals("AP4")) setPosSel(47);
            //CS
            if (CS.trim().equals("CS")) setPosSel(48);
            //P
            if (P.trim().equals("P")) setPosSel(49);
            //3P
            if (P.trim().equals("300P")) setPosSel(50);
        }
            
        //ｿﾉﾀ
        setPosSel(59);
        
        POS_ALL = 73;
    }
    
    public void setPosSel(int para) {
        if (POS_SEL == 0) POS_SEL = para;
    }
    
    public void Convey02(int POS_BLK, int POS_SEL) {
        if (POS_BLK == 0) {
            if (POS_SEL > 0) {
                if (POS_SEL > 20) {
                    if (POS_SEL > 60) {
                        if (POS_SEL > 70) {
                            if (POS_SEL > 74) {
                            } else {
                                POS_BLK = 73;
                            }
                        } else {
                            POS_BLK = 61;
                        }
                    } else {
                        POS_BLK = 21;
                    }
                } else {
                    POS_BLK = 1;
                }
            }
        }
        
        if (POS_ALL == 0) POS_ALL = 73;
        if ((POS_SEL > 0) && (POS_SEL < 75)) MSG_SEL = PP_TABLE[POS_SEL].substring(2, 17);
        if ((POS_BLK > 0) && (POS_BLK < 75)) MSG_BLK = PP_TABLE[POS_BLK].substring(2, 17);
        if ((POS_ALL > 0) && (POS_ALL < 75)) MSG_ALL = PP_TABLE[POS_ALL].substring(2, 17);        
    }
    
    public String getMsg(IcasBean bean) {

        String MSG1 = "";
        String MSG2 = "";
        String MSG3_1 = "";
        String MSG3_2 = "";
        String MSG3_3 = "";
        String MSG3_4 = "";
        String MSG3_5 = "";        
        String MSG4 = "";
        String MSG5 = "";
        String MSG6 = "";
        String MSG7 = "";
        String MSG8 = "";
	String MSG9 = "";
        
        String ltno = StringUtil.toFixlenStr(StringUtil.nvl(bean.getLTNO()), 10, " ");
        String lta = StringUtil.toFixlenStr(StringUtil.nvl(bean.getLTA()), 5, " ");
        String ltb = StringUtil.nvl(bean.getLTB());
        String yotoc = StringUtil.toFixlenStr(StringUtil.nvl(bean.getYOTOC()), 5, " ");
        String jcd106= StringUtil.nvl(bean.getJCD106());
        String siji = StringUtil.nvl(bean.getSIJI());
        String hikikbn = StringUtil.nvl(bean.getHIKIKBN());
        String lo = StringUtil.nvl(bean.getLO());
        String kstr = StringUtil.nvl(bean.getKSTR());
        String kshc = StringUtil.nvl(bean.getKSHC());
        String jcd101 = StringUtil.nvl(bean.getJCD101());
        String jcd112 = StringUtil.nvl(bean.getJCD112());
        String kc5 = StringUtil.nvl(bean.getKC5());
        String hokbn = StringUtil.nvl(bean.getHOKBN());
        String sokoikikbn = StringUtil.nvl(bean.getSOKOIKIKBN());
        String hen_ln = StringUtil.nvl(bean.getHEN_LN());
        String lot_flag = StringUtil.nvl(bean.getLOT_FLAG());
        String hokbn_save = StringUtil.nvl(bean.getHOKBN_SAVE());
	String gijutsu_hokbn = StringUtil.nvl(bean.getGIJUTSU_HOKBN());
		
        /**
         * MSG1　登録区分メッセージ
         */
        if (jcd106.equals("1")) {
            MSG1 = IcasConst.MSG1_1;
        } else if (jcd106.equals("2")) {
            MSG1 = IcasConst.MSG1_2;
        } else if (jcd106.equals("3")) {
            MSG1 = IcasConst.MSG1_3;
        } else if (jcd106.equals("4")) {
            MSG1 = IcasConst.MSG1_4;
        } else if (jcd106.equals("5")) {
            MSG1 = IcasConst.MSG1_5;
        } else if (jcd106.equals("6")) {
            MSG1 = IcasConst.MSG1_6;
        } else if (jcd106.equals("7")) {
            MSG1 = IcasConst.MSG1_7;
        } else if (jcd106.equals("8")) {
            MSG1 = IcasConst.MSG1_8;
        } else if (jcd106.equals("9")) {
            MSG1 = IcasConst.MSG1_9;
        }
        
        /**
         * MSG2　切取指示メッセージ
         */
        if (siji.equals("1")) {
            MSG2 = IcasConst.MSG2_1;
        } else if (siji.equals("2")) {
            MSG2 = IcasConst.MSG2_2;
        } else if (siji.equals("3")) {
            MSG2 = IcasConst.MSG2_3;
        } else if (siji.equals("4")) {
            MSG2 = IcasConst.MSG2_4;
        } else if (siji.equals("5")) {
            MSG2 = IcasConst.MSG2_5;
        } else if (siji.equals("6")) {
            MSG2 = IcasConst.MSG2_6;
        } else if (siji.equals("7")) {
            MSG2 = IcasConst.MSG2_7;
        } else if (siji.equals("8")) {
            MSG2 = IcasConst.MSG2_8;
        } else if (siji.equals("9")) {
            MSG2 = IcasConst.MSG2_9;
        }
        
        /**
         * MSG3.1　棚卸メッセージ
         */
        
        /**
         * MSG3.2　火延区分メッセージ
         * PR1-A7(火延べ区分)が公開ﾃﾞｰﾀﾍﾞｰｽに未連携のため
         * AKNO(実績未使用ＢＯＸＮｏ)がNULLかどうかで判断
         */
        if (bean.getAKNO() == null) {
            MSG3_2 = IcasConst.MSG3_2_1;
        } else if (bean.getAKNO().intValue() == 1) {
            MSG3_2 = IcasConst.MSG3_2_2;
        }
        
        /**
         * MSG3.3　引当・保留メッセージ
         */
        if (bean.getHIKICODE().intValue() != 0) {
            if (hikikbn.equals("A")) {
                MSG3_3 = IcasConst.MSG3_3_2;
                /**
                 * 引当ﾌｧｲﾙ未連携のためﾒｯｾｰｼﾞ出力不可
                 * IF      PA-BNO      NOT =   ZERO
                 * AND     SNMHRED-RTNCD = 0
                 * AND    (HK1-COD20        =   'H'  OR
                 *         HK1-BINKBN       =   '9')
                 *         MOVE    'Aｺﾞ(ｹﾝｼﾖｳ)'     TO  GO1-MSG3
                 * ELSE
                 *         MOVE    '* ｹﾝｻ A *'      TO  GO1-MSG3
                 * END-IF
                 */
            } else if (hikikbn.equals("B")) {
                MSG3_3 = IcasConst.MSG3_3_3;
            } else if (hikikbn.equals("H")) {
                if (bean.getCHOSEI().intValue() == 888) {
                    MSG3_3 = IcasConst.MSG3_3_9;
                } else if (bean.getCHOSEI().intValue() == 999) {
                    MSG3_3 = IcasConst.MSG3_3_8;
                } else {
                    MSG3_3 = "引当済（梱包指示書未確認）";
                }
                /**
                 * 引当ﾌｧｲﾙ未連携のためﾒｯｾｰｼﾞ出力不可
                 * IF      PA-BNO      NOT =   ZERO
                 * AND     SNMHRED-RTNCD = 0
                 * AND     HK1-COD20        =   'H'
                 *         MOVE    '* AﾏｴBｺﾞ *'     TO  GO1-MSG3
                 *         MOVE    'Aﾏｴ(ｹﾝｼﾖｳ)'     TO  GO1-MSG3
                 * ELSE
                 *     IF  HK1-HIKI-IO  =  9
                 *         MOVE    '+ﾋｷｱﾃｽﾞﾐ+'     TO  GO1-MSG3
                 *     ELSE
                 *         MOVE    '*ﾋｷｱﾃｽﾞﾐ*'     TO  GO1-MSG3
                 *     END-IF
                 * END-IF
                 */
            }
        } else {
            if (hikikbn.equals("H")) {
                MSG3_3 = IcasConst.MSG3_3_10;
            } else if (hikikbn.equals("A")) {
                MSG3_3 = IcasConst.MSG3_3_11;
            }
        }
        
        /**
         * MSG3.4　ロットアウトメッセージ
         */
        if (lot_flag.equals("1")) {
            if (!(lo.equals("0"))) {
                MSG3_4 = IcasConst.MSG3_4_1;
            } else {
                MSG3_4 = IcasConst.MSG3_4_2;
            }
        }
        
        /**
         * MSG3.5　工程ストア－メッセージ
         */
        if (kstr.equals("1")) MSG3_5 = IcasConst.MSG3_5_1;
        
        /**
         * MSG4　特急・払出区分メッセージ
         */
        if (jcd112.equals("1")) {
            MSG4 = IcasConst.MSG4_1;
        }
	if (jcd112.equals("2")) {
            MSG4 = IcasConst.MSG4_2;
        }
	// 2017/4/27 ADD 
	if (jcd112.equals("3")) {
            MSG4 = IcasConst.MSG4_7;
        }
	if (jcd112.equals("4")) {
            MSG4 = IcasConst.MSG4_8;
        }
	
        if (kstr.equals("1")) {
            if (kshc.equals("1")) {
                 MSG4 = IcasConst.MSG4_3;
            } else if (kstr.equals("2")) {
                 MSG4 = IcasConst.MSG4_4;
            } else if (kstr.equals("3")) {
                 MSG4 = IcasConst.MSG4_5;
            } else if (kstr.equals("6")) {
                 MSG4 = IcasConst.MSG4_6;
            }
        }
        /**
         * MSG5　輸出・保税区分メッセージ
         */
        if (jcd101.equals("1")) {
            MSG5 = IcasConst.MSG4_6;
            if (kc5.equals("2")) {
                MSG5 = IcasConst.MSG5_1;
            } else {
                MSG5 = IcasConst.MSG5_1;
            }
        } 
        
        /**
         * MSG6　テスト品メッセージ
         */
        if (ltno.substring(3,4).equals("9") 
        && (ltno.substring(4,5).equals("1") 
        || ltno.substring(4,5).equals("2")
        || ltno.substring(4,5).equals("3")
        || ltno.substring(4,5).equals("4")
        || ltno.substring(4,5).equals("5")
        || ltno.substring(4,5).equals("6")
        || ltno.substring(4,5).equals("7")
        || ltno.substring(4,5).equals("8"))) {
            MSG6 = IcasConst.MSG6_1 + bean.getNOKI3_RENBAN().toString() + IcasConst.MSG6_2;
        }
        if (ltno.substring(2,3).equals("9") 
        && (ltno.substring(3,4).equals("1") 
        || ltno.substring(3,4).equals("2")
        || ltno.substring(3,4).equals("3")
        || ltno.substring(3,4).equals("4")
        || ltno.substring(3,4).equals("5")
        || ltno.substring(3,4).equals("6")
        || ltno.substring(3,4).equals("7")
        || ltno.substring(3,4).equals("8"))
        && StringUtil.isAlphabet(ltno.substring(5,5))) {
            MSG6 = IcasConst.MSG6_1 + bean.getNOKI3_RENBAN().toString() + IcasConst.MSG6_2;
        }
		
        /**
         * MSG7　保留区分メッセージ
         */
		// @S 2011/01/21 ADD 保留区分が 6 かつ保留退避区分の値があり 0 以外の場合、保留退避用メッセージを作成
		if (hokbn.equals("6") && StringUtil.hasValue(hokbn_save) && !hokbn_save.equals("0")) {
			MSG7 = IcasConst.HokbnSave.getDispMsgFromHoryuKbn(hokbn_save);
		} else
		// @E 2011/01/21 ADD
		if (hokbn.equals("1")) {
            MSG7 = IcasConst.MSG7_1;
        } else if (hokbn.equals("2")) {
            MSG7 = IcasConst.MSG7_2;
        } else if (hokbn.equals("3")) {
            MSG7 = IcasConst.MSG7_3;
        } else if (hokbn.equals("4")) {
            MSG7 = IcasConst.MSG7_4;
        } else if (hokbn.equals("5")) {
            MSG7 = IcasConst.MSG7_5;
        } else if (hokbn.equals("6")) {
            MSG7 = IcasConst.MSG7_6;
        } else if (hokbn.equals("7")) {
            MSG7 = IcasConst.MSG7_7;
        } else if (hokbn.equals("8")) {
            MSG7 = IcasConst.MSG7_8;
        } else if (hokbn.equals("9")) {
            MSG7 = IcasConst.MSG7_9;
        }
		
		/**
         * 倉庫行きHOTコイル保留区分メッセージ
         */
        if (sokoikikbn.equals("1")) {
            MSG7 = MSG7.trim() + IcasConst.SOKOIKI_MSG;
        }
        
        /**
         * MSG8　LN変換区分メッセージ
         */
        if (hen_ln.equals("1")) MSG8 = IcasConst.MSG8_1;
        
	/**
	 * MSG9 技術保留区分メッセージ
	 */
	if (gijutsu_hokbn.equals("1")) MSG9 = IcasConst.MSG9;
	
        bean.setMSG1(StringUtil.toFixlenStr(MSG1,10, " "));
        bean.setMSG2(StringUtil.toFixlenStr(MSG2,11, " "));
        bean.setMSG3(StringUtil.toFixlenStr(MSG3_1 + MSG3_2 + MSG3_3 + MSG3_4 + MSG3_5,11, " "));
        bean.setMSG4(StringUtil.toFixlenStr(MSG4,11, " "));
        bean.setMSG5(StringUtil.toFixlenStr(MSG5,9, " "));
        bean.setMSG6(StringUtil.toFixlenStr(MSG6,11, " "));
        bean.setMSG7(StringUtil.toFixlenStr(MSG7,11, " "));
        bean.setMSG8(StringUtil.toFixlenStr(MSG8,3, " "));
	bean.setMSG9(StringUtil.toFixlenStr(MSG9, 2, " "));
        
        return StringUtil.toFixlenStr(MSG1,10, " ") +
                StringUtil.toFixlenStr(MSG2,11, " ") +
                StringUtil.toFixlenStr(MSG3_1 + MSG3_2 + MSG3_3 + MSG3_4 + MSG3_5,11, " ") +
                StringUtil.toFixlenStr(MSG4,11, " ") +
                StringUtil.toFixlenStr(MSG5,9, " ") +
                StringUtil.toFixlenStr(MSG6,11, " ") +
                StringUtil.toFixlenStr(MSG7,11, " ") +
                StringUtil.toFixlenStr(MSG8,3, " ") +
		StringUtil.toFixlenStr(MSG9, 2, " ");
    }
    
	private String getHokbnSave() {
		return null;
	}
	
    public String getMagMsg(IcasBean bean) {
        
        String jcd202 = StringUtil.toFixlenStr(StringUtil.nvl(bean.getJCD202()), 2, " ");
        
        if ((bean.getJUP().doubleValue() == 1.0) || (jcd202.substring(0,1).equals("1")) || (jcd202.substring(0,1).equals("2"))) {
            if (bean.getPITMAG_FLG() != null) {
                if (bean.getPITMAG_FLG().intValue() == 9) {
                    bean.setMAGMSG(StringUtil.toFixlenStr(IcasConst.MASMSG_1,10, " "));
                    return StringUtil.toFixlenStr(IcasConst.MASMSG_1,10, " ");
                } else {
                    bean.setMAGMSG(StringUtil.toFixlenStr(IcasConst.MASMSG_2, 10, " "));
                    return StringUtil.toFixlenStr(IcasConst.MASMSG_2, 10, " ");
                }
            } else {
                bean.setMAGMSG(StringUtil.toFixlenStr(IcasConst.MASMSG_2, 10, " "));
                return StringUtil.toFixlenStr(IcasConst.MASMSG_2, 10, " ");
            }
        }
        //if (bean.getLOT_FLAG().equals("2")) {
        bean.setMAGMSG("----------");
        return "----------";
        //} else {
        //    bean.setMAGMSG("          ");
        //    return "          ";
        //}

    }
    
}
