package nis.framework.kbn;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般区分
 */
public interface GeneralKbn {

    /**
     * 区分内容を表す内部表現を取得
     * @return 内部表現
     */
    public String literal();

    /**
     * 区分内容を表す外部表現を取得
     * @return 外部表現
     */
    public String label();

    /**
     * その他属性取得
     * @return その他属性
     */
    public default Map<String, Object> attrs() {
        return new HashMap<>();
    }

}
