package de.db.waggons_platform_case_study.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PlatformPositionResponse  implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> sections;

}
