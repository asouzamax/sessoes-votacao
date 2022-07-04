package com.br.sicredi.api.representation.model;

import com.br.sicredi.api.representation.BaseDTO;
import com.br.sicredi.domain.assembleia.StatusAssembleiaEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AssembleiaDTO extends BaseDTO {

    @NotNull
    private String pauta;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime abertura;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private StatusAssembleiaEnum status;
}
