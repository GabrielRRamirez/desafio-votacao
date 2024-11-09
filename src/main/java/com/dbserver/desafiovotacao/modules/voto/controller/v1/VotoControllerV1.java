package com.dbserver.desafiovotacao.modules.voto.controller.v1;

import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailResultadoVotacaoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.usecase.CreateVotoUseCase;
import com.dbserver.desafiovotacao.modules.voto.usecase.FindVotoUseCase;
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
@RequestMapping("v1/voto")
@RequiredArgsConstructor
@Tag(name = "Voto", description = "Controller para gerenciamento de votos")
public class VotoControllerV1 {
    private final CreateVotoUseCase createVotoUseCase;
    private final FindVotoUseCase findVotoUseCase;

    @Operation(summary = "Registra um novo voto", description = "Registra um novo voto para uma pauta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Voto registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<DetailVotoDTO> createVoto(
            @RequestBody
            @Valid
            @Parameter(description = "Dados para criação de um novo voto")
            CreateVotoDTO createVotoDTO) {
        return ResponseEntity.ok(createVotoUseCase.createVoto(createVotoDTO));
    }

    @Operation(
            summary = "Lista todos os votos com paginação",
            description = "Retorna uma lista paginada de todos os votos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de votos recuperada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    @GetMapping
    public ResponseEntity<Page<DetailVotoDTO>> findAll(
            @PageableDefault(size = 20)
            @Parameter(description = "Parâmetros de paginação")
            Pageable pageable) {
        return ResponseEntity.ok(findVotoUseCase.findAll(pageable));
    }

    @Operation(
            summary = "Apura o resultado da votação de uma pauta",
            description = "Retorna o resultado da votação para uma pauta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resultado da apuração obtido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    @GetMapping("/apuracao/{idPauta}")
    public ResponseEntity<DetailResultadoVotacaoDTO> apurar(
            @PathVariable
            @Parameter(description = "ID da pauta a ser apurada")
            long idPauta) {
        return ResponseEntity.ok(findVotoUseCase.count(idPauta));
    }
}
