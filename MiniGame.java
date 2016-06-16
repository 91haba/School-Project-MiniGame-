import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MiniGame extends JPanel{
//instance data
/*
panel�� 4���ҷ� ������ 4���� ������ ������� ���ÿ� ���� ������ �� �ְԲ� 4���� JLabel�� ����Ѵ�.
JLabel�� ����Ͽ� �޹���̹����� �����Ѵ�. 
�� ���Ӻ��� ���� START��ư�� ���Ӽ����� �� �� �ְԲ� 8���� JButton�� ����Ѵ�.
*/
	private JPanel subPanel;
	private JLabel subLabel1, subLabel2, subLabel3,subLabel4;
	private JLabel lblMain,lblTitle;
	private JButton btnGame1, btnGame2, btnGame3, btnGame4;	
	private JButton btnInfo1, btnInfo2, btnInfo3, btnInfo4;	

/*
JLabel�� ����Ͽ� ���Ӽ����� �����ش�.
JButton�� ����Ͽ� ���Ӽ����� �Ⱥ��̰Բ� �����.
*/		
	private JLabel lblInfo;
	private JButton btnBack;
/*Game classes
4���� ���� Ŭ������ �����Ͽ� START��ư�� ������ �� ������ ������ �� �ֵ��� �Ѵ�.
*/
	private BaseBall Bb;	//���ھ߱� ����
	private BlackJack bj;	//���� ����
	private EgRullet Eg;	//�Ǿ�귿 ����
	private CatchingThief ct;	//������� ����
/*
fnt : ��ư ��Ʈ�� �����Ѵ�
*/	  
	private Font fnt;
/*listener classes
btnL : ���� ���� ��ư, ���� ���� ��ư ������
ColorL  : ���Ӽ��� â�� ���� �ϴ� ������
*/
	private btnListener btnL;
	private ColorListener  ColorL;

//method
//������
	public MiniGame(){
		setPreferredSize(new Dimension(740,700));
		setBackground(GameConstants.bgColor);
		setLayout(null);
//������ Ŭ������ �����Ѵ�.		
		btnL = new btnListener();
		ColorL = new ColorListener();
/*
�� ������ Ŭ������ �����Ѵ�. MiniGame class�� �� ������ �Ķ���ͷ� ������ ���߿� MiniGame class�� ���ƿ� �� �ְԲ��Ѵ�.
*/		
		Bb = new BaseBall(this);		//���ھ߱�����
		bj = new BlackJack(this);		//�������
		Eg = new EgRullet(this);		//�Ǿ�귿����
		ct = new CatchingThief(this);	//������� ����
		
//��� ��ư�� ��Ʈ�� ����ؾ��ϹǷ� �����Ѵ�.		
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
		
// ù ��° ���� - BaseBall Game�� ���� ����		
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
			
// �� ��° ����	 - BlackJack Game�� ���� ����
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


//����° ���� - EgRullet Game�� ���� ����
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
	
//�� ��° ���� - CatchingThief Game�� ���� ����
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
 �ش���ӿ��� primaryPanel�� MiniGamePanel�� �ǵ��ƿ����ϱ� ���ؼ� �������.	
*/
	public JPanel getsubPanel()		{	return subPanel;}
	
/*actionListener 
- ���� ����� ���� ��ư�� ���� listener Ŭ����
*/
	private class btnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
//��ư�� ������ �� ���ӽ��۹�ư�� �����ư�� �Ⱥ��̰� �Ѵ�. 					
			if(obj == btnInfo1){//ù ��° ���� ���� ��ư
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
			else if(obj == btnInfo2){//�� ��° ���� ���� ��ư
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
			else if(obj == btnInfo3){//�� ��° ���� ���� ��ư
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
			else if(obj == btnInfo4){//�� ��° ���� ���� ��ư
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
	//���Ӽ����� ������ �� ��ư�� �Ⱥ��̰� �Ѵ�.
			if(obj == btnBack){
	//�ǵ��ư��� ��ư�� ������ �� ���·� ���ư���.
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
				//�߱����� ���� ����
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
				//���� ���� ����
				bj.setBounds(0,190,710,500);
				bj.setVisible(true);
				subPanel.setVisible(false);
				add(bj);
				Bb.setVisible(false);
				Eg.setVisible(false);
				ct.setVisible(false);
			}
			else if(obj == btnGame3){
				//�Ǿ� �귿 ���� ����
				Eg.setBounds(0,190,710,500);
				Eg.setVisible(true);
				subPanel.setVisible(false);
				add(Eg);
				Bb.setVisible(false);
				bj.setVisible(false);
				ct.setVisible(false);		
			}
			else if(obj == btnGame4){
				//������� ���� ����
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

//�� ��ư�� ���콺�� �÷� ������ �ش� ��ư�� ���ڻ��� �ٲ�.	
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