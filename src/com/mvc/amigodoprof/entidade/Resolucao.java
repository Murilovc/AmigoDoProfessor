package com.mvc.amigodoprof.entidade;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Resolucao.todas", query="SELECT r FROM Resolucao r"),
	@NamedQuery(name="Resolucao.porAtividade", query="SELECT r FROM Resolucao r WHERE r.atividade LIKE :atividade"),
	@NamedQuery(name="Resolucao.porAluno", query="SELECT r FROM Resolucao r WHERE r.aluno LIKE :aluno")
})

@Entity
@Table(name="resolucoes")
public class Resolucao {
	
	@GeneratedValue
	@Id
	@Column(name="id_resolucao")
	private long idResolucao;
	
	@Column
	private boolean entregue;
	
	@Column(name="valor_alcancado")
	private double valorAlcancado;
	
	@ManyToOne
	@JoinColumn(name="fk_atividade")
	private Atividade atividade;
	
	@ManyToOne
	@JoinColumn(name="fk_aluno")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="fk_nota")
	private Nota nota;

	
	public boolean isEntregue() {
		return entregue;
	}
	
	public void setEntregue(boolean entregue) {
		this.entregue = entregue;
	}
	
	
	public long getIdResolucao() {
		return idResolucao;
	}

	public double getValorAlcancado() {
		return valorAlcancado;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setIdResolucao(long idResolucao) {
		this.idResolucao = idResolucao;
	}

	public void setValorAlcancado(double valorAlcancado) {
		this.valorAlcancado = valorAlcancado;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	
	
	
	
	
}
