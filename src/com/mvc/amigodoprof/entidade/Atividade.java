package com.mvc.amigodoprof.entidade;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="atividades")
public class Atividade {
	
	@GeneratedValue
	@Id
	@Column(name="id_atividade")
	private long idAtividade;
	
	@Column(name="valor_maximo")
	private double valorMaximo;
	
	@Column
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="fk_aula")
	private Aula aula;
	
	//private Data dataEntrega;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="idResolucao", targetEntity=Resolucao.class)
	private List<Resolucao> resolucao;

	public long getIdAtividade() {
		return idAtividade;
	}

	public double getValorMaximo() {
		return valorMaximo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Aula getAula() {
		return aula;
	}

	public List<Resolucao> getResolucao() {
		return resolucao;
	}

	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public void setValorMaximo(double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public void setResolucao(List<Resolucao> resolucao) {
		this.resolucao = resolucao;
	}

}
