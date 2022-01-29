package io.educative.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.educative.utils.adapters.xml.DataFormatXmlAdapter;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@Data
@Entity
@XmlRootElement(name = "type")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"code", "dateCreated", "lastUpdated"})
@JsonPropertyOrder({"description", "code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TodoType {

    @Id
    @NotBlank
    @Size(min = 4, max = 10)
    private String code;

    //@JsonIgnore
    @XmlTransient
    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @XmlJavaTypeAdapter(DataFormatXmlAdapter.class)
    private Date lastUpdated;
}
