package app;

import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;

        do {
            System.out.println("======================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("======================================");

            System.out.print("Escolha a opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do teclado

            switch (opcao) {
                case 1:
                    incluirPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 2:
                    alterarPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 3:
                    excluirPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 4:
                    buscarPorId(scanner, repoFisica, repoJuridica);
                    break;
                case 5:
                    exibirTodos(scanner, repoFisica, repoJuridica);
                    break;
                case 6:
                    persistirDados(scanner, repoFisica, repoJuridica);
                    break;
                case 7:
                    recuperarDados(scanner, repoFisica, repoJuridica);
                    break;
                case 0:
                    System.out.println("Finalizando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);
    }

    private static void incluirPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        System.out.println("Insira os dados ...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (tipo == 'F') {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer do teclado

            PessoaFisica pessoaFisica = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pessoaFisica);
            System.out.println("Pessoa Fisica adicionada com sucesso.");
        } else if (tipo == 'J') {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();

            PessoaJuridica pessoaJuridica = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pessoaJuridica);
            System.out.println("Pessoa Juridica adicionada com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterarPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        if (tipo == 'F') {
            PessoaFisica atual = repoFisica.obter(id);
            if (atual != null) {
                System.out.println("Dados atuais:");
                atual.exibir();

                System.out.println("Insira os novos dados ...");
                System.out.print("Nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("CPF: ");
                String novoCpf = scanner.nextLine();
                System.out.print("Idade: ");
                int novaIdade = scanner.nextInt();
                scanner.nextLine();  // Limpar o buffer do teclado

                atual.setNome(novoNome);
                ((PessoaFisica) atual).setCpf(novoCpf);
                ((PessoaFisica) atual).setIdade(novaIdade);

                System.out.println("Pessoa Fisica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Fisica não encontrada com o id informado.");
            }
        } else if (tipo == 'J') {
            PessoaJuridica atual = repoJuridica.obter(id);
            if (atual != null) {
                System.out.println("Dados atuais:");
                atual.exibir();

                System.out.println("Insira os novos dados ...");
                System.out.print("Nome: ");
                String novoNome = scanner.nextLine();
                System.out.print("CNPJ: ");
                String novoCnpj = scanner.nextLine();

                atual.setNome(novoNome);
                ((PessoaJuridica) atual).setCnpj(novoCnpj);

                System.out.println("Pessoa Juridica alterada com sucesso.");
            } else {
                System.out.println("Pessoa Juridica não encontrada com o id informado.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluirPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        if (tipo == 'F') {
            repoFisica.excluir(id);
            System.out.println("Pessoa Fisica excluída com sucesso.");
        } else if (tipo == 'J') {
            repoJuridica.excluir(id);
            System.out.println("Pessoa Juridica excluída com sucesso.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void buscarPorId(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Limpar o buffer do teclado

        if (tipo == 'F') {
            PessoaFisica pessoaFisica = repoFisica.obter(id);
            if (pessoaFisica != null) {
                pessoaFisica.exibir();
            } else {
                System.out.println("Pessoa Fisica não encontrada com o id informado.");
            }
        } else if (tipo == 'J') {
            PessoaJuridica pessoaJuridica = repoJuridica.obter(id);
            if (pessoaJuridica != null) {
                pessoaJuridica.exibir();
            } else {
                System.out.println("Pessoa Juridica não encontrada com o id informado.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void exibirTodos(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        char tipo = scanner.nextLine().toUpperCase().charAt(0);

        System.out.println("Dados de todas as entidades:");

        if (tipo == 'F') {
            for (PessoaFisica pessoa : repoFisica.obterTodos()) {
                pessoa.exibir();
            }
        } else if (tipo == 'J') {
            for (PessoaJuridica pessoa : repoJuridica.obterTodos()) {
                pessoa.exibir();
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void persistirDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.persistir(prefixo + ".fisica.bin");
            repoJuridica.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados persistidos com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao persistir os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo dos arquivos: ");
        String prefixo = scanner.nextLine();

        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}
