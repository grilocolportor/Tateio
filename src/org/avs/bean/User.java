package org.avs.bean;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {
	
	private int _id;
	private String nome;
	private String email;
	private String telefone;
	private String senha;
	private Mensagens mensages;
	private Estados uf;
	private Cidade cidade;
	private int usuarioStatus; 
	
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Mensagens getMensages() {
		return mensages;
	}
	public void setMensages(Mensagens mensages) {
		this.mensages = mensages;
	}
	public Estados getUf() {
		return uf;
	}
	public void setUf(Estados uf) {
		this.uf = uf;
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	public int getUsuarioStatus() {
		return usuarioStatus;
	}
	public void setUsuarioStatus(int usuarioStatus) {
		this.usuarioStatus = usuarioStatus;
	}
}
