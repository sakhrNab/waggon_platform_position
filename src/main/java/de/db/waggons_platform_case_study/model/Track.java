package de.db.waggons_platform_case_study.model;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Track {
    private String name;
    private int number;

    @XmlElementWrapper(name = "trains")
    @XmlElement(name = "train")
    private List<Train> trains;
}
