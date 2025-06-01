package br.com.fiap.bean;

public class Ong {
    // Atributos
    private int id;
    private String nome;
    private String areaAtendimento; // Regiões do Brasil
    private double totalRecebido;


    // Construtor
    public Ong(int id, String nome, String areaAtendimento) {
        this.id = id;
        this.nome = nome;
        this.areaAtendimento = areaAtendimento;
        this.totalRecebido = 0;
    }

    // Getters e Setters
    public double getTotalRecebido() {
        return totalRecebido;
    }

    public void setTotalRecebido(double totalRecebido) {
        this.totalRecebido = totalRecebido;
    }

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

    // sobrecarga do metodo atendeRegiao
    public boolean atendeRegiao(String[] regioes) {
        for (String regiao : regioes) {
            if (this.areaAtendimento.equalsIgnoreCase(regiao)) {
                return true;
            }
        }
        return false;
    }

    public void receberDoacao(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da doação deve ser positivo");
        }
        this.totalRecebido += valor;
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

    // metodo operacional que exibe relatório resumido
    public void exibirRelatorioConsole(int mesesAtuacao) {
        System.out.println("=== Relatório da ONG ===");
        System.out.printf("ID: %d\n", id);
        System.out.printf("Nome: %s\n", nome);
        System.out.printf("Região de atuação: %s\n", areaAtendimento);
        System.out.printf("Tempo de atuação: %d meses\n", mesesAtuacao);
        System.out.printf("Total recebido em doações: R$%.2f\n", totalRecebido);

        int eficiencia = Math.min(100, mesesAtuacao * 10);
        System.out.printf("Nível de eficiência: %d%%\n", eficiencia);
    }


    // metodo para exibir informações básicas
    public void exibirInformacoes() {
        System.out.printf("ONG %d: %s (Atuação: %s)\n", id, nome, areaAtendimento);
    }
}