package de.db.waggons_platform_case_study;

import de.db.waggons_platform_case_study.dto.PlatformPositionResponse;
import de.db.waggons_platform_case_study.exceptions.WagonNotFoundException;
import de.db.waggons_platform_case_study.model.Station;
import de.db.waggons_platform_case_study.service.TrainService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrainServiceTest {

    @InjectMocks
    private TrainService trainService;

    @Test
    public void getPlatformPositionTest() throws Exception {
        // Arrange
        String ril100 = "FF";
        Integer trainNumber = 2310;
        Integer wagonNumber = 14;

        // Act
        PlatformPositionResponse response = trainService.getPlatformPosition(ril100, trainNumber, wagonNumber);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getSections()).isNotEmpty();
    }

    @Test
    public void getPlatformPositionTrainNotFoundTest() {
        String ril100 = "FF";
        Integer trainNumber = 9999;  // a non-existent train number
        Integer wagonNumber = 14;

        Exception exception = assertThrows(Exception.class, () -> {
            trainService.getPlatformPosition(ril100, trainNumber, wagonNumber);
        });

        String expectedMessage = "Train not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getPlatformPositionWagonNotFoundTest() {
        String ril100 = "FF";
        Integer trainNumber = 2310;
        Integer wagonNumber = 9999;  // a non-existent wagon number

        Exception exception = assertThrows(WagonNotFoundException.class, () -> {
            trainService.getPlatformPosition(ril100, trainNumber, wagonNumber);
        });

        String expectedMessage = "Wagen wurde nicht gefunden";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getTrainDataTest() throws JAXBException, IOException {
        String xmlFileName = "FF_2017-12-01_10-47-17.xml";

        Station station = trainService.getTrainData(xmlFileName);

        assertNotNull(station);
        assertEquals("FF", station.getShortcode());
    }

    @Test
    public void getTrainDataInvalidXmlFileTest() throws Exception {
        assertThrows(IOException.class, () -> {
            trainService.getTrainData("invalid.xml");
        });
    }

    @Test
    public void getPlatformPositionNullParametersTest() throws Exception {
        assertThrows(IllegalArgumentException.class, () -> {
            trainService.getPlatformPosition(null, null, null);
        });
    }


}
