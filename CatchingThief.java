import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CatchingThief extends JPanel{
/*instance data
 3개의 패널로 나누어서 leftPanel에는 25장의 카드를 놓고, rightPanel에는 3개의 버튼으로 설정한다.countPanel에는 카드를 선택하는 횟수를 보여주는 공간이다.
leftPanel안에 leftLabel을 생성하여 배경이미지를 설정하고 JLabel배열이 leftLabel위에 놓는다.
JLabel 배열을 통해서 카드를 저장하는 곳으로 나타낸다. JButton 3개를 사용하여 임의로 카드가 leftLabel에 놓이게끔 만들었고(btnRandom), 리셋과 Exit을 통해서 다시 임의로 카드를 놓이게 하고(btnReset), 메인(MiniGame class)으로 돌아갈 수 있게끔 만들었다.
nCount에는 카드를 선택한 횟수를 저장하는 변수이고, nRandom은 조커카드를 Label에 저장하는 위치를 나타내는 변수이다.
*/
	private JPanel leftPanel, rightPanel, countPanel, optionPanel;
	private JLabel[] CardBack, CardFront;
	private JLabel leftLabel;
	private JButton btnRandom, btnReset, btnExit;
	private JRadioButton red,black;
	private ctThread ctCount;
	private Font fnt;
	private int nCount,nRandom;
	private int[] selected;	//52개의 카드에서 선택되었는지를 확인
	private MiniGame mg;	//메인 클래스로 돌아갈 수 있게한다.
	
/*listener
btnL : 버튼에 대한 리스너 클래스
cardL : 카드를 누르면 뒤에서 앞으로 변하는 리스너 클래스
radioL : 카드 뒷면의 색을 바꾸는 리스너 클래스
*/
	private ButtonListener btnL;
	private CListener cardL;
	private RadioListener radioL;
	
//method
//생성자	
	public CatchingThief(MiniGame m){	//파라미터로 메인클래스를 받는다.
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(GameConstants.bgColor);
		setLayout(null);
	
		mg = m;//MiniGame class의 method를 사용하기 위해서 instance data에 대입한다.
		nCount = nRandom = 0;
		
//각각의 리스너 클래스를 생성한다.
		btnL = new ButtonListener();
		cardL = new CListener();
		radioL = new RadioListener();
		
		fnt = new Font("Verdana",Font.BOLD,12);
					
		leftPanel = new JPanel();
		leftPanel.setBounds(15,0,550,500);
		leftPanel.setLayout(null);
		add(leftPanel);	
		
		leftLabel = new JLabel();
		leftLabel.setBounds(0,0,550,500);
		leftLabel.setIcon(new ImageIcon("./images/Background.jpg"));
		leftLabel.setLayout(null);
		leftLabel.setVisible(true);
		leftPanel.add(leftLabel); //왼쪽 카드가 보이는 공간
		
		CardBack = new JLabel[GameConstants.ctTotal];
		int z=0;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				CardBack[z] = new JLabel(new ImageIcon("./images/CardBack2.jpg"));
				CardBack[z].setBounds((10+110*j),(10+100*i),90,80);
				CardBack[z].addMouseListener(cardL);
				CardBack[z].setVisible(false);
				leftLabel.add(CardBack[z++]);
			}
		}//카드의 위치 나타내기
		
		selected = new int[GameConstants.ctCard];
		for(int i=0;i<GameConstants.ctCard;i++){
			selected[i] = 0;
		}//중복된 카드를 내면 안되기 때문에 중복에 대한 판단을 할 수 있게 하는 배열 : 1인경우에 해당하는 카드가 이미 놓여있다.
		
		rightPanel = new JPanel();
		rightPanel.setBounds(565,0,130,400);
		rightPanel.setBackground(Color.gray);
		rightPanel.setLayout(null);
		add(rightPanel);// 오른쪽 버튼 공간
		
		btnRandom = new JButton("Random");	//랜덤 버튼
		btnRandom.setBounds(20,15,100,50);
		btnRandom.setFont(fnt);
		btnRandom.addActionListener(btnL);
		rightPanel.add(btnRandom);
		
		btnReset = new JButton("Reset");	//재시작 버튼
		btnReset.setBounds(20,75,100,50);
		btnReset.setFont(fnt);
		btnReset.setEnabled(false);
		btnReset.addActionListener(btnL);
		rightPanel.add(btnReset);
		
		btnExit = new JButton(GameConstants.Exit);	//메인화면으로 돌아가는 버튼
		btnExit.setBounds(20,135,100,50);
		btnExit.setFont(fnt);
		btnExit.setEnabled(false);
		btnExit.addActionListener(btnL);
		rightPanel.add(btnExit);
		
		optionPanel = new JPanel();	//카드의 색을 변경해 줄 수 있는 공간
		optionPanel.setBorder(BorderFactory.createTitledBorder("Choose Color"));
		optionPanel.setBounds(20,200,100,100);
		optionPanel.setBackground(Color.gray);
		optionPanel.setVisible(false);
		rightPanel.add(optionPanel);
		
		black = new JRadioButton("BLACK",false);
		black.addActionListener(radioL);
		black.setBackground(Color.gray);
		black.setBounds(0,0,70,50);	
		optionPanel.add(black);

		red = new JRadioButton("RED",false);
		red.addActionListener(radioL);
		red.setBackground(Color.gray);
		red.setBounds(0,50,70,50);
		optionPanel.add(red);
				
		ButtonGroup group = new ButtonGroup();
		group.add(black);
		group.add(red);	//하나의 버튼만 선택된 것으로 나타내기 위해서

		countPanel = new JPanel();
		countPanel.setBounds(565,400,130,100);
		countPanel.setBackground(Color.gray);
		countPanel.setLayout(null);
		add(countPanel);	//횟수 알려주는 공간
		
		ctCount = new ctThread(nCount);
		ctCount.setForeground(Color.black);
		ctCount.setFont(new Font("Verdana",Font.BOLD,15));
		ctCount.setBounds(10,10,120,50);
		ctCount.setHorizontalAlignment(SwingConstants.CENTER);
		ctCount.setVerticalAlignment(SwingConstants.CENTER);
		ctCount.setVisible(false);
		countPanel.add(ctCount);	//횟수를 기록하는 변수
	
	}//CatchingThief()
	
