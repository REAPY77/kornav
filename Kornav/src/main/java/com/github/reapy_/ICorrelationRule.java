package com.github.reapy_;

import java.util.concurrent.LinkedBlockingQueue;

public interface ICorrelationRule {
     void RuleStarter(LinkedBlockingQueue<Event> Correlationqueue);
     void SwordLogic();
     void RuleShutdown();


}
