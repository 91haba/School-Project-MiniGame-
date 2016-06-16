public class Card
{
	private int cardNumber;
	private int rank;
	private String front;
	
	public Card(int cardNumber, int rank, String front) {
		this.cardNumber = cardNumber;
		this.rank = rank;
		this.front = front;
	} // 카드배열 번호, 값, 이미지파일 제목
	
	public boolean isAce() { // 에이스 카드 메소드
		return rank == 0;
	}
	
	public int rank() { // 
		if (rank==0) { // rank가 0일 때 1값으로 반환
			return 1;
		}
		if (rank >= 9) { // rank가 9이거나 9보다 클 때 10값으로 반환
			return 10;
		}
		return rank+1; // rank가 1~8일 때 rank+1값으로 반환
	}
	
	public String toString() { // 이미지파일 제목 반환 메소드
		return this.front;
	}
	
}