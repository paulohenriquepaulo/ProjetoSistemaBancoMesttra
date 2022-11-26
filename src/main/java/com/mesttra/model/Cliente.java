package com.mesttra.model;

public abstract class Cliente {

	private Integer id;
	private String numeroConta;
	private String agencia;
	private String telefone;
	private float saldo;
	private float limiteChequeEspecial;
	private TipoConta tipoConta;

	private String senha;

	public Cliente(String numeroConta, String agencia, String telefone, float saldo, float limiteChequeEspecial, TipoConta tipoConta, String senha) {
		this.numeroConta = numeroConta;
		this.agencia = agencia;
		this.telefone = telefone;
		this.saldo = saldo;
		this.limiteChequeEspecial = limiteChequeEspecial;
		this.tipoConta = tipoConta;
		this.senha = senha;
	}

	protected Cliente() {
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public float getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(float limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
