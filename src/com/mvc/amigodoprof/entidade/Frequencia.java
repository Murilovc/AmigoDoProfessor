package com.mvc.amigodoprof.entidade;

import javax.persistence.*;
@NamedQueries({
	@NamedQuery(name="Frequencia.todas", query="SELECT f FROM Frequencia f"),
	@NamedQuery(name="Frequencia.porAluno", query="SELECT f FROM Frequencia f WHERE f.aluno LIKE :aluno"),
	@NamedQuery(name="Frequencia.porAula", query="SELECT f FROM Frequencia f WHERE f.aula LIKE :aula")
})
@Entity
@Table(name="frequencias")
public class Frequencia {
	
	@GeneratedValue
	@Id
	@Column(name="id_frequencia")
	private long idFrequencia;
	
	@Column
	private boolean presente;
	
	@Column
	private String justificativa;
	
	@ManyToOne
	@JoinColumn(name="fk_aluno")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="fk_aula")
	private Aula aula;

	public long getIdFrequencia() {
		return idFrequencia;
	}

	public boolean isPresente() {
		return presente;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public Aula getAula() {
		return aula;
	}

	public void setIdFrequencia(long idFrequencia) {
		this.idFrequencia = idFrequencia;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public void setJustificativa(String anotacao) {
		this.justificativa = anotacao;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	
	
	
}
