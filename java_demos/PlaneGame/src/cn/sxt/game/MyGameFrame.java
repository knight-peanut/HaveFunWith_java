package cn.sxt.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame{
	//��������
	
	//���ù������еķ������õ�ͼ��
	Image planeImg = GameUtil.getImage("images/plane.png");
	Image bg = GameUtil.getImage("images/bg.jpg");
	
	Plane plane = new Plane(planeImg,Constant.START_X,Constant.START_Y);
	
	//�����ڵ�����
	Shell[] shells = new Shell[Constant.SHELL_NUM];
	Explode bao;
	
	Date startTime = new Date();
	Date endTime;
	int peroid; //������Ϸʱ��
	
	//�Զ�������,g�൱��һ֧����
	public void paint(Graphics g) {
		Color c = g.getColor();
		
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);  //�����ɻ���ͼ��
		
		
		//���������е�ÿһ���ڵ�
		for(int i=0;i<shells.length;i++) {
			shells[i].draw(g);
			
			//�����ײ
			boolean peng = shells[i].getRect().intersects(plane.getRect());
			
			if(peng){
				plane.live = false;
				//���ɱ�ը��ʹ�䵼����ըЧ��
				if(bao == null) {
					bao = new Explode(plane.x, plane.y);
					
					endTime = new Date();
					peroid = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				
			    bao.draw(g);
			}
			
			if(!plane.live) {
				g.setColor(Color.RED);
				Font f = new Font("����", Font.BOLD, 40);
				g.setFont(f);
				g.drawString("���ʱ�䣺" + peroid + "��" , 150, 250);
			}
			
		}
		
			g.setColor(c);
	}
	
	//��ͣ�ػ��ɻ����ﵽ����Ч��
	class PaintThread extends Thread{
	
		public void run() {
			while(true) {
				repaint();
				
				try {
					Thread.sleep(Constant.TIME_SLEEP); //�߳�����ʱ�䣬��Ӧ����
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	//����һ�����̿�����
	class KeyMonito extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}
	
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
	}
	
	/**
	 *��ʼ������
	 */
	public void launchFrame() {
		this.setTitle("�ɻ�С��Ϸ");
		this.setVisible(true);  //ʹ�����ڿɼ�
		this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGTH);
		this.setLocationRelativeTo(null); //ʹ�����ھ���
		
		//�������ڼ���ʹ��Ϸ�Ľ������Ŵ��ڵĹرն�����
		this.addWindowListener(new WindowAdapter() {
			//�����ڲ���
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();  //���ɻ����߳�
		addKeyListener(new KeyMonito());  //���������Ӽ��̼���
		
		//�ڴ��ڳ�ʼ���ڵ�����
		for(int i=0;i<shells.length;i++) {
			shells[i] = new Shell();
		}
	}
	
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();
		f.launchFrame();
	}
	
	
	//˫������ͼƬ�ػ���˸����
	private Image offScreenImage = null;
	
	public void update(Graphics g) {
		if(offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGTH);
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
		


}
