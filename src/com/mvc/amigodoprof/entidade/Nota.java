package com.mvc.amigodoprof.entidade;

import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Nota.todos", query="SELECT b FROM Nota b")
})

@Entity
@Table(name="notas")
public class Nota {
	
	@GeneratedValue
	@Id
	@Column(name="id_nota")
	private long idNota;
	
	@Column
	private double notaLancada;
	
	/*B1, B2, B3, B4 e Final*/
	@Column
	private String bimestre;
	
	@ManyToOne
	@JoinColumn(name="fk_aluno")
	private Aluno aluno;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="idResolucao", targetEntity=Resolucao.class)
	private List<Resolucao> listaResolucoes;

	public long getIdNota() {
		return idNota;
	}
	
	public List<Resolucao> getListaResolucoes() {
		return listaResolucoes;
	}

	public void setIdNota(long idNota) {
		this.idNota = idNota;
	}
	
	public void setListaAtividades(List<Resolucao> listaAtividades) {
		this.listaResolucoes = listaAtividades;
	}

	public double getNotaLancada() {
		return notaLancada;
	}

	public void setNotaLancada(double notaLancada) {
		this.notaLancada = notaLancada;
	}
	
}
