package servico;

import modelo.Quarto;
import modelo.Hospede;
import modelo.Reserva;

import java.util.ArrayList;
import java.util.Date;

public class SistemaReserva {
    private ArrayList<Quarto> quartos;
    private ArrayList<Hospede> hospedes;
    private ArrayList<Reserva> reservas;

    public SistemaReserva(ArrayList<Quarto> quartos, ArrayList<Hospede> hospedes, ArrayList<Reserva> reservas) {
        this.quartos = quartos;
        this.hospedes = hospedes;
        this.reservas = reservas;
    }

    public ArrayList<Quarto> getQuartos() {
        return quartos;
    }

    public ArrayList<Hospede> getHospedes() {
        return hospedes;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}
