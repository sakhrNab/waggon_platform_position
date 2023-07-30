package de.db.waggons_platform_case_study.model;

import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Subtrain {

    @XmlElementWrapper(name = "destination")
    @XmlElement(name = "destinationName")
    private List<String> destinationName;

    @XmlElement(name = "destinationVia")
    private List<String> destinationVia;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> identifiers;
}
