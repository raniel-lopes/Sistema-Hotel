package servico;

import modelo.Hospede;
import modelo.Quarto;
import modelo.Reserva;
import java.util.List; // Usando List em vez de ArrayList

public class SistemaReserva {

    private List<Quarto> quartos;   // Alterando para List<Quarto>
    private List<Hospede> hospedes; // Alterando para List<Hospede>
    private List<Reserva> reservas; // Alterando para List<Reserva>

    public SistemaReserva(List<Quarto> quartos, List<Hospede> hospedes, List<Reserva> reservas) {
        this.quartos = quartos;
        this.hospedes = hospedes;
        this.reservas = reservas;
    }

    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    // Adiciona uma reserva se ela não existir
    public void adicionarReserva(Reserva reserva) {
        // Verifica se já existe uma reserva para o hóspede ou quarto
        if (buscarReservaPorHospede(reserva.getUsuario().getNome()) != null) {
            System.out.println("Já existe uma reserva para este hóspede.");
        } else if (buscarReservaPorQuarto(reserva.getQuarto().getNumero()) != null) {
            System.out.println("Este quarto já está reservado.");
        } else {
            reservas.add(reserva);
            System.out.println("Reserva adicionada com sucesso!");
        }
    }

    // Exibe todas as reservas
    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
        } else {
            System.out.println("\n*** Lista de Reservas ***");
            reservas.forEach(reserva -> {
                reserva.exibirInfo();
                System.out.println("------------------------------");
            });
        }
    }

    // Busca uma reserva pelo nome do hóspede
    public Reserva buscarReservaPorHospede(String nomeHospede) {
        return reservas.stream()
                       .filter(reserva -> reserva.getUsuario().getNome().equals(nomeHospede))
                       .findFirst()
                       .orElse(null);
    }

    // Busca uma reserva pelo número do quarto
    public Reserva buscarReservaPorQuarto(int numeroQuarto) {
        return reservas.stream()
                       .filter(reserva -> reserva.getQuarto().getNumero() == numeroQuarto)
                       .findFirst()
                       .orElse(null);
    }
}
