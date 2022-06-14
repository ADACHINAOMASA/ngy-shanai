package nis.framework.event;

/**
 * ロジック実行完了時イベント<br>
 * ロジッククラスが正しく実行され終わった場合に発生するイベント。<br>
 * Successとの違いは、このイベントからロジック実行状態を操作する事が出来る点。<br>
 * このイベントからエラーや例外が発生した場合、ロジックの例外とみなされ、ロールバックされる
 * @author Kengo-Nagase
 *
 */
public class DomainLogicCompleteEvent {

}
