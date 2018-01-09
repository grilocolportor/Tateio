package org.avs.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cidade {

	private int id;
	private String nome;
	private int estadoId;
	private Estados estados;
	private Mensagens mensagens;
	
	public Cidade() {};
	
	public Cidade(int id, String nome, int estadoId, Estados estados, Mensagens mensagens) {
		super();
		this.id = id;
		this.nome = nome;
		this.estadoId = estadoId;
		this.estados = estados;
		this.mensagens = mensagens;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}
	public Estados getEstados() {
		return estados;
	}
	public void setEstados(Estados estados) {
		this.estados = estados;
	}
	public Mensagens getMensagens() {
		return mensagens;
	}
	public void setMensagens(Mensagens mensagens) {
		this.mensagens = mensagens;
	}
	
	
}
