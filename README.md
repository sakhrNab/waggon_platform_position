﻿# Wagenstandsanzeiger

Diese Anwendung stellt eine RESTful-API zur Verfügung, um eine Liste mit den Gleisabschnitten eines bestimmten Wagens in einem Zug an einem bestimmten Bahnhof abzurufen.

## Erste Schritte


### Voraussetzungen

- JDK 17 oder höher
- Maven 3.6 oder höher

### Anwendung bauen und ausführen

Um die Anwendung zu bauen, navigieren Sie im Terminal zum Stammverzeichnis des Projekts und führen Sie den folgenden Befehl aus:

mvn clean install

Um die Anwendung zu starten, verwenden Sie den folgenden Befehl:

`mvn spring-boot:run`

Die Anwendung startet und ist unter http://localhost:8080 erreichbar.

### Verwendung der API
Der API-Endpunkt lautet wie folgt:
`GET /api/station/{ril100}/train/{trainNumber}/wagon/{wagonNumber}`


Ersetzen Sie `{ril100}`, `{trainNumber}` und `{wagonNumber}` durch den Ril100 (Betriebstelle), die Zugnummer und die Wagennummer.

z.B. `GET http://localhost:8080/api/station/FF/train/2310/wagon/10`

Wenn die Anwendung nicht auf die Portnummber 8080 läuft, dann muss die Portnummer entsprechend geändert werden. 

Die Antwort ist ein JSON-Objekt, das die Liste mit den Gleisabschnitten des angegebenen Wagens enthält.

### Logging
Die Anwendung verwendet SLF4J mit Logback für das Logging. Die Logs werden auf die Konsole gedruckt.

### Fehlerbehandlung
Wenn der angeforderte Wagen nicht gefunden werden kann, gibt die API einen Statuscode 404 mit einer beschreibenden Fehlermeldung und mögliche Lösung zurück.

#TODO: 
* JUnit Tests
* Schnittstellen implementieren?


