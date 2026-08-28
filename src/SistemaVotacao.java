
import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 4;

    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    static int[][] matrizVotos = new int[TOTAL_TURMAS][MAX_CANDIDATOS];

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

                    if (quantidadeCandidatos == 0) {
                        System.out.println("Cadastre pelo menos um candidato primeiro.");
                        break;
                    }

                    System.out.println("\n===== INÍCIO DA VOTAÇÃO =====");

                    for (int turma = 0; turma < TOTAL_TURMAS; turma++) {

                        System.out.println("\nTurma " + (turma + 1));

                        mostrarCandidatos();

                        System.out.print("Digite o número do candidato votado: ");
                        int voto = scanner.nextInt();

                        boolean encontrado = false;

                        for (int j = 0; j < quantidadeCandidatos; j++) {

                            if (numerosCandidatos[j] == voto) {
                                votosCandidatos[j]++;
                                matrizVotos[turma][j]++;
                                encontrado = true;
                                break;
                            }
                        }

                        if (encontrado) {
                            System.out.println("Voto registrado!");
                        } else {
                            System.out.println("Candidato inválido. Voto não registrado.");
                        }
                    }

                    System.out.println("\nVotação encerrada!");

                    break;

                case 3:

                    if (quantidadeCandidatos == 0) {
                        System.out.println("Nenhum candidato cadastrado.");
                        break;
                    }

                    System.out.println("\n===== RESULTADO DA VOTAÇÃO =====");

                    for (int j = 0; j < quantidadeCandidatos; j++) {
                        System.out.println(
                                numerosCandidatos[j] + " - "
                                        + nomesCandidatos[j] + ": "
                                        + votosCandidatos[j] + " voto(s)"
                        );
                    }

                    break;

                case 4:

                    if (quantidadeCandidatos == 0) {
                        System.out.println("Nenhum candidato cadastrado.");
                        break;
                    }

                    System.out.println("\n===== MATRIZ DE VOTOS =====");

                    System.out.print("Turma\t");

                    for (int j = 0; j < quantidadeCandidatos; j++) {
                        System.out.print(numerosCandidatos[j] + "\t");
                    }

                    System.out.println();

                    for (int turma = 0; turma < TOTAL_TURMAS; turma++) {

                        System.out.print("Turma " + (turma + 1) + "\t");

                        for (int j = 0; j < quantidadeCandidatos; j++) {
                            System.out.print(matrizVotos[turma][j] + "\t");
                        }

                        System.out.println();
                    }

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

    static void mostrarCandidatos() {
        System.out.println("\nCandidatos disponíveis:");

        for (int i = 0; i < quantidadeCandidatos; i++) {
            System.out.println(
                    numerosCandidatos[i] + " - " + nomesCandidatos[i]
            );
        }
    }
}

