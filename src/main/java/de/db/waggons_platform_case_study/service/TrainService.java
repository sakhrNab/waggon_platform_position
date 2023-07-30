package de.db.waggons_platform_case_study.service;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.exceptions.WagonNotFoundException;
import de.db.waggons_platform_case_study.model.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class TrainService {

    public Station getTrainData(String xmlFileName) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // Die FF-XML-Datei aus der folgenden Pfad xml file from src/main/resources/xml erhalten.
        File xmlFile = new ClassPathResource("xml/" + xmlFileName).getFile();

        return (Station) jaxbUnmarshaller.unmarshal(xmlFile);
    }

    public PlatformPositionResponse getPlatformPosition(String ril100, Integer trainNumber, Integer wagonNumber) throws Exception {
        Station station = getTrainData(ril100 + "_2017-12-01_10-47-17.xml");

        // Der Zug ueber die Zugnummer finden
        Train matchingTrain = station.getTracks().stream()
                .flatMap(track -> track.getTrains().stream())
                .filter(train -> train.getTrainNumbers().contains(trainNumber))
                .findFirst()
                .orElseThrow(() -> new Exception("Train not found"));

        // Der Wagen ueber die Wagennummer finden
        Waggon matchingWaggon = matchingTrain.getWaggons().stream()
                .filter(waggon -> waggon.getNumber() == wagonNumber)
                .findFirst()
                .orElseThrow(() -> new WagonNotFoundException("Wagen wurde nicht gefunden"));

        // Die Antwort erstellen
        PlatformPositionResponse response = new PlatformPositionResponse();
        response.setSections(matchingWaggon.getIdentifiers());

        return response;
    }
}
