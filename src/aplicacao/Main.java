package aplicacao;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.*;

public class Main {
	private static ArrayList<Quarto> quartos = new ArrayList<>();
	private static ArrayList<Hospede> hospedes = new ArrayList<>();
	private static ArrayList<Reserva> reservas = new ArrayList();
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
		// TODO Auto-generated method stub
	}

	private static void cadastrarHospede() {
		
	}

	private static void listarQuartos() {

	}

	private static void fazerReserva() {

	}
}
