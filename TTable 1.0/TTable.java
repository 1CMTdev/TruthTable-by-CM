import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class TTable extends JFrame implements ActionListener{
	JTextField maintxt = new JTextField ();
	JTextField klasi = new JTextField ();
	 
	 final JButton b1and = new JButton("^");
	  final JButton b2or = new JButton("v");
	   final JButton b3imp = new JButton("-- >");
	    final JButton b4bicon = new JButton("< - >");
	     final JButton b5excl = new JButton("xor");
	
	  final JButton phara = new JButton("(");
	  final JButton pharb = new JButton(")");
	  
	  final JButton p = new JButton("p");
	  final JButton q = new JButton("q");
	  final JButton r = new JButton("r");
	  
	  final JButton np = new JButton("¬p");
	  final JButton nq = new JButton("¬q");
	  final JButton nr = new JButton("¬r");
	  
	  final JButton rese = new JButton("RESET");
	  final JButton resu = new JButton("RESULT");
	  
	  Font font = new Font("Arial", Font.PLAIN, 12);
	  
   	  final JTable txtres = new JTable(8,10);
 	  final JTable taas = new JTable(1,10);
	  
	  
	  //declaratioonsssssss****
char[] arrp = new char[8];
char[] arrq = new char[8];
char[] arrr = new char[8];
char[] notp = new char[8];
char[] notq = new char[8];
char[] notr = new char[8];


char[] o1 = new char[8];
char[] o2 = new char[8];
char[] o3 = new char[8];
char[] o4 = new char[8];
char[] o5 = new char[8];


char[] arright = new char[8];
char[] arleft = new char[8];


public Stack<Integer> svar  = new Stack<Integer>();
public Stack<Integer> sope = new Stack<Integer>();	
public Stack<Integer> counter = new Stack<Integer>();	
Queue<Integer> pos = new LinkedList<>();	
		
int operate=0,left=0,right=0,ftest=0,ttest=0,classification=0;
int usep=11,useq=11,user=11,usenp=11,usenq=11,usenr=11;
int uo1=11,uo2=11,uo3=11,uo4=11,uo5=11,uo6=11;
String last,s1,s2,s3,s4,s5,rte,lte,ote;





	
/*******************OPERATORS***************************/
 public static String getpar(String str){
        return str.substring(str.indexOf('(')+1,str.indexOf(')'));
    }


public static char and1(char x,char y){
	if(x=='T'&&y=='T'){
	return 'T';
	}
	else
		return 'F';
	}
	//
public static char or2(char x,char y){
	if(x=='F'&&y=='F'){
	return 'F';
	}
	else
		return 'T';
	}
	//
public static char implies3(char x,char y){
	if(x=='T'&&y=='F'){
	return 'F';
	}
	else
		return 'T';
	}
	//
public static char bicon4(char x,char y){
	if(x=='T'&&y=='T'){
	return 'T';
	}
	else if(x=='F'&&y=='F'){
	return 'T'; 
	}
	else
		return 'F';
	}
	//
public static char excl5(char x,char y){
	if(x=='T'&&y=='T'){
	return 'F';
	}
	else if(x=='F'&&y=='F'){
	return 'F'; 
	}
	else
		return 'T';
	}
	//
public static char not6(char x){
	if(x=='T')return 'F';
	else return 'T';
	}	

/*******************END OF OPERATORS***************************/



	/*********************DESIGN*******************************/
	public TTable(){
		 				super("TRUTH TABLE GENERATOR");
						setLocation(0,0);
						setSize(780,570);
						setVisible (true);
						setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						setResizable(false);
		
//DECLAREEEEE
arrq[0]='T';
arrq[1]='T';
arrq[2]='F';
arrq[3]='F';
arrq[4]='T';
arrq[5]='T';
arrq[6]='F';
arrq[7]='F';


//loop for basic propositional variable values 
for(int z=0;z<arrp.length;z++){
	
			if(z<=3){
			arrp[z]='T';	
			}
			else if(z>=4){
			arrp[z]='F';	
			}
			if( z % 2 == 0 ) {
			arrr[z]='T';
			} 
			else {
			arrr[z]='F';   
			}	
	}	


for(int z=5;z>=1;z--){
counter.push(z);
}

for(int z=0;z<=9;z++){
pos.add(z);
}

//END DECLARE
		
						
	
		//background gui
	   setLayout(new BorderLayout());
       setContentPane(new JLabel(new ImageIcon("mainbg.jpg")));
   	   setLayout(new FlowLayout());
   	   setSize(779,569);
       setSize(780,570);


		Container c = getContentPane();
		c.setLayout(null);
	
		//textfields 
				c.add(maintxt);	
				maintxt.setBounds(2,2,440,50);
				maintxt.setEditable(false);
				maintxt.setFont(font);
				
			
				c.add(klasi);	
				klasi.setBounds(140,260,290,30);
				klasi.setEditable(false);
				klasi.setFont(font);
			
			//table
				c.add(txtres);	
				txtres.setBounds(5,380,750,140);
				c.add(taas);	
				taas.setBounds(5,360,750,22);
	
				
			
		
		//variables button
		c.add(p);	
		p.setBounds(10,70,45,45);
		p.addActionListener(this);	
		
		
		c.add(q);	
		q.setBounds(10,120,45,45);
		q.addActionListener(this);	
			
		
		c.add(r);	
		r.setBounds(10,170,45,45);
		r.addActionListener(this);	
			
		
		c.add(np);	
		np.setBounds(60,70,50,45);
		np.addActionListener(this);	
			
		
		c.add(nq);	
		nq.setBounds(60,120,50,45);
		nq.addActionListener(this);	
		
		c.add(nr);	
		nr.setBounds(60,170,50,45);
		nr.addActionListener(this);	
		
				//operators button
				c.add(b1and);	
				b1and.setBounds(140,70,45,45);
				b1and.setBackground(new Color (89,89,89));
				b1and.setForeground(new Color (255,255,255));
				b1and.addActionListener(this);
				c.add(b2or);	
				b2or.setBounds(140,120,45,45);
				b2or.addActionListener(this);
				b2or.setBackground(new Color (89,89,89));
				b2or.setForeground(new Color (255,255,255));
				c.add(b3imp);	
				b3imp.setBounds(190,70,58,45);
				b3imp.addActionListener(this);
				b3imp.setBackground(new Color (89,89,89));
				b3imp.setForeground(new Color (255,255,255));
				c.add(b4bicon);	
				b4bicon.setBounds(190,120,58,45);
				b4bicon.addActionListener(this);
				b4bicon.setBackground(new Color (89,89,89));
				b4bicon.setForeground(new Color (255,255,255));	
				c.add(b5excl );	
				b5excl .setBounds(253,120,58,45);
				b5excl.addActionListener(this);
				b5excl.setBackground(new Color (89,89,89));
				b5excl.setForeground(new Color (255,255,255));	
				
				
				
				//fucntionssss button
				c.add(rese);	
				rese.setBounds(240,200,71,45);
				rese.addActionListener(this);	
				rese.setBackground(new Color (255,255,255));	
					
				c.add(resu);	
				resu.setBounds(343,200,80,45);
				resu.addActionListener(this);
				resu.setBackground(new Color (252,252,157));
					
		//parenthesis button
		c.add(phara);	
		phara.setBounds(140,200,45,45);
		phara.addActionListener(this);	
		c.add(pharb);	
		pharb.setBounds(190,200,45,45);
		pharb.addActionListener(this);	
		

	
		}
	/**********************END OF DESIGN*****************************/
	
	
	
	
	
	
	
	/***********************ACTION*****************************/
	public void actionPerformed(ActionEvent e){



		
		
		//BUTTONS SEGMENTSSSSSS
		//variabbbleessss
		if (e.getSource()==p){
			maintxt.setText(maintxt.getText()+"p");
			svar.push(1);
			if(usep==11){
			usep=pos.remove();}
			}	
		else if (e.getSource()==r){
			maintxt.setText(maintxt.getText()+"r");
			svar.push(3);
			if(user==11){
			user=pos.remove();}
			}
		else if (e.getSource()==q){
			maintxt.setText(maintxt.getText()+"q");
			svar.push(2);
			if(useq==11){
			useq=pos.remove();}
			}	
		else if (e.getSource()==np){
			maintxt.setText(maintxt.getText()+"¬p");
				for(int z=0;z<arrp.length;z++){
				notp[z]=not6(arrp[z]);	
				}
			svar.push(4);
			if(usenp==11){
			if(usep==11){
			usep=pos.remove();}
			usenp=pos.remove();}
			}	
		else if (e.getSource()==nr){
			maintxt.setText(maintxt.getText()+"¬r");
			for(int z=0;z<arrp.length;z++){
			notr[z]=not6(arrr[z]);	
			}
			svar.push(6);
			if(usenr==11){
				if(user==11){
			user=pos.remove();}
			usenr=pos.remove();}
			}
		else if (e.getSource()==nq){
			maintxt.setText(maintxt.getText()+"¬q");
			for(int z=0;z<arrp.length;z++){
			notq[z]=not6(arrq[z]);	
			}
			svar.push(5);
			if(usenq==11){
				if(useq==11){
			useq=pos.remove();}
			usenq=pos.remove();}
			}
			
			
	/**operatorssssss*/
			else if (e.getSource()==b1and){
			maintxt.setText(maintxt.getText()+" ^ ");
			sope.push(1);
			}
			else if (e.getSource()==b2or){
			maintxt.setText(maintxt.getText()+" v ");
			sope.push(2);
			}
			else if (e.getSource()==b3imp){
			maintxt.setText(maintxt.getText()+" -- > ");
			sope.push(3);
			}
			else if (e.getSource()==b4bicon){
			maintxt.setText(maintxt.getText()+" < - > ");
			sope.push(4);
			}
			else if (e.getSource()==b5excl){
			maintxt.setText(maintxt.getText()+" xor ");
			sope.push(5);	
			}
			
			
	/**parenthesis*/
			else if (e.getSource()==phara){
			maintxt.setText(maintxt.getText()+"(");
			}
			
			/*************************************************************************/
			else if (e.getSource()==pharb){
			maintxt.setText(maintxt.getText()+")");

		
		while(!sope.empty()){
		 
		 operate=sope.pop();
		 right=svar.pop();
		 left=svar.pop();
		
		 					//RGHTSIDE
						if(right==1){
						for(int z=0;z<arrp.length;z++){	
						arright[z]=arrp[z];}	
						rte="p";
						}
						else if(right==2){
						for(int z=0;z<arrq.length;z++){	
						arright[z]=arrq[z];}	
						rte="q";
						}
						else if(right==3){	
						for(int z=0;z<arrr.length;z++){	
						arright[z]=arrr[z];}
						rte="r";	
						}
						else if(right==4){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=notp[z];}	
						rte="¬p";
						}
						else if(right==5){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=notq[z];}	
						rte="¬q";
						}
						else if(right==6){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=notr[z];}
						rte="¬r";	
						}
						else if(right==7){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=o1[z];}
						rte=s1;
						}
						else if(right==8){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=o2[z];}
						rte=s2;	
						}
						else if(right==9){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=o3[z];}
						rte=s3;
						}
						else if(right==10){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=o4[z];}
						rte=s4;
						}
						else if(right==11){
						for(int z=0;z<arrr.length;z++){	
						arright[z]=o5[z];}
						rte=s5;
						}else {System.out.println("VALUE OF OPERATE:"+operate);}
						
					
						
						if(left==1){
						for(int z=0;z<arrr.length;z++){	
						arleft[z]=arrp[z];}
						lte="p";
						}
						else if(left==2){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=arrq[z];}
							lte="q";
						}
						else if(left==3){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=arrr[z];}
							lte="r";
						}
						else if(left==4){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=notp[z];}
							lte="¬p";
						}
						else if(left==5){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=notq[z];}
						lte="¬q";
						}
						else if(left==6){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=notr[z];}
							lte="¬r";
						}
						else if(left==7){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=o1[z];}
							lte=s1;
						}
						else if(left==8){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=o2[z];}
							lte=s2;	
						}
						else if(left==9){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=o3[z];}
							lte=s3;	
						}
						else if(left==10){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=o4[z];}
							lte=s4;	
						}
						else if(left==11){
						for(int z=0;z<arrq.length;z++){	
						arleft[z]=o5[z];}
							lte=s5;	
						}else {System.out.println("VALUE OF OPERATE:"+operate);}
			
			//switch case for operation
			if(counter.peek()==1){
			switch(operate){
			case 1:
				for(int z=0;z<arrp.length;z++)
				{
				o1[z]=and1(arleft[z],arright[z]);	
				}
				svar.push(7);
			if(uo1==11){
			uo1=pos.remove();}
				s1=lte+"^"+rte;
				counter.pop();	
				break;
			case 2:
				
				for(int z=0;z<arrp.length;z++)
				{
				o1[z]=or2(arleft[z],arright[z]);	
				}
				svar.push(7);
				if(uo1==11){
				uo1=pos.remove();}
				s1=lte+"v"+rte;
				counter.pop();
				break;
			case 3:
				for(int z=0;z<arrp.length;z++)
				{
				o1[z]=implies3(arleft[z],arright[z]);	
				}	svar.push(7);
				if(uo1==11){
				uo1=pos.remove();}
				s1=lte+"-->"+rte;
				counter.pop();
				break;
			case 4:
				for(int z=0;z<arrp.length;z++)
				{
				o1[z]=bicon4(arleft[z],arright[z]);	
				}	svar.push(7);
				if(uo1==11){
				uo1=pos.remove();}
				s1=lte+"<->"+rte;
				counter.pop();break;
			case 5:
				for(int z=0;z<arrp.length;z++)
				{
				o1[z]=excl5(arleft[z],arright[z]);	
				}if(uo1==11){
				uo1=pos.remove();}
				svar.push(7);
				s1=lte+" xor "+rte;
				counter.pop();break;
			}}/******/
			
			else if(counter.peek()==2){
			switch(operate){
			case 1:
				for(int z=0;z<arrp.length;z++)
				{
				o2[z]=and1(arleft[z],arright[z]);	
				}
				svar.push(8);
				if(uo2==11){
				uo2=pos.remove();}
				s2="("+lte+") ^ ("+rte+")";	
				counter.pop();break;
			case 2:
				for(int z=0;z<arrp.length;z++)
				{
				o2[z]=or2(arleft[z],arright[z]);	
				}
				svar.push(8);
				if(uo2==11){
				uo2=pos.remove();}
				s2="("+lte+") v ("+rte+")";	
				counter.pop();break;
			case 3:
				for(int z=0;z<arrp.length;z++)
				{
				o2[z]=implies3(arleft[z],arright[z]);	
				}	svar.push(8);
				if(uo2==11){
				uo2=pos.remove();}
				s2="("+lte+") -> ("+rte+")";	
				counter.pop();break;
			case 4:
				for(int z=0;z<arrp.length;z++)
				{
				o2[z]=bicon4(arleft[z],arright[z]);	
				}	svar.push(8);
				if(uo2==11){
				uo2=pos.remove();}
				s2="("+lte+") <-> ("+rte+")";	
				counter.pop();break;
			case 5:
				for(int z=0;z<arrp.length;z++)
				{
				o2[z]=excl5(arleft[z],arright[z]);	
				}	svar.push(8);
				if(uo2==11){
				uo2=pos.remove();}
				s2="("+lte+") xor ("+rte+")";	
				counter.pop();break;
			}}/******/
			
			else if(counter.peek()==3){
			switch(operate){
			case 1:
				for(int z=0;z<arrp.length;z++)
				{
				o3[z]=and1(arleft[z],arright[z]);	
				}
				svar.push(9);
				if(uo3==11){
				uo3=pos.remove();}	
					s3="("+lte+") ^ ("+rte+")";	
				counter.pop();break;
			case 2:
				for(int z=0;z<arrp.length;z++)
				{
				o3[z]=or2(arleft[z],arright[z]);	
				}
				svar.push(9);
				if(uo3==11){
				uo3=pos.remove();}
					s3="("+lte+") v ("+rte+")";		
				counter.pop();break;
			case 3:
				for(int z=0;z<arrp.length;z++)
				{
				o3[z]=implies3(arleft[z],arright[z]);	
				}	svar.push(9);
				if(uo3==11){
				uo3=pos.remove();}
					s3="("+lte+") -> ("+rte+")";		
				counter.pop();break;
			case 4:
				for(int z=0;z<arrp.length;z++)
				{
				o3[z]=bicon4(arleft[z],arright[z]);	
				}	svar.push(9);
				if(uo3==11){
				uo3=pos.remove();}
				s3="("+lte+") <-> ("+rte+")";		
				counter.pop();break;
			case 5:
				for(int z=0;z<arrp.length;z++)
				{
				o3[z]=excl5(arleft[z],arright[z]);	
				}	svar.push(9);
				if(uo3==11){
				uo3=pos.remove();}
				s3="("+lte+") xor ("+rte+")";		
				counter.pop();break;
			}}/******/
			
			else if(counter.peek()==4){
			switch(operate){
			case 1:
				for(int z=0;z<arrp.length;z++)
				{
				o4[z]=and1(arleft[z],arright[z]);	
				}
				svar.push(10);
				if(uo4==11){
				uo4=pos.remove();}
					s4="("+lte+") ^ ("+rte+")";			
				counter.pop();break;
			case 2:
				for(int z=0;z<arrp.length;z++)
				{
				o4[z]=or2(arleft[z],arright[z]);	
				}
				svar.push(10);
				if(uo4==11){
				uo4=pos.remove();}
				s4="("+lte+") v ("+rte+")";	
				counter.pop();break;
			case 3:
				for(int z=0;z<arrp.length;z++)
				{
				o4[z]=implies3(arleft[z],arright[z]);	
				}	svar.push(10);
					if(uo4==11){
				uo4=pos.remove();}
					s4="("+lte+") -> ("+rte+")";	
				counter.pop();break;
			case 4:
				for(int z=0;z<arrp.length;z++)
				{
				o4[z]=bicon4(arleft[z],arright[z]);	
				}	svar.push(10);
					if(uo4==11){
				uo4=pos.remove();}
					s4="("+lte+") <-> ("+rte+")";	
				counter.pop();break;
			case 5:
				for(int z=0;z<arrp.length;z++)
				{
				o4[z]=excl5(arleft[z],arright[z]);	
				}	svar.push(10);
					if(uo4==11){
				uo4=pos.remove();}
					s4="("+lte+") xor ("+rte+")";	
				counter.pop();break;
			}}/******/
			
			else if(counter.peek()==5){
			switch(operate){
			case 1:
				for(int z=0;z<arrp.length;z++)
				{
				o5[z]=and1(arleft[z],arright[z]);	
				}
				svar.push(11);
				if(uo5==11){
				uo5=pos.remove();}
					s5="("+lte+") ^ ("+rte+")";		
				counter.pop();break;
			case 2:
				for(int z=0;z<arrp.length;z++)
				{
				o5[z]=or2(arleft[z],arright[z]);	
				}
				svar.push(11);
				if(uo5==11){
				uo5=pos.remove();}
					s5="("+lte+") v ("+rte+")";			
				counter.pop();break;
			case 3:
				for(int z=0;z<arrp.length;z++)
				{
				o5[z]=implies3(arleft[z],arright[z]);	
				}	svar.push(11);
				if(uo5==11){
				uo5=pos.remove();}
					s5="("+lte+") -> ("+rte+")";			
				counter.pop();break;
			case 4:
				for(int z=0;z<arrp.length;z++)
				{
				o5[z]=bicon4(arleft[z],arright[z]);	
				}	svar.push(11);
				if(uo5==11){
				uo5=pos.remove();}
					s5="("+lte+") <-> ("+rte+")";			
				counter.pop();break;
			case 5:
				for(int z=0;z<arrp.length;z++)
				{
				o5[z]=excl5(arleft[z],arright[z]);	
				}	svar.push(11);
				if(uo5==11){
				uo5=pos.remove();}
					s5="("+lte+") xor ("+rte+")";			
				counter.pop();break;
			}}/******/
			
		}//end while
}
			
			
			
			
			
					
	/**reset & Result*/
	else if (e.getSource()==rese){
	maintxt.setText(null);
	svar.clear();
	sope.clear();
	new TTable();
	}
	
