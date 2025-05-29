package br.com.fiap.main;

import br.com.fiap.bean.EmpresaParceira;
import br.com.fiap.bean.Ong;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        // Cadastro de Empresas Parceiras
        JOptionPane.showMessageDialog(null, "Cadastro de Empresas Parceiras", "Sistema de Doações", JOptionPane.INFORMATION_MESSAGE);

        EmpresaParceira[] empresas = new EmpresaParceira[2];
        for (int i = 0; i < empresas.length; i++) {
            int id = i + 1;
            String nome = JOptionPane.showInputDialog("Digite o nome da empresa " + (i+1) + ":");
            String setor = JOptionPane.showInputDialog("Digite o setor de atuação da empresa " + nome + ":");
            double doacao = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da doação da empresa " + nome + " (R$):"));

            empresas[i] = new EmpresaParceira(id, nome, setor, doacao);
        }

        // Cadastro de ONGs
        JOptionPane.showMessageDialog(null, "Cadastro de ONGs", "Sistema de Doações", JOptionPane.INFORMATION_MESSAGE);

        Ong[] ongs = new Ong[2];
        for (int i = 0; i < ongs.length; i++) {
            int id = i + 1;
            String nome = JOptionPane.showInputDialog("Digite o nome da ONG " + (i+1) + ":");
            String regiao = JOptionPane.showInputDialog("Digite a região de atuação da ONG " + nome + ":");

            ongs[i] = new Ong(id, nome, regiao);
        }

        // Exibição dos dados cadastrados
        StringBuilder resultado = new StringBuilder();
        resultado.append("=== EMPRESAS PARCEIRAS CADASTRADAS ===\n");
        for (EmpresaParceira empresa : empresas) {
            resultado.append(String.format("ID: %d | Nome: %s | Setor: %s | Doação: R$%.2f\n",
                    empresa.getId(), empresa.getNome(), empresa.getSetorAtuacao(), empresa.getValorDoacao()));
        }

        resultado.append("\n=== ONGs CADASTRADAS ===\n");
        for (Ong ong : ongs) {
            resultado.append(String.format("ID: %d | Nome: %s | Região: %s\n",
                    ong.getId(), ong.getNome(), ong.getAreaAtendimento()));
        }

        // Exibição dos relatórios completos
        resultado.append("\n=== RELATÓRIOS DETALHADOS ===\n");
        for (EmpresaParceira empresa : empresas) {
            resultado.append("\n");
            empresa.exibirRelatorio();
        }

        for (Ong ong : ongs) {
            resultado.append("\n");
            ong.exibirRelatorioConsole(12); // Considerando 12 meses de atuação
        }

        JOptionPane.showMessageDialog(null, resultado.toString(), "Resumo dos Cadastros", JOptionPane.INFORMATION_MESSAGE);
    }
}
