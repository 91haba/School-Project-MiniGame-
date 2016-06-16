import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class CatchingThief extends JPanel{
/*instance data
 3���� �гη� ����� leftPanel���� 25���� ī�带 ����, rightPanel���� 3���� ��ư���� �����Ѵ�.countPanel���� ī�带 �����ϴ� Ƚ���� �����ִ� �����̴�.
leftPanel�ȿ� leftLabel�� �����Ͽ� ����̹����� �����ϰ� JLabel�迭�� leftLabel���� ���´�.
JLabel �迭�� ���ؼ� ī�带 �����ϴ� ������ ��Ÿ����. JButton 3���� ����Ͽ� ���Ƿ� ī�尡 leftLabel�� ���̰Բ� �������(btnRandom), ���°� Exit�� ���ؼ� �ٽ� ���Ƿ� ī�带 ���̰� �ϰ�(btnReset), ����(MiniGame class)���� ���ư� �� �ְԲ� �������.
nCount���� ī�带 ������ Ƚ���� �����ϴ� �����̰�, nRandom�� ��Ŀī�带 Label�� �����ϴ� ��ġ�� ��Ÿ���� �����̴�.
*/
	private JPanel leftPanel, rightPanel, countPanel, optionPanel;
	private JLabel[] CardBack, CardFront;
	private JLabel leftLabel;
	private JButton btnRandom, btnReset, btnExit;
	private JRadioButton red,black;
	private ctThread ctCount;
	private Font fnt;
	private int nCount,nRandom;
	private int[] selected;	//52���� ī�忡�� ���õǾ������� Ȯ��
	private MiniGame mg;	//���� Ŭ������ ���ư� �� �ְ��Ѵ�.
	
/*listener
btnL : ��ư�� ���� ������ Ŭ����
cardL : ī�带 ������ �ڿ��� ������ ���ϴ� ������ Ŭ����
radioL : ī�� �޸��� ���� �ٲٴ� ������ Ŭ����
*/
	private ButtonListener btnL;
	private CListener cardL;
	private RadioListener radioL;
	
//method
//������	
	public CatchingThief(MiniGame m){	//�Ķ���ͷ� ����Ŭ������ �޴´�.
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(GameConstants.bgColor);
		setLayout(null);
	
		mg = m;//MiniGame class�� method�� ����ϱ� ���ؼ� instance data�� �����Ѵ�.
		nCount = nRandom = 0;
		
//������ ������ Ŭ������ �����Ѵ�.
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
		leftPanel.add(leftLabel); //���� ī�尡 ���̴� ����
		
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
		}//ī���� ��ġ ��Ÿ����
		
		selected = new int[GameConstants.ctCard];
		for(int i=0;i<GameConstants.ctCard;i++){
			selected[i] = 0;
		}//�ߺ��� ī�带 ���� �ȵǱ� ������ �ߺ��� ���� �Ǵ��� �� �� �ְ� �ϴ� �迭 : 1�ΰ�쿡 �ش��ϴ� ī�尡 �̹� �����ִ�.
		
		rightPanel = new JPanel();
		rightPanel.setBounds(565,0,130,400);
		rightPanel.setBackground(Color.gray);
		rightPanel.setLayout(null);
		add(rightPanel);// ������ ��ư ����
		
		btnRandom = new JButton("Random");	//���� ��ư
		btnRandom.setBounds(20,15,100,50);
		btnRandom.setFont(fnt);
		btnRandom.addActionListener(btnL);
		rightPanel.add(btnRandom);
		
		btnReset = new JButton("Reset");	//����� ��ư
		btnReset.setBounds(20,75,100,50);
		btnReset.setFont(fnt);
		btnReset.setEnabled(false);
		btnReset.addActionListener(btnL);
		rightPanel.add(btnReset);
		
		btnExit = new JButton(GameConstants.Exit);	//����ȭ������ ���ư��� ��ư
		btnExit.setBounds(20,135,100,50);
		btnExit.setFont(fnt);
		btnExit.setEnabled(false);
		btnExit.addActionListener(btnL);
		rightPanel.add(btnExit);
		
		optionPanel = new JPanel();	//ī���� ���� ������ �� �� �ִ� ����
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
		group.add(red);	//�ϳ��� ��ư�� ���õ� ������ ��Ÿ���� ���ؼ�

		countPanel = new JPanel();
		countPanel.setBounds(565,400,130,100);
		countPanel.setBackground(Color.gray);
		countPanel.setLayout(null);
		add(countPanel);	//Ƚ�� �˷��ִ� ����
		
		ctCount = new ctThread(nCount);
		ctCount.setForeground(Color.black);
		ctCount.setFont(new Font("Verdana",Font.BOLD,15));
		ctCount.setBounds(10,10,120,50);
		ctCount.setHorizontalAlignment(SwingConstants.CENTER);
		ctCount.setVerticalAlignment(SwingConstants.CENTER);
		ctCount.setVisible(false);
		countPanel.add(ctCount);	//Ƚ���� ����ϴ� ����
	
	}//CatchingThief()
	
//actionlistener class	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
			btnRandom.setEnabled(false);
