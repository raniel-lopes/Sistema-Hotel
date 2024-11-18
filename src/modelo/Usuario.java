package modelo;

public abstract class Usuario {
	private String nome;
	private String cpf;
	
	//Construtor
	public Usuario(String nome, String cpf) {
		setNome(nome);
		setCpf(cpf);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if(nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Nome não pode ser vazio ou nulo.");
		}
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if(cpf == null || !cpf.matches("\\d{11}")) {
			throw new IllegalArgumentException("CPF inválido. Deve conter 11 números.");
		}
		this.cpf = cpf;
	}
	
	//Método para polimorfismo
	public void exibirInfo() {
		System.out.println("Nome: " + nome);
		System.out.println("CPF: " + cpf);
	}
	
}
