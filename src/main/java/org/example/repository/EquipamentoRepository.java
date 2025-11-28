package org.example.repository;

import org.example.database.Conexao;
import org.example.model.Equipamento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {

    public Equipamento cadastrarEquipamento(Equipamento equipamento) throws SQLException {
        String query = """
                INSERT INTO Equipamento (nome,numeroDeSerie,areaSetor,statusOperacional) VALUES (?,?,?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getNumeroDeSerie());
            stmt.setString(3, equipamento.getAreaSetor());
            stmt.setString(4, "OPERACIONAL");
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Long id = rs.getLong(1);
                equipamento.setId(id);
            }
        }
        return equipamento;
    }

    public Equipamento buscarEquipamento(Long id) throws SQLException {
        String query = """
                SELECT id
                ,nome
                ,numeroDeSerie
                ,areaSetor
                ,statusOperacional
                FROM Equipamento
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Equipamento(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("numeroDeSerie"),
                        rs.getString("areaSetor"),
                        rs.getString("statusOperacional")
                );
            }
        }
        return null;
    }

    public List<Equipamento> buscarEquipamentosSemFalhasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws SQLException {
        List<Equipamento> equipamentos = new ArrayList<>();

        String query = """
                SELECT id
                ,nome
                ,numeroDeSerie
                ,areaSetor
                ,statusOperacional
                FROM Equipamento
                WHERE id NOT IN
                (SELECT equipamentoId
                FROM Falha
                WHERE dataHoraOcorrencia >= ? AND dataHoraOcorrencia <= ?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(dataInicio));
            stmt.setDate(2, Date.valueOf(dataFim));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String numeroDeSerie = rs.getString("numeroDeSerie");
                String areaSetor = rs.getString("areaSetor");
                String statusOperacional = rs.getString("statusOperacional");
                Equipamento equipamento = new Equipamento(id,nome,numeroDeSerie,areaSetor,statusOperacional);
                equipamentos.add(equipamento);
            }
        }
        return equipamentos;
    }
}
