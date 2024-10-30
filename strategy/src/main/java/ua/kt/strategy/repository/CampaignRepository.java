package ua.kt.strategy.repository;

import ua.kt.strategy.domain.campaign.Campaign;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CampaignRepository {

    private final Map<String, Campaign> campaignMap = new HashMap<>();
    private final Random random = new Random();

    public CampaignRepository() {
        for (long i = 1; i < 10; i++) {
            String name = "campaign" + i;
            campaignMap.put(name,
                    Campaign.builder()
                            .id(i)
                            .name(name)
                            .banner("Initial banner")
                            .startDate(Instant.now().plus(random.nextInt(1000), ChronoUnit.SECONDS))
                            .endDate(Instant.now().plus(random.nextInt(100), ChronoUnit.DAYS))
                            .build()
            );
        }
    }

    public void save(Campaign campaign) {
        campaignMap.put(campaign.getName(), campaign);
    }

    public Optional<Campaign> getCampaignByName(String name) {
        return Optional.ofNullable(campaignMap.get(name));
    }

    public List<Campaign> getAll() {
        return campaignMap.values().stream().toList();
    }
}
