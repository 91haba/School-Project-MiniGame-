import java.awt.*;
import javax.swing.*;

public class ctThread extends JLabel implements Runnable
{
	//instance data
	private int numCount;
	private Thread numberThread;
	
	//method
	//생성자
	public ctThread(){
		numCount = 0;
		numberThread = null;
	}
	public ctThread(int c){
		numCount = c;
		numberThread = null;
		setText("Count : "+numCount);
	}
	//get/set method of variable numCount
	public int getCount()	{ return numCount;}
	public void setCount(int c) { numCount = c;}
	//카드를 뒤집는 횟수를 0으로 초기화
	public void setinit(int c)	{
		numCount = c;
		setText("Count : "+numCount);
	}
	
	//others
	public void start(int c){
		if(numberThread == null)
			numberThread = new Thread(this);
		numCount = c;
		numberThread.start();	//run 메소드가 실행됨.
			
	}//start
	
	public void stop(){
		if(numberThread != null)
			numberThread.stop();
			numberThread = new Thread(this);	//새로운 스레드를 생성
	}//stop
	
	public void run(){
		this.setText("Count : "+numCount);	//색깔을 빨강색으로 변경
		setForeground(Color.red);
	
		//exception handling
		try{
//횟수가 증가할때마다 깜빡이는 효과를 집어 넣음 효과가 끝나고는 검정색으로 바뀜
			numberThread.sleep(100);
			this.setVisible(false);
			numberThread.sleep(500);
			this.setVisible(true);
			setForeground(Color.black);
		} catch(Exception e){}
		
	}//run()
}//ctThread class