//������ư�� ������ ���
			if(obj == btnRandom){
				if(!(red.isSelected()) && !(black.isSelected()) || !(red.isSelected()) && black.isSelected()){
					black.setSelected(true);
				}//red�� black�� ��� ���õ��� �ʾ��� ���(�ʱ�ȭ)�� red�� ���õ��� �ʰ� black�� ���õ� ���
				else if(red.isSelected() && !(black.isSelected())){
					red.setSelected(true);
				}//red�� ���õǰ� black�� ���õ��� �ʴ� ���
				btnReset.setEnabled(true);
				btnExit.setEnabled(true);
				optionPanel.setVisible(true);
				ctCount.setVisible(true);
				nRandom = (int)(Math.random()*(GameConstants.ctTotal));	//��Ŀī�带 ���� ���� ����
				
				//test
				System.out.println(nRandom);
				CardFront = new JLabel[GameConstants.ctTotal];

				int z=0;
				for(int i=0;i<5;i++){
					for(int j=0;j<5;j++){
						if(z == nRandom){//��Ŀī�带 �����
							CardFront[z] = new JLabel(new ImageIcon("./images/Card53.png"));
						}
						else{
//52���� ī�� �߿��� �������� ���� ī����� �̾� ���Ӽ����� �Ѵ�.
							while(true){
								int gen = (int)(Math.random()*(GameConstants.ctCard));
								if(selected[gen] == 0){//�ߺ��� �ȵ� ��쿡�� ī�带 �̴´�.
									//�̹� ����� ī�� �߿��� ��Ŀ�� �����ϰ� ���Ƿ� �����Ͽ� �����Ѵ�.
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
					}//for(i)//ī�� ��ġ �Ϸ�
			}//obj == btnRandom
		
			else if(obj == btnReset){//�����
				nCount = 0;
				ctCount.setVisible(false);//�ʱ�ȭ
				ctCount.stop();
				ctCount.setinit(nCount);
				btnRandom.setEnabled(true);//��Ŀī�� �ٽ� ����� ��ư Ȱ��ȭ�ϱ�
				optionPanel.setVisible(false);
				for(int i=0;i<(GameConstants.ctCard);i++){
					selected[i] = 0;
				}//�ߺ����� 0���� �ʱ�ȭ
				int z=0;
					for(int i=0;i<5;i++){
						for(int j=0;j<5;j++){
					//�ո��� �̹����� ���� ������ �ʱ�ȭ�Ͽ� leftLabel�� ���
							CardFront[z].setVisible(false);
							CardFront[z].setIcon(new ImageIcon(""));
							CardBack[z].setVisible(true);
							CardFront[z].setVisible(false);
							CardBack[z].setVisible(false);
							z++;
						}//for(j)//ī�� �޸����� ������
					}//for(i)	
			}//btnReset
		
			else if(obj == btnExit){//���� ȭ������ ������ - reset��ư�� ������ ���� �����ϰ� �ʱ�ȭ�� ��Ų��.
					nCount = 0;
					ctCount.setVisible(false);//�ʱ�ȭ
					ctCount.stop();
					ctCount.setinit(nCount);
					btnRandom.setEnabled(true);//��Ŀī�� �ٽ� ����� ��ư Ȱ��ȭ�ϱ�
					btnReset.setEnabled(false);
					btnExit.setEnabled(false);
					optionPanel.setVisible(false);
					for(int i=0;i<(GameConstants.ctCard);i++){
						selected[i] = 0;
					}//�ߺ����� 0���� �ʱ�ȭ
					int z=0;
						for(int i=0;i<5;i++){
							for(int j=0;j<5;j++){
								CardFront[z].setVisible(false);
								CardBack[z].setVisible(false);
								z++;
							}//for(j)//ī�� �޸����� ������, ī�带 �Ⱥ��̰� �ϱ�
						}//for(i)	
			
					setVisible(false);
					mg.getsubPanel().setVisible(true);//����ȭ������ ���ư���
			}//btnExit
		}//actionPerformed()
	}//ButtonListener class
	
//RadioButton�� ���� ������ Ŭ����
	private class RadioListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event){
			//ī���� ������ �ٲٴ� ���ÿ� ������ ������Ѵ�.
			Object obj = event.getSource();
				nCount = 0;
				for(int i=0;i<(GameConstants.ctCard);i++){
					selected[i] = 0;
				}//for(i) - �ߺ����θ� 0���� �ʱ�ȭ
					
				ctCount.setVisible(false);
				ctCount.stop();
				ctCount.setinit(nCount);
				btnRandom.setEnabled(true);
				btnReset.setEnabled(false);
				btnExit.setEnabled(false);

				int z=0;
				if(obj == black){
					//�ٲٷ��� ���� �������� ���
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
					//�ٲٷ��� ���� �������� ���
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

//mouseListener class - ī�带 ������ ȿ���� ��Ÿ���� ���� Ŭ����
	private class CListener implements MouseListener{
		public void mouseClicked(MouseEvent event){
			Object obj1 = event.getSource();

			ctCount.stop();//���� Ŭ���� ������ ������ ������ ����
			ctCount.setVisible(true);
			nCount++;
			ctCount.start(nCount);//ī�带 ������ Ƚ���� 1 ����
			
			for(int i=0;i<(GameConstants.ctTotal);i++){
				if(obj1 == CardBack[i]){
					CardFront[i].setEnabled(true);
					CardBack[i].setVisible(false);
					CardFront[i].setVisible(true);
				}//if - ������ ī���� �ո��� �����ֱ�
			}//for(i)
			if(obj1 == CardBack[nRandom]){//���õ� ī�尡 ��Ŀī���� ��� ������ ���� ����.
				int result = JOptionPane.showConfirmDialog(null,"You got a " + (GameConstants.ctTotal - nCount) + " point. Continue?");
				
				nCount = 0;
				for(int i=0;i<(GameConstants.ctCard);i++){
						selected[i] = 0;
				}//for(i) - �ߺ����θ� 0���� �ʱ�ȭ
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
				
				if(result == JOptionPane.YES_OPTION){}//�ٽ� �Ѵٰ� ���� ��	- �׸� �δ� ���� �Ϻ� ��ġ�Ƿ� ���� �ڵ�� ������� �ʾҴ�.
				else if(result == JOptionPane.NO_OPTION){//�׸� �Ѵٰ� ���� ��	- ���� ȭ������ �̵��Ѵ�.
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