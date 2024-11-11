package modelo;

public class Hospede extends Pessoa {
	private String telefone;
	
	public Hospede(String nome, String cpf, String telefone) {
		super(nome, cpf);
		this.telefone = telefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Telefone: " + telefone);
	}
}
