package org.example.model;

import java.time.LocalDateTime;

public class AcaoCorretiva {
    private Long id;

    private Long falhaId;

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraFim;

    private String responsavel;

    private String descricaoArea;

    public AcaoCorretiva(Long id, Long falhaId, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String responsavel, String descricaoArea) {
        this.id = id;
        this.falhaId = falhaId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoArea = descricaoArea;
    }

    public AcaoCorretiva(Long falhaId, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, String responsavel, String descricaoArea) {
        this.falhaId = falhaId;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.responsavel = responsavel;
        this.descricaoArea = descricaoArea;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFalhaId() {
        return falhaId;
    }

    public void setFalhaId(Long falhaId) {
        this.falhaId = falhaId;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    public void setDataHoraFim(LocalDateTime dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getDescricaoArea() {
        return descricaoArea;
    }

    public void setDescricaoArea(String descricaoArea) {
        this.descricaoArea = descricaoArea;
    }
}
