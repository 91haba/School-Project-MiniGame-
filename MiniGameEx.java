import javax.swing.*;
/*
 (팀명) : (2조)_팀명정하기가제일어렵조
 (팀원) : 하태경(팀장), 심우일, 장호열, 곽지훈
*/
public class MiniGameEx{
	
	public static void main(String[] args){
		
		JFrame frame = new JFrame("미니게임 천국4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		MiniGame game = new MiniGame();
	
		frame.getContentPane().add(game);
		frame.pack();
		ImageIcon ig = new ImageIcon("images/Minigame.jpg");//프레임 좌측상단의 이미지를 설정한다.
		frame.setIconImage(ig.getImage());
		frame.setVisible(true);
	}//main()		
}//MiniGameEx class