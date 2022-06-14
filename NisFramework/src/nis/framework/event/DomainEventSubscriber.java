package nis.framework.event;

public interface DomainEventSubscriber <T> {

    public void handle(T event);

    public Class<T> eventType();

}
