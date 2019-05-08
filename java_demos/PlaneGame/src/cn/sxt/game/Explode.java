package cn.sxt.game;

import java.awt.Graphics;
import java.awt.Image;

public class Explode {

	//��ը�࣬��Ҫ��ʵ�ֱ�ըʱ��ͼƬ����
	double x,y;
	
	//��̬������16��ͼƬ������ÿ��ͼƬ���ٴ�new()��������Դ������
	static Image[] imgs = new Image[16];
	
	static {
		for(int i=0;i<16;i++) {
			imgs[i] = GameUtil.getImage("images/explode/e" +(i + 1)+ ".gif");
			imgs[i].getWidth(null);
		}
	}
	
	//ͨ������g������ÿ��ͼ�����λ���ʵ������Ч��
	int count=0;
	public void draw(Graphics g) {
		if(count<=15) {
			g.drawImage(imgs[count], (int)x, (int)y, null);
			count ++;
		}
	}
	
	//��ըλ������
	public Explode(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
}
