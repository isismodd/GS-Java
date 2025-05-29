package br.com.fiap.bean;
/**Classe para cadastrar empresas parceiraas e listar
 * @author Isis Macedo
 * @version 5.0
 */
public class EmpresaParceira {
    // Atributos
    private int id;
    private String nome;
    private String setorAtuacao;
    private double valorDoacao;

    // Construtor
    public EmpresaParceira(int id, String nome, String setorAtuacao, double valorDoacao) {
        this.id = id;
        this.nome = nome;
        this.setorAtuacao = setorAtuacao;
        this.valorDoacao = valorDoacao;
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

    public String getSetorAtuacao() {
        return setorAtuacao;
    }

    public void setSetorAtuacao(String setorAtuacao) {
        this.setorAtuacao = setorAtuacao;
    }

    public double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(double valorDoacao) {
        this.valorDoacao = valorDoacao;
    }

    // Metodo operacional que verifica se a empresa atua em um setor específico
    public boolean atuaNoSetor(String setor) {
        return this.setorAtuacao.equalsIgnoreCase(setor);
    }

    // Sobrecarga do metodo atuaNoSetor pra verificar varios setores
    public boolean atuaNoSetor(String[] setores) {
        for (String setor : setores) {
            if (this.setorAtuacao.equalsIgnoreCase(setor)) {
                return true;
            }
        }
        return false;
    }

    // Metodo operacional que alcula o valor de benefício fiscal (10% do valor doado)
    public double calcularBeneficioFiscal() {
        return this.valorDoacao * 0.10;
    }

    // Sobrecarga do metodo calcularBeneficioFiscal com taxa personalizada
    public double calcularBeneficioFiscal(double taxaPersonalizada) {
        if (taxaPersonalizada < 0 || taxaPersonalizada > 0.30) {
            System.out.printf("Erro: Taxa de benefício deve ser entre 0 e 0.30 (30%%)");
            return 0;
        }
        return this.valorDoacao * taxaPersonalizada;
    }

    // Metodo operacional Exibe relatório da empresa
    public void exibirRelatorio() {
        System.out.println("=== Relatório da Empresa Parceira ===");
        System.out.printf("ID: %d\n", id);
        System.out.printf("Nome: %s\n", nome);
        System.out.printf("Setor de Atuação: %s\n", setorAtuacao);
        System.out.printf("Valor doado: R$%.2f\n", valorDoacao);
        System.out.printf("Benefício fiscal estimado: R$%.2f\n", calcularBeneficioFiscal());
    }
}
