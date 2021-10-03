package com.mvc.amigodoprof.entidade;

import java.util.List;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Turma.todas", query="SELECT t FROM Turma t ORDER BY t.anoLetivo"),
	@NamedQuery(name="Turma.porPrefixo", query="SELECT t FROM Turma t WHERE t.prefixo LIKE :prefixo"),
	@NamedQuery(name="Turma.porValor", query="SELECT t FROM Turma t WHERE t.valor LIKE :valor"),
	@NamedQuery(name="Turma.porCodigo", query="SELECT t FROM Turma t WHERE t.codigo LIKE :codigo"),
	@NamedQuery(name="Turma.porAnoLetivo", query="SELECT t FROM Turma t WHERE t.anoLetivo LIKE :ano_letivo"),
	@NamedQuery(name="Turma.porTurno", query="SELECT t FROM Turma t WHERE t.turno LIKE :turno")

})


@Entity
@Table(name="turmas")
public class Turma {
	
	@GeneratedValue
	@Id
	@Column(name="id_turma")
	private long idTurma;
	
	/*Exemplo: ano, série e etc.*/
	@Column
	private String prefixo;
	
	/*Exemplo: 1º, 3º, 1ª*/
	@Column
	private String valor;
	
	/*Exemplo: A, C, D, f1te*/
	@Column
	private String codigo;
	
	@Column
	private String turno;
	
	/* Similar a uma descrição, porém com o 
	 * intuito de ser frequentemente atualizado*/
	@Column
	private String lembrete;
	
	@Column(name="ano_letivo")
	private String anoLetivo;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="idAluno", targetEntity=Aluno.class)
	private List<Aluno> listaAlunos;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="idAula", targetEntity=Aula.class)
	private List<Aula> listaAula;

	
	
	
	
	public long getIdTurma() {
		return idTurma;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public String getValor() {
		return valor;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getLembrete() {
		return lembrete;
	}

	public String getAnoLetivo() {
		return anoLetivo;
	}

	public List<Aluno> getListaAlunos() {
		return listaAlunos;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setLembrete(String lembrete) {
		this.lembrete = lembrete;
	}

	public void setAnoLetivo(String anoLetivo) {
		this.anoLetivo = anoLetivo;
	}

	public void setListaAlunos(List<Aluno> listaAlunos) {
		this.listaAlunos = listaAlunos;
	}

	public String getTurno() {
		return turno;
	}

	public List<Aula> getListaAula() {
		return listaAula;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public void setListaAula(List<Aula> listaAula) {
		this.listaAula = listaAula;
	}
	
	
	
	
	
}
