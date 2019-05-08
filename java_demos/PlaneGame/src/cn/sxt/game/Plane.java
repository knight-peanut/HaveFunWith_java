package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	boolean left,right,up,down;
	
	//�жϷɻ�����״��
	boolean live = true;
	
	public void drawSelf(Graphics g) {
		
		if(live) {
			
			g.drawImage(img, (int)x, (int)y, null);
			
			//�԰��µļ������жϣ�������Ӧ�ķ������˶�
			if(left) {
				x -= speed;
			}
			if(right) {
				x += speed;
			}
			if(up) {
				y -= speed;
			}
			if(down) {
				y += speed;
			}
		}
		
	}

	public Plane(Image img, double x, double y) {
		super(img, x, y);
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = Constant.PLANE_SPEED;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}
	
	//���·���������ӷ���
	public void addDirection(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}
	
	
	//���·������������
		public void minusDirection(KeyEvent e){
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = false;
				break;
			case KeyEvent.VK_RIGHT:
				right = false;
				break;
			case KeyEvent.VK_UP:
				up = false;
				break;
			case KeyEvent.VK_DOWN:
				down = false;
				break;
			}
		}

}
