package com.mvc.amigodoprof.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;

import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Frequencia;
import com.mvc.amigodoprof.model.table.TableModelFrequencia;

public class MenuFrequencia extends MenuBase{

	
	private TabelaDoProf tabela;
	
	private Aluno aluno;
	
	
	public MenuFrequencia(MenuBase menuPai, Aluno aluno) {
		super(ModoDeAcesso.RESTRITO, menuPai);
		super.configuracaoInicial(this);
		
		this.aluno = aluno;
		
		this.add(new JLabel("Existo"));
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}
	
	
	
	private void adicionarComponentes() {
		tabela = new TabelaDoProf();
	}
	
	private void carregarTabela(List<Frequencia> listaFrequencias) {
		TableModelFrequencia tmf = new TableModelFrequencia(listaFrequencias);
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				

				

			}
		}
	}
	
	@Override
	protected void buscarPor() {
		
		
	}

}
