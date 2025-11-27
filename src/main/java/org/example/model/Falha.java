package org.example.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Falha {

    private Long id;

    private Long equipamentoId;

    private LocalDateTime dataHoraOcorrencia;

    private String descricao;

    private String criticidade;

    private String status;

    private BigDecimal tempoParadaHoras;

    public Falha(Long id, Long equipamentoId, LocalDateTime dataHoraOcorrencia, String descricao, String criticidade, String status, BigDecimal tempoParadaHoras) {
        this.id = id;
        this.equipamentoId = equipamentoId;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Falha(Long equipamentoId, LocalDateTime dataHoraOcorrencia, String descricao, String criticidade, String status, BigDecimal tempoParadaHoras) {
        this.equipamentoId = equipamentoId;
        this.dataHoraOcorrencia = dataHoraOcorrencia;
        this.descricao = descricao;
        this.criticidade = criticidade;
        this.status = status;
        this.tempoParadaHoras = tempoParadaHoras;
    }

    public Falha() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(Long equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public LocalDateTime getDataHoraOcorrencia() {
        return dataHoraOcorrencia;
    }

    public void setDataHoraOcorrencia(LocalDateTime dataHoraOcorrencia) {
        this.dataHoraOcorrencia = dataHoraOcorrencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTempoParadaHoras() {
        return tempoParadaHoras;
    }

    public void setTempoParadaHoras(BigDecimal tempoParadaHoras) {
        this.tempoParadaHoras = tempoParadaHoras;
    }
}
