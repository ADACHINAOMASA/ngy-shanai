package nis.framework.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * ドメインイベントパブリッシャ
 * @author Kengo-Nagase
 *
 */
public class DomainEventPublisher {

    @SuppressWarnings("rawtypes")
    private static final ThreadLocal<Map<Object, List>> subscribers = new ThreadLocal<Map<Object, List>>();

    private static final ThreadLocal<Map<Object, Boolean>> publishing = new ThreadLocal<Map<Object, Boolean>>() {
        protected Map<Object, Boolean> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * インスタンス取得
     * @return instance
     */
    public static DomainEventPublisher instance() {
        return new DomainEventPublisher();
    }

    private DomainEventPublisher() {
    }

    /**
     * パブリッシュ
     * @param event イベント
     */
    @SuppressWarnings("unchecked")
    public <T> void publish(final T event) {
        if (publishing.get().get(event.getClass()) == null) {
            return;
        }
        if (publishing.get().get(event.getClass())) {
            throw new IllegalStateException("二重のpublishです。");
        }
        try {
            publishing.get().put(event.getClass(), Boolean.TRUE);
            List<DomainEventSubscriber<T>> registeredSubscribers = subscribers.get().get(event.getClass());
            if (registeredSubscribers != null) {
                for (DomainEventSubscriber<T> subscriber : registeredSubscribers) {
                    subscriber.handle(event);
                }
            }
        }
        finally {
            publishing.get().put(event.getClass(), Boolean.FALSE);
        }
    }

    /**
     * リセット
     * @return
     */
    public DomainEventPublisher reset() {
        if (publishing.get().values().stream().allMatch(run -> !run)) {
            subscribers.set(new HashMap<>());
        }
        return this;
    }

    /**
     * サブスク登録
     * @param subscriber
     */
    @SuppressWarnings("unchecked")
    public <T> void subscribe(DomainEventSubscriber<T> subscriber) {
        if (publishing.get().get(subscriber.eventType()) == null) {
            publishing.get().put(subscriber.eventType(), false);
        }
        if (publishing.get().get(subscriber.eventType())) {
            throw new IllegalStateException("publish中にsubscriveを追加する事は出来ません。");
        }
        List<DomainEventSubscriber<T>> registeredSubscribers = subscribers.get().get(subscriber.eventType());

        if (registeredSubscribers == null) {
            registeredSubscribers = new ArrayList<DomainEventSubscriber<T>>();
            subscribers.get().put(subscriber.eventType(), registeredSubscribers);
        }
        registeredSubscribers.add(subscriber);
    }

    /**
     * サブスク登録
     * @param event
     * @param handle
     */
    public <T> void subscribe(Class<T> event, Consumer<T> handle) {
        subscribe(new DomainEventSubscriber<T>() {

            @Override
            public void handle(T event) {
                handle.accept(event);
            }

            @Override
            public Class<T> eventType() {
                return event;
            }

        });
    }

}
