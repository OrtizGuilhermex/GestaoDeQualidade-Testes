package org.example.service.falha;

import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.util.List;

public class FalhaServiceImpl implements FalhaService{
    @Override
    public Falha registrarNovaFalha(Falha falha) throws SQLException {
       FalhaRepository falhaRepository = new FalhaRepository();
       EquipamentoRepository equipamentoRepository = new EquipamentoRepository();

       falhaRepository.registrarNovaFalha(falha);
       Equipamento equipamentoEncontrado = equipamentoRepository.buscarEquipamento(falha.getId());

       if(equipamentoEncontrado == null){
           throw new IllegalArgumentException("Equipamento não encontrado!");
       } else {
           falhaRepository.atualizarEquipamentoCriticidade(falha);
       }
       return falha;
    }

    @Override
    public List<Falha> buscarFalhasCriticasAbertas() throws SQLException {
        FalhaRepository falhaRepository = new FalhaRepository();
        return falhaRepository.buscarFalhasCriticasAbertas();
    }
}
