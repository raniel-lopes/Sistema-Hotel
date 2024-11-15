package servico;

import java.util.ArrayList;
import java.util.List;

import modelo.Quarto;

public class QuartoServico {
	private List<Quarto> quartos;

	public QuartoServico() {
		this.quartos = new ArrayList<>();
	}

	public List<Quarto> getQuartos() {
		return quartos;
	}

	public void adicionarQuarto(Quarto quarto) {
		quartos.add(quarto);
		System.out.println("Quarto adicionado: " + quarto.getNumero());
	}

	public void listarQuartos() {
		for (Quarto quarto : quartos) {
			quarto.exibirInfo();
		}
	}
	public Quarto buscarQuarto(int numero) {
		return quartos.stream().filter(q -> q.getNumero() == numero).findFirst().orElse(null);
	}
	
	public boolean isQuartoDisponivel(int numero) {
		Quarto quarto = buscarQuarto(numero);
		return quarto != null && quarto.isDisponivel();
		
	}
}
