package com.app.controller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.app.service.TrainService;

@Component
public class TrainStatusScheduler {

    private final TrainService trainService;

    public TrainStatusScheduler(TrainService trainService) {
        this.trainService = trainService;
    }

    // Scheduled task to toggle train statuses when they start running
//    @Scheduled(cron = "0 0 * * * *") // Runs every hour
//    public void toggleTrainStatus() {
//        trainService.toggleTrainStatusWhenRunning();
//    }
}
