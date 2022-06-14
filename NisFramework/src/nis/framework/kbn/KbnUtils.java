package nis.framework.kbn;

import java.util.Arrays;
import java.util.List;

/**
 * 区分ユーティリティ
 */
public class KbnUtils {

    /**
     * 列挙子内で指定された内部表現と同じ内部表現を持つ列挙子を取得する
     * @param literal 内部表現
     * @param values 列挙子
     * @param <E> 列挙子
     * @return 対応する列挙子
     */
    static public <E extends GeneralKbn> E literalOf(String literal, E[] values) {
        if (literal == null) {
        } else {
            // リテラルが有効な値の場合、同じ値を持つ列挙子を取得する
            for (E e : values) {
                if (literal.equals(e.literal())) {
                    return e;
                }
            }
        }

        // NULLの列挙子を返す
        for (E e : values) {
            if (e.literal() == null) {
                return e;
            }
        }

        // NULLの列挙子もなかった場合は列挙子自体をnullで返す。
        return null;
    }

    public static <T extends Enum<T> & GeneralKbn> List<T> getKbns(Class<T> kbnClass) {
        return Arrays.asList(kbnClass.getEnumConstants());
    }

    public static <T extends Enum<T> & GeneralKbn> T literalOf(String literal, Class<T> kbnClass) {
        List<T> kbns = getKbns(kbnClass);

        if (literal != null) {
            for (T kbn : kbns) {
                if (literal.equals(kbn.literal())) {
                    return kbn;
                }
            }
        }

        return nullOf(kbnClass);
    }

    public static <T extends Enum<T> & GeneralKbn> T nullOf(Class<T> kbnClass) {
        List<T> kbns = getKbns(kbnClass);

        for (T kbn : kbns) {
            if (kbn.literal() == null) {
                return kbn;
            }
        }

        throw new IllegalStateException("NULLが設定されていない区分:" + kbnClass);
    }

}
