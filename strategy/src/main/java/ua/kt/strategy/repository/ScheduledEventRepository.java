package ua.kt.strategy.repository;

import ua.kt.strategy.domain.ScheduledEvent;

import java.util.*;

public class ScheduledEventRepository {

    private final Map<UUID, ScheduledEvent> scheduledEvents = new HashMap<>();

    public ScheduledEvent save(ScheduledEvent scheduledEvent) {
        return scheduledEvents.put(scheduledEvent.getId(), scheduledEvent);
    }

    public List<ScheduledEvent> getAll() {
        return scheduledEvents.values().stream().toList();
    }

    public Optional<ScheduledEvent> getById(UUID id) {
        return Optional.ofNullable(scheduledEvents.get(id));
    }

}
