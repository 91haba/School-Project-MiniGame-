import javax.swing.*;
/*
 (����) : (2��)_�������ϱⰡ���Ͼ����
 (����) : ���°�(����), �ɿ���, ��ȣ��, ������
*/
public class MiniGameEx{
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("�̴ϰ��� õ��4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		MiniGame game = new MiniGame();
	
		frame.getContentPane().add(game);
		frame.pack();
		ImageIcon ig = new ImageIcon("images/Minigame.jpg");//������ ��������� �̹����� �����Ѵ�.
		frame.setIconImage(ig.getImage());
		frame.setVisible(true);
	}//main()		
}//MiniGameEx class