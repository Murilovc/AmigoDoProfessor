package com.mvc.amigodoprof.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mvc.amigodoprof.controle.ControleTurma;
import com.mvc.amigodoprof.entidade.Turma;
import com.mvc.amigodoprof.tablemodel.CellRendererDoProf;
import com.mvc.amigodoprof.tablemodel.CellRendererDoProf.Alinhamento;
import com.mvc.amigodoprof.tablemodel.ColumnModelDoProf;
import com.mvc.amigodoprof.tablemodel.TableModelTurma;

public class MenuPrincipal extends MenuBase {
	
	public MenuPrincipal() {
		
		/* O modo de acesso não importa,
		 * já que não se aplica ao MenuPrincipal*/
		super(ModoDeAcesso.GERAL, null);
		super.configuracaoInicial(this);
		
		/* Como este é o primeiro menu, precisa
		 * explicitamente ser tornado visível,
		 * já aque a o método da superclasse 
		 * por padrão torna os menus invisíveis.
		 * 
		 * NOTA: Se nenhum menu estiver visível,
		 * a JVM encerra o programa
		 * */
		this.setVisible(true);
		
		this.adicionarComponentes();
		
		//MenuTurma mt = new MenuTurma(this);
	}
	
	private void adicionarComponentes() {
		
		JButton botaoVerTurma = new JButton(new AcaoVerTurma());
		botaoVerTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoVerTurma, "Arial", 14);
		
		JButton botaoNovaTurma = new JButton(new AcaoNovaTurma());
		botaoNovaTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoNovaTurma, "Arial", 14);
		
		JButton botaoEditarTurma = new JButton(new AcaoEditarTurma());
		botaoEditarTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoEditarTurma, "Arial", 14);
		
		JButton botaoApagarTurma = new JButton(new AcaoApagarTurma());
		botaoApagarTurma = UtilidadesGUI.
				estilizarBotaoComBordaPadrao(botaoApagarTurma, "Arial", 14);
		
		JPanel painelLateralEsquerdo = new JPanel(new FlowLayout());
		painelLateralEsquerdo.setPreferredSize(new Dimension(150, 680));
		
		painelLateralEsquerdo.add(botaoVerTurma);
		painelLateralEsquerdo.add(botaoNovaTurma);
		painelLateralEsquerdo.add(botaoEditarTurma);
		painelLateralEsquerdo.add(botaoApagarTurma);
		
		

		
		JButton botaoMenuAtividades = new JButton(new AcaoAbrirMenuAtividades());
		botaoMenuAtividades = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuAtividades,
				"Arial", 14);
		
		JButton botaoMenuAlunos = new JButton(new AcaoAbrirMenuAlunos());
		botaoMenuAlunos = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuAlunos, "Arial", 14);
		
		JButton botaoMenuFrequencias = new JButton(new AcaoAbrirMenuFrequencias());
		botaoMenuFrequencias = UtilidadesGUI.estilizarBotaoComBordaPadrao(botaoMenuFrequencias,
				"Arial", 14);
		
		
		JPanel painelLateralDireito = new JPanel(new FlowLayout());
		painelLateralDireito.setPreferredSize(new Dimension(150,680));
		
		painelLateralDireito.add(botaoMenuAtividades);
		painelLateralDireito.add(botaoMenuAlunos);
		painelLateralDireito.add(botaoMenuFrequencias);
		
		
		List<Turma> listaTurmas = ControleTurma.pegarTodasAsTurmas();
		
		TableModelTurma tmt = new TableModelTurma(listaTurmas);
		
		CellRendererDoProf.Alinhamento[] alinhamento = {Alinhamento.ESQUERDA,Alinhamento.CENTRO,
				Alinhamento.CENTRO,Alinhamento.CENTRO, Alinhamento.CENTRO, Alinhamento.CENTRO};
		ColumnModelDoProf cm = new ColumnModelDoProf(alinhamento, alinhamento.length,
				20, new Color(122, 255, 135), tmt);
		
		TabelaDoProf tabela = new TabelaDoProf();
		tabela.setModel(tmt);
		tabela.setColumnModel(cm);
		
		JScrollPane jcp = new JScrollPane(tabela);
		


		this.add(painelLateralDireito, BorderLayout.EAST);
		this.add(painelLateralEsquerdo, BorderLayout.WEST);
		this.add(jcp, BorderLayout.CENTER);
	}
	
	
	
	
	
	protected class AcaoVerTurma extends AbstractAction {

		public AcaoVerTurma() {
			super("Ver turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_T);
			putValue(SHORT_DESCRIPTION, "Visualizar turma selecionada");
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JanelaCadastroAluno ca = new JanelaCadastroAluno(MenuPrincipal.this);
		}
		
	}
	
	protected class AcaoNovaTurma extends AbstractAction {

		
		public AcaoNovaTurma() {
			super("Criar nova turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Criar uma nova turma");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	protected class AcaoEditarTurma extends AbstractAction {

		
		public AcaoEditarTurma() {
			super("Editar turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(SHORT_DESCRIPTION, "Editar turma selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaGerenciarTurma jgt = new JanelaGerenciarTurma();
			
		}
		
	}
	
	protected class AcaoApagarTurma extends AbstractAction {

		
		public AcaoApagarTurma() {
			super("Apagar turma");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
			putValue(SHORT_DESCRIPTION, "Apaga a turma selecionada");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//JanelaGerenciarTurma jgt = new JanelaGerenciarTurma();
			
		}
		
	}
	
	protected class AcaoAbrirMenuAtividades extends AbstractAction {
		
		public AcaoAbrirMenuAtividades() {
			super("Atividades");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	protected class AcaoAbrirMenuAlunos extends AbstractAction {
		
		public AcaoAbrirMenuAlunos() {
			super("Alunos");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
	
	protected class AcaoAbrirMenuFrequencias extends AbstractAction {
		
		public AcaoAbrirMenuFrequencias() {
			super("Frequências");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}
