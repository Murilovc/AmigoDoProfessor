package com.mvc.amigodoprof.entidade;

import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Aluno.todos", query="SELECT a FROM Aluno a ORDER BY a.nome"),
	@NamedQuery(name="Aluno.porChamada", query="SELECT a FROM Aluno a WHERE a.numeroChamada LIKE :numero_chamada"),
	@NamedQuery(name="Aluno.porTurma", query="SELECT a FROM Aluno a WHERE a.turma LIKE :turma")
})
@Entity
@Table(name="alunos")
public class Aluno {
	
	@GeneratedValue
	@Id
	@Column(name="id_aluno")
	private long idAluno;
	
	//exemplo: cursando, reprovado, aprovado
	//private String status;
	
	@Column(name="numero_chamada")
	private int numeroChamada;
	
	@Column
	private String nome;
	
	/* Campo para anotações sobre o
	 * aluno pertinentes à ao desempenho
	 * escolar dele.
	 * */
	@Column
	private String anotacao;
	
	@ManyToOne
	@JoinColumn(name="fk_turma")
	private Turma turma;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="idResolucao", targetEntity=Resolucao.class)
	private List<Resolucao> listaResolucao;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="idFrequencia", targetEntity=Frequencia.class)
	private List<Frequencia> listaFrequencia;

	public long getIdAluno() {
		return idAluno;
	}

	public int getNumeroChamada() {
		return numeroChamada;
	}

	public String getNome() {
		return nome;
	}

	public String getAnotacao() {
		return anotacao;
	}

	public Turma getTurma() {
		return turma;
	}

	public List<Resolucao> getListaResolucao() {
		return listaResolucao;
	}

	public List<Frequencia> getListaFrequencia() {
		return listaFrequencia;
	}

	public void setIdAluno(long idAluno) {
		this.idAluno = idAluno;
	}

	public void setNumeroChamada(int numeroChamada) {
		this.numeroChamada = numeroChamada;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAnotacao(String anotacao) {
		this.anotacao = anotacao;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public void setListaResolucao(List<Resolucao> listaResolucao) {
		this.listaResolucao = listaResolucao;
	}

	public void setListaFrequencia(List<Frequencia> listaFrequencia) {
		this.listaFrequencia = listaFrequencia;
	}
	
	
}
