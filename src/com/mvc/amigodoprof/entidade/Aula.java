package com.mvc.amigodoprof.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Aula.todas", query="SELECT a FROM Aula a"),
	@NamedQuery(name="Aula.todasPorData", query="SELECT a FROM Aula a ORDER BY a.data")
})


@Entity
@Table(name="aulas")
public class Aula {

	@GeneratedValue
	@Id
	@Column(name="id_aula")
	private long idAula;
	
	@Column
	private Date data;
	
	@Column
	private String conteudo;
	
	@Column
	private int quantidadeConteudos;
	
	@Column
	private String planejamento;
		
	@OneToMany(fetch=FetchType.LAZY, mappedBy="idAtividade", targetEntity=Atividade.class)
	private List<Atividade> listaAtividades;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="idFrequencia", targetEntity=Frequencia.class)
	private List<Frequencia> listaFrequencia;

	
	/*
	 * Getters
	 */
	
	
	
	public long getIdAula() {
		return idAula;
	}

	public Date getData() {
		return data;
	}

	public String getConteudo() {
		return conteudo;
	}

	public int getQuantidadeConteudos() {
		return quantidadeConteudos;
	}

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public List<Frequencia> getListaFrequencia() {
		return listaFrequencia;
	}

	
	/*
	 * Setters
	 */
	
	
	
	public void setIdAula(long idAula) {
		this.idAula = idAula;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setQuantidadeConteudos(int quantidadeConteudos) {
		this.quantidadeConteudos = quantidadeConteudos;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public void setListaFrequencia(List<Frequencia> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;
	}
	
	
}
