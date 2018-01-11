/*
 * 动态加载图片做背景的JPanel
 */
package view;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;

public class ImagePanel extends JPanel{

	Image im;
	public ImagePanel(Image im)
	{
		this.im=im;
		//大小自适应
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	public void paintComponent(Graphics g)                    //paintComponent针对画组件
	{
		super.paintComponent(g);
		g.drawImage(im, 0, 0, this.getWidth(), this.getHeight(), this);		
		
	}
	
}
