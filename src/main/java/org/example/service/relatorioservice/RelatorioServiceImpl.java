package org.example.service.relatorioservice;

import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.dto.FalhaDetalhadaDTO;
import org.example.dto.RelatorioParadaDTO;
import org.example.model.Equipamento;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.EquipamentoRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RelatorioServiceImpl implements RelatorioService{
    FalhaRepository falhaRepository = new FalhaRepository();
    EquipamentoRepository equipamentoRepository = new EquipamentoRepository();
    AcaoCorretivaRepository acaoCorretivaRepository = new AcaoCorretivaRepository();

    @Override
    public List<RelatorioParadaDTO> gerarRelatorioTempoParada() throws SQLException {
        return falhaRepository.gerarRelatorioTempoParada();
    }

    @Override
    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate datafim) throws SQLException {
        return equipamentoRepository.buscarEquipamentosSemFalhasPorPeriodo(dataInicio,datafim);
    }

    @Override
    public Optional<FalhaDetalhadaDTO> buscarDetalhesCompletosFalha(long falhaId) throws SQLException {
        Falha falha = falhaRepository.buscarFalhaPorId(falhaId);

        if(falha == null){
            throw new RuntimeException();
        }

        Equipamento equipamento = equipamentoRepository.buscarEquipamento(falha.getEquipamentoId());

        if(equipamento == null){
            throw new RuntimeException();
        }

        List<String> acao = acaoCorretivaRepository.buscarAcaoCorretivaPorIdFalha(falhaId);
        return Optional.of(new FalhaDetalhadaDTO(falha,equipamento,acao));
    }

    @Override
    public List<EquipamentoContagemFalhasDTO> gerarRelatorioManutencaoPreventiva(int contagemMinimaFalhas) throws SQLException {

        if(contagemMinimaFalhas < 1){
            throw new RuntimeException();
        }
        return equipamentoRepository.gerarRelatorioManutencaoPreventiva(contagemMinimaFalhas);
    }
}
