package com.dbserver.desafiovotacao.modules.associado.controller.v1;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.usecase.CreateAssociadoUsecase;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
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
@RequestMapping("v1/associado")
@RequiredArgsConstructor
@Tag(name = "Associado", description = "Controller para gerenciamento de associados")
public class AssociadoControllerV1 {

    private final CreateAssociadoUsecase createAssociadoUsecase;
    private final FindAssociadoUseCase findAssociadoUseCase;

    @Operation(summary = "Cria um novo associado", description = "Cria um novo associado com os dados fornecidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Associado criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @PostMapping
    public ResponseEntity<DetailAssociadoDTO> createAssociado(
            @RequestBody
            @Valid
            @Parameter(description = "Dados do associado a ser criado")
            CreateAssociadoDTO createAssociadoDTO) {
        return ResponseEntity.ok(createAssociadoUsecase.create(createAssociadoDTO));
    }

    @Operation(
            summary = "Lista todos os associados com paginação",
            description = "Retorna uma lista paginada de todos os associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de associados recuperada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos")
    })
    @GetMapping
    public ResponseEntity<Page<DetailAssociadoDTO>> findAll(
            @PageableDefault(size = 20)
            @Parameter(description = "Parâmetros de paginação")
            Pageable page) {
        return ResponseEntity.ok(findAssociadoUseCase.findAll(page));
    }

    @Operation(summary = "Busca um associado pelo ID", description = "Retorna detalhes de um associado específico")
    @GetMapping("/{id}")
    public ResponseEntity<DetailAssociadoDTO> findById(
            @PathVariable
            @Parameter(description = "ID do associado")
            Long id) {
        return ResponseEntity.ok(findAssociadoUseCase.findById(id));
    }
}
