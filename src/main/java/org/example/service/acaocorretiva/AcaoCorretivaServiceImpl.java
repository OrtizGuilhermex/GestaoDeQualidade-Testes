package org.example.service.acaocorretiva;

import org.example.model.AcaoCorretiva;
import org.example.model.Falha;
import org.example.repository.AcaoCorretivaRepository;
import org.example.repository.FalhaRepository;

import java.sql.SQLException;

public class AcaoCorretivaServiceImpl implements AcaoCorretivaService{

    @Override
    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {

        AcaoCorretivaRepository acaoCorretivaRepository = new AcaoCorretivaRepository();
        FalhaRepository falhaRepository = new FalhaRepository();

        Falha falha = falhaRepository.buscarFalhaPorId(acao.getFalhaId());

        if(falha == null) {
            throw new RuntimeException("Falha não encontrada!");
        }

        acaoCorretivaRepository.registrarConclusaoDeAcao(acao);
        acaoCorretivaRepository.AtualizarAcaoCorretivaFalhaResolvida(acao);
        return acaoCorretivaRepository.AtualizarAcaoCorretivaEquipamento(acao);
    }
}


