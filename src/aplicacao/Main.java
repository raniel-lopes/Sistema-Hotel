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

    public static void main(String[] args) {
        inicializarQuartos(); // Inicializa os quartos disponíveis

        // Usando List<Quarto> ao invés de ArrayList<Quarto>
        List<Quarto> quartos = quartoServico.getQuartos(); 
        SistemaReserva sistemaReserva = new SistemaReserva(quartos, hospedes, reservas);

        int opcao;

        do {
            exibirMenu();  // Criado método para exibir menu
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> cadastrarHospede();
                case 2 -> listarQuartos();
                case 3 -> fazerReserva(sistemaReserva);
                case 4  -> adicionarNovoQuarto();
                case 5 -> System.out.println("Saindo... Programa encerrado.");
                default -> System.out.println("Opção inválida");
            }

        } while (opcao != 5);
    }

    private static void exibirMenu() {
        System.out.println("\n*** Sistema de Reservas ***");
        System.out.println("1. Cadastrar Hóspede");
        System.out.println("2. Listar Quartos");
        System.out.println("3. Fazer Reserva");
        System.out.println("4. Adicionar novo Quarto");
        System.out.println("5. Sair");
    }

    private static void inicializarQuartos() {
        // Adiciona quartos ao sistema usando o QuartoServico
        quartoServico.adicionarQuarto(new Quarto(201, "Simples", true));
        quartoServico.adicionarQuarto(new Quarto(202, "Duplo", true));
        quartoServico.adicionarQuarto(new Quarto(203, "Luxo", true));
        System.out.println("Quartos iniciais cadastrados como disponíveis.");
    }
    
    private static void adicionarNovoQuarto() {
        // Lógica para adicionar um novo quarto
        System.out.println("Digite os dados do novo quarto:");

        System.out.print("Número do quarto: ");
        int numero = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        System.out.print("Tipo do quarto (Simples, Duplo, Luxo): ");
        String tipo = sc.nextLine();

        System.out.print("Está disponível (true/false): ");
        boolean disponivel = sc.nextBoolean();
        sc.nextLine(); // Limpar o buffer

        // Criação do novo quarto e adição à lista
        Quarto novoQuarto = new Quarto(numero, tipo, disponivel);
        quartoServico.adicionarQuarto(novoQuarto);
        
        System.out.println("Novo quarto adicionado com sucesso!");
    }


    private static void cadastrarHospede() {
        System.out.println("Digite os dados do hóspede:");

        System.out.print("Nome: ");
        String nome = sc.nextLine().trim();  // Usando trim() para garantir que não há espaços antes e depois do nome

        if (nome.isEmpty()) { // Verifica se o nome está vazio
            System.out.println("Erro: O nome não pode ser vazio!");
            return; // Não prossegue com o cadastro se o nome estiver vazio
        }

        // Verificação de duplicidade de nome
        for (Hospede h : hospedes) {
            if (h.getNome().equalsIgnoreCase(nome)) { // Compara sem distinguir maiúsculas e minúsculas
                System.out.println("Erro: Já existe um hóspede cadastrado com esse nome.");
                return; // Não prossegue com o cadastro se o nome já existir
            }
        }

        // Validação de CPF
        String cpf;
        while (true) {
            System.out.print("CPF (somente números): ");
            cpf = sc.nextLine();
            if (cpf.matches("\\d{11}")) {  // Verifica se o CPF tem 11 dígitos
                // Verificação de duplicidade de CPF
                boolean cpfDuplicado = false;
                for (Hospede h : hospedes) {
                    if (h.getCpf().equals(cpf)) { // Verifica se o CPF já está registrado
                        System.out.println("Erro: Já existe um hóspede cadastrado com esse CPF.");
                        cpfDuplicado = true;
                        break;
                    }
                }
                if (!cpfDuplicado) {
                    break; // CPF válido e não duplicado, então sai do loop
                }
            } else {
                System.out.println("CPF inválido! Tente novamente.");
            }
        }

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        // Agora podemos cadastrar o hóspede com segurança
        Hospede hospede = new Hospede(nome, cpf, telefone);
        hospedes.add(hospede); // Adiciona o hóspede à lista
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    private static void listarQuartos() {
        quartoServico.listarQuartos();  // Usando o método do QuartoServico
    }

    private static void fazerReserva(SistemaReserva sistemaReserva) {
        listarQuartos();
        
        // Verificação de entrada para o número do quarto
        int numeroQuarto;
        while (true) {
            System.out.print("Número do quarto: ");
            numeroQuarto = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            if (quartoServico.isQuartoDisponivel(numeroQuarto)) {
                break; // Se o quarto estiver disponível, sai do loop
            } else {
                System.out.println("Quarto indisponível, por favor escolha outro.");
            }
        }

        System.out.print("Nome do hóspede: ");
        String nomeHospede = sc.nextLine();

        // Verifica se o hóspede está cadastrado
        Hospede hospede = hospedes.stream()
                                   .filter(h -> h.getNome().equals(nomeHospede))
                                   .findFirst()
                                   .orElse(null);

        if (hospede == null) {
            System.out.println("Hóspede não encontrado! Faça o cadastro primeiro.");
            return;  // Retorna para o menu principal se o hóspede não for encontrado
        }

        System.out.print("Data de início (dd/MM/yyyy): ");
        String dataInicio = sc.nextLine();

        System.out.print("Data de término (dd/MM/yyyy): ");
        String dataFim = sc.nextLine();

        // Chama o serviço de reserva passando o sistema de reserva
        reservaServico.fazerReserva(numeroQuarto, nomeHospede, dataInicio, dataFim, sistemaReserva);
    }
}
