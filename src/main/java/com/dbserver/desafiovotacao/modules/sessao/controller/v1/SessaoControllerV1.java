package com.dbserver.desafiovotacao.modules.sessao.controller.v1;

import com.dbserver.desafiovotacao.modules.sessao.domain.dto.CreateSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.domain.dto.DetailSessaoDTO;
import com.dbserver.desafiovotacao.modules.sessao.usecase.CreateSessaoUseCase;
import com.dbserver.desafiovotacao.modules.sessao.usecase.FindSessaoUseCase;
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
public class SessaoControllerV1 {
    private final CreateSessaoUseCase createSessaoUseCase;
    private final FindSessaoUseCase findSessaoUseCase;

    @PostMapping
    public ResponseEntity<DetailSessaoDTO> createSessao(@RequestBody @Valid CreateSessaoDTO createSessao) {
        return ResponseEntity.ok(createSessaoUseCase.createSessao(createSessao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailSessaoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(findSessaoUseCase.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DetailSessaoDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(findSessaoUseCase.findAll(pageable));
    }
}
