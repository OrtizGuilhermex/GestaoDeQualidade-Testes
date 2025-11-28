package org.example.repository;

import org.example.database.Conexao;
import org.example.dto.EquipamentoContagemFalhasDTO;
import org.example.model.AcaoCorretiva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcaoCorretivaRepository {


    public AcaoCorretiva registrarConclusaoDeAcao(AcaoCorretiva acao) throws SQLException {
        String query = """
                    INSERT INTO AcaoCorretiva(
                    falhaId,
                    dataHoraInicio,
                    dataHoraFim,
                    responsavel,
                    descricaoAcao
                    ) VALUES (?,?,?,?,?)
                    """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, acao.getFalhaId());
            stmt.setTimestamp(2, Timestamp.valueOf(acao.getDataHoraInicio()));
            stmt.setTimestamp(3, Timestamp.valueOf(acao.getDataHoraFim()));
            stmt.setString(4, acao.getResponsavel());
            stmt.setString(5, acao.getDescricaoArea());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                String id = rs.getString(1);

                acao.setId(Long.valueOf(id));

                return acao;
            }
        }
        return null;
    }

    public AcaoCorretiva AtualizarAcaoCorretivaFalhaResolvida(AcaoCorretiva acaoCorretiva)throws  SQLException{
        String query = """
                UPDATE Falha f
                JOIN AcaoCorretiva c ON f.id = c.falhaId
                    SET f.status = ?
                WHERE 1=1
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1,"RESOLVIDA");
            stmt.executeUpdate();
        }
        return acaoCorretiva;
    }

    public AcaoCorretiva AtualizarAcaoCorretivaEquipamento(AcaoCorretiva acaoCorretiva) throws SQLException {
        String query = """
                UPDATE Equipamento e
                JOIN Falha f ON f.equipamentoId = e.id
                JOIN AcaoCorretiva c ON f.id = c.falhaId
                    SET e.statusOperacional = ?
                WHERE f.status = 'RESOLVIDA'
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1,"OPERACIONAL");

            stmt.executeUpdate();
        }
        return acaoCorretiva;
    }

    public List<String> buscarAcaoCorretivaPorIdFalha (long idFalha) throws SQLException {
        List<String> acoes = new ArrayList<>();
        String query = """
                SELECT descricaoAcao
                FROM AcaoCorretiva
                WHERE falhaId = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS  )){

            stmt.setLong(1,idFalha);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                String descricaoAcao = rs.getString("descricaoAcao");
                acoes.add(descricaoAcao);
            }
        }
        return acoes;
    }
}
