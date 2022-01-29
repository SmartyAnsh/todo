package io.educative.domains;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.educative.utils.adapters.xml.DataFormatXmlAdapter;
import lombok.Data;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Data
@JsonPropertyOrder(alphabetic = true)
@XmlRootElement(name="type")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "code", "dateCreated", "lastUpdated" })
public class TodoType {

    private String code;

    @XmlTransient
    private String description;

    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date dateCreated;

    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date lastUpdated;
}
