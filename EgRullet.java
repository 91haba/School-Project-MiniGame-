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
// Restart��ư�� Exit��ư, �׸��� lblTitle ���̺��� ����ִ� �г�		
		gamePanel = new JPanel();
		gamePanel.setBounds(245, 10, 320, 480);
		gamePanel.setLayout(null);
		add(gamePanel);
// ���� ������ �����ϴ� �г�		
		btnStart = new JButton(new ImageIcon("./images/start.png"));
		btnStart.setBounds(120, 190, 80, 36);
		btnStart.setVisible(true);
		btnStart.addActionListener(btnL);
		gamePanel.add(btnStart);
// ������ �����ϱ� ���� ��ư		
		btnEnd = new JButton(GameConstants.Exit);
		btnEnd.setForeground(Color.black);
		btnEnd.setBackground(Color.red);
		btnEnd.setFont(new Font("", Font.BOLD , 19));
		btnEnd.setVisible(true);
		btnEnd.setBounds(120, 236, 80, 36);
		btnEnd.addActionListener(exitL);
		gamePanel.add(btnEnd);
// EgRullet�г��� ���, MiniGame�гη� ���� ���� ��ư		
		Font fnt = new Font("Verdana", Font.ITALIC, 13);
		
		btnRe = new JButton("RESTART");
		btnRe.setForeground(Color.blue);
		btnRe.setBackground(Color.green);
		btnRe.setFont(fnt);
		btnRe.setBounds(5,5,95,30);
		btnRe.setEnabled(false);
		btnRe.addActionListener(btnL);
		menuPanel.add(btnRe);
// ������ �����ų�, ���� ���൵�� ������� ���� ���, ������ ó������ �ٽ� �����ϴ� ��ư		
		btnTooth = new JButton[19];
		nCount = new int[19];
		for(int i=0; i<19; i++){
			btnTooth[i] = new JButton();
			btnTooth[i].setVisible(false);
			btnTooth[i].setBorderPainted(false);
			nCount[i] = i;
		}
// �Ǿ� �̻� 19���� ���� ����� ����		
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
// 19���� �Ǿ��̻� ������ ��ġ�� �̹��� ����		
		
		for(int i=0; i<19; i++){
			btnTooth[i].addActionListener(btnL);
			gamePanel.add(btnTooth[i]);
			}
// �̻� 19���� �����гο� �߰��Ѵ�		
		
		btnEx = new JButton(GameConstants.Exit);
		btnEx.setForeground(Color.red);
		btnEx.setBackground(Color.green);
		btnEx.setFont(fnt);
		btnEx.setBounds(5,40,95,30);
		btnEx.setEnabled(false);
		btnEx.addActionListener(btnL);	
		menuPanel.add(btnEx);
// ���������� �����гη� ���ư��� ���� Exit��ư�̳� ������ ������ �� �ִ� Start��ư�� ���̱����� ��ư		
		Font fnt2 = new Font("Gothic", Font.ITALIC, 30);
		
		lblTitle = new JLabel(new ImageIcon("./images/funny.png"));
		lblTitle.setBackground(GameConstants.bgColor);
		lblTitle.setBounds(5, 75, 95, 400);
		menuPanel.add(lblTitle);
// ������ Ÿ��Ʋ�̴� �̹����� �����Ͽ���		
		lblState = new JLabel("You Lost Your Finger...");
		lblState.setForeground(new Color(255,100,0));
		lblState.setFont(fnt2);
		lblState.setBounds(10, 200, 320, 40);
		lblState.setVisible(false);
		gamePanel.add(lblState);
// (��÷�̻��� ����) ������ ����Ǿ��� ��� �� ���̺��� ���̰� �Ѵ�		
		lblBackground = new JLabel(new ImageIcon("./images/main2.png"));
		lblBackground.setBounds(0,0,320,480);
		gamePanel.add(lblBackground);
// ������ ȭ�� ��ȯ�� ���̺��� ����ؼ� �ߴ�. �̹����� �ٲ�� ���̴� �ʱ�ȭ�� �������� ����		
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
			}// btnStart ��ư�� ������ ���� �̺�Ʈ ���ȭ���� ���� �������ִ� �̹����� �ٲ��, 19���� �̻����� ��������, btnRe��ư�� btnEx��ư�� Ȱ��ȭ�ȴ� ���� 19���� �̻��� ��÷�̻��� Math.random�� ����Ͽ� ���Ѵ�
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
			}// ���ȭ���� �Ǿ ���� �ٹ����ִ� �ʱ�ȭ������ ���ư��� �׿����� ��ư�� ��������
			
			else if(obj == btnRe){
				lblState.setVisible(false);
				nRandom = (int)(Math.random()*19);
				System.out.println(""+nRandom);
				for(int i=0; i<19; i++){
					btnTooth[i].setVisible(true);
				}
				lblBackground.setIcon(new ImageIcon("./images/1.png"));
			}// �����������̳� �Ϸ��� ������ϴ� ��ư�̴� �̻� 19���� �ϳ��� �ٽ� �����ϰ�, ������ ����� ���� ������� ������ ���ȭ��� �̻��� �ٽ� �������ش�
			
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
			}// ����ڰ� �̻� ��ư�� ������ ��� ��÷����� �ȉ������ �������� for���̴�
			
			
		}//actionperformed
		
	}//Btnlistener class
	private class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object obj = event.getSource();
			
			if(obj == btnEnd){
				setVisible(false);
				mg.getsubPanel().setVisible(true); //�ʱ�ȭ�鿡�� MiniGame�гη� ���ư������� �̺�Ʈ
			}//if
		}//actionPerformed()
	}//ExitListner class

}//class