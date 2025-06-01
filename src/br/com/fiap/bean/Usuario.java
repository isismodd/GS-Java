package br.com.fiap.bean;

/**
 * Classe para representar um usuário do sistema
 * @author Isis Macedo
 * @version 3.2
 */
public class Usuario extends Ong {
    // Atributos
    private String cpf;
    private String username;
    private double totalDoado;
    private boolean heroiDaVizinhanca;

    // Construtor
    public Usuario(int id, String nome, String cpf, String username) {
        super(id, nome, "N/A"); // Passa "N/A" como área de atendimento
        setCpf(cpf);
        setUsername(username);
        this.totalDoado = 0;
        this.heroiDaVizinhanca = false;
    }

    // Sobrescrita do metodo exibirInformacoes
    @Override
    public void exibirInformacoes() {
        System.out.println("\n=== Informações do Usuário ===");
        System.out.printf("ID: %d\n", getId());
        System.out.printf("Nome: %s%s\n", getNome(), heroiDaVizinhanca ? " - Herói da Vizinhança" : "");
        System.out.printf("CPF: %s\n", cpf);
        System.out.printf("Username: %s\n", username);
        System.out.printf("Total Doado: R$%.2f\n", totalDoado);
        System.out.printf("Área de Atendimento: %s\n", getAreaAtendimento());
    }

    // Métodos operacionais

    /**
     * Realiza uma doação para uma ONG (versão normal)
     */
    public void realizarDoacao(double valor, Ong ong) {
        realizarDoacao(valor, ong, "Doação sem mensagem");
    }

    /**
     * Sobrecarga: realiza doação com mensagem personalizada
     */
    public void realizarDoacao(double valor, Ong ong, String mensagem) {
        validarDoacao(valor, ong);
        this.totalDoado += valor;
        verificarHeroiDaVizinhanca(); // Verifica se virou herói
        ong.receberDoacao(valor);

        String titulo = heroiDaVizinhanca ? " Herói da Vizinhança" : "";
        System.out.printf("\n%s%s doou R$%.2f para %s\n",
                getNome(), titulo, valor, ong.getNome());
        System.out.println("Mensagem: " + mensagem);
    }

    /**
     * Verifica se o usuário se tornou Herói da Vizinhança
     * (doou mais de R$5.000)
     */
    public void verificarHeroiDaVizinhanca() {
        if (!heroiDaVizinhanca && totalDoado > 5000.00) {
            this.heroiDaVizinhanca = true;
            System.out.println("\nParabéns! " + getNome() + " agora é um Herói da Vizinhança!");
        }
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")) {
            throw new IllegalArgumentException("CPF inválido");
        }
        this.cpf = cpf;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username não pode ser vazio");
        }
        this.username = username.trim();
    }

    public double getTotalDoado() {
        return totalDoado;
    }

    public boolean isHeroiDaVizinhanca() {
        return heroiDaVizinhanca;
    }

    // Metodo privado para validação
    private void validarDoacao(double valor, Ong ong) {
        if (valor <= 0) throw new IllegalArgumentException("Valor deve ser positivo");
        if (ong == null) throw new IllegalArgumentException("ONG inválida");
    }
}