package de.db.waggons_platform_case_study.service;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.exceptions.WagonNotFoundException;
import de.db.waggons_platform_case_study.model.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service for handling train-related operations.
 *
 * @author SakhrNabil
 */
@Service
public class TrainService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainService.class);

    /**
     * Retrieves train data from an XML file.
     *
     * @param xmlFileName the name of the XML file to read from
     * @return a Station object representing the train station data in the XML file
     * @throws JAXBException if an error occurs while unmarshalling the XML
     * @throws IOException if an error occurs while reading the file
     */
    public Station getTrainData(String xmlFileName) throws JAXBException, IOException {

        if (xmlFileName == null) throw new IllegalArgumentException("xmlFileName cannot be null");

        LOGGER.info("Getting train data from {}", xmlFileName);

        JAXBContext jaxbContext = JAXBContext.newInstance(Station.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        // Die FF-XML-Datei aus der folgenden Pfad xml file from src/main/resources/xml erhalten.
        File xmlFile = new ClassPathResource("xml/" + xmlFileName).getFile();

        return (Station) jaxbUnmarshaller.unmarshal(xmlFile);
    }

    /**
     * Retrieves the platform position for a specific wagon of a train.
     *
     * @param ril100 the shortcode of the station
     * @param trainNumber the number of the train
     * @param wagonNumber the number of the wagon
     * @return a PlatformPositionResponse object representing the platform position of the wagon
     * @throws Exception if an error occurs while retrieving the data
     */
    @Cacheable(value ="trainData", keyGenerator="customKeyGenerator")
    public PlatformPositionResponse getPlatformPosition(String ril100, Integer trainNumber,
                                                        Integer wagonNumber) throws Exception {

        if (ril100 == null || trainNumber == null || wagonNumber == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        LOGGER.info("Getting platform position for station: {}, train: {}, wagon: {}", ril100, trainNumber, wagonNumber);

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
