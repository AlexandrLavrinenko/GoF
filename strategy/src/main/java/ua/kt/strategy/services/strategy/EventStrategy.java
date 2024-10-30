package ua.kt.strategy.services.strategy;

import ua.kt.strategy.domain.EventResourceType;
import ua.kt.strategy.domain.EventType;
import ua.kt.strategy.domain.ScheduledEvent;
import org.apache.commons.lang3.tuple.Pair;

public interface EventStrategy {

    ScheduledEvent process(ScheduledEvent event);

    Pair<EventType, EventResourceType> getType();

}
