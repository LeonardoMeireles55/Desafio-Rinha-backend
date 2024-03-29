package com.backend.leonardo.web.service;

import com.backend.leonardo.dto.RecuperarSaldoDTO;
import com.backend.leonardo.dto.RetornoTransacaoCompletaDTO;
import com.backend.leonardo.dto.RetornoTrasacaoDTO;
import com.backend.leonardo.dto.SaldoETransacoesDTO;
import com.backend.leonardo.web.errorHandiling.CustomResponseEntityExceptionHandler.UnprocessableEntityException;
import com.backend.leonardo.web.repository.ClienteRepository;
import com.backend.leonardo.web.repository.TransacaoRepository;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final TransacaoRepository transacaoRepository;

    public ClienteService(TransacaoRepository transacaoRepository, ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.transacaoRepository = transacaoRepository;
    }
        @Transactional(isolation = Isolation.SERIALIZABLE)
        public Mono<SaldoETransacoesDTO> getBalance(int id) {

        Mono<RecuperarSaldoDTO> saldoMono = clienteRepository.findById(id)
            .map(saldo -> new RecuperarSaldoDTO(saldo.valor(), LocalDateTime.now(), saldo.limite()));

        Flux<RetornoTransacaoCompletaDTO> transacoesFlux = transacaoRepository.findAllByClienteIdOrderByRealizadaEm(id);

        return Mono.zip(saldoMono, transacoesFlux.collectList())
            .map(tuple -> new SaldoETransacoesDTO(tuple.getT1(), tuple.getT2()));

        }

    public Mono<RetornoTrasacaoDTO> createTransaction(int clienteIdParam, char tipoParam, int valorParam,
            String descricaoParam) {
        return transacaoRepository.transaction(clienteIdParam, tipoParam, valorParam, descricaoParam)
                .onErrorResume(e -> Mono.error(new UnprocessableEntityException()));
    }
}