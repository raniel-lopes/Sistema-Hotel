package modelo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
	    // Criando o objeto SimpleDateFormat para definir o formato da data
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    System.out.println("Reserva para o hóspede: " + hospede.getNome());
	    System.out.println("Quarto Reservado: " + quarto.getNumero());
	    System.out.println("Data de Entrada: " + sdf.format(dataEntrada)); // Formatando a data de entrada
	    System.out.println("Data de Saída: " + sdf.format(dataSaida));   // Formatando a data de saída
	}

	public Pessoa getHospede() {
		// TODO Auto-generated method stub
		return hospede;
	}

	public Quarto getQuarto() {
		// TODO Auto-generated method stub
		return quarto;
	}

	public LocalDate getDataInicio() {
		// TODO Auto-generated method stub
		return null;
	}
}
