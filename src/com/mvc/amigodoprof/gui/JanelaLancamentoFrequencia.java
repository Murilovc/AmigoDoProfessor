package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.mvc.amigodoprof.controle.ControleAula;
import com.mvc.amigodoprof.entidade.Aluno;
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.MenuTurma.AcaoLancarNaTabela;
import com.mvc.amigodoprof.model.column.ColumnModelParaLancamento;
import com.mvc.amigodoprof.model.table.TableModelLancamentoFrequencia;


public class JanelaLancamentoFrequencia extends JDialog {
	
	private Aula aula;
	private List<Aluno> listaAlunos;
	private MenuTurma mt;
	
	private TabelaDoProf tabela;
	
	String data;	
	
	List<JRadioButton> listaBotoesPresente;
	List<JRadioButton> listaBotoesFalta;
	List<JRadioButton> listaBotoesJustificado;
	
	
	public JanelaLancamentoFrequencia(MenuTurma mt, Aula aula, List<Aluno> listaAlunos) {
		super(mt, "Lançamento de frequência", ModalityType.APPLICATION_MODAL);
		
		this.mt = mt;
		
		this.aula = aula;
		this.listaAlunos = listaAlunos;
		
		String dataString = aula.getData().toString();
		String dataDia = dataString.substring(8,10);
		String dataMes = dataString.substring(5,7);
		String dataAno = dataString.substring(0,4);
		data = dataDia+"/"+dataMes+"/"+dataAno;	
		
		configurarJanela();
		adicionarComponentes();
	}
	
	private void configurarJanela() {
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(800,600));
		
		this.setLayout(new BorderLayout());
		this.pack();
		
	}
	
	private void adicionarComponentes() {
		
		JButton botaoLancar = new JButton(new AcaoLancarFrequencia());
		botaoLancar = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoLancar, "Arial", 14);
		JButton botaoCancelar = new JButton(new AcaoCancelar());
		botaoCancelar = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoCancelar, "Arial", 14);
		
		JPanel painelInferior = UtilidadesGUI.
				criarJPanelSemBorda(null, new BorderLayout(), UtilidadesGUI.getCorTema1());;
		painelInferior.add(botaoLancar, BorderLayout.EAST);
		painelInferior.add(botaoCancelar, BorderLayout.WEST);
		
		tabela = new TabelaDoProf();
		
		criarTabela();
		
		JScrollPane scroll = new JScrollPane(tabela);
		
	
		JLabel label = new JLabel(data);
		label.setFont(new Font("Arial", Font.BOLD, 14));
		
		this.add(label, BorderLayout.NORTH);
		this.add(painelInferior, BorderLayout.SOUTH);
		this.add(scroll, BorderLayout.CENTER);
	}
	
	private void criarTabela() {
		
		listaBotoesPresente = new ArrayList<JRadioButton>();
		listaBotoesFalta = new ArrayList<JRadioButton>();
		listaBotoesJustificado = new ArrayList<JRadioButton>();
		
		for(int i = 0; i < listaAlunos.size(); i++) {
			
			listaBotoesPresente.add(new JRadioButton());
			listaBotoesFalta.add(new JRadioButton());
			listaBotoesJustificado.add(new JRadioButton());
			
		}
		
		TableModelLancamentoFrequencia tmt = new TableModelLancamentoFrequencia(listaAlunos, 
				listaBotoesPresente, listaBotoesFalta, listaBotoesJustificado);
		

		ColumnModelParaLancamento ca = new ColumnModelParaLancamento(tmt.getAlinhamento(),
				tmt.getAlinhamento().length, 20, new Color(122, 255, 135), tmt, this.getWidth());
		
		
		
		tabela.setEnabled(true);
		tabela.setModel(tmt);
		tabela.setColumnModel(ca);
		tabela.addMouseListener(new HabilitarEdicaoExclusao());
	}
	
	protected class HabilitarEdicaoExclusao extends MouseAdapter{
		
		public void mousePressed(MouseEvent e) {
			if (tabela.getSelectedRow() >= 0) {
				
				int coluna = tabela.getSelectedColumn();
				int linha = tabela.getSelectedRow();
				
				if(coluna == 2) {
					listaBotoesPresente.get(linha).setSelected(true);
					listaBotoesFalta.get(linha).setSelected(false);
					listaBotoesJustificado.get(linha).setSelected(false);
				} 
				else if(coluna == 3){
					listaBotoesPresente.get(linha).setSelected(false);
					listaBotoesFalta.get(linha).setSelected(true);
					listaBotoesJustificado.get(linha).setSelected(false);
				} else if(coluna == 4){
					listaBotoesPresente.get(linha).setSelected(false);
					listaBotoesFalta.get(linha).setSelected(false);
					listaBotoesJustificado.get(linha).setSelected(true);
				}
			}
		}
	}
	
	
	private class AcaoLancarFrequencia extends AbstractAction {

		public AcaoLancarFrequencia() {
			super("Lançar");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Lançar a frequência para esta aula");
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			JanelaLancamentoFrequencia.this.dispose();
			
			for(int i = 0; i < listaAlunos.size(); i++) {
				//pegar os valores das listas para lançar a frequência
			}
		}
		
	}
	
	private class AcaoCancelar extends AbstractAction {

		public AcaoCancelar() {
			super("Cancelar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			JanelaLancamentoFrequencia.this.dispose();
	
		}
	}
}
