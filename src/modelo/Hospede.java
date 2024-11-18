package modelo;

public class Hospede extends Usuario {
	private String telefone;
	
	public Hospede(String nome, String cpf, String telefone) {
		super(nome, cpf);
		setTelefone(telefone);
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		 if (telefone == null || telefone.trim().isEmpty()) {
	            throw new IllegalArgumentException("Telefone não pode ser vazio ou nulo.");
	        }
		this.telefone = telefone;
	}

	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Telefone: " + telefone);
	}
}
