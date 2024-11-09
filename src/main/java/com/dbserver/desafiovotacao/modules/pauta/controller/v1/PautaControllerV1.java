package com.dbserver.desafiovotacao.modules.pauta.controller.v1;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.usecase.CreatePautaUseCase;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
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
@RequestMapping("v1/pauta")
@RequiredArgsConstructor
@Tag(name = "Pauta", description = "Controller para gerenciamento de pautas")
public class PautaControllerV1 {
    private final CreatePautaUseCase createPautaUseCase;
    private final FindPautaUseCase findPautaUseCase;

    @Operation(summary = "Cria uma nova pauta", description = "Cria uma nova pauta com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<DetailPautaDTO> createPauta(@RequestBody @Valid CreatePautaDTO createPautaDTO) {
        return ResponseEntity.ok(createPautaUseCase.create(createPautaDTO));
    }

    @Operation(summary = "Busca uma pauta pelo ID", description = "Retorna os detalhes de uma pauta específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pauta encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DetailPautaDTO> findById(
            @PathVariable
            @Parameter(description = "ID da pauta")
            Long id) {
        return ResponseEntity.ok(findPautaUseCase.findById(id));
    }

    @Operation(
            summary = "Lista todas as pautas com paginação",
            description = "Retorna uma lista paginada de todas as pautas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pautas recuperada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    @GetMapping
    public ResponseEntity<Page<DetailPautaDTO>> findAll(
            @PageableDefault(size = 20)
            @Parameter(description = "Parametros de paginação")
            Pageable pageable) {
        return ResponseEntity.ok(findPautaUseCase.findAll(pageable));
    }
}
