package aplicacao;

import modelo.*;
import servico.QuartoServico;
import servico.ReservaServico;
import servico.SistemaReserva;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Hospede> hospedes = new ArrayList<>(); // Usando List< Hospede >
    private static List<Reserva> reservas = new ArrayList<>(); // Usando List<Reserva>
    private static QuartoServico quartoServico = new QuartoServico();
    private static ReservaServico reservaServico = new ReservaServico();
    private static Scanner sc = new Scanner(System.in);
    
    // Variáveis de autenticação
    private static boolean isAdministrador = false;

    public static void main(String[] args) {
        inicializarQuartos(); // Inicializa os quartos disponíveis
        List<Quarto> quartos = quartoServico.getQuartos(); 
        SistemaReserva sistemaReserva = new SistemaReserva(quartos, hospedes, reservas);

        int opcao;
        
        // Menu de login
        if (fazerLogin()) {
            do {
                exibirMenu();  // Exibe o menu dependendo do tipo de usuário
                opcao = sc.nextInt();
                sc.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1 -> cadastrarHospede();
                    case 2 -> listarQuartos();
                    case 3 -> fazerReserva(sistemaReserva);
                    case 4 -> adicionarNovoQuarto();
                    case 5 -> System.out.println("Saindo... Programa encerrado.");
                    default -> System.out.println("Opção inválida");
                }

            } while (opcao != 5);
        } else {
            System.out.println("Login falhou. Programa encerrado.");
        }
    }

    // Método de login
    private static boolean fazerLogin() {
        System.out.println("Escolha o tipo de usuário: ");
        System.out.println("1. Administrador");
        System.out.println("2. Hóspede");

        int opcao = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        if (opcao == 1) {
            // Logar como administrador
            System.out.print("Digite a senha do administrador: ");
            String senha = sc.nextLine();
            if (senha.equals("admin123")) {  // Senha fictícia do admin
                isAdministrador = true;
                System.out.println("Bem-vindo, Administrador!");
                return true;
            } else {
                System.out.println("Senha incorreta!");
                return false;
            }
        } else if (opcao == 2) {
            // Logar como hóspede
            System.out.println("Bem-vindo, Hóspede!");
            return true;
        } else {
            System.out.println("Opção inválida!");
            return false;
        }
    }

    // Método para exibir o menu com base no tipo de usuário
    private static void exibirMenu() {
        System.out.println("\n*** Sistema de Reservas ***");
        
        if (isAdministrador) {
            // Menu do Administrador
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Listar Quartos");
            System.out.println("3. Fazer Reserva");
            System.out.println("4. Adicionar novo Quarto");
            System.out.println("5. Sair");
        } else {
            // Menu do Hóspede
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Listar Quartos");
            System.out.println("3. Fazer Reserva");
            System.out.println("5. Sair");
        }
    }

    private static void inicializarQuartos() {
        quartoServico.adicionarQuarto(new Quarto(201, "Simples", true));
        quartoServico.adicionarQuarto(new Quarto(202, "Duplo", true));
        quartoServico.adicionarQuarto(new Quarto(203, "Luxo", true));
        System.out.println("Quartos iniciais cadastrados como disponíveis.");
    }

    private static void adicionarNovoQuarto() {
        if (isAdministrador) {  // Somente administrador pode adicionar quartos
            System.out.println("Digite os dados do novo quarto:");
            System.out.print("Número do quarto: ");
            int numero = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            // Verificar se já existe um quarto com o mesmo número
            for (Quarto quarto : quartoServico.getQuartos()) {
                if (quarto.getNumero() == numero) {
                    System.out.println("Erro: Já existe um quarto com esse número.");
                    return;  // Interrompe a execução se o número já existir
                }
            }

            System.out.print("Tipo do quarto (Simples, Duplo, Luxo): ");
            String tipo = sc.nextLine();

            System.out.print("Está disponível (true/false): ");
            boolean disponivel = sc.nextBoolean();
            sc.nextLine(); // Limpar o buffer

            // Criar e adicionar o novo quarto
            Quarto novoQuarto = new Quarto(numero, tipo, disponivel);
            quartoServico.adicionarQuarto(novoQuarto);

            System.out.println("Novo quarto adicionado com sucesso!");
        } else {
            System.out.println("Você não tem permissão para adicionar quartos.");
        }
    }


    private static void cadastrarHospede() {
        if (isAdministrador) {
            // Somente o administrador pode cadastrar novos hóspedes
            System.out.println("Digite os dados do hóspede:");
            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Erro: O nome não pode ser vazio!");
                return;
            }

            for (Hospede h : hospedes) {
                if (h.getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Erro: Já existe um hóspede cadastrado com esse nome.");
                    return;
                }
            }

            String cpf;
            while (true) {
                System.out.print("CPF (somente números): ");
                cpf = sc.nextLine();
                if (cpf.matches("\\d{11}")) {
                    boolean cpfDuplicado = false;
                    for (Hospede h : hospedes) {
                        if (h.getCpf().equals(cpf)) {
                            System.out.println("Erro: Já existe um hóspede cadastrado com esse CPF.");
                            cpfDuplicado = true;
                            break;
                        }
                    }
                    if (!cpfDuplicado) {
                        break;
                    }
                } else {
                    System.out.println("CPF inválido! Tente novamente.");
                }
            }

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            Hospede hospede = new Hospede(nome, cpf, telefone);
            hospedes.add(hospede);
            System.out.println("Hóspede cadastrado com sucesso!");
        } else {
            // Caso não seja administrador, o hóspede pode se cadastrar a si mesmo
            System.out.println("Digite os dados para o seu cadastro:");
            System.out.print("Nome: ");
            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Erro: O nome não pode ser vazio!");
                return;
            }

            // Verifica se o hóspede já está cadastrado pelo nome
            for (Hospede h : hospedes) {
                if (h.getNome().equalsIgnoreCase(nome)) {
                    System.out.println("Erro: Já existe um hóspede cadastrado com esse nome.");
                    return;
                }
            }

            // Lógica para o cadastro de CPF
            String cpf;
            while (true) {
                System.out.print("CPF (somente números): ");
                cpf = sc.nextLine();
                if (cpf.matches("\\d{11}")) {
                    boolean cpfDuplicado = false;
                    for (Hospede h : hospedes) {
                        if (h.getCpf().equals(cpf)) {
                            System.out.println("Erro: Já existe um hóspede cadastrado com esse CPF.");
                            cpfDuplicado = true;
                            break;
                        }
                    }
                    if (!cpfDuplicado) {
                        break;
                    }
                } else {
                    System.out.println("CPF inválido! Tente novamente.");
                }
            }

            System.out.print("Telefone: ");
            String telefone = sc.nextLine();

            // Cria e adiciona o hóspede à lista
            Hospede hospede = new Hospede(nome, cpf, telefone);
            hospedes.add(hospede);
            System.out.println("Cadastro realizado com sucesso!");
        }
    }


    private static void listarQuartos() {
        quartoServico.listarQuartos();
    }

    private static void fazerReserva(SistemaReserva sistemaReserva) {
        listarQuartos();

        int numeroQuarto;
        while (true) {
            System.out.print("Número do quarto: ");
            numeroQuarto = sc.nextInt();
            sc.nextLine();

            if (quartoServico.isQuartoDisponivel(numeroQuarto)) {
                break;
            } else {
                System.out.println("Quarto indisponível, por favor escolha outro.");
            }
        }

        System.out.print("Nome do hóspede: ");
        String nomeHospede = sc.nextLine();

        Hospede hospede = hospedes.stream()
                                   .filter(h -> h.getNome().equals(nomeHospede))
                                   .findFirst()
                                   .orElse(null);

        if (hospede == null) {
            System.out.println("Hóspede não encontrado! Faça o cadastro primeiro.");
            return;
        }

        System.out.print("Data de início (dd/MM/yyyy): ");
        String dataInicio = sc.nextLine();

        System.out.print("Data de término (dd/MM/yyyy): ");
        String dataFim = sc.nextLine();

        reservaServico.fazerReserva(numeroQuarto, nomeHospede, dataInicio, dataFim, sistemaReserva);
    }
}
