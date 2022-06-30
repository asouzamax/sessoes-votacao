package com.br.sicredi.api.representation.model;

import com.br.sicredi.api.representation.BaseDTO;
import com.br.sicredi.domain.voto.TipoVotoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=true)
public class VotoDTO extends BaseDTO {

    TipoVotoEnum tipo;
}
