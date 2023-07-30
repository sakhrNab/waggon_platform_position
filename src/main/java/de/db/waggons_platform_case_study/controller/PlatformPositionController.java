package de.db.waggons_platform_case_study.controller;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class PlatformPositionController {

    private final TrainService trainService;

    @Autowired
    public PlatformPositionController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping("/station/{ril100}/train/{trainNumber}/wagon/{wagonNumber}")
    public ResponseEntity<PlatformPositionResponse> getPlatformPosition(
            @PathVariable String ril100,
            @PathVariable Integer trainNumber,
            @PathVariable Integer wagonNumber) throws Exception {

        PlatformPositionResponse response = trainService.getPlatformPosition(ril100, trainNumber, wagonNumber);

        return ResponseEntity.ok(response);
    }

}
