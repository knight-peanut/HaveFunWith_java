package cm.usc.fivechess;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameUI implements Config {
	// �������ӵĶ�ά����
	private int[][] chessArray = new int[LINE+2][LINE+2]; 
	
	public void showUI() {
		
		JFrame jf = new JFrame();
		jf.setTitle("������_���װ�");
		jf.setSize(800, 700);
		// �����˳����̵ķ���
		jf.setDefaultCloseOperation(3);
		// ���þ�����ʾ
		jf.setLocationRelativeTo(null);
		GamePanel gp = new GamePanel(chessArray);
		//�������̱���ɫ
		gp.setBackground(new Color(139,139,122));
		jf.add(gp,BorderLayout.CENTER);
		//�����б߿������
		JPanel jp=new JPanel();
		jp.setBackground(new Color(245,245,245));
		jf.add(jp,BorderLayout.EAST);
		//����ѡ��߿���ʽ���ֵ�����
		jp.setLayout(new java.awt.FlowLayout(4,4,70));
		jp.setPreferredSize(new Dimension(120,0));
		 
		//���ð�ť��������
		//Object gameButton[];
		ArrayList<Object> gameButton = new ArrayList<Object>();
		String strButton[] = new String[4];
		strButton[0] = "���˶�ս";
		strButton[1] = "�˻���ս";
		strButton[2] = "����";
		strButton[3] = "���¿�ʼ";
		
		for(int i=0;i<4;i++) {
			gameButton.add(new javax.swing.JButton(strButton[i]));
			((JComponent) gameButton.get(i)).setPreferredSize(new Dimension(100,60));
			jp.add((JComponent) gameButton.get(i));
		}
		
		jf.setVisible(true);
		//�������̵Ļ���
		Graphics g = gp.getGraphics();
		
		//ȥ������ͼ���Ե��ݻ�
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		GameMouse mouse = new GameMouse(g,chessArray,gp);
		
		//����ʱ�������
		for(int i=0;i<4;i++) {
			((JButton) gameButton.get(i)).addActionListener(mouse);
		}		
	}
	
	public static void main(String[] args) {
		GameUI ui = new GameUI();
		ui.showUI();
	}

}