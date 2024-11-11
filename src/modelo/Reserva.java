package modelo;

import java.util.Date;

public class Reserva {
	private Hospede hospede;
	private Quarto quarto;
	private Date dataEntrada;
	private Date dataSaida;
	
	
	public Reserva(Hospede hospede, Quarto quarto, Date dataEntrada, Date dataSaida) {
		this.hospede = hospede;
		this.quarto = quarto;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		quarto.setDisponivel(false); //O quarto está reservado
	}
	
	public void cancelarReserva() {
		quarto.setDisponivel(true); //O quarto fica disponível
	}
	
	public void exibirInfo() {
		System.out.println("Reserva para o hóspede: " + hospede.getNome());
		System.out.println("Quarto Reservado: " + quarto.getNumero());
		System.out.println("Data de Entrada: " + dataEntrada);
		System.out.println("Data de Saída: " + dataSaida);
	}
}
