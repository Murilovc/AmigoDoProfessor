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
	private String nome;
	
	@Column
	private int ordemNoAnoLetivo;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="idResolucao", targetEntity=Resolucao.class)
	private List<Resolucao> listaResolucoes;

	public long getIdBimestre() {
		return idNota;
	}

	public String getNome() {
		return nome;
	}

	public int getOrdemNoAnoLetivo() {
		return ordemNoAnoLetivo;
	}

	public List<Resolucao> getListaResolucoes() {
		return listaResolucoes;
	}

	public void setIdBimestre(long idNota) {
		this.idNota = idNota;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setOrdemNoAnoLetivo(int ordemNoAnoLetivo) {
		this.ordemNoAnoLetivo = ordemNoAnoLetivo;
	}

	public void setListaAtividades(List<Resolucao> listaAtividades) {
		this.listaResolucoes = listaAtividades;
	}
	
	
	
	
}
