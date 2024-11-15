package servico;

import java.util.ArrayList;
import java.util.List;

import modelo.Hospede;

public class HospedeServico {
	private List<Hospede> hospedes;
	
	public  HospedeServico() {
		this.hospedes = new ArrayList<>();
	}
	
	public List<Hospede> getHospedes(){
		return hospedes;
	}
	
	public void adicionarHospedes(Hospede hospede) {
		hospedes.add(hospede);
		System.out.println("Hóspede cadastrado: " + hospede.getNome());
	}
	
	public Hospede buscarHospede(String nome) {
		return hospedes.stream().filter(h -> h.getNome().equals(nome)).findFirst().orElse(null);
	}
}
