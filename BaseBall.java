import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class BaseBall extends JPanel
{
	

	
	
	private JPanel strBg,baBg;
	private JLabel Strike,Ball,Title, strMark, baMark, Grade;// �߰����� - 1. Grade(������� �õ� Ƚ���� ���� ��޺з�)
	private JLabel Out, fourBall;// �߰����� - 2.Ư��ī��Ʈ(4strike or 4ball ���޽� �ش� �г�(strBg,baBg) ��� ����)
	private JButton Throw,Reset,Exit; // �߰����� - 3. ���� �ʱ�ȭ ��ư �߰�
	private JTextField inNum;
	private List enteredNum;
	private MiniGame mg;

	private int strike, ball, cnt;
	private int check[];
	private int num[];
	
	
	private BaseListener BaseL;
	
	public BaseBall(MiniGame m){
		
		mg = m;
	//JPanel
		setPreferredSize(new Dimension(GameConstants.Width,GameConstants.Height));
		setBackground(new Color(0,108,16));
		setLayout(null);
		
	    strike = 0;
		ball = 0;
		cnt = 0;
		
		check = new int[4];// ��ǻ���� ���� �� ������ ���� �޸� �Ҵ�
		num = new int[4];//����ڰ� �Է��� ���ڸ� ������ �޸� �Ҵ�
		
		
		BaseL = new BaseListener();
		
	 
	
		
		strBg = new JPanel();
		strBg.setBounds(30,50,200,300);
		strBg.setBackground(new Color(255,255,128));
		strBg.setLayout(null);
		add(strBg);
		
		
		baBg = new JPanel();
		baBg.setBounds(480,50,200,300);
		baBg.setBackground(new Color(255,255,128));
		baBg.setLayout(null);
		add(baBg);
		
		Font fnt = new Font("Verdana",Font.BOLD,24);
	

	//JLabel	
		
		Title = new JLabel("Base Ball Game");
		Title.setFont(fnt);
		Title.setBounds(255,10,230,40);
		Title.setForeground(Color.black);
		add(Title);
		
		
		Strike = new JLabel("Strike");
		Strike.setFont(fnt);
		Strike.setBounds(60,210,80,80);
		Strike.setForeground(Color.black);
		strBg.add(Strike);
		
		strMark = new JLabel(""+cnt);
		strMark.setFont(new Font("Verdana",Font.BOLD,80));
		strMark.setBounds(75,60,100,100);
		strMark.setForeground(Color.red);
		strBg.add(strMark);
		
		Ball = new JLabel("Ball");
		Ball.setFont(fnt);
		Ball.setBounds(70,210,80,80);
		Ball.setForeground(Color.black);
		baBg.add(Ball);
		
		baMark = new JLabel(""+cnt);
		baMark.setFont(new Font("Verdana",Font.BOLD,80));
		baMark.setBounds(75,60,100,100);
		baMark.setForeground(Color.red);
		baBg.add(baMark);
		
		Grade = new JLabel();
		Grade.setFont(new Font("Verdana",Font.BOLD,22));
		Grade.setBounds(20,310,230,110);
		Grade.setForeground(Color.black);
		add(Grade);
		
		Out = new JLabel();
		Out.setBounds(0,0,200,300);
		Out.setIcon(new ImageIcon("images/Out.jpg"));
		Out.setVisible(false);
		strBg.add(Out);
		
		fourBall = new JLabel();
		fourBall.setBounds(0,0,200,300);
		fourBall.setIcon(new ImageIcon("images/fourBall.jpg"));
		fourBall.setVisible(false);
		baBg.add(fourBall);
		
		
		
	//JButton
	
	   Font fnt2 = new Font("Verdana",Font.ITALIC,16);
	  
	   Throw = new JButton(new ImageIcon("images/Throw.png"));
	   Throw.setFont(fnt2);
	   Throw.setBounds(240,420,100,30);;
	   Throw.addActionListener(BaseL);
	   add(Throw);
		
	  
	   Reset = new JButton(new ImageIcon("images/Reset.jpg"));
	   Reset.setFont(fnt2);
	   Reset.setBounds(360,420,100,30);
	   Reset.setForeground(Color.blue);
	   Reset.addActionListener(BaseL);
	   add(Reset);
	   
	   Exit = new JButton(GameConstants.Exit);
	   Exit.setFont(fnt2);
	   Exit.setBounds(600,470,100,30);
	   Exit.setForeground(Color.cyan);
	   Exit.addActionListener(BaseL);
	   add(Exit);
	   
	//Text
	
	   inNum = new JTextField();
	   inNum.setFont(fnt2);
	   inNum.setBounds(240,380,220,30);
	   inNum.addActionListener(BaseL);
	   add(inNum);
	   
	//List
	
	   enteredNum = new List();
	   enteredNum.setBounds(260,50,190,300);
	   add(enteredNum);
		
		
		
		}//BaseBall()
	
	 public void randomNum(){
	    
	    //4�ڸ��� ���� ���� 
	 	check[0] = (int)(Math.random()*9+1);	
	 	check[1] = (int)(Math.random()*9+1);
	 	check[2] = (int)(Math.random()*9+1);	
	 	check[3] = (int)(Math.random()*9+1);
	 //4�ڸ��� ���ڰ� �ߺ��� ���� �������� �ʰ� ���� ���� 
	 if(check[0]==check[1] || check[0]==check[2] || check[0]==check[3] || check[1]==check[2] || check[1]==check[3] || check[2]==check[3])
	    {
	    	while(check[0]==check[1] || check[0]==check[2] || check[0]==check[3] || check[1]==check[2] || check[1]==check[3] || check[2]==check[3]){
	    		
	    check[0] = (int)(Math.random()*9+1);	
	 	check[1] = (int)(Math.random()*9+1);
	 	check[2] = (int)(Math.random()*9+1);	
	 	check[3] = (int)(Math.random()*9+1);
	    		
	    		}

	    	}
	 	
	 	
	 	System.out.println(check[0]);
	 	System.out.println(check[1]);
	 	System.out.println(check[2]);
	 	System.out.println(check[3]);
	 	} // randomNum()
	
private class BaseListener implements ActionListener{	
	
	public void actionPerformed(ActionEvent e){
		
		Object obj = e.getSource();
		
		if(obj == Throw || obj == inNum){
			if(ball!=4)//4 ball �� �ƴҶ��� ���� �г��� setVisible(false);
			fourBall.setVisible(false);
	        int length = inNum.getText().length();
	        cnt++;//������� �õ� Ƚ�� (Grade ������ ���� ����) 
	        if(length==4){
	        	
	        
			    
	        	//�Էµ� ������ �ڸ��� ���� int�� ĳ����
			num[0] = Integer.parseInt(inNum.getText().substring(0,1));
			num[1] = Integer.parseInt(inNum.getText().substring(1,2));
			num[2] = Integer.parseInt(inNum.getText().substring(2,3));
			num[3] = Integer.parseInt(inNum.getText().substring(3,4));
			  //�ߺ����� �Է¹���  
			if(num[0] == num[1] || num[0] == num[2] || num[0] == num[3] ||
	           num[1] == num[2] || num[1] == num[3] || num[2] == num[3]    ){  
			    
			   num[0] = 0;
			   num[1] = 0;
			   num[2] = 0;
			   num[3] = 0; 
			    JOptionPane.showMessageDialog(null,"�ߺ����ڴ� �Է� �� �� �����ϴ�!");
			   }
	
			inNum.requestFocus();
			}
		   else{
   
JOptionPane.showMessageDialog(null,"4�ڸ��� '����'�� �Է¹޽��ϴ�.");//�߰����� - 4. 4�ڸ� �̻��� �����̿��� ���� �� ���� �Է½� ����â ���.
	
	}
			

  
	 //Basball Game �⺻ �˰���
		for(int i=0;i<4;i++){ 
				
		   
			for(int j=0;j<4;j++){
				
				if(check[i] == num[j]){
					
					if(i==j){
				   
				    
					strike++;
			
				   
		if(strike == 4){//4strike�� ��
		
		 
		Font fnt3 = new Font("Verdana",Font.BOLD,16);
		Grade.setFont(fnt3);
		Grade.setForeground(Color.white);
		
		//cnt ���ں� ������� ���	
		if(cnt<=5)
		Grade.setText("You are perfect pitcher !");
		else if(cnt>5 && cnt<11)
		Grade.setText("You are great pitcher !");
		else if(cnt>=11 && cnt<15)
		Grade.setText("You are good pitcher! ");
		else if(cnt>=15 && cnt<20)
		Grade.setText("You are pitcher !");
		else
		Grade.setText("Are you pitcher?");
	
		
		enteredNum.add("You Win!!");
		inNum.requestFocus();
		Out.setVisible(true);
	
	
		
		}//if(4strike)
			
				}//if(i==j)
					else
					{
					
						ball++;
						if(ball==4){
							fourBall.setVisible(true);
						
							
							}
	
					}//(i!=j),ball
					
			
			
			}//if(check[i] == num[j])
			
		
				strMark.setText(""+strike);//show strike cnt
				  baMark.setText(""+ball);//show ball cnt
				  
	if(strike==4){
	int result = JOptionPane.showConfirmDialog(null,"Reset?");
	if(result == JOptionPane.YES_OPTION){
 	 strike =0;
 	 ball = 0;
 	 cnt=0;
 	 Grade.setText("");
 	 baMark.setText(""+ball);
 	 strMark.setText(""+strike);
 	 randomNum();
 	 inNum.setText("");
 	 enteredNum.removeAll();

 	 Out.setVisible(false);}
 	 else{
 	 Throw.setEnabled(false);
 	 inNum.setEnabled(false);}
 	 
 
				  }//f(strike==4)
				}//for j
		}//for i
      
	enteredNum.add("Entered: \""  + inNum.getText() + "\" �� " + strike + "S " + ball + "B");//���� ��� List�� ���� �Է��� ���� ����	

			strike=0;
			ball=0;
			
	inNum.setText("");//�����Է¶� �������� �ʱ�ȭ 
	
  
			
 }//if 
 
 
 else if(obj==Reset)
   {
 
 	 strike =0;
 	 ball = 0;
 	 cnt=0;
 	 Grade.setText("");
 	 baMark.setText(""+ball);
 	 strMark.setText(""+strike);
 	 randomNum();
 	 inNum.setText("");
 	 enteredNum.removeAll();
 	 Throw.setEnabled(true);
 	 inNum.setEnabled(true);
 	 
 	 
 	 Out.setVisible(false);

 	
 	 
 	}// Reset
 	
 else if(obj == Exit)
 {
   //data �ʱ�ȭ  
 	 strike =0; 
 	 ball = 0;
 	 cnt=0;
 	 Grade.setText("");
 	 baMark.setText(""+ball);
 	 strMark.setText(""+strike);
 	 inNum.setText("");
 	 enteredNum.removeAll();
 	 
 	 setVisible(false);
 	 mg.getsubPanel().setVisible(true);
 	
 	
}//Exit
			
      }//actionPerformed
		
   } // BaseListener class
   
}//BaseBall class
