/*
 *	Nippon Light Metal Co.Ltd Lotdsp 2006
 *	StringUtil.java
 *	履歴：	2006.10.17	Hirohiko-Matsushita	新規作成
 *
 */
package ngyshanai.domain.nagoya.util;

import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * 文字列に関する拡張ユーティリティ
 */
public class StringUtil {

	/**
	 * 空文字
	 */
	private static final String EMPTY = "";

	/**
	 * 引数の値が Null、または空文字の場合は false を、それ以外の場合は true を返す。
	 * <ul>
	 * <li>Nullの扱い→false、スペースの扱い→false
	 * </ul>
	 * <br>
	 * 例： hasValue("") → false
	 * @param val 文字列
	 * @return 文字列が Null、空文字の場合 → false、それ以外の場合 → true
	 */
	public static boolean hasValue(String val) {
		return (val != null && !val.trim().equals(EMPTY));
	}

	/**
	 * 引数の値が Nullの場合は空文字を返す。
	 * <ul>
	 * <li>Nullの扱い→空文字、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： nvl(null) → ""
	 * @param val 文字列
	 * @return Null回避された文字列
	 */
	public static String nvl(String val) {
		return nvl(val, EMPTY);
	}

	/**
	 * 引数の値が Null、空文字の場合は代わりの文字列を返す。
	 * <ul>
	 * <li>Nullの扱い→代わりの文字列、スペースの扱い→代わりの文字列
	 * </ul>
	 * <br>
	 * 例： nvl(null, "sub") → sub
	 * @param val 文字列
	 * @param sub 代わりの文字列
	 * @return Null回避された文字列
	 */
	public static String nvl(String val, String sub) {
		return (val == null || val.trim().equals(EMPTY) ? sub : val);
	}

	/**
	 * 引数の値が Nullの場合は空文字として比較。
	 * <ul>
	 * <li>Nullの扱い→空文字、スペースの扱い→スペース
	 * </ul>
	 * <br>
	 * 例： nvlEquals(null, null) → 0
	 * @param val 文字列
	 * @param dst 文字列
	 * @return nullをスペースとしたequals結果
	 */
	public static boolean nvlEquals(String val, String dst) {
		return (nvl(val).equals(nvl(dst)));
	}

	/**
	 * 指定された文字列を代わりの文字列に置き換えて返す。
	 * <ul>
	 * <li>Nullの扱い→Null、スペースの扱い→置き換えて返す
	 * </ul>
	 * <br>
	 * 例： replace("ABCABC", "A", "Z") → ZBCZBC
	 * @param val 文字列
	 * @param x 置換対象の文字列
	 * @param sub 代わりの文字列
	 * @return 置換された文字列
	 */
	public static String replace(String val, String x, String sub) {
		if (val == null) {
			return null;
		}
		StringBuffer buf = new StringBuffer(val);
		int i = buf.toString().indexOf(x);
		while (i >= 0) {
			buf.replace(i, i + x.length(), sub);
			i = buf.toString().indexOf(x);
		}
		return buf.toString();
	}

	/**
	 * 引数の文字列を指定区切り文字で分割して配列にして返す。
	 * <ul>
	 * <li>文字列に指定区切り文字が存在しないときはサイズ１の配列で返す
	 * <li>文字列の最初、最後が区切り文字のときも一つの文字列として扱う
	 * <li>Nullの扱い→Null、スペースの扱い→分割して返す
	 * </ul>
	 * <br>
	 * 例： split("1/2/34/5", "/") → 1, 2, 34, 5(サイズ４のString配列)
	 * @param val 文字列
	 * @param separator 区切り文字
	 * @return 分割された文字列配列
	 */
	public static String[] split(String val, char separator) {
		if (val == null) {
			return null;
		}
		ArrayList array = new ArrayList();
		int i = 0;
		int index = val.indexOf(separator, i);
		while (index > -1) {
			array.add(val.substring(i, index));
			i = index + 1;
			index = val.indexOf(separator, i);
		}
		array.add(val.substring(i));
		return (String[]) array.toArray(new String[0]);
	}

