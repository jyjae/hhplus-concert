package com.hhplus.concert.application.config;

import com.hhplus.concert.domain.config.ConfigRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class ConfigService {

    private final ConfigRepository configRepository;

    public ConfigService(ConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    public String getConfigValue(String key) {
        return configRepository.findByKeyValue(key);
    }

    @Cacheable("maxQueueTokens")
    public Integer getMaxQueueTokens() {
        return Integer.parseInt(configRepository.findByKeyValue(ConfigKey.MAX_PROCESSING_TOKEN.getKey()));
    }

//    @CacheEvict(value = "maxQueueTokens", allEntries = true)
//    public void refreshMaxQueueTokens() {
//        log.info("Cache evicted for maxQueueTokens");
//    }


}
