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

    // Alterando para List<Quarto>, List<Hospede> e List<Reserva>
    public List<Quarto> getQuartos() {
        return quartos;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Não há reservas registradas.");
        } else {
            System.out.println("\n*** Lista de Reservas ***");
            for (Reserva reserva : reservas) {
                reserva.exibirInfo();
                System.out.println("------------------------------");
            }
        }
    }

    public Reserva buscarReservaPorHospede(String nomeHospede) {
        for (Reserva reserva : reservas) {
            if (reserva.getHospede().getNome().equals(nomeHospede)) {
                return reserva;
            }
        }
        return null;
    }

    public Reserva buscarReservaPorQuarto(int numeroQuarto) {
        for (Reserva reserva : reservas) {
            if (reserva.getQuarto().getNumero() == numeroQuarto) {
                return reserva;
            }
        }
        return null;
    }
}
