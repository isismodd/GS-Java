package br.com.fiap.main;

import br.com.fiap.bean.EmpresaParceira;
import br.com.fiap.bean.Ong;
import br.com.fiap.bean.ChamadoResgate;
import br.com.fiap.bean.Usuario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Ong> ongs = new ArrayList<>();
    private static ArrayList<EmpresaParceira> empresas = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<ChamadoResgate> chamados = new ArrayList<>();
    private static int nextOngId = 1;
    private static int nextEmpresaId = 1;
    private static int nextUsuarioId = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    cadastrarOng(scanner);
                    break;
                case 2:
                    cadastrarEmpresa(scanner);
                    break;
                case 3:
                    cadastrarUsuario(scanner);
                    break;
                case 4:
                    registrarChamado(scanner);
                    break;
                case 5:
                    realizarDoacao(scanner);
                    break;
                case 6:
                    listarOngs();
                    break;
                case 7:
                    listarEmpresas();
                    break;
                case 8:
                    listarUsuarios();
                    break;
                case 9:
                    listarChamados();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar ONG");
        System.out.println("2. Cadastrar Empresa Parceira");
        System.out.println("3. Cadastrar Usuário");
        System.out.println("4. Registrar Chamado de Resgate");
        System.out.println("5. Realizar Doação");
        System.out.println("6. Listar ONGs");
        System.out.println("7. Listar Empresas Parceiras");
        System.out.println("8. Listar Usuários");
        System.out.println("9. Listar Chamados");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarOng(Scanner scanner) {
        System.out.println("\n=== CADASTRO DE ONG ===");
        System.out.print("Informe o nome da ONG: ");
        String nome = scanner.nextLine();

        System.out.print("Informe a área de atendimento: ");
        String areaAtendimento = scanner.nextLine();

        Ong ong = new Ong(nextOngId++, nome, areaAtendimento);
        ongs.add(ong);
        System.out.println("ONG cadastrada com sucesso! ID: " + ong.getId());
    }

    private static void cadastrarEmpresa(Scanner scanner) {
        System.out.println("\n=== CADASTRO DE EMPRESA ===");
        System.out.print("Informe o nome da empresa: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o setor de atuação: ");
        String setorAtuacao = scanner.nextLine();

        System.out.print("Informe o valor da doação: ");
        double valorDoacao = scanner.nextDouble();
        scanner.nextLine();


        EmpresaParceira empresa = new EmpresaParceira(nextEmpresaId++, nome, setorAtuacao, valorDoacao);
        empresas.add(empresa);
        System.out.println("Empresa cadastrada com sucesso! ID: " + empresa.getId());
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("\n=== CADASTRO DE USUÁRIO ===");
        System.out.print("Informe o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Informe o CPF (formato 123.456.789-00): ");
        String cpf = scanner.nextLine();

        System.out.print("Informe o username: ");
        String username = scanner.nextLine();


        Usuario usuario = new Usuario(nextUsuarioId++, nome, cpf, username);
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso! ID: " + usuario.getId());
    }

    private static void registrarChamado(Scanner scanner) {
        if (ongs.isEmpty()) {
            System.out.println("Não há ONGs cadastradas. Cadastre uma ONG primeiro.");
            return;
        }

        System.out.println("\n=== REGISTRO DE CHAMADO ===");
        listarOngs();
        System.out.print("Informe o ID da ONG responsável: ");
        int idOng = scanner.nextInt();
        scanner.nextLine(); // Limpar buffer

        Ong ongSelecionada = null;
        for (Ong ong : ongs) {
            if (ong.getId() == idOng) {
                ongSelecionada = ong;
                break;
            }
        }

        if (ongSelecionada == null) {
            System.out.println("ONG não encontrada!");
            return;
        }

        System.out.print("Informe o tipo de resgate (Pessoa/Animal Doméstico/Animal Silvestre): ");
        String tipo = scanner.nextLine();

        System.out.print("Informe a situação (Ferido seriamente/Ferido levemente/Sem ferimento): ");
        String situacao = scanner.nextLine();

        System.out.print("Informe a região do resgate: ");
        String regiao = scanner.nextLine();

        ChamadoResgate chamado = new ChamadoResgate(ongSelecionada.getId(), ongSelecionada.getNome(), ongSelecionada.getAreaAtendimento());
        chamado.cadastrarChamado(tipo, situacao, regiao);
        chamados.add(chamado);
        System.out.println("Chamado registrado com sucesso! ID: " + chamado.getIdChamado());
    }

    private static void realizarDoacao(Scanner scanner) {
        if (usuarios.isEmpty() || ongs.isEmpty()) {
            System.out.println("É necessário ter usuários e ONGs cadastradas!");
            return;
        }

        System.out.println("\n=== REALIZAR DOAÇÃO ===");

        listarUsuarios();
        System.out.print("Informe o número do usuário: ");
        int indexUsuario = scanner.nextInt() - 1;
        scanner.nextLine();

        listarOngs();
        System.out.print("Informe o ID da ONG: ");
        int idOng = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Informe o valor da doação: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Deseja incluir uma mensagem? (opcional): ");
        String mensagem = scanner.nextLine();

        if (indexUsuario < 0 || indexUsuario >= usuarios.size()) {
            System.out.println("Usuário inválido!");
            return;
        }

        Ong ongSelecionada = null;
        for (Ong ong : ongs) {
            if (ong.getId() == idOng) {
                ongSelecionada = ong;
                break;
            }
        }

        if (ongSelecionada == null) {
            System.out.println("ONG não encontrada!");
            return;
        }

        try {
            if (mensagem.isEmpty()) {
                usuarios.get(indexUsuario).realizarDoacao(valor, ongSelecionada);
            } else {
                usuarios.get(indexUsuario).realizarDoacao(valor, ongSelecionada, mensagem);
            }
            System.out.println("Doação realizada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarOngs() {
        System.out.println("\n=== LISTA DE ONGs ===");
        if (ongs.isEmpty()) {
            System.out.println("Nenhuma ONG cadastrada.");
        } else {
            for (Ong ong : ongs) {
                ong.exibirInformacoes();
            }
        }
    }

    private static void listarEmpresas() {
        System.out.println("\n=== LISTA DE EMPRESAS ===");
        if (empresas.isEmpty()) {
            System.out.println("Nenhuma empresa cadastrada.");
        } else {
            for (EmpresaParceira empresa : empresas) {
                empresa.exibirInformacoes();
            }
        }
    }

    private static void listarUsuarios() {
        System.out.println("\n=== LISTA DE USUÁRIOS ===");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.printf("%d. %s (%s)\n", i+1, usuarios.get(i).getNome(), usuarios.get(i).getUsername());
            }
        }
    }

    private static void listarChamados() {
        System.out.println("\n=== LISTA DE CHAMADOS ===");
        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado registrado.");
        } else {
            for (ChamadoResgate chamado : chamados) {
                chamado.exibirInformacoes();
            }
        }
    }
}