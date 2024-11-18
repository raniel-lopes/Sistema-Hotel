package modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	private Usuario usuario;
	private Quarto quarto;
	private Date dataEntrada;
	private Date dataSaida;
	
	
	public Reserva(Usuario usuario, Quarto quarto, Date dataEntrada, Date dataSaida) {
		this.usuario = usuario;
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

	    System.out.println("Reserva para o hóspede: " + usuario.getNome());
	    System.out.println("Quarto Reservado: " + quarto.getNumero());
	    System.out.println("Data de Entrada: " + sdf.format(dataEntrada)); // Formatando a data de entrada
	    System.out.println("Data de Saída: " + sdf.format(dataSaida));   // Formatando a data de saída
	    
	    if(usuario instanceof Hospede hospede) {
	    	System.out.println("Telefone do hóspede: " + hospede.getTelefone());
	    }
	}

	public Usuario getUsuario() {
		// TODO Auto-generated method stub
		return usuario;
	}

	public Quarto getQuarto() {
		// TODO Auto-generated method stub
		return quarto;
	}

	public Date getDataEntrada() {
		// TODO Auto-generated method stub
		return dataEntrada;
	}
	
	public Date getDataSaida() {
		return dataSaida;
	}
}
