package com.mvc.amigodoprof.model.table;

import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.Alinhamento;


public class TableModelAula extends AbstractTableModel {

	List<Aula> listaAulas;
	List<JButton> listaBotoes;
	List<JButton> listaBotoesAtividades;
	
	private Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO,
			 					 		 Alinhamento.CENTRO,  Alinhamento.CENTRO, Alinhamento.CENTRO};
	
	public TableModelAula(List<Aula> aulas, List<JButton> listaBotoes,
			List<JButton> listaBotoesAtividades) {
		
		this.listaAulas = aulas;
		
		this.listaBotoes = listaBotoes;
		
		this.listaBotoesAtividades = listaBotoesAtividades;
	}
	
	@Override
	public int getRowCount() {
		return listaAulas.size();
	}

	@Override
	public int getColumnCount() {		
		return 5;	
	}
	
	@Override	
	public Object getValueAt(int indiceLinha, int indiceColuna) {		

		Aula aula;
		Object dado = null;
		
		aula = listaAulas.get(indiceLinha);
		
		switch(indiceColuna) {
			case 0: 
				dado = aula.getIdAula();
				break;
			case 1: 
				String dataString = aula.getData().toString();
				String dataDia = dataString.substring(8,10);
				String dataMes = dataString.substring(5,7);
				String dataAno = dataString.substring(0,4);
				dado = dataDia+"/"+dataMes+"/"+dataAno;
				break;
			case 2:
				dado = aula.getConteudo();
				break;
			case 3:
				JButton b = listaBotoes.get(indiceLinha);
				if(aula.isFrequenciaLancada()) {
					b.setText("Lançada");
				} else {
					b.setText("Pendente");
				}
				dado = b;
				break;
			case 4:
				/*Colocar o botao ver atividade aqui*/
				JButton c = listaBotoesAtividades.get(indiceLinha);
				dado = c;
				break;

		}
		return dado;		
	}

	@Override
	public String getColumnName(int indice_coluna) {

		String nome="";
		
		switch (indice_coluna) {
			case 0: 
				nome = "Id"; 
				break;
			case 1: 
				nome = "Data"; 
				break;		
			case 2: 
				nome = "Conteúdo"; 
				break;
			case 3: 
				nome = "Frequência"; 
				break;
			case 4:
				nome = "Atividades";
				break;

		}		
		return nome; 	
	}
	
	@Override
	public Class<?> getColumnClass(int indice_coluna) {
		
		Object obj;
		
		switch (indice_coluna) {
			case 0: 
				obj = Long.class; 
				break;
			case 1: 
				obj = String.class; 
				break;
			case 2: 
				obj = String.class; 
				break;
			case 3:
				obj = JButton.class;
				break;
			case 4:
				obj = JButton.class;
				break;
			default: 
				obj = null; 
				break;
		}		

		return obj.getClass();
	}
	
	public Alinhamento[] getAlinhamento() {
		return this.alinhamento;
	}
	
	public boolean isCellEditable(int linha, int coluna) {
		if(coluna == 3) {
			return true;
		}
		else
			return false;
	}

}
