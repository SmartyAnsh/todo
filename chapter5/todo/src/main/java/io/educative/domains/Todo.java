package io.educative.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.educative.utils.validators.TitleConstraint;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Todo extends AbstractAggregateRoot<Todo> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @TitleConstraint
    private String title;

    @JsonIgnore
    private String description;

    private boolean done;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateCreated;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dateDone;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private Date lastUpdated;

    @ManyToOne
    @JsonProperty("type")
    private TodoType type;
}
