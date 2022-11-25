package com.mesttra.model;

public class ClientePf extends Cliente {
	
	private String cpf;
	private String nome;
	private int idade;
	
	public ClientePf(String numeroConta, String agencia, String telefone, Float saldo, Float limiteChequeEspecial,
			String cpf, String nome, int idade, TipoConta tipoConta) {
		super(numeroConta, agencia, telefone, saldo, limiteChequeEspecial, tipoConta);
		
		this.cpf = cpf;
		this.nome = nome;
		this.idade = idade;
		
	}

	public ClientePf() {
	}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	
}
