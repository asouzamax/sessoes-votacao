package com.br.sicredi.api.rest;

import com.br.sicredi.api.representation.model.AssembleiaDTO;
import com.br.sicredi.api.representation.model.AssociadoDTO;
import com.br.sicredi.api.representation.model.VotoDTO;
import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.AssembleiaRepository;
import com.br.sicredi.domain.voto.Voto;
import com.br.sicredi.domain.voto.VotoRepository;
import com.br.sicredi.service.AssembleiaService;
import com.br.sicredi.service.VotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/votacao")
@RequiredArgsConstructor
@Slf4j
public class VotacaoRest extends MapperRest<Voto, VotoDTO> {

	private final VotacaoService service;
	private final VotoRepository repository;

	@ApiOperation(value = "Vota em uma pauta de Assembléia")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Realiza o voto na assembleia", response = AssembleiaDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@RequestMapping(path = "/{assembleiaId}/{associadoId}/{voto}/votar", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public VotoDTO create(@PathVariable String assembleiaId, @PathVariable String associadoId, @PathVariable String voto) {
		return fromModel(service.create(assembleiaId, voto, associadoId));
	}

}
