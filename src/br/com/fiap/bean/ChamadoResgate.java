package br.com.fiap.bean;
/**
 * Classe para cadastrar chamados de resgate
 * @author Isis Macedo
 * @version 7.0
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChamadoResgate extends Ong {
    // Atributos
    private int idChamado;
    private String tipoChamado;
    private String situacaoVitima;
    private String regiao;
    private LocalDateTime dataHora;
    private int nivelEmergencia;
    private static int proximoId = 1;

    // Construtor
    public ChamadoResgate(int idOng, String nomeOng, String areaAtuacao) {
        super(idOng, nomeOng, areaAtuacao);
        this.idChamado = proximoId++;
        this.dataHora = LocalDateTime.now();
    }

    // Métodos operacionais

    /**
     * Cadastra um novo chamado de resgate
     * @param tipo Tipo do chamado (animal doméstico, pessoa, animal silvestre)
     * @param situacao Situação da vítima (machucado seriamente, levemente, intacto)
     * @param regiao Região onde ocorre o resgate
     */
    public void cadastrarChamado(String tipo, String situacao, String regiao) {
        setTipoChamado(tipo);
        setSituacaoVitima(situacao);
        setRegiao(regiao);
        this.nivelEmergencia = calcularEmergencia();
        System.out.println(gerarResumoChamado());
    }

    /**
     * Calcula o nível de emergência com base na situação
     * @return Nível de emergência (1-3)
     */
    private int calcularEmergencia() {
        if (getSituacaoVitima().equalsIgnoreCase("ferido seriamente")) {
            return 3;
        } else if (getSituacaoVitima().equalsIgnoreCase("ferido levemente")) {
            return 2;
        }
        return 1;

    }

    /**
     * Verifica se o chamado é de alta prioridade
     * @return true se for emergência máxima
     */
    public boolean isAltaPrioridade() {
        return getNivelEmergencia() == 3;
    }

    /**
     * Calcula tempo estimado de resposta em horas
     * @return Tempo estimado para atendimento
     */
    public double calcularTempoResposta() {
        return switch (getNivelEmergencia()) {
            case 3 -> 0.5;  // 30 minutos
            case 2 -> 2.0;  // 2 horas
            default -> 4.0; // 4 horas
        };
    }

    // Métodos de sobrecarga

    public void atualizarSituacao(String novaSituacao) {
        setSituacaoVitima(novaSituacao);
        this.nivelEmergencia = calcularEmergencia();
    }

    public void atualizarSituacao(String novaSituacao, String observacoes) {
        setSituacaoVitima(novaSituacao + " (" + observacoes + ")");
        this.nivelEmergencia = calcularEmergencia();
    }

    // Método sobrescrito
    @Override
    public void exibirInformacoes() {
        System.out.printf("Chamado %d - %s\nTipo: %s | Situação: %s | Urgência: %d\n",
                idChamado, getNome(), tipoChamado, situacaoVitima, nivelEmergencia);
    }

    // Getters e Setters
    public int getIdChamado() {
        return idChamado;
    }

    public String getTipoChamado() {
        return tipoChamado;
    }

    public void setTipoChamado(String tipoChamado) {
        if (tipoChamado == null || tipoChamado.isEmpty()) {
            throw new IllegalArgumentException("Tipo do chamado não pode ser vazio");
        }
        this.tipoChamado = tipoChamado;
    }

    public String getSituacaoVitima() {
        return situacaoVitima;
    }

    public void setSituacaoVitima(String situacaoVitima) {
        if (situacaoVitima == null || situacaoVitima.isEmpty()) {
            throw new IllegalArgumentException("Situação não pode ser vazia");
        }
        this.situacaoVitima = situacaoVitima;
        this.nivelEmergencia = calcularEmergencia(); // Atualiza emergência ao mudar situação
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        if (regiao == null || regiao.isEmpty()) {
            throw new IllegalArgumentException("Região não pode ser vazia");
        }
        this.regiao = regiao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new IllegalArgumentException("Data/hora não pode ser nula");
        }
        this.dataHora = dataHora;
    }

    public int getNivelEmergencia() {
        return nivelEmergencia;
    }

    // metodo privado para gerar resumo (usado internamente)
    private String gerarResumoChamado() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = dataHora.format(formatter);

        String resumo = "=== Chamado " + idChamado + " ===\n" +
                "Tipo: " + tipoChamado + "\n" +
                "Situação: " + situacaoVitima + "\n" +
                "Região: " + regiao + "\n" +
                "Data: " + dataFormatada + "\n" +
                "Nível emergência: " + nivelEmergencia + "\n" +
                "ONG: " + getNome();

        return resumo;
    }
}