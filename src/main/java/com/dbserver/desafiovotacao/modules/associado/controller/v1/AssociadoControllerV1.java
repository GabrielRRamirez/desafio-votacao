package com.dbserver.desafiovotacao.modules.associado.controller.v1;

import com.dbserver.desafiovotacao.modules.associado.domain.dto.CreateAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.domain.dto.DetailAssociadoDTO;
import com.dbserver.desafiovotacao.modules.associado.usecase.CreateAssociadoUsecase;
import com.dbserver.desafiovotacao.modules.associado.usecase.FindAssociadoUseCase;
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
public class AssociadoControllerV1 {

    private final CreateAssociadoUsecase createAssociadoUsecase;
    private final FindAssociadoUseCase findAssociadoUseCase;

    @PostMapping
    public ResponseEntity<DetailAssociadoDTO> createAssociado(@RequestBody @Valid CreateAssociadoDTO createAssociadoDTO) {
        return ResponseEntity.ok(createAssociadoUsecase.create(createAssociadoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<DetailAssociadoDTO>> findAll(@PageableDefault(size = 20) Pageable page) {
        return ResponseEntity.ok(findAssociadoUseCase.findAll(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailAssociadoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(findAssociadoUseCase.findById(id));
    }
}
