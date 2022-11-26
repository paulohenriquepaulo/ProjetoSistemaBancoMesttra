package com.mesttra.model;

public class ClientePj extends Cliente {
	
	private String cnpj;
	private String[] nomeSocios = new String[3];
	private String razaoSocial;
	private String nomeFantasia;

	public ClientePj() {

	}
	public ClientePj(String numeroConta, String agencia, String telefone, float saldo, float limiteChequeEspecial, TipoConta tipoConta, String  senha) {
		super(numeroConta, agencia, telefone, saldo, limiteChequeEspecial, tipoConta, senha);
	}


	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String[] getNomeSocios() {
		return nomeSocios;
	}

	public void setNomeSocios(String[] nomeSocios) {
		this.nomeSocios = nomeSocios;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
}
