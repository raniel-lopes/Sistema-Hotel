package aplicacao;

import modelo.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Main {
	private static ArrayList<Quarto> quartos = new ArrayList<>();
	private static ArrayList<Hospede> hospedes = new ArrayList<>();
	private static ArrayList<Reserva> reservas = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		inicializarQuartos();

		int opcao;

		do {
			System.out.println("1. Cadastrar Hóspede");
			System.out.println("2. Listar Quartos");
			System.out.println("3. Fazer Reserva");
			System.out.println("4. Sair");
			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {
			case 1 -> cadastrarHospede();
			case 2 -> listarQuartos();
			case 3 -> fazerReserva();
			case 4 -> System.out.println("Saindo...");
			default -> System.out.println("Opção inválida");

			}

		} while (opcao != 4);

	}

	private static void inicializarQuartos() {
		// Adiciona quartos ao sistema
		quartos.add(new Quarto(201, "Simples", true));
		quartos.add(new Quarto(202, "Duplo", true));
		quartos.add(new Quarto(203, "Luxo", true));
		System.out.println("Quartos inicias cadastrados e disponíveis.");
	}

	private static void cadastrarHospede() {
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("CPF: ");
		String cpf = sc.nextLine();
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();

		Hospede hospede = new Hospede(nome, cpf, telefone);
		hospedes.add(hospede);
		System.out.println("Hóspede cadastrado com sucesso!");
	}

	private static void listarQuartos() {
		// Laço for-each
		for (Quarto quarto : quartos) {
			quarto.exibirInfo();
		}

	}

	private static void fazerReserva() {
		listarQuartos();
		System.out.println("Número do quarto: ");
		int numeroQuarto = sc.nextInt();
		sc.nextLine(); // Garante que o buffer seja limpo para a entrada de texto

		Quarto quartoEscolhido = quartos.stream().filter(q -> q.getNumero() == numeroQuarto).findFirst().orElse(null);

		if (quartoEscolhido != null && quartoEscolhido.isDisponivel()) {
			System.out.println("Nome do hóspede: ");
			String nomeHospede = sc.nextLine();

			Hospede hospede = hospedes.stream().filter(h -> h.getNome().equals(nomeHospede)).findFirst().orElse(null);

			if (hospede != null) {
				// Cria uma nova reserva com as datas de início e término
				Reserva reserva = new Reserva(hospede, quartoEscolhido, new Date(), new Date());
				reservas.add(reserva);
				quartoEscolhido.setDisponivel(false); // Define o quarto como indisponivel
				System.out.println("Reserva realizada com sucesso!");

			} else {
				System.out.println("Hóspede não encontrado!");
			}
		} else {
			System.out.println("Quarto indisponível!");
		}

	}
}
