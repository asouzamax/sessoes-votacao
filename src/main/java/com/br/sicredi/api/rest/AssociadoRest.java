package com.br.sicredi.api.rest;

import com.br.sicredi.api.representation.model.AssociadoDTO;
import com.br.sicredi.domain.associado.Associado;
import com.br.sicredi.domain.associado.AssociadoRepository;
import com.br.sicredi.domain.exception.AssociadoNaoEncontradoException;
import com.br.sicredi.service.AssociadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associado")
@RequiredArgsConstructor
@Slf4j
public class AssociadoRest extends MapperRest<Associado, AssociadoDTO> {

	private final AssociadoService service;
	private final AssociadoRepository repository;

	@ApiOperation(value = "Cria um Associado")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cria e retorna um Associado", response = AssociadoDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AssociadoDTO create(@Valid @RequestBody AssociadoDTO associado) {
		final var associadoCriado = service.create(toModel(associado));
		return fromModel(associadoCriado);
	}

	@ApiOperation(value = "Retorna todos os Associadoes")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de Associadoes", response = AssociadoDTO[].class),
			@ApiResponse(code = 400, message = "Erros negociais: validações da dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<AssociadoDTO> readAll() {
		return fromModel(repository.findAll());
	}

	@ApiOperation(value = "Retorna um Associado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna um Associado", response = AssociadoDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public AssociadoDTO readOne(@PathVariable("id") String id) {
		return fromModel(repository.findById(id).orElseThrow(() -> new AssociadoNaoEncontradoException()));
	}

	@ApiOperation(value = "Atualiza um Associado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Associado atualizado", response = AssociadoDTO.class),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public AssociadoDTO update(@Valid @RequestBody AssociadoDTO Associado) {
		return fromModel(service.update(toModel(Associado)));
	}

	@ApiOperation(value = "Deleta um Associado")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Resposta sem conteúdo"),
			@ApiResponse(code = 400, message = "Erros negociais: validações de dados e fluxo", response = Error.class),
			@ApiResponse(code = 500, message = "Erros não experados")
	})
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		final var Associado = repository.findById(id).orElseThrow(() -> new AssociadoNaoEncontradoException());
		repository.delete(Associado);
	}
}
