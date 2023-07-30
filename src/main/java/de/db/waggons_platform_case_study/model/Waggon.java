package de.db.waggons_platform_case_study.model;
import lombok.Data;
import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Waggon {
    private int position;
    private int isWaggon;

    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "identifier")
    private List<String> identifiers;

    private int number;
    private String type;
    private String symbols;
    private String differentDestination;
    private int length;
}