else if (e.getSource()==resu){ //START NG RESULT
					last=maintxt.getText();
					
					
					//Basic variables display
					if(usep!=11){
					taas.setValueAt('p',0,usep);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(arrp[z],z,usep);		
						}}else System.out.println("p");
						
						
					if(useq!=11){
					taas.setValueAt('q',0,useq);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(arrq[z],z,useq);		
						}}else System.out.println("q");
						
					if(user!=11){
					taas.setValueAt('r',0,user);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(arrr[z],z,user);		
					}}else System.out.println("r");
					
					if(usenp!=11){
					taas.setValueAt("¬p",0,usenp);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(notp[z],z,usenp);		
					}}else System.out.println("nop");
					
					
					if(usenq!=11){
					taas.setValueAt("¬q",0,usenq);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(notq[z],z,usenq);		
					}}else System.out.println("noq");
					
					if(usenr!=11){
					taas.setValueAt("¬r",0,usenr);
					for(int z=0;z<arrp.length;z++){
					txtres.setValueAt(notr[z],z,usenr);		
					}}else System.out.println("nor");


	if(uo1!=11){
	taas.setValueAt(s1,0,uo1);
	for(int z=0;z<arrp.length;z++){
	txtres.setValueAt(o1[z],z,uo1);		
	}classification=1;}else System.out.println("o1");
	
	if(uo2!=11){
	taas.setValueAt(s2,0,uo2);
	for(int z=0;z<arrp.length;z++){
	txtres.setValueAt(o2[z],z,uo2);		
	}classification=2;}else System.out.println("o2");
	
	if(uo3!=11){
	taas.setValueAt(s3,0,uo3);
	for(int z=0;z<arrp.length;z++){
	txtres.setValueAt(o3[z],z,uo3);		
	}classification=3;}else System.out.println("o3");

	if(uo4!=11){
	taas.setValueAt(s4,0,uo4);
	for(int z=0;z<arrp.length;z++){
	txtres.setValueAt(o4[z],z,uo4);		
	}classification=4;}else System.out.println("o4");
	
	if(uo5!=11){
	taas.setValueAt(s5,0,uo5);
	for(int z=0;z<arrp.length;z++){
	txtres.setValueAt(o5[z],z,uo5);
	}classification=5;	}else System.out.println("o5");



