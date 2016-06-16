import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MiniGame extends JPanel{
//instance data
/*
panel을 4분할로 나누어 4개의 게임을 사용자의 선택에 따라 실행할 수 있게끔 4개의 JLabel을 사용한다.
JLabel을 사용하여 뒷배경이미지를 설정한다. 
각 게임별로 각각 START버튼과 게임설명을 볼 수 있게끔 8개의 JButton을 사용한다.
*/
	private JPanel subPanel;
	private JLabel subLabel1, subLabel2, subLabel3,subLabel4;
	private JLabel lblMain,lblTitle;
	private JButton btnGame1, btnGame2, btnGame3, btnGame4;	
	private JButton btnInfo1, btnInfo2, btnInfo3, btnInfo4;	

/*
JLabel을 사용하여 게임설명을 보여준다.
JButton을 사용하여 게임설명을 안보이게끔 만든다.
*/		
	private JLabel lblInfo;
	private JButton btnBack;
/*Game classes
4개의 게임 클래스를 선언하여 START버튼을 눌렀을 때 게임이 시작할 수 있도록 한다.
*/
	private BaseBall Bb;	//숫자야구 게임
	private BlackJack bj;	//블랙잭 게임
	private EgRullet Eg;	//악어룰렛 게임
	private CatchingThief ct;	//도둑잡기 게임
/*
fnt : 버튼 폰트를 설정한다
*/	  
	private Font fnt;
/*listener classes
btnL : 게임 설명 버튼, 게임 시작 버튼 리스너
ColorL  : 게임설명 창을 끄게 하는 리스너
*/
	private btnListener btnL;
	private ColorListener  ColorL;

//method
//생성자
	public MiniGame(){
		setPreferredSize(new Dimension(740,700));
		setBackground(GameConstants.bgColor);
		setLayout(null);
//리스너 클래스를 생성한다.		
		btnL = new btnListener();
		ColorL = new ColorListener();
/*
각 게임의 클래스를 생성한다. MiniGame class를 각 게임의 파라미터로 보내서 나중에 MiniGame class로 돌아올 수 있게끔한다.
*/		
		Bb = new BaseBall(this);		//숫자야구게임
		bj = new BlackJack(this);		//블랙잭게임
		Eg = new EgRullet(this);		//악어룰렛게임
		ct = new CatchingThief(this);	//도둑잡기 게임
		
//모든 버튼에 폰트를 사용해야하므로 생성한다.		
		fnt = new Font("Verdana",Font.BOLD,10);
		
		subPanel = new JPanel();
		subPanel.setBounds(15,190,GameConstants.Width,GameConstants.Height);
		subPanel.setBackground(GameConstants.bgColor);
		subPanel.setLayout(null);
		add(subPanel);
	
		lblMain = new JLabel(new ImageIcon("./images/mainLabel.gif"));
		lblMain.setBounds(15,20,114,143);
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setVerticalAlignment(SwingConstants.CENTER);
		add(lblMain);
		
		lblTitle = new JLabel(new ImageIcon("./images/Title.png"));
		lblTitle.setBounds(160,20,550,143);
		add(lblTitle);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setVerticalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(50,50,570,410);
		lblInfo.setVisible(false);
		subPanel.add(lblInfo);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(520,460,100,30);
		btnBack.setFont(new Font("Verdana", Font.BOLD, 15));
		btnBack.setForeground(Color.blue);
		btnBack.setVisible(false);
		btnBack.addActionListener(btnL);
		subPanel.add(btnBack);		
		
// 첫 번째 게임 - BaseBall Game을 위한 공간		
		btnGame1 = new JButton(""+GameConstants.START);
		btnGame1.setFont(fnt);
		btnGame1.setBounds(230,175,90,25);
		btnGame1.addActionListener(btnL);
		btnGame1.addMouseListener(ColorL);
		subPanel.add(btnGame1);
			
		btnInfo1 = new JButton(""+GameConstants.INFO);
		btnInfo1.setFont(fnt);
		btnInfo1.setBounds(230,200,90,25);
		btnInfo1.addActionListener(btnL);
		btnInfo1.addMouseListener(ColorL);
		subPanel.add(btnInfo1);
		
		subLabel1 = new JLabel(new ImageIcon("./images/bg1.png"));
		subLabel1.setBounds(0,0,350,250);
		subPanel.add(subLabel1);
			
// 두 번째 게임	 - BlackJack Game을 위한 공간
		btnGame2 = new JButton(""+GameConstants.START);
		btnGame2.setBounds(580,175,90,25);
		btnGame2.setFont(fnt);
		btnGame2.addActionListener(btnL);
		btnGame2.addMouseListener(ColorL);
		subPanel.add(btnGame2);
		
		btnInfo2 = new JButton(""+GameConstants.INFO);
		btnInfo2.setFont(fnt);
		btnInfo2.setBounds(580,200,90,25);
		btnInfo2.addActionListener(btnL);
		btnInfo2.addMouseListener(ColorL);
		subPanel.add(btnInfo2);
		
		subLabel2 = new JLabel(new ImageIcon("./images/BALCKJAK.jpg"));
		subLabel2.setBounds(350,0,350,250);
		subPanel.add(subLabel2);


//세번째 게임 - EgRullet Game을 위한 공간
		btnGame3 = new JButton(""+GameConstants.START);
		btnGame3.setFont(fnt);
		btnGame3.setBounds(230,425,90,25);
		btnGame3.addActionListener(btnL);
		btnGame3.addMouseListener(ColorL);
		subPanel.add(btnGame3);
		
		btnInfo3 = new JButton(""+GameConstants.INFO);
		btnInfo3.setFont(fnt);
		btnInfo3.setBounds(230,450,90,25);
		btnInfo3.addActionListener(btnL);
		btnInfo3.addMouseListener(ColorL);
		subPanel.add(btnInfo3);
		
		subLabel3 = new JLabel(new ImageIcon("./images/Eg.png"));
		subLabel3.setBounds(0,250,350,250);
		subPanel.add(subLabel3);	
	
//네 번째 게임 - CatchingThief Game을 위한 공간
		btnGame4 = new JButton(""+GameConstants.START);
		btnGame4.setFont(fnt);
		btnGame4.setBounds(580,425,90,25);
		btnGame4.addActionListener(btnL);
		btnGame4.addMouseListener(ColorL);
		subPanel.add(btnGame4);
		
		btnInfo4 = new JButton(""+GameConstants.INFO);
		btnInfo4.setFont(fnt);
		btnInfo4.setBounds(580,450,90,25);
		btnInfo4.addActionListener(btnL);
		btnInfo4.addMouseListener(ColorL);
		subPanel.add(btnInfo4);

		subLabel4 = new JLabel(new ImageIcon("./images/cat.png"));
		subLabel4.setBounds(350,250,350,250);
		subPanel.add(subLabel4);		
	}//MiniGame()
/*set/get method
 해당게임에서 primaryPanel인 MiniGamePanel로 되돌아오게하기 위해서 만들었다.	
*/
	public JPanel getsubPanel()		{	return subPanel;}
	
/*actionListener 
- 게임 설명과 시작 버튼을 위한 listener 클래스
*/
	private class btnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
//버튼을 누르면 각 게임시작버튼과 설명버튼을 안보이게 한다. 					
			if(obj == btnInfo1){//첫 번째 게임 설명 버튼
				lblInfo.setIcon(new ImageIcon("./images/BaseBallInfo.png"));
				lblInfo.setVisible(true);
				btnBack.setVisible(true);
				btnGame1.setVisible(false);
				btnInfo1.setVisible(false);
				btnGame2.setVisible(false);
				btnInfo2.setVisible(false);
				btnGame3.setVisible(false);
				btnInfo3.setVisible(false);
				btnGame4.setVisible(false);
				btnInfo4.setVisible(false);
			}
			else if(obj == btnInfo2){//두 번째 게임 설명 버튼
				lblInfo.setIcon(new ImageIcon("./images/BlackJackInfo.png"));
				lblInfo.setVisible(true);
				btnBack.setVisible(true);
				btnGame1.setVisible(false);
				btnInfo1.setVisible(false);
				btnGame2.setVisible(false);
				btnInfo2.setVisible(false);
				btnGame3.setVisible(false);
				btnInfo3.setVisible(false);
				btnGame4.setVisible(false);
				btnInfo4.setVisible(false);
				}
			else if(obj == btnInfo3){//세 번째 게임 설명 버튼
				lblInfo.setIcon(new ImageIcon("./images/EgRulletInfo.png"));
				lblInfo.setVisible(true);
				btnBack.setVisible(true);
				btnGame1.setVisible(false);
				btnInfo1.setVisible(false);
				btnGame2.setVisible(false);
				btnInfo2.setVisible(false);
				btnGame3.setVisible(false);
				btnInfo3.setVisible(false);
				btnGame4.setVisible(false);
				btnInfo4.setVisible(false);
				}
			else if(obj == btnInfo4){//네 번째 게임 설명 버튼
				lblInfo.setIcon(new ImageIcon("./images/GameInfo4.png"));
				lblInfo.setVisible(true);
				btnBack.setVisible(true);
				btnGame1.setVisible(false);
				btnInfo1.setVisible(false);
				btnGame2.setVisible(false);
				btnInfo2.setVisible(false);
				btnGame3.setVisible(false);
				btnInfo3.setVisible(false);
				btnGame4.setVisible(false);
				btnInfo4.setVisible(false);
				}
	//게임설명을 볼때는 각 버튼을 안보이게 한다.
			if(obj == btnBack){
	//되돌아가는 버튼을 누르면 원 상태로 돌아간다.
				lblInfo.setVisible(false);
				btnBack.setVisible(false);
				btnGame1.setVisible(true);
				btnInfo1.setVisible(true);
				btnGame2.setVisible(true);
				btnInfo2.setVisible(true);
				btnGame3.setVisible(true);
				btnInfo3.setVisible(true);
				btnGame4.setVisible(true);
				btnInfo4.setVisible(true);
			}
			if(obj == btnGame1){
				//야구게임 게임 실행
				Bb.setBounds(0,190,710,500);
				Bb.randomNum();
				Bb.setVisible(true);
				subPanel.setVisible(false);
				add(Bb);
				bj.setVisible(false);
				Eg.setVisible(false);
				ct.setVisible(false);
			}
			else if(obj == btnGame2){
				//블랙잭 게임 실행
				bj.setBounds(0,190,710,500);
				bj.setVisible(true);
				subPanel.setVisible(false);
				add(bj);
				Bb.setVisible(false);
				Eg.setVisible(false);
				ct.setVisible(false);
			}
			else if(obj == btnGame3){
				//악어 룰렛 게임 실행
				Eg.setBounds(0,190,710,500);
				Eg.setVisible(true);
				subPanel.setVisible(false);
				add(Eg);
				Bb.setVisible(false);
				bj.setVisible(false);
				ct.setVisible(false);		
			}
			else if(obj == btnGame4){
				//도둑잡기 게임 실행
				ct.setBounds(0,190,710,500);
				ct.setVisible(true);
				subPanel.setVisible(false);
				add(ct);
				Bb.setVisible(false);
				bj.setVisible(false);
				Eg.setVisible(false);
			}//if-else
		}//actionPerformed()
	}//btnListener class

