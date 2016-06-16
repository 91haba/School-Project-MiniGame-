public class Deck {
	
	private Card[] deck;
	private int N = 0;
	
	public Deck()
	{
		deck = new Card[52];
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<13; j++) {
				
				deck[N] = new Card(N, j, i +"" + j + ".png");
				N++;
				
			}
		} // 52개의 카드 배열 선언
	}
	
	public Card dealFrom() { // BlackJack class에서 카드 생성할 때 필요한 메소드
        return deck[--N];
    }
    
	public int size() { // 카드 배열 번호 반환 메소드
        return N;
    }
	
	public void shuffle() { // 카드배열을 섞어주는 메소드
		for(int i=0; i<N; i++) {
			int r = (int)(Math.random() * i);
			Card swap = deck[i];
			deck[i] = deck[r];
			deck[r] = swap;
			
		}
	}
	
}