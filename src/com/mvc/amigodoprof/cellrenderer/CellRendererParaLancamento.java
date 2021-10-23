package com.mvc.amigodoprof.cellrenderer;

import java.awt.Color;
import java.awt.Component;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import com.mvc.amigodoprof.gui.Alinhamento;
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
public class CellRendererParaLancamento extends DefaultTableCellRenderer{
    
    private int tamanhoLetra;
    private Color corTema;
   
	
	Alinhamento alinhamento;
    
    
    public CellRendererParaLancamento(int tamanhoLetra, Color corTema, Alinhamento alinhamento) {
        
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
    	
    	if(coluna != 0) {
    		JRadioButton botao = (JRadioButton)valor;
    		
    		if(tabela.getSelectedRow() == linha && tabela.getSelectedColumn() == coluna) {
    			
    			if(botao.isSelected() == true) {
    				botao.setSelected(false);
    			} else {
    				botao.setSelected(true);
    			}
    			
    			tabela.clearSelection();
    			
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

