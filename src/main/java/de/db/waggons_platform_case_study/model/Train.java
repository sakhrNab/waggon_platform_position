package de.db.waggons_platform_case_study.model;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Train {

    @XmlElementWrapper(name = "trainNumbers")
    @XmlElement(name = "trainNumber")
    private List<Integer> trainNumbers;

    private String anno;
    private String time;
    private String additionalText;
    private String name;

    @XmlElementWrapper(name = "subtrains")
    @XmlElement(name = "subtrain")
    private List<Subtrain> subtrains;

    @XmlElementWrapper(name = "waggons")
    @XmlElement(name = "waggon")
    private List<Waggon> waggons;

    @XmlElementWrapper(name = "traintypes")
    @XmlElement(name = "traintype")
    private List<String> traintypes;
}
