package com.backend.leonardo.web.service;

import com.backend.leonardo.dto.RecuperarSaldoDTO;
import com.backend.leonardo.dto.RetornoTransacaoCompletaDTO;
import com.backend.leonardo.dto.RetornoTrasacaoDTO;
import com.backend.leonardo.dto.SaldoETransacoesDTO;
import com.backend.leonardo.web.repository.ClienteRepository;
import com.backend.leonardo.web.repository.TransacaoRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;
    private static ResponseStatusException errorNotFound() {
        return new ResponseStatusException(HttpStatusCode.valueOf(404));
    }
    private static ResponseStatusException errorUnprocessableEntity() {
        return new ResponseStatusException(HttpStatusCode.valueOf(422));
    }
    private static boolean clientExistById(int id) {
        return id > 0 && id <= 5;
    }

    public ClienteService(TransacaoRepository transacaoRepository, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }

    public Mono<SaldoETransacoesDTO> getBalance(int id) {
        if (!clientExistById(id)) {
            return Mono.error(errorNotFound());
        }

        Mono<RecuperarSaldoDTO> saldoMono = clienteRepository.findById(id)
                .map(saldo -> new RecuperarSaldoDTO(saldo.valor(), LocalDateTime.now(), saldo.limite()));

        Flux<RetornoTransacaoCompletaDTO> transacoesFlux =
                transacaoRepository.findAllByClienteIdOrderByRealizadaEm(id);

        return Mono.zip(saldoMono, transacoesFlux.collectList())
                .map(tuple -> new SaldoETransacoesDTO(tuple.getT1(), tuple.getT2()));
    }

    public Mono<RetornoTrasacaoDTO> createTransaction(int clienteIdParam, char tipoParam, int valorParam,
            String descricaoParam) {
        if (!clientExistById(clienteIdParam)) {
            return Mono.error(errorNotFound());
        }

        if (tipoParam != 'd' && tipoParam != 'c') {
            return Mono.error(errorUnprocessableEntity());
        }

        if (descricaoParam == null || descricaoParam.isEmpty() || descricaoParam.isBlank()
                || descricaoParam.length() > 10) {
            return Mono.error(errorUnprocessableEntity());
        }
        return transacaoRepository.transaction(clienteIdParam, tipoParam, valorParam, descricaoParam)
                .onErrorResume(exception -> Mono.error(errorUnprocessableEntity()));
    }
}