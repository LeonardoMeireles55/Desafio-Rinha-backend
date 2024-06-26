package com.backend.leonardo.web.repository;

import com.backend.leonardo.dto.RetornoTransacaoCompletaDTO;
import com.backend.leonardo.dto.RetornoTrasacaoDTO;
import com.backend.leonardo.model.Transacao;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransacaoRepository extends ReactiveCrudRepository<Transacao, Integer> {
    
    @Query("SELECT VALOR, TIPO, DESCRICAO, REALIZADA_EM FROM TRANSACAO WHERE id_cliente = :cliente_fk ORDER BY realizada_em DESC LIMIT 10")
    Flux<RetornoTransacaoCompletaDTO> findAllByClienteIdOrderByRealizadaEm(@Param("cliente_fk") int cliente_fk);

    @Query("SELECT saldo, limite FROM efetuar_transacao(:clienteIdParam, :tipoParam, :valorParam, :descricaoParam)")
    Mono<RetornoTrasacaoDTO> transaction(
            @Param("clienteIdParam") int clienteIdParam,
            @Param("tipoParam") char tipoParam,
            @Param("valorParam") int valorParam,
            @Param("descricaoParam") String descricaoParam
            );
}
