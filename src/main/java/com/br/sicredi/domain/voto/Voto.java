package com.br.sicredi.domain.voto;

import com.br.sicredi.domain.associado.Associado;
import com.br.sicredi.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Set;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Voto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "associado_id")
	private Associado associado;

	private TipoVotoEnum tipo;



}