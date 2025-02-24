package ua.kt.strategy.services;

import ua.kt.strategy.domain.EventResourceType;
import ua.kt.strategy.domain.EventType;
import ua.kt.strategy.domain.ScheduledEvent;
import ua.kt.strategy.services.strategy.EventStrategy;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ScheduledEventContext {

    private final Map<Pair<EventType, EventResourceType>, EventStrategy> eventStrategiesMap = new HashMap<>();

    public ScheduledEventContext(List<EventStrategy> eventStrategies) {
        for (EventStrategy eventStrategy : eventStrategies) {
            eventStrategiesMap.put(eventStrategy.getType(), eventStrategy);
        }
    }

    public void processEvent(ScheduledEvent event) {
        Optional.ofNullable(eventStrategiesMap.get(Pair.of(event.getEventType(), event.getResourceType())))
                .ifPresent(eventStrategy -> eventStrategy.process(event));
    }

}
