package com.zhujiejun.spring.listen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.zookeeper.discovery.watcher.DependencyState;
import org.springframework.cloud.zookeeper.discovery.watcher.DependencyWatcherListener;

@Slf4j
public class Lsn014 implements DependencyWatcherListener {
    @Override
    public void stateChanged(String dependencyName, DependencyState newState) {
        log.info("----------the dependency name and state are {}, {}----------", dependencyName, newState);
    }
}