switch(classification){
				case 0:
				System.out.println("No classification.");
				break;
				
				case 1:taas.setValueAt(last,0,uo1);
				for(int z=0;z<arrp.length;z++){
				if(o1[z]=='T'){
				ttest=ttest+1;
				}else {ftest=ftest+1;}
				}
				if(ttest==8){
				klasi.setText("CLASSIFICATION: TAUTOLOGY!");
				}
				else if(ftest==8){
				klasi.setText("CLASSIFICATION: CONTRADICTION!");	
				}else {klasi.setText("CLASSIFICATION: CONTINGENCY!");
				}break;
				
				case 2:taas.setValueAt(last,0,uo2);
				for(int z=0;z<arrp.length;z++){
				if(o2[z]=='T'){
				ttest=ttest+1;
				}else {ftest=ftest+1;}
				}
				if(ttest==8){
				klasi.setText("CLASSIFICATION: TAUTOLOGY!");
				}
				else if(ftest==8){
				klasi.setText("CLASSIFICATION: CONTRADICTION!");	
				}else {klasi.setText("CLASSIFICATION: CONTINGENCY!");
				}break;
				
				case 3:taas.setValueAt(last,0,uo3);
				for(int z=0;z<arrp.length;z++){
				if(o3[z]=='T'){
				ttest=ttest+1;
				}else {ftest=ftest+1;}
				}
				if(ttest==8){
				klasi.setText("CLASSIFICATION: TAUTOLOGY!");
				}
				else if(ftest==8){
				klasi.setText("CLASSIFICATION: CONTRADICTION!");	
				}else {klasi.setText("CLASSIFICATION: CONTINGENCY!");
				}break;
				
				case 4:taas.setValueAt(last,0,uo4);
				for(int z=0;z<arrp.length;z++){
				if(o4[z]=='T'){
				ttest=ttest+1;
				}else {ftest=ftest+1;}
				}
				if(ttest==8){
				klasi.setText("CLASSIFICATION: TAUTOLOGY!");
				}
				else if(ftest==8){
				klasi.setText("CLASSIFICATION: CONTRADICTION!");	
				}else {klasi.setText("CLASSIFICATION: CONTINGENCY!");
				}break;
				
				case 5:taas.setValueAt(last,0,uo5);
				for(int z=0;z<arrp.length;z++){
				if(o5[z]=='T'){
				ttest=ttest+1;
				}else {ftest=ftest+1;}
				}
				if(ttest==8){
				klasi.setText("CLASSIFICATION: TAUTOLOGY!");
				}
				else if(ftest==8){
				klasi.setText("CLASSIFICATION: CONTRADICTION!");	
				}else {klasi.setText("CLASSIFICATION: CONTINGENCY!");
				}break;
				
				
				
				}//end switch
	}//end of result
		
			
			
			}
			
			
			
			
			
		
   
   	/****************END OF ACTION*************************/
   
   
   
   
   
   
   
   /******************MAIN*****************************/
    public static void main (String args[]){
	new TTable();
	}
	/***********END OF MAIN*****************************************/
    
    }



