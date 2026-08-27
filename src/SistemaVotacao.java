import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;

    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    static int quantidadeCandidatos = 0;

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE VOTAÇÃO =====");
            System.out.println("1 - Cadastrar candidatos");
            System.out.println("2 - Iniciar votação");
            System.out.println("3 - Exibir resultado");
            System.out.println("4 - Exibir matriz de votos");
            System.out.println("5 - Sair");

            System.out.print("Opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    if (quantidadeCandidatos >= MAX_CANDIDATOS) {
                        System.out.println("Limite de candidatos atingido.");
                        break;
                    }

                    int i = quantidadeCandidatos;

                    System.out.print("Número do candidato: ");
                    int numero = scanner.nextInt();
                    scanner.nextLine();

                    String nome;

                    do {
                        System.out.print("Nome do candidato: ");
                        nome = scanner.nextLine().trim();

                        if (nome.isEmpty()) {
                            System.out.println("O nome não pode ficar vazio.");
                        }

                    } while (nome.isEmpty());

                    numerosCandidatos[i] = numero;
                    nomesCandidatos[i] = nome;
                    votosCandidatos[i] = 0;

                    quantidadeCandidatos++;

                    System.out.println("Candidato cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("Votação selecionada.");
                    break;

                case 3:
                    System.out.println("Resultado selecionado.");
                    break;

                case 4:
                    System.out.println("Matriz selecionada.");
                    break;

                case 5:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 5);

        scanner.close();
    }
}