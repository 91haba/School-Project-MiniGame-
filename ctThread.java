import java.awt.*;
import javax.swing.*;

public class ctThread extends JLabel implements Runnable
{
	//instance data
	private int numCount;
	private Thread numberThread;
	
	//method
	//������
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
	//ī�带 ������ Ƚ���� 0���� �ʱ�ȭ
	public void setinit(int c)	{
		numCount = c;
		setText("Count : "+numCount);
	}
	
	//others
	public void start(int c){
		if(numberThread == null)
			numberThread = new Thread(this);
		numCount = c;
		numberThread.start();	//run �޼ҵ尡 �����.
			
	}//start
	
	public void stop(){
		if(numberThread != null)
			numberThread.stop();
			numberThread = new Thread(this);	//���ο� �����带 ����
	}//stop
	
	public void run(){
		this.setText("Count : "+numCount);	//������ ���������� ����
		setForeground(Color.red);
	
		//exception handling
		try{
//Ƚ���� �����Ҷ����� �����̴� ȿ���� ���� ���� ȿ���� ������� ���������� �ٲ�
			numberThread.sleep(100);
			this.setVisible(false);
			numberThread.sleep(500);
			this.setVisible(true);
			setForeground(Color.black);
		} catch(Exception e){}
		
	}//run()
}//ctThread class