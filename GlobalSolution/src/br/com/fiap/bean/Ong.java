package br.com.fiap.bean;

public class Ong {
    // Atributos
    private int id;
    private String nome;
    private String areaAtendimento; // Regiões do Brasil

    // Construtor
    public Ong(int id, String nome, String areaAtendimento) {
        this.id = id;
        this.nome = nome;
        this.areaAtendimento = areaAtendimento;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAreaAtendimento() {
        return areaAtendimento;
    }

    public void setAreaAtendimento(String areaAtendimento) {
        this.areaAtendimento = areaAtendimento;
    }

    // Metodo operacional que verifica se a Ong atende uma região específica
    public boolean atendeRegiao(String regiao) {
        return this.areaAtendimento.equalsIgnoreCase(regiao);
    }

    // Sobrecarga do metodo atendeRegiao
    public boolean atendeRegiao(String[] regioes) {
        for (String regiao : regioes) {
            if (this.areaAtendimento.equalsIgnoreCase(regiao)) {
                return true;
            }
        }
        return false;
    }

    // Metodo operacional que calcula prioridade com base na urgência (1-10)
    public int calcularPrioridade(int nivelUrgencia) {
        try {
            if (nivelUrgencia < 1 || nivelUrgencia > 10) {
                throw new Exception("Nível de urgência deve ser entre 1 e 10");
            }
            return nivelUrgencia * 2;
        } catch (Exception e) {
            System.out.printf("Erro: %s\n", e.getMessage());
            return 0;
        }
    }

    // Metodo operacional que exibe relatório resumido
    public void exibirRelatorioConsole(int mesesAtuacao) {
        System.out.println("=== Relatório da ONG ===");
        System.out.printf("ID: %d\n", id);
        System.out.printf("Nome: %s\n", nome);
        System.out.printf("Região de atuação: %s\n", areaAtendimento);
        System.out.printf("Tempo de atuação: %d meses\n", mesesAtuacao);

        int eficiencia = Math.min(100, mesesAtuacao * 10);
        System.out.printf("Nível de eficiência: %d%%\n", eficiencia);
    }


    // Metodo para exibir informações básicas
    public void exibirInformacoes() {
        System.out.printf("ONG %d: %s (Atuação: %s)\n", id, nome, areaAtendimento);
    }
}