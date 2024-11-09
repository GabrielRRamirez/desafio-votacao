package com.dbserver.desafiovotacao.modules.sessao.controller.v1;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.usecase.CreateSessaoUseCase;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/sessao")
@RequiredArgsConstructor
@Tag(name = "Sessao", description = "Controller para gerenciamento de sessões")
public class SessaoControllerV1 {
    private final CreateSessaoUseCase createSessaoUseCase;
    private final FindSessaoUseCase findSessaoUseCase;

    @Operation(
            summary = "Cria uma nova sessão de votação",
            description = "Cria uma nova sessão de votação com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<DetailSessaoDTO> createSessao(
            @RequestBody
            @Valid
            @Parameter(description = "Dados para criação de uma nova sessão")
            CreateSessaoDTO createSessao) {
        return ResponseEntity.ok(createSessaoUseCase.createSessao(createSessao));
    }

    @Operation(summary = "Busca uma sessão pelo ID", description = "Retorna os detalhes de uma sessão específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sessão não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetailSessaoDTO> findById(
            @PathVariable
            @Parameter(description = "ID da sessão a ser encontrada")
            Long id) {
        return ResponseEntity.ok(findSessaoUseCase.findById(id));
    }

    @Operation(
            summary = "Lista todas as sessões com paginação",
            description = "Retorna uma lista paginada de todas as sessões de votação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de sessões recuperada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    @GetMapping
    public ResponseEntity<Page<DetailSessaoDTO>> findAll(
            @PageableDefault(size = 20)
            @Parameter(description = "Parâmetros de paginação")
            Pageable pageable) {
        return ResponseEntity.ok(findSessaoUseCase.findAll(pageable));
    }
}
