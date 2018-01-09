package org.avs.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Estados {

	private int id;
	private String nome;
	private String sigla;
	private List<Cidade> cidades;
	private Mensagens mensagens;
	
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Cidade> getCidades() {
		return cidades;
	}
	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	public Mensagens getMensagens() {
		return mensagens;
	}
	public void setMensagens(Mensagens mensagens) {
		this.mensagens = mensagens;
	}
	
}
