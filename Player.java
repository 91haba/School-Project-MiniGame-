public class Player {
	
	public Card[] deck = new Card[52];
	private int N = 0;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public Card dealTo(Card c) { // BlackJack class���� ī�� ������ �� �ʿ��� �޼ҵ�
		deck[N++] = c;
		return c;
	}
	
	public void reset() { // ī��迭�� ���� �����ִ� �޼ҵ�
		N = 0;
	}
	
	public int value() { // ������ �ִ� ī����� ���� ��Ÿ���� �޼ҵ�
		int val = 0;
		boolean hasAce = false;
		for (int i=0; i<N; i++) {
			val = val + deck[i].rank(); // Card�� rank���� �޾ƿ� value�� �����ش�.
			if (deck[i].isAce()) {
				hasAce = true;
			}
		} 
		if (hasAce && (val <= 11)) {
			val = val + 10;
		} // ī���� ���� 11 ������ �� ���̽�ī�� �� 11�� ��ȯ�Ͽ� val���� �����ش�.
		return val;
	}
}