import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class EgRullet extends JPanel {
	
	private JPanel menuPanel,gamePanel;
	private JLabel lblBackground, lblTitle, lblState;
	private JButton[] btnTooth;
	private JButton btnRe, btnEx, btnStart, btnEnd;
	private int nRandom;
	private int[] nCount;
	private MiniGame mg;
	
	private BtnListener btnL;
	private ExitListener exitL;
	
	public EgRullet(MiniGame m){
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(GameConstants.bgColor);
		setLayout(null);

		mg = m;
		btnL = new BtnListener();
		exitL = new ExitListener();
		
		menuPanel = new JPanel();
		menuPanel.setBounds(130,10,105,480);
		menuPanel.setBackground(new Color(0, 128, 0));
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		menuPanel.setLayout(null);
		add(menuPanel);
// Restart버튼과 Exit버튼, 그리고 lblTitle 레이블을 담고있는 패널		
		gamePanel = new JPanel();
		gamePanel.setBounds(245, 10, 320, 480);
		gamePanel.setLayout(null);
		add(gamePanel);
// 실제 게임을 진행하는 패널		
		btnStart = new JButton(new ImageIcon("./images/start.png"));
		btnStart.setBounds(120, 190, 80, 36);
		btnStart.setVisible(true);
		btnStart.addActionListener(btnL);
		gamePanel.add(btnStart);
// 게임을 시작하기 위한 버튼		
		btnEnd = new JButton(GameConstants.Exit);
		btnEnd.setForeground(Color.black);
		btnEnd.setBackground(Color.red);
		btnEnd.setFont(new Font("", Font.BOLD , 19));
		btnEnd.setVisible(true);
		btnEnd.setBounds(120, 236, 80, 36);
		btnEnd.addActionListener(exitL);
		gamePanel.add(btnEnd);
// EgRullet패널을 벗어나, MiniGame패널로 가기 위한 버튼		
		Font fnt = new Font("Verdana", Font.ITALIC, 13);
		
		btnRe = new JButton("RESTART");
		btnRe.setForeground(Color.blue);
		btnRe.setBackground(Color.green);
		btnRe.setFont(fnt);
		btnRe.setBounds(5,5,95,30);
		btnRe.setEnabled(false);
		btnRe.addActionListener(btnL);
		menuPanel.add(btnRe);
// 게임이 끝나거나, 게임 진행도중 재시작을 원할 경우, 게임을 처음부터 다시 시작하는 버튼		
		btnTooth = new JButton[19];
		nCount = new int[19];
		for(int i=0; i<19; i++){
			btnTooth[i] = new JButton();
			btnTooth[i].setVisible(false);
			btnTooth[i].setBorderPainted(false);
			nCount[i] = i;
		}
// 악어 이빨 19개에 대한 선언과 생성		
		btnTooth[0].setBounds(40, 260, 24, 36);
		btnTooth[0].setIcon(new ImageIcon("./images/E0.png"));//0
		btnTooth[1].setBounds(33, 301, 26, 46);
		btnTooth[1].setIcon(new ImageIcon("./images/E1.png"));//1
		btnTooth[2].setBounds(48, 340, 36, 51);
		btnTooth[2].setIcon(new ImageIcon("./images/E2.png"));//2
		btnTooth[3].setBounds(85, 360, 41, 52);
		btnTooth[3].setIcon(new ImageIcon("./images/E3.png"));//3
		btnTooth[4].setBounds(145, 366, 39, 51);
		btnTooth[4].setIcon(new ImageIcon("./images/E4.png"));//4
		btnTooth[5].setBounds(198, 361, 39, 50);
		btnTooth[5].setIcon(new ImageIcon("./images/E5.png"));//5
		btnTooth[6].setBounds(244, 340, 34, 50);
		btnTooth[6].setIcon(new ImageIcon("./images/E6.png"));//6
		btnTooth[7].setBounds(268, 301, 26, 47);
		btnTooth[7].setIcon(new ImageIcon("./images/E7.png"));//7
		btnTooth[8].setBounds(261, 260, 22, 36);
		btnTooth[8].setIcon(new ImageIcon("./images/E8.png"));//8
		btnTooth[9].setBounds(261, 210, 24, 31);
		btnTooth[9].setIcon(new ImageIcon("./images/E9.png"));//9
		btnTooth[10].setBounds(267, 165, 28, 45);
		btnTooth[10].setIcon(new ImageIcon("./images/E10.png"));//10
		btnTooth[11].setBounds(254, 123, 35, 51);
		btnTooth[11].setIcon(new ImageIcon("./images/E11.png"));//11
		btnTooth[12].setBounds(215, 101, 41, 52);
		btnTooth[12].setIcon(new ImageIcon("./images/E12.png"));//12
		btnTooth[13].setBounds(169, 97, 38, 52);
		btnTooth[13].setIcon(new ImageIcon("./images/E13.png"));//13
		btnTooth[14].setBounds(114, 97, 39, 50);
		btnTooth[14].setIcon(new ImageIcon("./images/E14.png"));//14
		btnTooth[15].setBounds(65, 103, 39, 49);
		btnTooth[15].setIcon(new ImageIcon("./images/E15.png"));//15
		btnTooth[16].setBounds(33, 123, 35, 49);
		btnTooth[16].setIcon(new ImageIcon("./images/E16.png"));//16
		btnTooth[17].setBounds(26, 165, 28, 46);
		btnTooth[17].setIcon(new ImageIcon("./images/E17.png"));//17
		btnTooth[18].setBounds(35, 209, 24, 33);
		btnTooth[18].setIcon(new ImageIcon("./images/E18.png"));//18
// 19개의 악어이빨 각각의 위치와 이미지 삽입		
		
		for(int i=0; i<19; i++){
			btnTooth[i].addActionListener(btnL);
			gamePanel.add(btnTooth[i]);
			}
// 이빨 19개를 게임패널에 추가한다		
		
		btnEx = new JButton(GameConstants.Exit);
		btnEx.setForeground(Color.red);
		btnEx.setBackground(Color.green);
		btnEx.setFont(fnt);
		btnEx.setBounds(5,40,95,30);
		btnEx.setEnabled(false);
		btnEx.addActionListener(btnL);	
		menuPanel.add(btnEx);
// 게임진행중 메인패널로 돌아가기 위한 Exit버튼이나 게임을 시작할 수 있는 Start버튼을 보이기위한 버튼		
		Font fnt2 = new Font("Gothic", Font.ITALIC, 30);
		
		lblTitle = new JLabel(new ImageIcon("./images/funny.png"));
		lblTitle.setBackground(GameConstants.bgColor);
		lblTitle.setBounds(5, 75, 95, 400);
		menuPanel.add(lblTitle);
// 게임의 타이틀이다 이미지로 설정하였다		
		lblState = new JLabel("You Lost Your Finger...");
		lblState.setForeground(new Color(255,100,0));
		lblState.setFont(fnt2);
		lblState.setBounds(10, 200, 320, 40);
		lblState.setVisible(false);
		gamePanel.add(lblState);
// (당첨이빨을 눌러) 게임이 종료되었을 경우 이 레이블을 보이게 한다		
		lblBackground = new JLabel(new ImageIcon("./images/main2.png"));
		lblBackground.setBounds(0,0,320,480);
		gamePanel.add(lblBackground);
// 게임의 화면 전환을 레이블을 사용해서 했다. 이미지가 바뀌는 식이다 초기화면 사진으로 설정		
	}
	
	private class BtnListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			Object obj = event.getSource();
			if(obj == btnStart){
				btnStart.setVisible(false);
				btnEnd.setVisible(false);
				btnRe.setEnabled(true);
				btnEx.setEnabled(true);
				for(int i=0; i<19; i++){ btnTooth[i].setVisible(true); }
				lblBackground.setIcon(new ImageIcon("./images/1.png"));
				nRandom = (int)(Math.random()*19);
				System.out.println("" + nRandom);
			}// btnStart 버튼을 눌렀을 때의 이벤트 배경화면이 입을 벌리고있는 이미지로 바뀌고, 19개의 이빨들은 보여지고, btnRe버튼과 btnEx버튼은 활성화된다 또한 19개의 이빨중 당첨이빨을 Math.random을 사용하여 정한다
			else if(obj == btnEx){
				lblState.setVisible(false);
				btnStart.setVisible(true);
				btnEnd.setVisible(true);
				btnRe.setEnabled(false);
				btnEx.setEnabled(false);
				for(int i=0; i<19; i++){
					btnTooth[i].setVisible(false);
					}
				lblBackground.setIcon(new ImageIcon("./images/main2.png"));
			}// 배경화면을 악어가 입을 다물고있는 초기화면으로 돌아가고 그에대한 버튼도 보여진다
			
			else if(obj == btnRe){
				lblState.setVisible(false);
				nRandom = (int)(Math.random()*19);
				System.out.println(""+nRandom);
				for(int i=0; i<19; i++){
					btnTooth[i].setVisible(true);
				}
				lblBackground.setIcon(new ImageIcon("./images/1.png"));
			}// 게임진행중이나 완료후 재시작하는 버튼이다 이빨 19개중 하나를 다시 설정하고, 게임이 종료된 뒤의 재시작을 가정해 배경화면과 이빨을 다시 설정해준다
			
			for(int i=0; i<19; i++){
				if(obj == btnTooth[i]){
					if(nCount[i] == nRandom){
						lblBackground.setIcon(new ImageIcon("./images/2.png"));
						lblState.setVisible(true);
						for(int j=0; j<19; j++){
							btnTooth[j].setVisible(false);
						}
						break;
					}
					else{
						btnTooth[i].setVisible(false);
					}
				}
			}// 사용자가 이빨 버튼을 눌렀을 경우 당첨됬는지 안됬는지를 보기위한 for문이다
			
			
		}//actionperformed
		
	}//Btnlistener class
	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
			
			if(obj == btnEnd){
				setVisible(false);
				mg.getsubPanel().setVisible(true); //초기화면에서 MiniGame패널로 돌아가기위한 이벤트
			}//if
		}//actionPerformed()
	}//ExitListner class

}//class