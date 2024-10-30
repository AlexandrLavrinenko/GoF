package ua.kt.strategy.services.strategy;

import ua.kt.strategy.domain.EventResourceType;
import ua.kt.strategy.domain.EventType;
import ua.kt.strategy.domain.ScheduledEvent;
import ua.kt.strategy.domain.Status;
import ua.kt.strategy.domain.campaign.Campaign;
import ua.kt.strategy.domain.campaign.CampaignStatus;
import ua.kt.strategy.repository.CampaignRepository;
import ua.kt.strategy.repository.ScheduledEventRepository;
import ua.kt.strategy.utils.Constants;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;

public class CampaignStatusUpdateStrategy extends AbstractEventStrategy {

    private final CampaignRepository campaignRepository;

    public CampaignStatusUpdateStrategy(ScheduledEventRepository eventRepository, CampaignRepository campaignRepository) {
        super(eventRepository);
        this.campaignRepository = campaignRepository;
    }

    @Override
    public ScheduledEvent process(ScheduledEvent event) {
        try {
            event = super.process(event);
            Campaign campaign = campaignRepository.getCampaignByName(event.getResourceName())
                    .orElseThrow(() -> new RuntimeException("Campaign not found"));

            Optional.ofNullable(event.getProperties().get(Constants.CAMPAIGN_STATUS))
                    .map(o -> CampaignStatus.valueOf(String.valueOf(o)))
                    .ifPresentOrElse(campaignStatus -> {
                                campaign.setStatus(campaignStatus);
                                campaignRepository.save(campaign);
                            },
                            () -> new RuntimeException("New status was not set")
                    );

            event.setStatus(Status.COMPLETED);
        } catch (RuntimeException ex) {
            event.setStatus(Status.TERMINATED);
            event.addErrorMessage(ex.getMessage());
        } catch (Exception ex) {
            event.setStatus(Status.FAILED);
            event.addErrorMessage(ex.getMessage());
        }

        return eventRepository.save(event);
    }

    @Override
    public Pair<EventType, EventResourceType> getType() {
        return Pair.of(EventType.UPDATE_STATUS, EventResourceType.CAMPAIGN);
    }
}