	/**
	 * 2つの値が等しいかどうかを返す。
	 */
	public static boolean isEqual(String s1, String s2) {
		if (s1 == null || s2 == null) {
			return (s1 == null && s2 == null);
		}
		return s1.equals(s2);
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1) {
		return MessageFormat.format(message, new Object[] { param1 });
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1, Object param2) {
		return MessageFormat.format(message, new Object[] { param1, param2 });
	}

	/**
	 * {n} 形式を含む文字列をフォーマットして返す。
	 */
	public static String format(String message, Object param1, Object param2, Object param3) {
		return MessageFormat.format(message, new Object[] { param1, param2, param3 });
	}

        /**
         * 全角チェック
         */
        public static boolean isFullSize(String str) {
            byte []bytes;
            bytes = str.getBytes();

            // lengthを２倍する
            int beams = str.length() * 2;
            int enterCounter = 0;

            StringBuffer sb = new StringBuffer(str);

            for (int i=0; i < str.length(); i++) {
                // 改行コードは対象外とする
                if ('\n' == sb.charAt(i)) {
                    beams = beams - 2;
                }
            }
            // lengthの２倍とバイト数が異なる場合は半角が含まれている
            if (beams != bytes.length) {
                return false;
            }
            return true;
        }

        /**
         * 半角チェック
         */
        public static boolean isHalfSize(String str) {
            byte [] bytes;
            bytes = str.getBytes();

            int beams = str.length();
            int enterCounter = 0;

            StringBuffer sb = new StringBuffer(str);

            for (int i=0; i < str.length(); i++) {
                //改行コードは対象外とする
                if ('\n' == sb.charAt(i)) {
                    beams = beams - 2;
                }
            }

            //lengthとバイト数が異なる場合は全角が含まれている
            if (beams != bytes.length) {
                return false;
            }
            return true;
        }

        /**
         * 半角数値チェック
         */
        public static boolean isDigit(String input) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if ( (c < '0' || c > '9') ) {
                    //数字でない
                    return false;
                }
            }
            return true;
        }

        /**
         * 半角英字チェック
         */
        public static boolean isAlphabet(String input) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if ((c < 'a' || c > 'z')    //小文字アルファベットでない
                && (c < 'A' || c > 'Z')) {  //大文字アルファベットでない
                    return false;
                }
            }
            return true;
        }

        /**
         * 半角英数字チェック
         */
        public static boolean checkDigitAlphabet(String input) {
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if ( (c < '0' || c > '9') &&        //数字でない
                     (c < 'a' || c > 'z') &&        //小文字アルファベットでない
                     (c < 'A' || c > 'Z') ) {       //大文字アルファベットでない
                     return false;
                }
            }
            return true;
        }

        /**
         * 半角カナチェック
         */
         public static boolean checkHankakuKana(String input) {

            final String HANKAKU_KANA = "ｱｲｳｴｵｶｷｸｹｺｻｼｽｾｿ" +
                "ﾀﾁﾂﾃﾄﾅﾆﾇﾈﾉﾊﾋﾌﾍﾎﾏﾐﾑﾒﾓﾔﾕﾖﾗﾘﾙﾚﾛﾜｦﾝｧｨｩｪｫｯｬｭｮﾞﾟｰ､｡｢｣･";

            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);

                if ( -1 == HANKAKU_KANA.indexOf( ( int ) c ) ) {
                    return false;
                }
            }
            return true;
        }

         /**
          * 桁数チェック
          */
         private boolean isLength( String val, int min, int max){

            int strlen = 0;

            if ( val == null ) {
                return false;
            }

            strlen = val.getBytes().length;

            if ( strlen < min || strlen > max ) {
                return false;
            }
            return true;
        }


    /**
     * 固定長の文字列の作成
     * data      : 与えるデータ
     * fixlen    : 生成する文字列の長さ
     * pad_char  : 固定長にするためにつめる文字（例：' ','0' 等）
     */
    public static String toFixlenStr(String data,int fixlen,String pad_char) {

        String s = new String();
        s = s + data;
        for (int i = data.length() + 1; i < fixlen + 1; i++) {
            s = s + pad_char;
        }
        return s;
    }

}