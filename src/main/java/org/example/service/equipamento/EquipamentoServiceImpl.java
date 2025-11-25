package org.example.service.equipamento;

import org.example.model.Equipamento;
import org.example.repository.EquipamentoRepository;

import java.sql.SQLException;

public class EquipamentoServiceImpl implements EquipamentoService{
    @Override
    public Equipamento criarEquipamento(Equipamento equipamento) throws SQLException {
        EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
        equipamentoRepository.cadastrarEquipamento(equipamento);
        return equipamento;
    }

    @Override
    public Equipamento buscarEquipamentoPorId(Long id) throws SQLException {
        EquipamentoRepository equipamentoRepository = new EquipamentoRepository();

        Equipamento equipamentoEncontrado = equipamentoRepository.buscarEquipamento(id);
        if(equipamentoEncontrado == null){
            throw new RuntimeException("Equipamento não encontrado!");
        }
        return equipamentoEncontrado;
    }
}