//actionlistener class	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
			btnRandom.setEnabled(false);
//랜덤버튼을 눌렀을 경우
			if(obj == btnRandom){
				if(!(red.isSelected()) && !(black.isSelected()) || !(red.isSelected()) && black.isSelected()){
					black.setSelected(true);
				}//red와 black이 모두 선택되지 않았을 경우(초기화)나 red가 선택되지 않고 black이 선택된 경우
				else if(red.isSelected() && !(black.isSelected())){
					red.setSelected(true);
				}//red가 선택되고 black이 선택되지 않는 경우
				btnReset.setEnabled(true);
				btnExit.setEnabled(true);
				optionPanel.setVisible(true);
				ctCount.setVisible(true);
				nRandom = (int)(Math.random()*(GameConstants.ctTotal));	//조커카드를 넣을 곳을 선택
				
				//test
				System.out.println(nRandom);
				CardFront = new JLabel[GameConstants.ctTotal];

				int z=0;
				for(int i=0;i<5;i++){
					for(int j=0;j<5;j++){
						if(z == nRandom){//조커카드를 숨기기
							CardFront[z] = new JLabel(new ImageIcon("./images/Card53.png"));
						}
						else{
//52개의 카드 중에서 랜덤으로 받은 카드들을 뽑아 게임세팅을 한다.
							while(true){
								int gen = (int)(Math.random()*(GameConstants.ctCard));
								if(selected[gen] == 0){//중복이 안된 경우에만 카드를 뽑는다.
									//이미 저장된 카드 중에서 조커를 제외하고 임의로 선택하여 저장한다.
									CardFront[z] = new JLabel(new ImageIcon("./images/Card"+ (gen+1) +".png"));
									selected[gen] = 1;
									break;
								}//if
							}//while
						}//if-else
						CardFront[z].setBounds((10+110*j),(10+100*i),90,80);
						CardFront[z].setVisible(false);
						CardBack[z].setVisible(true);
						CardFront[z].setEnabled(false);
						leftLabel.add(CardFront[z]);
						z++;
						}//for(j)
					}//for(i)//카드 배치 완료
			}//obj == btnRandom
		
			else if(obj == btnReset){//재시작
				nCount = 0;
				ctCount.setVisible(false);//초기화
				ctCount.stop();
				ctCount.setinit(nCount);
				btnRandom.setEnabled(true);//조커카드 다시 숨기는 버튼 활성화하기
				optionPanel.setVisible(false);
				for(int i=0;i<(GameConstants.ctCard);i++){
					selected[i] = 0;
				}//중복여부 0으로 초기화
				int z=0;
					for(int i=0;i<5;i++){
						for(int j=0;j<5;j++){
					//앞면의 이미지를 없는 것으로 초기화하여 leftLabel에 깔기
							CardFront[z].setVisible(false);
							CardFront[z].setIcon(new ImageIcon(""));
							CardBack[z].setVisible(true);
							CardFront[z].setVisible(false);
							CardBack[z].setVisible(false);
							z++;
						}//for(j)//카드 뒷면으로 돌리기
					}//for(i)	
			}//btnReset
		
			else if(obj == btnExit){//메인 화면으로 나가기 - reset버튼을 눌렀을 경우와 동일하게 초기화를 시킨다.
					nCount = 0;
					ctCount.setVisible(false);//초기화
					ctCount.stop();
					ctCount.setinit(nCount);
					btnRandom.setEnabled(true);//조커카드 다시 숨기는 버튼 활성화하기
					btnReset.setEnabled(false);
					btnExit.setEnabled(false);
					optionPanel.setVisible(false);
					for(int i=0;i<(GameConstants.ctCard);i++){
						selected[i] = 0;
					}//중복여부 0으로 초기화
					int z=0;
						for(int i=0;i<5;i++){
							for(int j=0;j<5;j++){
								CardFront[z].setVisible(false);
								CardBack[z].setVisible(false);
								z++;
							}//for(j)//카드 뒷면으로 돌리기, 카드를 안보이게 하기
						}//for(i)	
			
					setVisible(false);
					mg.getsubPanel().setVisible(true);//메인화면으로 돌아가기
			}//btnExit
		}//actionPerformed()
	}//ButtonListener class
	
