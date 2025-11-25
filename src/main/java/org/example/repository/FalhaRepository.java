package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Falha;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FalhaRepository {

    public Falha registrarNovaFalha(Falha falha) throws SQLException{
        String query = """
                INSERT INTO Falha (
                equipamentoId,
                dataHoraOcorrencia,
                descricao,
                criticidade,
                status,
                tempoParadaHoras
                ) VALUES (?,?,?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            stmt.setLong(1,falha.getEquipamentoId());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3, falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, "ABERTA");
            stmt.setBigDecimal(6,falha.getTempoParadaHoras());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            if(rs.next()){
                long id = rs.getLong(1);
                falha.setId(id);
            }
        }
        return falha;
    }

    public Falha atualizarEquipamentoCriticidade(Falha falha) throws SQLException {
        String query = """
                UPDATE Falha
                    SET equipamentoId = ?,
                        dataHoraOcorrencia = ?,
                        descricao = ?,
                        criticidade = ?,
                        status = ?,
                        tempoParadaHoras = ?
                WHERE criticidade = 'CRITICA'
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setLong(1, falha.getEquipamentoId());
            stmt.setTimestamp(2,java.sql.Timestamp.valueOf(falha.getDataHoraOcorrencia()));
            stmt.setString(3,falha.getDescricao());
            stmt.setString(4, falha.getCriticidade());
            stmt.setString(5, "EM_ANDAMENTO");
            stmt.setBigDecimal(6,falha.getTempoParadaHoras());
            stmt.executeUpdate();
        }
        return falha;
    }

    public List<Falha> buscarFalhasCriticasAbertas () throws SQLException {
        List<Falha> falhasAbertas = new ArrayList<>();
        String query = """
                SELECT equipamentoId
                ,dataHoraOcorrencia
                ,descricao
                ,criticidade
                ,status
                ,tempoParadaHoras
                FROM Falha
                WHERE status = 'ABERTA' AND criticidade = 'CRITICA'
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                long equipamentoId = rs.getLong("equipamentoId");
                LocalDateTime dataHoraOcorrencia = rs.getTimestamp("dataHoraOcorrencia").toLocalDateTime();
                String descricao = rs.getString("descricao");
                String criticidade = rs.getString("criticidade");
                String status = rs.getString("status");
                BigDecimal tempoParadaHoras = rs.getBigDecimal("tempoParadaHoras");
                Falha falha = new Falha(equipamentoId,dataHoraOcorrencia,descricao,criticidade,status,tempoParadaHoras);
                falhasAbertas.add(falha);
            }
        }
        return falhasAbertas;
    }

}
