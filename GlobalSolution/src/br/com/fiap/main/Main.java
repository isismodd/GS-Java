package br.com.fiap.main;

import br.com.fiap.bean.EmpresaParceira;
import br.com.fiap.bean.Ong;
import br.com.fiap.bean.ChamadoResgate;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Cadastro de ONG ===");
        Ong ong = cadastrarOng(scanner);
        
        System.out.println("\n=== Cadastro de Empresa Parceira ===");
        EmpresaParceira empresa = cadastrarEmpresaParceira(scanner);
        
        System.out.println("\n=== Cadastro de Chamado de Resgate ===");
        ChamadoResgate chamado = cadastrarChamadoResgate(scanner, ong);
        
        System.out.println("\n=== Relatório Completo ===");
        exibirInformacoesCompletas(ong, empresa, chamado);
        
        scanner.close();
    }
    
    private static Ong cadastrarOng(Scanner scanner) {
        System.out.print("Informe o ID da ONG: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Informe o nome da ONG: ");
        String nome = scanner.nextLine();
        
        System.out.print("Informe a área de atendimento: ");
        String areaAtendimento = scanner.nextLine();
        
        return new Ong(id, nome, areaAtendimento);
    }
    
    private static EmpresaParceira cadastrarEmpresaParceira(Scanner scanner) {
        System.out.print("Informe o ID da empresa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer
        
        System.out.print("Informe o nome da empresa: ");
        String nome = scanner.nextLine();
        
        System.out.print("Informe a área de atendimento: ");
        String areaAtendimento = scanner.nextLine();
        
        System.out.print("Informe o setor de atuação: ");
        String setorAtuacao = scanner.nextLine();
        
        System.out.print("Informe o valor da doação: ");
        double valorDoacao = scanner.nextDouble();
        scanner.nextLine(); // Limpar buffer
        
        return new EmpresaParceira(id, nome, areaAtendimento, setorAtuacao, valorDoacao);
    }
    
    private static ChamadoResgate cadastrarChamadoResgate(Scanner scanner, Ong ong) {
        System.out.println("Cadastrando chamado para a ONG: " + ong.getNome());
        
        System.out.print("Informe o tipo de resgate (Pessoa/Animal Doméstico/Animal Silvestre): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Informe a situação (Machucado seriamente/Machucado levemente/Intacto): ");
        String situacao = scanner.nextLine();
        
        System.out.print("Informe a região do resgate: ");
        String regiao = scanner.nextLine();
        
        ChamadoResgate chamado = new ChamadoResgate(ong.getId(), ong.getNome(), ong.getAreaAtendimento());
        chamado.cadastrarChamado(tipo, situacao, regiao);
        
        return chamado;
    }
    
    private static void exibirInformacoesCompletas(Ong ong, EmpresaParceira empresa, ChamadoResgate chamado) {
        System.out.println("\n--- Informações da ONG ---");
        ong.exibirInformacoes();
        
        System.out.println("\n--- Informações da Empresa Parceira ---");
        empresa.exibirInformacoes();
        
        System.out.println("\n--- Informações do Chamado ---");
        chamado.exibirInformacoes();
        
        // Demonstração de métodos específicos
        System.out.println("\n--- Detalhes do Chamado ---");
        System.out.println("Tempo estimado de resposta: " + chamado.calcularTempoResposta() + " horas");
        System.out.println("É alta prioridade? " + chamado.isAltaPrioridade());
        
        // Demonstração de atualização de situação (sobrecarga)
        System.out.println("\nAtualizando situação do chamado...");
        chamado.atualizarSituacao("Machucado levemente", "Animal está estável");
        chamado.exibirInformacoes();
    }
}
