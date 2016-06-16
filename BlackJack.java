import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class BlackJack extends JPanel
{
	// instance data
	private JPanel ground;
	private JButton btnDeal, btnHit, btnStay, btnExit;
	private JLabel[] lblDslot;
	private JLabel[] lblPslot;
	private JLayeredPane lpDslot, lpPslot; // ������ ī�� �̹����� ������� ��Ÿ���� ���� JLayeredPane ����
	private JLabel lblDinfo, lblPinfo, lblDscore, lblPscore;
	private JLabel lblDTotal, lblPTotal, lblDeck;
	private JLabel lblBackground;
	private JLabel lblResult;
	private int playerScore, dealerScore; // �÷��̾�� ������ ���� ��Ÿ���� ����
	private int nplayerHit=2, ndealerHit=2; // ī�带 �� ���� �� ī��迭 ��ȣ�� ��Ÿ���� ����
	private MiniGame mg; // ���� Ŭ������ ���ư� �� �ְ��Ѵ�.
	
	private Deck deck; 
	public Player player = new Player("player");
	public Player dealer = new Player("dealer");
	
	private GameListener gameL;
	
	public BlackJack(MiniGame m) {
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(GameConstants.bgColor);
		setLayout(null);
		
		mg = m; // m�� instance �����ͷ� �ٲ��ش�.
		gameL = new GameListener();
		
		int j=0;
		Font fnt = new Font("Verdana",Font.BOLD, 16); // �ַ� ����� ��Ʈ ����
		
		ground = new JPanel();
		ground.setBounds(0,0,GameConstants.Width,GameConstants.Height);
		add(ground); // ��ü �г�
		
		lblBackground = new JLabel();
		lblBackground.setBounds(0,0,GameConstants.Width,GameConstants.Height);
		lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		ground.add(lblBackground); // ����� ��Ÿ���� ��
		
		lpDslot = new JLayeredPane();
		lpDslot.setBounds(240,30,220,133);
		lblBackground.add(lpDslot); // ���� ī��â
		
		lblDslot = new JLabel[5];
		for (int i=0; i<5; i++) {
			lblDslot[i] = new JLabel();
			lblDslot[i].setBounds(0+j,0,100,133);
			lblDslot[i].setVisible(false);
			lpDslot.add(lblDslot[i],new Integer(i)); // �� ī�� �迭���� ������ ������
			j = j+20;
		} // ������ ī�� �迭
		
		lpPslot = new JLayeredPane();
		lpPslot.setBounds(140,300,280,133);
		lblBackground.add(lpPslot); // �÷��̾� ī��â
		
		lblPslot = new JLabel[5];
		for (int i=0; i<5; i++) {
			lblPslot[i] = new JLabel();
			lblPslot[i].setBounds(0+j,0,100,133);
			lblPslot[i].setVisible(false);
			lpPslot.add(lblPslot[i],new Integer(i));
			j = j+20;
		} // �÷��̾��� ī�� �迭
		
		lblPTotal = new JLabel();
		lblPTotal.setFont(fnt);
		lblPTotal.setForeground(Color.yellow);
		lblPTotal.setBounds(240,270,100,30);
		lblPTotal.setVisible(false);
		lblBackground.add(lblPTotal); // �÷��̾��� ī�� ���� ��Ÿ���� ��
		
		lblDTotal = new JLabel();
		lblDTotal.setFont(fnt);
		lblDTotal.setForeground(Color.yellow);
		lblDTotal.setBounds(240,160,100,30);
		lblBackground.add(lblDTotal); // ������ ī�� ���� ��Ÿ���� ��
		
		lblDeck = new JLabel();
		lblDeck.setBounds(550,165,115,133);
		lblDeck.setIcon(new ImageIcon("images/Deck.png"));
		lblBackground.add(lblDeck); // ���� ��Ÿ���� ��
		
		dealerScore = 100;
		playerScore = 100; // ������ �÷��̾��� ���� 100���� �ʱ�ȭ
		
		lblDinfo = new JLabel();
		lblDinfo.setText("<html>Dealer<br>Bet : $10</html>"); // <html> <br> </html> : �ٹٲ��� ����ϱ� ����
		lblDinfo.setFont(fnt);
		lblDinfo.setBounds(500,30,180,42);
		lblDinfo.setForeground(Color.white);
		lblBackground.add(lblDinfo); // ������ ������ ��Ÿ���� ��
		
		lblPinfo = new JLabel();
		lblPinfo.setText("<html>Player<br>Bet : $10</html>"); 
		lblPinfo.setFont(fnt);
		lblPinfo.setBounds(500,360,180,50);
		lblPinfo.setForeground(Color.white);
		lblBackground.add(lblPinfo); // �÷��̾��� ������ ��Ÿ���� ��
		
		lblDscore = new JLabel("Money : $"+dealerScore);
		lblDscore.setFont(fnt);
		lblDscore.setBounds(500,74,180,16);
		lblDscore.setForeground(Color.white);
		lblBackground.add(lblDscore); // ������ �� ��Ÿ���� ��
		
		lblPscore = new JLabel("Money : $"+playerScore);
		lblPscore.setFont(fnt);
		lblPscore.setBounds(500,408,180,16);
		lblPscore.setForeground(Color.white);
		lblBackground.add(lblPscore); // �÷��̾��� ���� ��Ÿ���� ��
		
		lblResult = new JLabel("");
		lblResult.setFont(fnt);
		lblResult.setBounds(270,220,150,20);
		lblResult.setForeground(Color.cyan);
		lblBackground.add(lblResult); // ���â�� ��Ÿ���� ��
		
		btnDeal = new JButton("DEAL");
		btnDeal.setFont(fnt);
		btnDeal.setBounds(20,100,100,50);
		btnDeal.addActionListener(gameL);
		btnDeal.setEnabled(true);
		lblBackground.add(btnDeal); // DEAL��ư ����
		
		btnHit = new JButton("HIT");
		btnHit.setFont(fnt);
		btnHit.setBounds(20,180,100,50);
		btnHit.addActionListener(gameL);
		btnHit.setEnabled(false);
		lblBackground.add(btnHit); // HIT��ư ����
		
		btnStay = new JButton("STAY");
		btnStay.setFont(fnt);
		btnStay.setBounds(20,260,100,50);
		btnStay.addActionListener(gameL);
		btnStay.setEnabled(false);
		lblBackground.add(btnStay); // STAY��ư ����
		
		btnExit = new JButton(GameConstants.Exit);
		btnExit.setFont(fnt);
		btnExit.setBounds(20,400,100,30);
		btnExit.addActionListener(gameL);
		lblBackground.add(btnExit); // EXIT��ư ����
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == btnDeal) { // DEAL��ư�� ������ ��
				
				lblDscore.setText("Money : $"+dealerScore);
				lblPscore.setText("Money : $"+playerScore);
				lblResult.setText("");
				lblDTotal.setText("");
				
				for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
				//�ʱ�ȭ

				if (deck == null || deck.size() < 15) {
            		deck = new Deck();
            		deck.shuffle();
        		} // �� ���� ī����� ������
        		
        		for(int i=0; i<2; i++) {
        			Card newCard = player.dealTo(deck.dealFrom());
        			lblPslot[i].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblPslot[i].setVisible(true);
        		} // �÷��̾��� ī�� 2���� ���� �� �̹��� ����
        		
        		for(int i=0; i<2; i++) {
        			Card newCard = dealer.dealTo(deck.dealFrom());
        			lblDslot[i].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblDslot[i].setVisible(true);
        		} // ������ ī�� 2���� ������ �̹��� ����
        		lblDslot[0].setIcon(new ImageIcon("images/Back.png")); // ������ ù��° ī�带 �޸��� ���̴� �̹��� ����
        		
        		lblPTotal.setText("Total : "+player.value());
        		lblPTotal.setVisible(true); // �÷��̾��� ī�� ���� ǥ��
        		btnDeal.setEnabled(false);
        		btnHit.setEnabled(true);
        		btnStay.setEnabled(true);
			}
			
			else if(obj == btnHit) { // HIT��ư�� ������ ��
				
				if(nplayerHit>4) {
					JOptionPane.showMessageDialog (null, "�ִ� 5����� ���� �� �ֽ��ϴ�.");
				} // ī�尡 5���� �� HIT��ư�� ������ �� ���̾�α�â�� ��
				
				Card newCard = player.dealTo(deck.dealFrom());
        		lblPslot[nplayerHit].setIcon(new ImageIcon("images/" + newCard.toString()));
        		lblPslot[nplayerHit].setVisible(true); // �÷��̾��� ī�带 ������ �̹��� ����
        		
        		lblPTotal.setText("Total : "+player.value()); // ���� ī���� ���� ���� �÷��̾��� ī�� �տ� ǥ��
        		
        		nplayerHit++; // ���� ī�带 �ޱ� ���� nplayerHit�� 1�� ������
        		
        		if(player.value()>21) {
					result();
					btnDeal.setEnabled(true);
        			btnHit.setEnabled(false);
        			btnStay.setEnabled(false);
				}
			} // �÷��̾��� ���� 21�� �Ѿ�� result �Լ� ȣ��
			else if(obj == btnStay) { // STAY��ư�� ������ ��
				
				while (dealer.value()<17 && ndealerHit<5) {
					Card newCard = dealer.dealTo(deck.dealFrom());
        			lblDslot[ndealerHit].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblDslot[ndealerHit].setVisible(true);
					ndealerHit++;
				} // ������ ī�� ���� 17 �̸��̸� ī�带 �����ϰ� �̹��� ����(�ִ� 5�����)
				
				
				result(); // result �Լ� ȣ��
				btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false); 
			}
			
			else if(obj == btnExit){ // EXIT��ư�� ������ ��
				for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
        		
        		dealerScore = 100;
				playerScore = 100;
        		lblDscore.setText("Money : $"+dealerScore);
        		lblPscore.setText("Money : $"+playerScore);
        		lblDTotal.setText("");
        		lblResult.setText("");
        		
        		lblPTotal.setVisible(false);
        		btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false); // ó�� ������ �� �ʱ�ȭ
				mg.getsubPanel().setVisible(true);
				setVisible(false); 
			}
		}
	} 
	
	private void result() {
		
		lblDslot[0].setIcon(new ImageIcon("images/" + dealer.deck[0].toString())); // �޸����� �� ������ ù��° ī�带 ������
		nplayerHit=2;
		ndealerHit=2;
		lblDTotal.setText("Total : "+dealer.value()); // ������ ī�� �� ǥ��
		
		if (player.value()>21) { // �÷��̾��� ���� 21�� �Ѿ�� ��
			lblResult.setText("Player Busts!");
			dealerScore = dealerScore+10;
			playerScore = playerScore-10;
		} else if (dealer.value()>21) { // ������ ���� 21�� �Ѿ�� ��
			lblResult.setText("Dealer Busts!");
			dealerScore = dealerScore-10;
			playerScore = playerScore+10;
		} else if (dealer.value() == player.value()) { // ������ �հ� �÷��̾� ���� ���� ��
			lblResult.setText("Push!");
		} else if (dealer.value() < player.value()) { // ������ ���� �÷��̾��� �պ��� ���� ��
			lblResult.setText("Player Wins!");
			dealerScore = dealerScore-10;
			playerScore = playerScore+10;
		} else if (dealer.value() > player.value()){ // ������ ���� �÷��̾��� �պ��� Ŭ ��
			lblResult.setText("Dealer Wins!");
			dealerScore = dealerScore+10;
			playerScore = playerScore-10;
		}
		
		if(dealerScore == 0) { // ������ ���� 0�� ���� ��
		
        	int result = JOptionPane.showConfirmDialog(null,"You Win ! ��� �Ͻðڽ��ϱ�?"); // ���̾�α� ����
        			
        	if(result == JOptionPane.YES_OPTION) { // YES�� ������ ��
        		
				for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
        		
        		dealerScore = 100;
				playerScore = 100;
        		lblDscore.setText("Money : $"+dealerScore);
        		lblPscore.setText("Money : $"+playerScore);
        		lblDTotal.setText("");
        		lblResult.setText("");
        		
        		lblPTotal.setVisible(false);
        		btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false);
        		
        		// ��� �� �ʱ�ȭ
        	} else if(result == JOptionPane.NO_OPTION) { // NO�� ������ ��
        	
        		for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
        		
        		dealerScore = 100;
				playerScore = 100;
        		lblDscore.setText("Money : $"+dealerScore);
        		lblPscore.setText("Money : $"+playerScore);
        		lblDTotal.setText("");
        		lblResult.setText("");
        		
        		lblPTotal.setVisible(false);
        		btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false);
				
				mg.getsubPanel().setVisible(true);
				ground.setVisible(false);
				
        		// �� �ʱ�ȭ �� ���� ȭ������ �̵�
        	}		
        } else if (playerScore == 0) { // �÷��̾��� ���� 0�� ���� ��
        
       		int result = JOptionPane.showConfirmDialog(null,"You Lose ! ��� �Ͻðڽ��ϱ�?"); // ���̾�α� ���� 
        			
        	if(result == JOptionPane.YES_OPTION) { // YES�� ������ �� 
        		
				for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
        		
        		dealerScore = 100;
				playerScore = 100;
        		lblDscore.setText("Money : $"+dealerScore);
        		lblPscore.setText("Money : $"+playerScore);
        		lblDTotal.setText("");
        		lblResult.setText("");
				
        		lblPTotal.setVisible(false);
        		btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false);
        		
        		// ��� �� �ʱ�ȭ 
        	} else if(result == JOptionPane.NO_OPTION) { // NO�� ������ �� 

        		for(int i=0; i<5; i++) {
        			lblPslot[i].setVisible(false);
        		}
        		for(int i=0; i<5; i++) {
        			lblDslot[i].setVisible(false);
        		}
				player.reset();
				dealer.reset();
        		
        		dealerScore = 100;
				playerScore = 100;
        		lblDscore.setText("Money : $"+dealerScore);
        		lblPscore.setText("Money : $"+playerScore);
        		lblDTotal.setText("");
        		lblResult.setText("");
        		
        		lblPTotal.setVisible(false);
        		btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false);
				
				mg.getsubPanel().setVisible(true);
				ground.setVisible(false);
        			
        		// �� �ʱ�ȭ �� ���� ȭ������ �̵� 
        	}
       	}
	}
}