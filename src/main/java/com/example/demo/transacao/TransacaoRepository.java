package com.example.demo.transacao;


import com.example.demo.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository
        extends JpaRepository<Transacao, Integer> {
    @Query(value= "SELECT * FROM transacao WHERE dt_transacao LIKE %?1%", nativeQuery = true)
    Optional<List<Transacao>> findAllByDtTransacao( @Param ("dtTransacao") String data);
}
