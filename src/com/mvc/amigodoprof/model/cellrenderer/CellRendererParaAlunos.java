package com.mvc.amigodoprof.model.cellrenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import com.mvc.amigodoprof.gui.Alinhamento;
import com.mvc.amigodoprof.gui.MenuAluno;
import com.mvc.amigodoprof.gui.MenuTurma;



/**
 * @author Murilo Vieira
 * Baseado nas funções encontradas em:
 * https://www.guj.com.br/t/colorir-apenas-uma-celula-do-jtable/30998/9
 * Criadas por: 
 * @author fredferrao
 *
 */

@SuppressWarnings("serial")
public class CellRendererParaAlunos extends DefaultTableCellRenderer{
    
    private int tamanhoLetra;
    private Color corTema;
    
	
	Alinhamento alinhamento;
    
    
    public CellRendererParaAlunos(int tamanhoLetra, Color corTema, Alinhamento alinhamento) {
        
		super();
        this.tamanhoLetra = tamanhoLetra;
        this.corTema = corTema;
        
    	switch(alinhamento) {
			
    		case ESQUERDA:
				this.setHorizontalAlignment(SwingConstants.LEFT);
				break;
		
			case CENTRO:
				this.setHorizontalAlignment(SwingConstants.CENTER);
				break;
			
			case DIREITA:
				this.setHorizontalAlignment(SwingConstants.RIGHT);
				break;
    	}
    }    
    
    public Component getTableCellRendererComponent(JTable tabela, 
            Object valor, boolean isSelected, boolean hasFocus, int linha, int coluna){
    	
    	if(coluna == 3) {
    		JButton botao = (JButton)valor;
    		
    		if(tabela.getSelectedRow() == linha && tabela.getSelectedColumn() == coluna) {
    			botao.setBackground(Color.GREEN);
    			
    			tabela.clearSelection();
    			//mt.abrirJanelaVerResolucoes(linha);
    		}
    		return botao;
    	}
    	
    	if(coluna == 4) {
    		JButton botao = (JButton)valor;
    		
    		if(tabela.getSelectedRow() == linha && tabela.getSelectedColumn() == coluna) {
    			botao.setBackground(Color.GREEN);
    			
    			tabela.clearSelection();
    			//mt.abrirJanelaVerFrequencias(linha);
    		}
    		return botao;
    	}
    	
    	
    	
    	JLabel label = (JLabel)super.getTableCellRendererComponent(tabela, valor, isSelected, hasFocus, 
                linha, coluna);

    	
    	if(isSelected == true){
            
    		label.setForeground(Color.BLACK);
            label.setBackground(corTema);
            label.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
            this.setBorder(noFocusBorder);    
            //Border b = new LineBorder(Color.BLACK, 3, false);
                 
            //label.setBorder(b);

              
        }else{
        	/*TODO 
        	 * colocar aqui alguma lógica de deixar em vermelho valores negativos*/
        	label.setForeground(Color.BLACK);
            label.setBackground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, tamanhoLetra));
            
        }


       //lembrando que vc pode obter o objeto da linhas correspondente assim
       //Pessoa pessoa = ((PessoasTableModel)table.getModel()).getValoresPessoa(row);
       //e fazer os if's direto nos valores do objeto, nesse caso para colorir a linha inteira!!
        return label;       
    } 
	
}

