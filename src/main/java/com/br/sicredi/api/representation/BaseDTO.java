package com.br.sicredi.api.representation;

import com.br.sicredi.api.handler.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class BaseDTO {

    @NotBlank(groups = ValidationGroups.Id.class)
    @ApiModelProperty(value = "Identificador único")
    private String id;

    @JsonProperty(access = Access.READ_ONLY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiModelProperty(value = "Data de cadastro")
    private OffsetDateTime cadastro;

}