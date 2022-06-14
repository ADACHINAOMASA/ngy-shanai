package nis.framework.cdi;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * コンテナベースの依存の解決クラス
 */
public class CdiBeansAccessor {

    private BeanManager bm;

    private CdiBeansAccessor(){}

    public static CdiBeansAccessor get() {
        return new CdiBeansAccessor();
    }

    /**
     * インスタンス取得
     * @param beanClass 対象となるクラス
     * @param <T> 対象クラス
     * @return インスタンス
     */
    @SuppressWarnings("unchecked")
    public <T> T find(Class<T> beanClass) {
        // JNDIのレスポンスはコンテナで最適化されているハズなのでキャッシュはやめる
        // 時々アプリケーションを再起動したとき"ビーンが確定できませんでした。"とエラーになる。
        // その時はサーバーを再起動しないといけない。
        // この対応でまたでるようだったら、これには意味がないので、キャッシュするようにする
        //if (bm == null) {
            bm = getBm();
        //}
        return (T)getResolvedBean(beanClass);
    }

    /**
     *
     * @param beanClass 対象となるクラス
     * @return インスタンス
     */
    private Object getResolvedBean(Class<?> beanClass) {
        Set<Bean<?>> beans = bm.getBeans(beanClass);
        if (beans == null) {
            throw new IllegalStateException("ビーンズが確定できませんでした。" + beanClass.getName());
        }
        Bean<?> bean = bm.resolve(beans);
        if (bean == null) {
            throw new IllegalStateException("ビーンが確定できませんでした:" + beanClass.getName());
        }
        CreationalContext<?> cc = bm.createCreationalContext(bean);
        return bm.getReference(bean, beanClass, cc);
    }

    private BeanManager getBm() {
        try {
            Context context = new InitialContext();
            return (BeanManager) context.lookup("java:comp/BeanManager");
        } catch (javax.naming.NamingException e) {
            System.out.println("ビーンマネージャーがJNDIから取得できませんでした。");
            throw new IllegalStateException(e);
        }
    }
}
