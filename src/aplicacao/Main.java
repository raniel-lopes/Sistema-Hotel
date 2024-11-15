package aplicacao;

import modelo.*;
import servico.QuartoServico;
import servico.ReservaServico;
import servico.SistemaReserva;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Hospede> hospedes = new ArrayList<>();
    private static ArrayList<Reserva> reservas = new ArrayList<>();
    private static QuartoServico quartoServico = new QuartoServico();
    private static ReservaServico reservaServico = new ReservaServico();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarQuartos(); // Inicializa os quartos disponíveis

        // Aqui estamos usando List<Quarto> ao invés de ArrayList<Quarto>
        List<Quarto> quartos = quartoServico.getQuartos(); // Garantindo o tipo correto
        SistemaReserva sistemaReserva = new SistemaReserva(quartos, hospedes, reservas);

        int opcao;

        do {
            System.out.println("1. Cadastrar Hóspede");
            System.out.println("2. Listar Quartos");
            System.out.println("3. Fazer Reserva");
            System.out.println("4. Sair");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> cadastrarHospede();
                case 2 -> listarQuartos();
                case 3 -> fazerReserva(sistemaReserva);
                case 4 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }

        } while (opcao != 4);
    }

    private static void inicializarQuartos() {
        // Adiciona quartos ao sistema usando o QuartoServico
        quartoServico.adicionarQuarto(new Quarto(201, "Simples", true));
        quartoServico.adicionarQuarto(new Quarto(202, "Duplo", true));
        quartoServico.adicionarQuarto(new Quarto(203, "Luxo", true));
        System.out.println("Quartos iniciais cadastrados como disponíveis.");
    }

    private static void cadastrarHospede() {
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("CPF: ");
        String cpf = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();

        Hospede hospede = new Hospede(nome, cpf, telefone);
        hospedes.add(hospede); // Adiciona o hospede à lista
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    private static void listarQuartos() {
        quartoServico.listarQuartos();  // Usando o método do QuartoServico
    }

    private static void fazerReserva(SistemaReserva sistemaReserva) {
        listarQuartos();
        System.out.println("Número do quarto: ");
        int numeroQuarto = sc.nextInt();
        sc.nextLine(); // Limpar o buffer

        System.out.println("Nome do hóspede: ");
        String nomeHospede = sc.nextLine();

        System.out.println("Data de início (dd/MM/yyyy): ");
        String dataInicio = sc.nextLine();

        System.out.println("Data de término (dd/MM/yyyy): ");
        String dataFim = sc.nextLine();

        // Chama o serviço de reserva passando o sistema de reserva
        reservaServico.fazerReserva(numeroQuarto, nomeHospede, dataInicio, dataFim, sistemaReserva);
    }
}
