package de.db.waggons_platform_case_study.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "station")
public class Station {
    private String shortcode;
    private String name;

    @XmlElementWrapper(name = "validity")
    @XmlElement(name = "from")
    private List<String> from;

    @XmlElement(name = "to")
    private List<String> to;

    @XmlElementWrapper(name = "tracks")
    @XmlElement(name = "track")
    private List<Track> tracks;
}
