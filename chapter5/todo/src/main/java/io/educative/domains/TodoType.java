package io.educative.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class TodoType {

    @Id
    @NotBlank
    @Size(min = 4, max = 10)
    private String code;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date lastUpdated;
}
