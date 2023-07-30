package de.db.waggons_platform_case_study.controller;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.exceptions.WagonNotFoundException;
import de.db.waggons_platform_case_study.service.TrainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Controller for handling requests related to platform positions.
 *
 * @author SakhrNabil
 */
@RestController
@RequestMapping("/api")
public class PlatformPositionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlatformPositionController.class);

    private final TrainService trainService;

    @Autowired
    public PlatformPositionController(TrainService trainService) {
        this.trainService = trainService;
    }
    @Value("${error.wagonNotFound}")
    private String wagonNotFoundError;

    @Value("${error.wagonNotFound.solution}")
    private String wagonNotFoundSolution;
    /**
     * Retrieves the platform position for a specific wagon of a train.
     *
     * @param ril100 the shortcode of the station
     * @param trainNumber the number of the train
     * @param wagonNumber the number of the wagon
     * @return a ResponseEntity containing a PlatformPositionResponse object and an HTTP status code
     * @throws Exception if an error occurs while retrieving the data
     */
    @GetMapping("/station/{ril100}/train/{trainNumber}/wagon/{wagonNumber}")
    public ResponseEntity<PlatformPositionResponse> getPlatformPosition(
            @PathVariable String ril100,
            @PathVariable Integer trainNumber,
            @PathVariable Integer wagonNumber) throws Exception {
        LOGGER.info("Received request for platform position for station: {}, train: {}, wagon: {}", ril100, trainNumber, wagonNumber);
        PlatformPositionResponse response = trainService.getPlatformPosition(ril100, trainNumber, wagonNumber);
        LOGGER.info("Successfully retrieved platform position for station: {}, train: {}, wagon: {}", ril100, trainNumber, wagonNumber);
        return ResponseEntity.ok(response);
    }
    @ExceptionHandler(WagonNotFoundException.class)
    public ResponseEntity<String> handleWagonNotFoundException(WagonNotFoundException ex) {
        LOGGER.error("Wagon is not found: " + ex.getMessage());
        return new ResponseEntity<>(wagonNotFoundError + ex.getMessage() + wagonNotFoundSolution, HttpStatus.NOT_FOUND);
    }

}
