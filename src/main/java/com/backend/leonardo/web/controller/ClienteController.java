package com.backend.leonardo.web.controller;

import com.backend.leonardo.dto.EfetuarTransacaoDTO;
import com.backend.leonardo.dto.RetornoTrasacaoDTO;
import com.backend.leonardo.dto.SaldoETransacoesDTO;
import com.backend.leonardo.web.errorHandiling.CustomResponseEntityExceptionHandler.BadRequestException;
import com.backend.leonardo.web.errorHandiling.CustomResponseEntityExceptionHandler.NoContentException;
import com.backend.leonardo.web.service.ClienteService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/{id}/transacoes")
    public ResponseEntity<Mono<RetornoTrasacaoDTO>> postTransaction(
            @PathVariable int id,
            @RequestBody EfetuarTransacaoDTO transacaoDTO) {
        if (id < 1 || id > 5) {
            throw new NoContentException();
        }
        if (transacaoDTO.valor() == null || transacaoDTO.valor().intValue() != transacaoDTO.valor() ||
            transacaoDTO.tipo() != 'd' && transacaoDTO.tipo() != 'c'
                || transacaoDTO.descricao() == null || transacaoDTO.descricao().isBlank() ||
                transacaoDTO.descricao().length() > 10) {
            throw new BadRequestException();
        }

        return ResponseEntity.ok(clienteService.createTransaction(id, transacaoDTO.tipo(),
                transacaoDTO.valor().intValue(),
                transacaoDTO.descricao()));
    }

    @GetMapping("{id}/extrato")
    public ResponseEntity<Mono<SaldoETransacoesDTO>> getBalance(@PathVariable int id) {
        if (id < 1 || id > 5) {
            throw new NoContentException();
        }

        return ResponseEntity.ok(clienteService.getBalance(id));
    }
}
