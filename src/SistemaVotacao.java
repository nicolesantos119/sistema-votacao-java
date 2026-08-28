import java.util.Scanner;

public class SistemaVotacao {

    static Scanner scanner = new Scanner(System.in);

    static final int MAX_CANDIDATOS = 5;
    static final int TOTAL_TURMAS = 3;
    static final int MAX_VOTANTES_POR_TURMA = 10;

    static int[] numerosCandidatos = new int[MAX_CANDIDATOS];
    static String[] nomesCandidatos = new String[MAX_CANDIDATOS];
    static int[] votosCandidatos = new int[MAX_CANDIDATOS];

    static int[][] votosPorTurma = new int[TOTAL_TURMAS][MAX_VOTANTES_POR_TURMA];
    static int[] quantidadeVotosTurma = new int[TOTAL_TURMAS];

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

            opcao = lerInteiro("Opção: ");

            switch (opcao) {

                case 1:

                    if (quantidadeCandidatos >= MAX_CANDIDATOS) {
                        System.out.println("Limite de candidatos atingido.");
                        break;
                    }

                    int numero;

                    do {
                        numero = lerInteiro("Número do candidato: ");

                        if (numero <= 0) {
                            System.out.println("O número deve ser maior que zero.");
                            continue;
                        }

                        if (buscarCandidato(numero) != -1) {
                            System.out.println("Esse número já está cadastrado.");
                            numero = -1;
                        }

                    } while (numero <= 0);

                    String nome;

                    do {
                        System.out.print("Nome do candidato: ");
                        nome = scanner.nextLine().trim();

                        if (nome.isEmpty()) {
                            System.out.println("O nome não pode ficar vazio.");
                        }

                    } while (nome.isEmpty());

                    numerosCandidatos[quantidadeCandidatos] = numero;
                    nomesCandidatos[quantidadeCandidatos] = nome;
                    votosCandidatos[quantidadeCandidatos] = 0;

                    quantidadeCandidatos++;

                    System.out.println("Candidato cadastrado com sucesso.");

                    break;

                case 2:
                    iniciarVotacao();
                    break;

                case 3:

                    if (quantidadeCandidatos == 0) {
                        System.out.println("Nenhum candidato cadastrado.");
                        break;
                    }

                    int totalVotos = 0;

                    for (int i = 0; i < quantidadeCandidatos; i++) {
                        totalVotos += votosCandidatos[i];
                    }

                    if (totalVotos == 0) {
                        System.out.println("Nenhum voto foi registrado.");
                        break;
                    }

                    System.out.println("\n===== RESULTADO DA VOTAÇÃO =====");

                    for (int i = 0; i < quantidadeCandidatos; i++) {

                        double percentual =
                                (votosCandidatos[i] * 100.0) / totalVotos;

                        System.out.printf(
                                "%s -> %d voto(s) (%.2f%%)%n",
                                nomesCandidatos[i],
                                votosCandidatos[i],
                                percentual
                        );
                    }

                    int maiorQuantidadeVotos = votosCandidatos[0];

                    for (int i = 1; i < quantidadeCandidatos; i++) {
                        if (votosCandidatos[i] > maiorQuantidadeVotos) {
                            maiorQuantidadeVotos = votosCandidatos[i];
                        }
                    }

                    int quantidadeVencedores = 0;

                    for (int i = 0; i < quantidadeCandidatos; i++) {
                        if (votosCandidatos[i] == maiorQuantidadeVotos) {
                            quantidadeVencedores++;
                        }
                    }

                    if (quantidadeVencedores == 1) {
                        System.out.print("Vencedor: ");
                    } else {
                        System.out.print("Empate entre: ");
                    }

                    boolean primeiroNome = true;

                    for (int i = 0; i < quantidadeCandidatos; i++) {

                        if (votosCandidatos[i] == maiorQuantidadeVotos) {

                            if (!primeiroNome) {
                                System.out.print(", ");
                            }

                            System.out.print(nomesCandidatos[i]);
                            primeiroNome = false;
                        }
                    }

                    System.out.println();

                    break;

                case 4:
                    exibirMatrizVotos();
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

    static void iniciarVotacao() {

        if (quantidadeCandidatos == 0) {
            System.out.println(
                    "Cadastre os candidatos antes de iniciar a votação."
            );
            return;
        }

        int turma;

        do {
            turma = lerInteiro("Informe a turma de 1 a 3: ");

            if (turma < 1 || turma > TOTAL_TURMAS) {
                System.out.println("Turma inválida.");
            }

        } while (turma < 1 || turma > TOTAL_TURMAS);

        int indiceTurma = turma - 1;

        if (quantidadeVotosTurma[indiceTurma] >= MAX_VOTANTES_POR_TURMA) {
            System.out.println("Essa turma já atingiu o limite de votantes.");
            return;
        }

        mostrarCandidatos();

        System.out.println("\nDigite 0 para encerrar a votação desta turma.");

        while (quantidadeVotosTurma[indiceTurma] < MAX_VOTANTES_POR_TURMA) {

            int numero = lerInteiro("\nNúmero do candidato: ");

            if (numero == 0) {
                System.out.println("Votação encerrada.");
                break;
            }

            int indiceCandidato = buscarCandidato(numero);

            if (indiceCandidato == -1) {
                System.out.println(
                        "Candidato inexistente. Tente novamente."
                );
                continue;
            }

            int posicaoVoto = quantidadeVotosTurma[indiceTurma];

            votosPorTurma[indiceTurma][posicaoVoto] = numero;
            quantidadeVotosTurma[indiceTurma]++;
            votosCandidatos[indiceCandidato]++;

            System.out.println("Voto registrado com sucesso.");
        }

        if (quantidadeVotosTurma[indiceTurma]
                == MAX_VOTANTES_POR_TURMA) {

            System.out.println("Limite de 10 votantes atingido.");
        }
    }

    static void mostrarCandidatos() {

        System.out.println("\nCandidatos disponíveis:");

        for (int i = 0; i < quantidadeCandidatos; i++) {
            System.out.println(
                    numerosCandidatos[i] + " - " + nomesCandidatos[i]
            );
        }
    }

    static void exibirMatrizVotos() {

        System.out.println("\n===== MATRIZ DE VOTOS =====");

        for (int i = 0; i < TOTAL_TURMAS; i++) {

            System.out.print("Turma " + (i + 1) + ": ");

            for (int j = 0; j < MAX_VOTANTES_POR_TURMA; j++) {

                if (j < quantidadeVotosTurma[i]) {
                    System.out.print(votosPorTurma[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }

            System.out.println();
        }
    }

    static int buscarCandidato(int numero) {

        for (int i = 0; i < quantidadeCandidatos; i++) {

            if (numerosCandidatos[i] == numero) {
                return i;
            }
        }

        return -1;
    }

    static int lerInteiro(String mensagem) {

        while (true) {

            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas um número inteiro.");
            }
        }
    }
}