//RadioButton에 대한 리스너 클래스
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			//카드의 색깔을 바꾸는 동시에 게임을 재시작한다.
			Object obj = event.getSource();
				nCount = 0;
				for(int i=0;i<(GameConstants.ctCard);i++){
					selected[i] = 0;
				}//for(i) - 중복여부를 0으로 초기화
					
				ctCount.setVisible(false);
				ctCount.stop();
				ctCount.setinit(nCount);
				btnRandom.setEnabled(true);
				btnReset.setEnabled(false);
				btnExit.setEnabled(false);

				int z=0;
				if(obj == black){
					//바꾸려는 색이 검정색인 경우
					for(int i=0;i<5;i++){
						for(int j=0;j<5;j++){
							CardBack[z].setIcon(new ImageIcon("./images/CardBack2.jpg"));
							CardBack[z].setVisible(false);
							CardFront[z].setVisible(false);
							z++;
						}
					}
					red.setSelected(false);
					black.setSelected(true);
				}
				else if(obj == red){
					//바꾸려는 색이 빨간색인 경우
					for(int i=0;i<5;i++){
						for(int j=0;j<5;j++){
							CardBack[z].setIcon(new ImageIcon("./images/CardBack1.jpg"));
							CardBack[z].setVisible(false);
							CardFront[z].setVisible(false);
							z++;
						}
					}
					red.setSelected(true);
					black.setSelected(false);
				}
				
		}//actionPerformed()
	}//RadioListener class

//mouseListener class - 카드를 뒤집는 효과를 나타내기 위한 클래스
	private class CListener implements MouseListener{
		public void mouseClicked(MouseEvent event){
			Object obj1 = event.getSource();

			ctCount.stop();//다음 클릭이 들어오기 전까지 쓰레드 종료
			ctCount.setVisible(true);
			nCount++;
			ctCount.start(nCount);//카드를 누르면 횟수가 1 증가
			
			for(int i=0;i<(GameConstants.ctTotal);i++){
				if(obj1 == CardBack[i]){
					CardFront[i].setEnabled(true);
					CardBack[i].setVisible(false);
					CardFront[i].setVisible(true);
				}//if - 선택한 카드의 앞면을 보여주기
			}//for(i)
			if(obj1 == CardBack[nRandom]){//선택된 카드가 조커카드인 경우 게임이 끝이 난다.
				int result = JOptionPane.showConfirmDialog(null,"You got a " + (GameConstants.ctTotal - nCount) + " point. Continue?");
				
				nCount = 0;
				for(int i=0;i<(GameConstants.ctCard);i++){
						selected[i] = 0;
				}//for(i) - 중복여부를 0으로 초기화
				int z=0;
				for(int i=0;i<5;i++){
					for(int j=0;j<5;j++){
						CardFront[z].setVisible(false);
						CardBack[z].setVisible(false);
						z++;
					}//for(j)
				}//for(i)
				
				ctCount.setVisible(false);
				btnRandom.setEnabled(true);
				btnReset.setEnabled(false);
				btnExit.setEnabled(false);
				
				if(result == JOptionPane.YES_OPTION){}//다시 한다고 했을 때	- 그만 두는 경우와 일부 겹치므로 따로 코드는 사용하지 않았다.
				else if(result == JOptionPane.NO_OPTION){//그만 한다고 했을 때	- 메인 화면으로 이동한다.
					mg.getsubPanel().setVisible(true);
					setVisible(false);	
				}//if-else
			}//if

		}//Clicked()
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseEntered(MouseEvent event){}
		public void mouseExited(MouseEvent event){}
	}//CListener class
		
}//CatchingThief class