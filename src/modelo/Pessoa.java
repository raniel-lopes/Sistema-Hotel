package modelo;

public class Pessoa {
	private String nome;
	private String cpf;
	
	//Construtor
	public Pessoa(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	//Método para polimorfismo
	public void exibirInfo() {
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
	}
	
}
