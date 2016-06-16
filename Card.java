public class Card
{
	private int cardNumber;
	private int rank;
	private String front;
	
	public Card(int cardNumber, int rank, String front) {
		this.cardNumber = cardNumber;
		this.rank = rank;
		this.front = front;
	} // ī��迭 ��ȣ, ��, �̹������� ����
	
	public boolean isAce() { // ���̽� ī�� �޼ҵ�
		return rank == 0;
	}
	
	public int rank() { // 
		if (rank==0) { // rank�� 0�� �� 1������ ��ȯ
			return 1;
		}
		if (rank >= 9) { // rank�� 9�̰ų� 9���� Ŭ �� 10������ ��ȯ
			return 10;
		}
		return rank+1; // rank�� 1~8�� �� rank+1������ ��ȯ
	}
	
	public String toString() { // �̹������� ���� ��ȯ �޼ҵ�
		return this.front;
	}
	
}