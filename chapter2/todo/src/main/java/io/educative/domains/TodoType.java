package io.educative.domains;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@XmlRootElement
@JsonPropertyOrder(alphabetic = true)
public class TodoType {

    private String code;
    private String description;
    private Date dateCreated;
    private Date lastUpdated;

}
