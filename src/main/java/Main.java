
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dark_
 */
public class Main extends javax.swing.JFrame implements ActionListener, MouseListener{
    JButton[][] matrizButtons = new JButton[12][13];
    ManipuladorArq arqs = new ManipuladorArq();
    CampoMatriz matriz = new CampoMatriz();
    private char[][] campoMod=new char[12][12];
    
    
    public Main() throws IOException{
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            matrizButtons = new JButton[13][12];
            getContentPane().setLayout(new GridLayout(13,12));
            setTitle("CAMPO MINOSO     .: by Herik Evangelinelis :.");
            for(int i=0; i<13; i++){
                    for(int j=0; j<12; j++){
                            matrizButtons[i][j] = new JButton();
                            matrizButtons[i][j].setText(" ");
                            matrizButtons[i][j].addActionListener(this);
                            matrizButtons[i][j].addMouseListener(this);
                            getContentPane().add(matrizButtons[i][j]);
                            String x = Integer.toString(i);
                            String y = Integer.toString(j);
                            String space = " ";
                            String z = x.concat(space);
                            z = z.concat(y);
                            matrizButtons[i][j].setName(z);
                    }
            }

            setSize(600,600);
            setVisible(true);


            for(int i=0; i<12; i++){
                matrizButtons[0][i].setVisible(false);
                matrizButtons[i][0].setVisible(false);
                matrizButtons[11][i].setVisible(false);
                matrizButtons[i][11].setVisible(false);
            }   
            for(int i=1; i<12;i++){
                matrizButtons[12][i].setVisible(false);
            }
            matrizButtons[12][0].setText("R");
            setResizable(false);

            File arquivo = new File("save.game");
            if(arquivo.exists()){
                int o=JOptionPane.showConfirmDialog(this,"Existe um jogo salvo, deseja continua-lo?");
                if(o==JOptionPane.YES_OPTION){
                    arqs.loadGame();
                    campoMod=arqs.getCampoMod();
                    for(int i=0; i<12; i++){
                        for(int j=0; j<12; j++){
                            if(campoMod[i][j]!=' '&&campoMod[i][j]!='F'){
                                matrizButtons[i][j].setText(Character.toString(campoMod[i][j]));
                                matrizButtons[i][j].setEnabled(false);
                            }else{matrizButtons[i][j].setText(Character.toString(campoMod[i][j]));}
                        }
                    }    
                }else{
                	reset();
                }         
            }else {
            	reset();
            }
    }

    public static void main(String[] args) throws IOException {
            new Main();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String choice = ((JButton)e.getSource()).getText();

            String nome = ((JButton)e.getSource()).getName();
            System.out.println(nome);
            String choice2[] = nome.split(" ");
            int x = Integer.parseInt(choice2[0]);
            int y = Integer.parseInt(choice2[1]);
            char campoMatriz[][] = arqs.getCampoMatriz();


            if("R".equals(choice)){
                reset();
                
            }else if(campoMatriz[x][y]==(char)164){
                ((JButton)e.getSource()).setText(Character.toString(campoMatriz[x][y]));
                JOptionPane.showMessageDialog(this,"BOOOOMMMM, VOCE EXPLODIU UMA BOMBA");
                campoMod[x][y]=((JButton)e.getSource()).getText().charAt(0);
                reset();

            }else if(campoMatriz[x][y]=='0'){
                matrizButtons[x-1][y-1].setText(Character.toString(campoMatriz[x-1][y-1]));
                campoMod[x-1][y-1]=matrizButtons[x-1][y-1].getText().charAt(0);
                matrizButtons[x-1][y-1].setEnabled(false);

                matrizButtons[x-1][y].setText(Character.toString(campoMatriz[x-1][y]));
                campoMod[x-1][y]=matrizButtons[x-1][y].getText().charAt(0);
                matrizButtons[x-1][y].setEnabled(false);

                matrizButtons[x-1][y+1].setText(Character.toString(campoMatriz[x-1][y+1])); 
                campoMod[x-1][y+1]=matrizButtons[x-1][y+1].getText().charAt(0);
                matrizButtons[x-1][y+1].setEnabled(false);

                matrizButtons[x][y-1].setText(Character.toString(campoMatriz[x][y-1])); 
                campoMod[x][y-1]=matrizButtons[x][y-1].getText().charAt(0);
                matrizButtons[x][y-1].setEnabled(false);

                matrizButtons[x][y+1].setText(Character.toString(campoMatriz[x][y+1]));  
                campoMod[x][y+1]=matrizButtons[x][y+1].getText().charAt(0);
                matrizButtons[x][y+1].setEnabled(false);

                matrizButtons[x][y].setText(Character.toString(campoMatriz[x][y]));  
                campoMod[x][y]=matrizButtons[x][y].getText().charAt(0);
                matrizButtons[x][y].setEnabled(false);

                matrizButtons[x+1][y-1].setText(Character.toString(campoMatriz[x+1][y-1]));
                campoMod[x+1][y-1]=matrizButtons[x+1][y-1].getText().charAt(0);
                matrizButtons[x+1][y-1].setEnabled(false);

                matrizButtons[x+1][y].setText(Character.toString(campoMatriz[x+1][y]));  
                campoMod[x+1][y]=matrizButtons[x+1][y].getText().charAt(0);
                matrizButtons[x+1][y].setEnabled(false);

                matrizButtons[x+1][y+1].setText(Character.toString(campoMatriz[x+1][y+1]));  
                campoMod[x+1][y+1]=matrizButtons[x+1][y+1].getText().charAt(0);
                matrizButtons[x+1][y+1].setEnabled(false);
                verificador();
                try {
                    arqs.saveGame(arqs.getCampoMatriz(), campoMod);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                ((JButton)e.getSource()).setText(Character.toString(campoMatriz[x][y]));
                campoMod[x][y]=((JButton)e.getSource()).getText().charAt(0);
                ((JButton)e.getSource()).setEnabled(false);
                verificador();
                try {
                    arqs.saveGame(arqs.getCampoMatriz(), campoMod);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

    public void reset(){
        for(int i=0; i<12; i++){
            for(int j=0; j<12; j++){
                matrizButtons[i][j].setText(" ");
                campoMod[i][j] = matrizButtons[i][j].getText().charAt(0);
                matrizButtons[i][j].setEnabled(true);
            }
        }
        
        int p=0;
        do {
        	String num = JOptionPane.showInputDialog(this,"Quantas bombas deseja no novo campo? (entre 0 e 99)");
        	p = Integer.parseInt(num);
        }while (p<1||p>99);
        matriz.gerarCampo(p);
        try {
            arqs.saveGame(matriz.getCampoMatriz(), campoMod);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verificador(){
        char[][] campoMod2 = new char [12][12];
        int cont=0;
        for(int i=1; i<11; i++){
            for(int j=1; j<11; j++){
                campoMod2[i][j] = matrizButtons[i][j].getText().charAt(0);
                if(campoMod2[i][j]==' '||campoMod2[i][j]=='F'){
                    char[][]campoMatriz = arqs.getCampoMatriz();
                    if(campoMatriz[i][j]!=(char)164){
                        cont = cont+1;
                    }
                }
            }
        }
        if(cont==0){
            JOptionPane.showMessageDialog(this,"PARABENS VOCE GANHOU O JOGO!");
            reset();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            if(((JButton)e.getSource()).getText()==" "){
                ((JButton)e.getSource()).setText("F");
            }else if(((JButton)e.getSource()).getText()=="F"){
                ((JButton)e.getSource()).setText(" ");
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
}
