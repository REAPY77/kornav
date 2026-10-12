package com.github.reapy_.rules;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.reapy_.Event;
import com.github.reapy_.ICorrelationRule;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;

public class CorrelationRule implements ICorrelationRule {
    Cache<Integer,> CacheAlerts = Caffeine.newBuilder().expireAfterWrite(Duration.ofMinutes(10)).build();
    LinkedBlockingQueue<Event> RuleQueue;


    @Override
    public void RuleStarter(LinkedBlockingQueue<Event> Correlationqueue) {

    }

    @Override
    public void SwordLogic() {

    }

    @Override
    public void RuleShutdown() {

    }
}
