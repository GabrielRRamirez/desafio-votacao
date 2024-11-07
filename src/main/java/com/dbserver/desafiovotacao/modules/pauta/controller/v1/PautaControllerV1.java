package com.dbserver.desafiovotacao.modules.pauta.controller.v1;

import com.dbserver.desafiovotacao.modules.pauta.domain.dto.CreatePautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.domain.dto.DetailPautaDTO;
import com.dbserver.desafiovotacao.modules.pauta.usecase.CreatePautaUseCase;
import com.dbserver.desafiovotacao.modules.pauta.usecase.FindPautaUseCase;
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
public class PautaControllerV1 {
    private final CreatePautaUseCase createPautaUseCase;
    private final FindPautaUseCase findPautaUseCase;

    @PostMapping
    public ResponseEntity<DetailPautaDTO> createPauta(@RequestBody @Valid CreatePautaDTO createPautaDTO) {
        return ResponseEntity.ok(createPautaUseCase.create(createPautaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailPautaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(findPautaUseCase.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DetailPautaDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(findPautaUseCase.findAll(pageable));
    }
}