//각 버튼에 마우스를 올려 놓으면 해당 버튼의 글자색이 바뀜.	
	private class ColorListener implements MouseListener{
		public void mouseClicked(MouseEvent event){}
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){
			Object obj = event.getSource();
			
			if(obj == btnGame1)				btnGame1.setForeground(Color.red);	
			else if(obj == btnGame2)		btnGame2.setForeground(Color.red);
			else if(obj == btnGame3)		btnGame3.setForeground(Color.red);
			else if(obj == btnGame4)		btnGame4.setForeground(Color.red);	
			else if(obj == btnInfo1)		btnInfo1.setForeground(Color.red);
			else if(obj == btnInfo2)		btnInfo2.setForeground(Color.red);
			else if(obj == btnInfo3)		btnInfo3.setForeground(Color.red);
			else if(obj == btnInfo4)		btnInfo4.setForeground(Color.red);
		}//mouseEntered
		public void mouseExited(MouseEvent event){
			Object obj = event.getSource();
			
			if(obj == btnGame1)				btnGame1.setForeground(Color.black);	
			else if(obj == btnGame2)		btnGame2.setForeground(Color.black);
			else if(obj == btnGame3)		btnGame3.setForeground(Color.black);
			else if(obj == btnGame4)		btnGame4.setForeground(Color.black);	
			else if(obj == btnInfo1)		btnInfo1.setForeground(Color.black);
			else if(obj == btnInfo2)		btnInfo2.setForeground(Color.black);
			else if(obj == btnInfo3)		btnInfo3.setForeground(Color.black);
			else if(obj == btnInfo4)		btnInfo4.setForeground(Color.black);
		}	//mouseExited
	}//BtnListener class
}//MiniGame class