public class Player {
	
	public Card[] deck = new Card[52];
	private int N = 0;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Card dealTo(Card c) { // BlackJack class에서 카드 생성할 때 필요한 메소드
		deck[N++] = c;
		return c;
	}
	
	public void reset() { // 카드배열을 리셋 시켜주는 메소드
		N = 0;
	}
	
	public int value() { // 가지고 있는 카드들의 합을 나타내는 메소드
		int val = 0;
		boolean hasAce = false;
		for (int i=0; i<N; i++) {
			val = val + deck[i].rank(); // Card의 rank값을 받아와 value에 더해준다.
			if (deck[i].isAce()) {
				hasAce = true;
			}
		} 
		if (hasAce && (val <= 11)) {
			val = val + 10;
		} // 카드의 합이 11 이하일 때 에이스카드 값 11로 반환하여 val값에 더해준다.
		return val;
	}
}