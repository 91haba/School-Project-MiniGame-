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
		} // 52���� ī�� �迭 ����
	}
	
	public Card dealFrom() { // BlackJack class���� ī�� ������ �� �ʿ��� �޼ҵ�
        return deck[--N];
    }
    
	public int size() { // ī�� �迭 ��ȣ ��ȯ �޼ҵ�
        return N;
    }
	
	public void shuffle() { // ī��迭�� �����ִ� �޼ҵ�
		for(int i=0; i<N; i++) {
			int r = (int)(Math.random() * i);
			Card swap = deck[i];
			deck[i] = deck[r];
			deck[r] = swap;
			
		}
	}
	
}