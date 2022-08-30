package com.mvc.amigodoprof.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

<<<<<<< Updated upstream
import com.mvc.amigodoprof.controle.ControleAtividade;
=======
import com.mvc.amigodoprof.controle.ControleAluno;
import com.mvc.amigodoprof.controle.ControleAtividade;
import com.mvc.amigodoprof.entidade.Atividade;
>>>>>>> Stashed changes
import com.mvc.amigodoprof.entidade.Aula;
import com.mvc.amigodoprof.gui.menu.MenuAtividade;
import com.mvc.amigodoprof.gui.menu.MenuBase;

public class JanelaCadastroAtividade extends JDialog{
	
	
	private JTextField tf1;
	private JTextField tf2;
	private JComboBox<String> cbBimestre;
	private JTextArea tadescricao;
	
	private Aula aula;
<<<<<<< Updated upstream
	
	JFrame pai;
	
	private String caminhoArquivo;
=======
	private String nomeArquivo;
	private MenuAtividade pai;
	private Atividade atividade;
>>>>>>> Stashed changes

	public JanelaCadastroAtividade(MenuAtividade pai, Aula aula, ModoDeAcesso modo, Atividade atividade) {
		super(pai, ModalityType.APPLICATION_MODAL);
		this.pai = pai;
		this.aula = aula;
		
		this.pai = pai;
		
		this.setTitle("Cadastro / Edição de Atividade");
		this.setSize(new Dimension(428, 291));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 412, 256);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lbl1 = new JLabel("Valor: ");
		lbl1.setBounds(10, 11, 46, 14);
		panel.add(lbl1);
		
		tf1 = new JTextField();
		tf1.setBounds(10, 36, 392, 20);
		panel.add(tf1);
		tf1.setColumns(10);
		
		JLabel lbl2 = new JLabel("Aula: ");
		lbl2.setBounds(10, 67, 46, 14);
		panel.add(lbl2);
		
		tf2 = new JTextField(aula.toString());
		tf2.setBounds(10, 92, 197, 20);
		panel.add(tf2);
		tf2.setColumns(10);
		tf2.setEditable(false);
		
		JButton btnEscolherArquivo = new JButton(new AcaoEscolherArquivo());
		btnEscolherArquivo.setBounds(144, 221, 124, 23);
		panel.add(btnEscolherArquivo);
		
		JButton btnCancelar = new JButton(new AcaoCancelar());
		btnCancelar.setBounds(278, 221, 124, 23);
		panel.add(btnCancelar);
		
		JButton btnSalvar = new JButton(new AcaoSalvar());
		btnSalvar.setBounds(10, 221, 124, 23);
		panel.add(btnSalvar);
		
		JLabel lblBimestre = new JLabel("Bimestre: ");
		lblBimestre.setBounds(236, 67, 58, 14);
		panel.add(lblBimestre);
		
		cbBimestre = new JComboBox<String>();
		cbBimestre.addItem("1");
		cbBimestre.addItem("2");
		cbBimestre.addItem("3");
		cbBimestre.addItem("4");
		cbBimestre.addItem("5");
		cbBimestre.setBounds(236, 91, 58, 22);
		panel.add(cbBimestre);
		
		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 123, 80, 14);
		panel.add(lblDescricao);
		
		tadescricao = new JTextArea();
		JScrollPane scroll = new JScrollPane(tadescricao);
		scroll.setBounds(10, 148, 392, 62);
		panel.add(scroll);
		
		if(modo == ModoDeAcesso.CADASTRO) {
			this.setTitle("Cadastro de atividades");
		}
		else {
			this.atividade = atividade;
			btnEscolherArquivo.setEnabled(false);
			btnSalvar.setAction(new AcaoSalvarEdicao());
			
			tf1.setText(String.valueOf(atividade.getValorMaximo()));
			tf2.setText(atividade.getAula().toString());
			tadescricao.setText(atividade.getDescricao());
			cbBimestre.setSelectedItem(atividade.getBimestre());
		}
		
	}
	
	private class AcaoEscolherArquivo extends AbstractAction {

		public AcaoEscolherArquivo() {
<<<<<<< Updated upstream
			super("Escolher arquivo");
=======
			super("Anexar arquivo");
>>>>>>> Stashed changes
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser seletor = new JFileChooser();
			seletor.showOpenDialog(JanelaCadastroAtividade.this);
			File arquivo = seletor.getSelectedFile();
			caminhoArquivo = arquivo.toString();
			
		}
		
	}
	
	private class AcaoSalvar extends AbstractAction {

		public AcaoSalvar() {
			super("Salvar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAtividade.
				cadastrarAtividade(
						tf1.getText(),
						tadescricao.getText(),
						caminhoArquivo, aula);
			
			
			pai.setVisible(true);
			JanelaCadastroAtividade.this.setVisible(false);
			
			
			try {
				Files.copy(arquivo.toPath(), Paths.get("./"+arquivo.getName()));
				nomeArquivo = arquivo.getName();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			
		}
		
	}
	
	private class AcaoSalvar extends AbstractAction {

		public AcaoSalvar() {
			super("Salvar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAtividade.cadastrarAtividade(
					tf1.getText(),//valor
					(String)cbBimestre.getSelectedItem(),
					tadescricao.getText(),
					nomeArquivo,
					aula);

			pai.buscarPor();

			JanelaCadastroAtividade.this.dispose();
			
		}
		
	}
	
	private class AcaoSalvarEdicao extends AbstractAction {

		public AcaoSalvarEdicao() {
			super("Salvar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ControleAtividade.editarAtividade(
					atividade,
					tf1.getText(),//valor
					(String)cbBimestre.getSelectedItem(),
					tadescricao.getText()
					);

			pai.buscarPor();

			JanelaCadastroAtividade.this.dispose();
			
		}
		
	}
	
	private class AcaoCancelar extends AbstractAction{

		public AcaoCancelar() {
			super("Cancelar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JanelaCadastroAtividade.this.dispose();
			
		}
		
	}
	
	private class AcaoCancelar extends AbstractAction {

		public AcaoCancelar() {
			super("Cancelar");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			pai.setVisible(true);
			JanelaCadastroAtividade.this.setVisible(false);
		}
		
	}
}
