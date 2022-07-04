package com.br.sicredi.api.rest;

import com.br.sicredi.api.representation.model.AssembleiaDTO;
import com.br.sicredi.api.representation.model.AssociadoDTO;
import com.br.sicredi.domain.assembleia.Assembleia;
import com.br.sicredi.domain.assembleia.AssembleiaRepository;
import com.br.sicredi.service.AssembleiaService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/assembleia")
@RequiredArgsConstructor
@Slf4j
public class AssembleiaRest extends MapperRest<Assembleia, AssembleiaDTO> {

	private final AssembleiaService service;
	private final AssembleiaRepository repository;

	@ApiOperation(value = "Cria uma pauta em uma Assembléia")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cria e retorna uma Assembléia", response = AssembleiaDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssembleiaDTO create(@Valid @RequestBody AssembleiaDTO assembleia) {
		final var assembleiaCriada = service.create(toModel(assembleia));
		return fromModel(assembleiaCriada);
	}

	@ApiOperation(value = "Abre a votacao de uma Assembléia")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Abre sessao e Retorna uma Assembléia", response = AssembleiaDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@RequestMapping(path = "/{assembleiaId}/abrir", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public AssembleiaDTO openSession(@PathVariable String assembleiaId) {
		final var assembleiaCriada = service.openSession(assembleiaId);
		return fromModel(assembleiaCriada);
	}

	@ApiOperation(value = "Retorna todas as Assembléia")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de Assembléia", response = AssociadoDTO[].class),
			@ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AssembleiaDTO> readAll() {
		return fromModel((List<Assembleia>) repository.findAll());
	}

}
