package com.backend.leonardo.web.controller;

import com.backend.leonardo.dto.EfetuarTransacaoDTO;
import com.backend.leonardo.dto.RetornoTrasacaoDTO;
import com.backend.leonardo.dto.SaldoETransacoesDTO;
import com.backend.leonardo.web.service.ClienteService;
import jakarta.validation.Valid;
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
    public Mono<RetornoTrasacaoDTO> postTransaction(
            @PathVariable Integer id,
            @Valid @RequestBody EfetuarTransacaoDTO transacaoDTO) {

        return clienteService.createTransaction(id,transacaoDTO.tipo(), transacaoDTO.valor().intValue(),
                 transacaoDTO.descricao());
    }

    @GetMapping("{id}/extrato")
    public ResponseEntity<Mono<SaldoETransacoesDTO>> getBalance(@PathVariable Integer id) {

        return ResponseEntity.ok(clienteService.getBalance(id));
    }
}    
