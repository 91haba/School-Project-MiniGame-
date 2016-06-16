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
	private JLayeredPane lpDslot, lpPslot; // 겹쳐진 카드 이미지를 순서대로 나타내기 위해 JLayeredPane 생성
	private JLabel lblDinfo, lblPinfo, lblDscore, lblPscore;
	private JLabel lblDTotal, lblPTotal, lblDeck;
	private JLabel lblBackground;
	private JLabel lblResult;
	private int playerScore, dealerScore; // 플레이어와 딜러의 돈을 나타내는 변수
	private int nplayerHit=2, ndealerHit=2; // 카드를 더 받을 때 카드배열 번호를 나타내는 변수
	private MiniGame mg; // 메인 클래스로 돌아갈 수 있게한다.
	
	private Deck deck; 
	public Player player = new Player("player");
	public Player dealer = new Player("dealer");
	
	private GameListener gameL;
	
	public BlackJack(MiniGame m) {
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(GameConstants.bgColor);
		setLayout(null);
		
		mg = m; // m을 instance 데이터로 바꿔준다.
		gameL = new GameListener();
		
		int j=0;
		Font fnt = new Font("Verdana",Font.BOLD, 16); // 주로 사용할 폰트 생성
		
		ground = new JPanel();
		ground.setBounds(0,0,GameConstants.Width,GameConstants.Height);
		add(ground); // 전체 패널
		
		lblBackground = new JLabel();
		lblBackground.setBounds(0,0,GameConstants.Width,GameConstants.Height);
		lblBackground.setIcon(new ImageIcon("images/Background.jpg"));
		ground.add(lblBackground); // 배경을 나타내는 라벨
		
		lpDslot = new JLayeredPane();
		lpDslot.setBounds(240,30,220,133);
		lblBackground.add(lpDslot); // 딜러 카드창
		
		lblDslot = new JLabel[5];
		for (int i=0; i<5; i++) {
			lblDslot[i] = new JLabel();
			lblDslot[i].setBounds(0+j,0,100,133);
			lblDslot[i].setVisible(false);
			lpDslot.add(lblDslot[i],new Integer(i)); // 각 카드 배열마다 순서를 정해줌
			j = j+20;
		} // 딜러의 카드 배열
		
		lpPslot = new JLayeredPane();
		lpPslot.setBounds(140,300,280,133);
		lblBackground.add(lpPslot); // 플레이어 카드창
		
		lblPslot = new JLabel[5];
		for (int i=0; i<5; i++) {
			lblPslot[i] = new JLabel();
			lblPslot[i].setBounds(0+j,0,100,133);
			lblPslot[i].setVisible(false);
			lpPslot.add(lblPslot[i],new Integer(i));
			j = j+20;
		} // 플레이어의 카드 배열
		
		lblPTotal = new JLabel();
		lblPTotal.setFont(fnt);
		lblPTotal.setForeground(Color.yellow);
		lblPTotal.setBounds(240,270,100,30);
		lblPTotal.setVisible(false);
		lblBackground.add(lblPTotal); // 플레이어의 카드 합을 나타내는 라벨
		
		lblDTotal = new JLabel();
		lblDTotal.setFont(fnt);
		lblDTotal.setForeground(Color.yellow);
		lblDTotal.setBounds(240,160,100,30);
		lblBackground.add(lblDTotal); // 딜러의 카드 합을 나타내는 라벨
		
		lblDeck = new JLabel();
		lblDeck.setBounds(550,165,115,133);
		lblDeck.setIcon(new ImageIcon("images/Deck.png"));
		lblBackground.add(lblDeck); // 덱을 나타내는 라벨
		
		dealerScore = 100;
		playerScore = 100; // 딜러와 플레이어의 돈을 100으로 초기화
		
		lblDinfo = new JLabel();
		lblDinfo.setText("<html>Dealer<br>Bet : $10</html>"); // <html> <br> </html> : 줄바꿈을 사용하기 위해
		lblDinfo.setFont(fnt);
		lblDinfo.setBounds(500,30,180,42);
		lblDinfo.setForeground(Color.white);
		lblBackground.add(lblDinfo); // 딜러의 정보를 나타내는 라벨
		
		lblPinfo = new JLabel();
		lblPinfo.setText("<html>Player<br>Bet : $10</html>"); 
		lblPinfo.setFont(fnt);
		lblPinfo.setBounds(500,360,180,50);
		lblPinfo.setForeground(Color.white);
		lblBackground.add(lblPinfo); // 플레이어의 정보를 나타내는 라벨
		
		lblDscore = new JLabel("Money : $"+dealerScore);
		lblDscore.setFont(fnt);
		lblDscore.setBounds(500,74,180,16);
		lblDscore.setForeground(Color.white);
		lblBackground.add(lblDscore); // 딜러의 돈 나타내는 라벨
		
		lblPscore = new JLabel("Money : $"+playerScore);
		lblPscore.setFont(fnt);
		lblPscore.setBounds(500,408,180,16);
		lblPscore.setForeground(Color.white);
		lblBackground.add(lblPscore); // 플레이어의 돈을 나타내는 라벨
		
		lblResult = new JLabel("");
		lblResult.setFont(fnt);
		lblResult.setBounds(270,220,150,20);
		lblResult.setForeground(Color.cyan);
		lblBackground.add(lblResult); // 결과창을 나타내는 라벨
		
		btnDeal = new JButton("DEAL");
		btnDeal.setFont(fnt);
		btnDeal.setBounds(20,100,100,50);
		btnDeal.addActionListener(gameL);
		btnDeal.setEnabled(true);
		lblBackground.add(btnDeal); // DEAL버튼 생성
		
		btnHit = new JButton("HIT");
		btnHit.setFont(fnt);
		btnHit.setBounds(20,180,100,50);
		btnHit.addActionListener(gameL);
		btnHit.setEnabled(false);
		lblBackground.add(btnHit); // HIT버튼 생성
		
		btnStay = new JButton("STAY");
		btnStay.setFont(fnt);
		btnStay.setBounds(20,260,100,50);
		btnStay.addActionListener(gameL);
		btnStay.setEnabled(false);
		lblBackground.add(btnStay); // STAY버튼 생성
		
		btnExit = new JButton(GameConstants.Exit);
		btnExit.setFont(fnt);
		btnExit.setBounds(20,400,100,30);
		btnExit.addActionListener(gameL);
		lblBackground.add(btnExit); // EXIT버튼 생성
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			if(obj == btnDeal) { // DEAL버튼을 눌렀을 때
				
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
				//초기화

				if (deck == null || deck.size() < 15) {
            		deck = new Deck();
            		deck.shuffle();
        		} // 덱 안의 카드들을 섞어줌
        		
        		for(int i=0; i<2; i++) {
        			Card newCard = player.dealTo(deck.dealFrom());
        			lblPslot[i].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblPslot[i].setVisible(true);
        		} // 플레이어의 카드 2장을 생성 후 이미지 삽입
        		
        		for(int i=0; i<2; i++) {
        			Card newCard = dealer.dealTo(deck.dealFrom());
        			lblDslot[i].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblDslot[i].setVisible(true);
        		} // 딜러의 카드 2장을 생성후 이미지 삽입
        		lblDslot[0].setIcon(new ImageIcon("images/Back.png")); // 딜러의 첫번째 카드를 뒷면이 보이는 이미지 삽입
        		
        		lblPTotal.setText("Total : "+player.value());
        		lblPTotal.setVisible(true); // 플레이어의 카드 합을 표시
        		btnDeal.setEnabled(false);
        		btnHit.setEnabled(true);
        		btnStay.setEnabled(true);
			}
			
			else if(obj == btnHit) { // HIT버튼을 눌렀을 때
				
				if(nplayerHit>4) {
					JOptionPane.showMessageDialog (null, "최대 5장까지 받을 수 있습니다.");
				} // 카드가 5장일 때 HIT버튼을 누르면 이 다이얼로그창이 뜸
				
				Card newCard = player.dealTo(deck.dealFrom());
        		lblPslot[nplayerHit].setIcon(new ImageIcon("images/" + newCard.toString()));
        		lblPslot[nplayerHit].setVisible(true); // 플레이어의 카드를 생성후 이미지 삽입
        		
        		lblPTotal.setText("Total : "+player.value()); // 받은 카드의 값을 더해 플레이어의 카드 합에 표시
        		
        		nplayerHit++; // 다음 카드를 받기 위해 nplayerHit에 1을 더해줌
        		
        		if(player.value()>21) {
					result();
					btnDeal.setEnabled(true);
        			btnHit.setEnabled(false);
        			btnStay.setEnabled(false);
				}
			} // 플레이어의 합이 21이 넘어가면 result 함수 호출
			else if(obj == btnStay) { // STAY버튼을 눌렀을 때
				
				while (dealer.value()<17 && ndealerHit<5) {
					Card newCard = dealer.dealTo(deck.dealFrom());
        			lblDslot[ndealerHit].setIcon(new ImageIcon("images/" + newCard.toString()));
        			lblDslot[ndealerHit].setVisible(true);
					ndealerHit++;
				} // 딜러의 카드 합이 17 미만이면 카드를 생성하고 이미지 삽입(최대 5장까지)
				
				
				result(); // result 함수 호출
				btnDeal.setEnabled(true);
        		btnHit.setEnabled(false);
        		btnStay.setEnabled(false); 
			}
			
			else if(obj == btnExit){ // EXIT버튼을 눌렀을 때
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
        		btnStay.setEnabled(false); // 처음 값으로 다 초기화
				mg.getsubPanel().setVisible(true);
				setVisible(false); 
			}
		}
	} 
	
	private void result() {
		
		lblDslot[0].setIcon(new ImageIcon("images/" + dealer.deck[0].toString())); // 뒷면으로 된 딜러의 첫번째 카드를 보여줌
		nplayerHit=2;
		ndealerHit=2;
		lblDTotal.setText("Total : "+dealer.value()); // 딜러의 카드 합 표시
		
		if (player.value()>21) { // 플레이어의 합이 21을 넘어갔을 때
			lblResult.setText("Player Busts!");
			dealerScore = dealerScore+10;
			playerScore = playerScore-10;
		} else if (dealer.value()>21) { // 딜러의 합이 21을 넘어갔을 때
			lblResult.setText("Dealer Busts!");
			dealerScore = dealerScore-10;
			playerScore = playerScore+10;
		} else if (dealer.value() == player.value()) { // 딜러의 합과 플레이어 합이 같을 때
			lblResult.setText("Push!");
		} else if (dealer.value() < player.value()) { // 딜러의 합이 플레이어의 합보다 작을 때
			lblResult.setText("Player Wins!");
			dealerScore = dealerScore-10;
			playerScore = playerScore+10;
		} else if (dealer.value() > player.value()){ // 딜러의 합이 플레이어의 합보다 클 때
			lblResult.setText("Dealer Wins!");
			dealerScore = dealerScore+10;
			playerScore = playerScore-10;
		}
		
		if(dealerScore == 0) { // 딜러의 돈이 0이 됐을 때
		
        	int result = JOptionPane.showConfirmDialog(null,"You Win ! 계속 하시겠습니까?"); // 다이얼로그 생성
        			
        	if(result == JOptionPane.YES_OPTION) { // YES를 눌렀을 때
        		
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
        		
        		// 모든 값 초기화
        	} else if(result == JOptionPane.NO_OPTION) { // NO를 눌렀을 때
        	
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
				
        		// 값 초기화 후 메인 화면으로 이동
        	}		
        } else if (playerScore == 0) { // 플레이어의 돈이 0이 됐을 때
        
       		int result = JOptionPane.showConfirmDialog(null,"You Lose ! 계속 하시겠습니까?"); // 다이얼로그 생성 
        			
        	if(result == JOptionPane.YES_OPTION) { // YES를 눌렀을 때 
        		
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
        		
        		// 모든 값 초기화 
        	} else if(result == JOptionPane.NO_OPTION) { // NO를 눌렀을 때 

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
        			
        		// 값 초기화 후 메인 화면으로 이동 
        	}
       	}
	}
}