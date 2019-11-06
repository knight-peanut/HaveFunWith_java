package cm.usc.fivechess;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMouse extends MouseAdapter implements Config, ActionListener {
	private JPanel j;
	private Graphics g;
	private int x1, y1;
	private int xx, yy; // ��ǰ���ӵĽ���λ��
	private int chessColor = 0; // �������ӵ���ɫ
	private int[][] chessArray = new int[LINE + 2][LINE + 2]; // �������ӵĶ�ά����
	AI startAI ; // ����AI����
	public int chooseModule; // �����õİ�ť���й���ѡ��

	/**
	 * ���幹�췽����������ʼ������
	 * @param g ����
	 * @param chessArray ��������
	 * @param j ����
	 */
	public GameMouse(Graphics g, int[][] chessArray, JPanel j) {
		this.chessArray = chessArray;
		this.j = j;
		this.g = g;
	}
	
	// ��ʤ��Ľ���
	public void winFrame(int winFlag) {
		JDialog frame = new JDialog();//����һ���µ�JFrame����Ϊ�´��ڡ�
        frame.setBounds(// ���öԻ��߿�
                new Rectangle(400,350,500,200)
            );
        JLabel jl = new JLabel(); // ע��������д���ˡ�
        frame.getContentPane().add(jl);
        
        String text = ""; //��ʼ��
        
        if(winFlag==1) {
        	//���öԻ�������������
             text = "<html>" + 
            		"<body>" + 
            		"<p align=\"center\">" + 
            		"�����ʤ" + 
            		"<br>" + 
            		"�رնԻ����ɼ���������" + 
            		"</p>" + 
            		"</body>" + 
            		"</html>";
        }else if(winFlag==-1){
        	text = "<html>" + 
            		"<body>" + 
            		"<p align=\"center\">" + 
            		"�����ʤ" + 
            		"<br>" + 
            		"�رնԻ����ɼ���������" + 
            		"</p>" + 
            		"</body>" + 
            		"</html>";
		}else {
			text = "<html>" + 
            		"<body>" + 
            		"<p align=\"center\">" + 
            		"���˶�ս���ɻ���" + 
            		"<br>" + 
            		"�رնԻ����ɼ���������" + 
            		"</p>" + 
            		"</body>" + 
            		"</html>";
		}
        
        jl.setText(text);
        //����������ʽ���������ͣ�����Ӵ֣������С
        jl.setFont(new java.awt.Font("Dialog", 1, 20));
        jl.setVerticalAlignment(JLabel.CENTER);
        jl.setHorizontalAlignment(JLabel.CENTER);
        //����ģʽ����
        frame.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        //���� APPLICATION_MODAL������ͬһ Java Ӧ�ó����е����ж��㴰�ڣ��������Լ����Ӳ��
        frame.setVisible(true);
	}

	// �ж��Ƿ�Ӯ��
	public boolean CheckWin(int xIndex, int yIndex) {
        int max = 0;
        int tempXIndex = xIndex;
        int tempYIndex = yIndex;
        
        int countConect;
        boolean flag;
        // ��ά�����¼����������б����б���ƶ�
        int[][][] dir = new int[][][] {
                // ����
                { { -1, 0 }, { 1, 0 } },
                // ����
                { { 0, -1 }, { 0, 1 } },
                // ��б
                { { -1, -1 }, { 1, 1 } },
                // ��б
                { { 1, -1 }, { -1, 1 } } };
 
        // ����ѭ�����������ӵ���2Ϊ�뾶����Χ����
        for (int i = 0; i < 4; i++) {
            countConect = 1;
            for (int j = 0; j < 2; j++) {
                flag = true;
                // whileѭ����һֱ��һ���������
                while (flag) {
                    tempXIndex = tempXIndex + dir[i][j][0];
                    tempYIndex = tempYIndex + dir[i][j][1];
 
                    // �ж�һ�������ϵ�����������ɫ
                    if (tempXIndex >= 0 && tempXIndex <= 15 && tempYIndex >= 0 && tempYIndex <= 15) {
                        if ((chessArray[tempXIndex][tempYIndex] == chessArray[xIndex][yIndex])) 
                            countConect++;
                        else
                            flag = false;
                    }else{
                        flag = false;
                    }
                    
                }
                tempXIndex = xIndex;
                tempYIndex = yIndex;
            }
            // ������������������
            if (countConect >= 5) {
                max = 1;
                break;
            } else
                max = 0;
        }
        if (max == 1)
            return true;
        else
            return false;
    }
	
	public void caculate_p2p() {
		// �������ӽ���
		if ((x1 - X) % SIZE > SIZE / 2) {
			xx = (x1 - X) / SIZE + 1;
		} else {
			xx = (x1 - X) / SIZE;
		}
		if ((y1 - Y) % SIZE > SIZE / 2) {
			yy = (y1 - Y) / SIZE + 1;
		} else {
			yy = (y1 - Y) / SIZE;
		}

		// �������ӵĺڰ�ɫ�������
		if (chessArray[xx][yy] == 0) {
			if (chessColor == 1) {
				g.setColor(Color.WHITE);
				chessArray[xx][yy] = 1;
				chessColor--;
			} else {
				g.setColor(Color.BLACK);
				chessArray[xx][yy] = -1;
				chessColor++;
			}
			
			g.fillOval(xx * SIZE + X - CHESS / 2, yy * SIZE + Y - CHESS / 2,CHESS, CHESS);
			//�ж�ʤ��
			if(CheckWin(xx, yy)){
				winFrame(chessArray[xx][yy]);
			}
		}
	}

	public void caculate_p2c() {
		// �������ӽ���
		if ((x1 - X) % SIZE > SIZE / 2) {
			xx = (x1 - X) / SIZE + 1;
		} else {
			xx = (x1 - X) / SIZE;
		}
		if ((y1 - Y) % SIZE > SIZE / 2) {
			yy = (y1 - Y) / SIZE + 1;
		} else {
			yy = (y1 - Y) / SIZE;
		}
		
		if (chessArray[xx][yy] == 0) {
			g.setColor(Color.BLACK);
			chessArray[xx][yy] = -1;
			g.fillOval(xx * SIZE + X - CHESS / 2, yy * SIZE + Y - CHESS / 2,
					CHESS, CHESS);
		}

	}

	// ���ư�ť���¼�����ģ��
	public void actionPerformed(ActionEvent e) {
		System.out.println("ѡ��ģʽ��" + e.getActionCommand());
		
		if (e.getActionCommand().equals("���¿�ʼ")) {
			// ����repaint������е����Ӳ�ʹÿ���¹��ĵ��������
			for (int i = 0; i < LINE; i++) {
				for (int j = 0; j < LINE; j++) {
					chessArray[i][j] = 0;
					chessColor = 0;
				}
			}
			j.repaint();
		}
		
		if (e.getActionCommand().equals("���˶�ս")) {
				j.addMouseListener(this); 
				chooseModule = 1;
		}
			
		if (e.getActionCommand().equals("�˻���ս")) {
				j.addMouseListener(this); 
				startAI = new AI(g, chessArray, j);
				chooseModule = 2;
		}
		
		
		if (e.getActionCommand().equals("����")) {
			
			if (chooseModule==2) {
				chessArray[xx][yy] = 0;
				chessColor = 0;
				// �ػ滭��
				j.update(g);
			}else {
				// �������˶�ս���ɻ���
				winFrame(0);
			}
			
		}
		
	}
	
	// ��ѡ�����Ϸģ����з�Ӧ
	public void mouseClicked(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		if (chooseModule == 1) {
			caculate_p2p();
			System.out.println("ģʽΪ1");
		}
		if (chooseModule == 2) {
			caculate_p2c();
			System.out.println("ģʽΪ2");
			if(CheckWin(xx, yy)){
				winFrame(chessArray[xx][yy]);
			}
		    try {
			    Thread.sleep(350);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		      
			startAI.chessAI();
			if(CheckWin(startAI.getMaxi(), startAI.getMaxi())){
				winFrame(-chessArray[xx][yy]);
			}
		}

	}
	
}