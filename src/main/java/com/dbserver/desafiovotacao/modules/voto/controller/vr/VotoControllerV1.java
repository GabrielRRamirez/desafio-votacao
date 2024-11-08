package com.dbserver.desafiovotacao.modules.voto.controller.vr;

import com.dbserver.desafiovotacao.modules.voto.domain.dto.CreateVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailResultadoVotacaoDTO;
import com.dbserver.desafiovotacao.modules.voto.domain.dto.DetailVotoDTO;
import com.dbserver.desafiovotacao.modules.voto.usecase.CreateVotoUseCase;
import com.dbserver.desafiovotacao.modules.voto.usecase.FindVotoUseCase;
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
public class VotoControllerV1 {
    private final CreateVotoUseCase createVotoUseCase;
    private final FindVotoUseCase findVotoUseCase;

    @PostMapping
    public ResponseEntity<DetailVotoDTO> createVoto(@RequestBody @Valid CreateVotoDTO createVotoDTO) {
        return ResponseEntity.ok(createVotoUseCase.createVoto(createVotoDTO));
    }

    @GetMapping
    public ResponseEntity<Page<DetailVotoDTO>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(findVotoUseCase.findAll(pageable));
    }

    @GetMapping("/apuracao/{idPauta}")
    public ResponseEntity<DetailResultadoVotacaoDTO> apurar(@PathVariable long idPauta) {
        return ResponseEntity.ok(findVotoUseCase.count(idPauta));
    }
}
