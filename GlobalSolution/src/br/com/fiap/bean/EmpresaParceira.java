package br.com.fiap.bean;

/**
 * Classe para cadastrar empresas parceiras
 * @author Isis Macedo
 * @version 6.0
 */
public class EmpresaParceira extends Ong {
    // Atributos 
    private String setorAtuacao;
    private double valorDoacao;

    // Construtor
    public EmpresaParceira(int id, String nome, String areaAtendimento, String setorAtuacao, double valorDoacao) {
        super(id, nome, areaAtendimento);
        setSetorAtuacao(setorAtuacao);
        setValorDoacao(valorDoacao);
    }

    // Getters e Setters 
    public String getSetorAtuacao() {
        return setorAtuacao;
    }

    public void setSetorAtuacao(String setorAtuacao) {
        if (setorAtuacao == null || setorAtuacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Setor de atuação não pode ser vazio");
        }
        this.setorAtuacao = setorAtuacao.trim();
    }

    public double getValorDoacao() {
        return valorDoacao;
    }

    public void setValorDoacao(double valorDoacao) {
        if (valorDoacao < 0) {
            throw new IllegalArgumentException("Valor da doação não pode ser negativo");
        }
        this.valorDoacao = valorDoacao;
    }

    // Métodos operacionais

    /**
     * Verifica se a empresa atua em um setor específico
     * @param setor Setor a ser verificado
     * @return true se a empresa atuar no setor
     */
    public boolean atuaNoSetor(String setor) {
        return this.setorAtuacao.equalsIgnoreCase(setor);
    }

    /**
     * Verifica se a empresa atua em algum dos setores informados
     * @param setores Array de setores a verificar
     * @return true se a empresa atuar em algum dos setores
     */
    public boolean atuaNoSetor(String[] setores) {
        for (String setor : setores) {
            if (this.setorAtuacao.equalsIgnoreCase(setor)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula o benefício fiscal padrão (10% do valor doado)
     * @return Valor do benefício fiscal
     */
    public double calcularBeneficioFiscal() {
        return this.valorDoacao * 0.10;
    }

    /**
     * Calcula benefício fiscal com taxa personalizada
     * @param taxaPersonalizada Taxa entre 0 e 0.30 (30%)
     * @return Valor do benefício fiscal
     */
    public double calcularBeneficioFiscal(double taxaPersonalizada) {
        if (taxaPersonalizada < 0 || taxaPersonalizada > 0.30) {
            throw new IllegalArgumentException("Taxa deve ser entre 0 e 0.30 (30%)");
        }
        return this.valorDoacao * taxaPersonalizada;
    }

    /**
     * Sobrescrita do método exibirInformacoes da classe Ong
     * mostra algumas informações específicas da empresa parceira
     */
    @Override
    public void exibirInformacoes() {
        System.out.println("=== Informações da Empresa Parceira ===");
        System.out.printf("ID: %d\n", getId());
        System.out.printf("Nome: %s\n", getNome());
        System.out.printf("Área de Atendimento: %s\n", getAreaAtendimento());
        System.out.printf("Setor de Atuação: %s\n", setorAtuacao);
        System.out.printf("Valor Doado: R$%.2f\n", valorDoacao);
        System.out.printf("Benefício Fiscal: R$%.2f\n", calcularBeneficioFiscal());
    }
}
