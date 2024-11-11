package modelo;

public class Funcionario extends Pessoa {
	private String cargo;

	public Funcionario(String nome, String cpf, String cargo) {
		super(nome, cpf);
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	
	@Override
	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Cargo: " + cargo);
	}
	
	
}
