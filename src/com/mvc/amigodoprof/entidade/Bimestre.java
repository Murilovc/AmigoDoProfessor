package com.mvc.amigodoprof.entidade;

import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Bimestre.todos", query="SELECT b FROM Bimestre b")
})

@Entity
@Table(name="bimestres")
public class Bimestre {
	
	@GeneratedValue
	@Id
	@Column(name="id_bimestre")
	private long idBimestre;
	
	@Column
	private String nome;
	
	@Column
	private int ordemNoAnoLetivo;
	
	/*Total de pontos no bimestre*/
	@Column
	private int pontuacaoTotal;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="idAtividade", targetEntity=Atividade.class)
	private List<Atividade> listaAtividades;

	public long getIdBimestre() {
		return idBimestre;
	}

	public String getNome() {
		return nome;
	}

	public int getOrdemNoAnoLetivo() {
		return ordemNoAnoLetivo;
	}

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public void setIdBimestre(long idBimestre) {
		this.idBimestre = idBimestre;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setOrdemNoAnoLetivo(int ordemNoAnoLetivo) {
		this.ordemNoAnoLetivo = ordemNoAnoLetivo;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}
	
	
	
	
}
