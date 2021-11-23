package com.mvc.amigodoprof.entidade;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@NamedQueries({
	/*Esta será a única consulta que retornará todas as aulas
	 * independente de qual turma ela pertença. Ainda não está
	 * claro se essa query estará disponível na versão final do
	 * programa.*/
	@NamedQuery(name="Aula.todas", query="SELECT a FROM Aula a"),
	
	
	/*Todas a consultas abaixo retornam as aulas de acordo com a turma
	 * que elas pertencem*/
	@NamedQuery(name="Aula.porTurma",
	query="SELECT a FROM Aula a WHERE a.turma LIKE :turma ORDER BY a.data"),
	//@NamedQuery(name="Aula.todasPorData", query="SELECT a FROM Aula a ORDER BY a.data"),
	
	/*Para pesquisar por uma data específica*/
	@NamedQuery(name="Aula.porData", 
	query="SELECT a FROM Aula a RIGHT JOIN Turma b ON b.idTurma = a.turma WHERE a.data LIKE :data"),
	
	@NamedQuery(name="Aula.porFrequenciaLancada",
	query="SELECT a FROM Aula a WHERE a.frequenciaLancada LIKE 1"),
	@NamedQuery(name="Aula.porFrequenciaNaoLancada",
	query="SELECT a FROM Aula a WHERE a.frequenciaLancada LIKE 0")
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
	private String planejamento;
	
	@Column
	private boolean frequenciaLancada;
	
	@ManyToOne
	@JoinColumn(name="fk_turma")
	private Turma turma;
		
	@OneToMany(fetch=FetchType.LAZY, mappedBy="aula", targetEntity=Atividade.class)
	private List<Atividade> listaAtividades;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="aula", targetEntity=Frequencia.class)
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

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public List<Frequencia> getListaFrequencia() {
		return listaFrequencia;
	}

	
	public String getPlanejamento() {
		return planejamento;
	}
	
	/*
	 * Setters
	 */

	public void setPlanejamento(String planejamento) {
		this.planejamento = planejamento;
	}

	public void setIdAula(long idAula) {
		this.idAula = idAula;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public void setListaFrequencia(List<Frequencia> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;
	}

	public boolean isFrequenciaLancada() {
		return frequenciaLancada;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setFrequenciaLancada(boolean frequenciaLancada) {
		this.frequenciaLancada = frequenciaLancada;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}
	
	
}
