package com.br.sicredi.domain.assembleia;

import com.br.sicredi.domain.exception.VotoJaComputadoException;
import com.br.sicredi.domain.voto.Voto;
import com.br.sicredi.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Assembleia extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pauta;

	@ManyToMany(cascade= CascadeType.MERGE)
	@JoinTable(name = "assembleia_votacao",
			joinColumns = { @JoinColumn(name = "assembleia_id") },
			inverseJoinColumns = { @JoinColumn(name = "voto_id") })
	private Set<Voto> votacao = new HashSet<>();

	private OffsetDateTime abertura;
	private StatusAssembleiaEnum status;

	public void addVoto(Voto voto){
		if(isExists(voto)){
			throw new VotoJaComputadoException();
		}

		getVotacao().add(voto);

	}

	private boolean isExists(Voto voto) {
		return getVotacao()
				.stream().filter(v -> v.equals(voto)).count() == 1;
	}

}