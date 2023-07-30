package de.db.waggons_platform_case_study.controller;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.exceptions.WagonNotFoundException;
import de.db.waggons_platform_case_study.service.TrainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @ExceptionHandler(WagonNotFoundException.class)
    public ResponseEntity<String> handleWagonNotFoundException(WagonNotFoundException ex) {

        // Die untenstehende Nachricht kann entsprechend bearbeitet werden.
        return new ResponseEntity<>("Fehler: " + ex.getMessage()
                +"\n\nLösung: Geben Sie eine andere Wegennummer ein, die in der Liste existiert (z.B. die Nummer 10)."
                , HttpStatus.NOT_FOUND);
    }

}
