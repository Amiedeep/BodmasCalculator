
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Calculator extends JFrame implements ActionListener
{
    Button btOperand[];
    Button btOperation[];
    Button btEqual;
    Button btClear;
    Button btRight;
    Button btLeft;
    int x,y;
    int inputA,inputB;
    JTextField tx;
    String operation;
    float result;
    AdvanceCalculator ac;
    Label title;
    Calculator()
    {
        this.setTitle("Calculator(AMIE)");
        tx= new JTextField();
        title= new Label("BODMAS CALCULATOR");
        this.setLayout(null);
        x=40;
        y=80;
        ac=new AdvanceCalculator();
        btOperand=new Button[10];
        btOperation=new Button[5];
        btEqual=new Button("=");
        btClear=new Button("CLR");
        btLeft=new Button("(");
        btRight=new Button(")");
        this.setVisible(true);
        this.setSize(255,360);
        this.setLocation(350, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        int n=0;
        for (int i = 1; i < 10; i++) 
        {
            
            if(n==3)
            {
                y=y+40;
                x=40;
                n=0;
            }
            
            btOperand[i]=new Button(""+i);
            btOperand[i].setBounds(x, y,40,40);
            btOperand[i].setVisible(true);
            x=x+40;
            n=n+1;
        }
        btOperand[0]=new Button("0");
        btOperand[0].setBounds(80,200,40,40);
        btOperand[0].setVisible(true);
        btOperation[0]=new Button("+");
        btOperation[1]=new Button("-");
        btOperation[2]=new Button("*");
        btOperation[3]=new Button("/");
        btOperation[4]=new Button("^");
        x=160;
        y=80;
        n=0;
        for (int i = 0; i < 4; i++) 
        {
            if(n>2)
            {
                x=40;
                y=200;
            }
            btOperation[i].setBounds(x,y,40,40);
            btOperation[i].setVisible(true);
            y=y+40;
            n=n+1;
        }
        btOperation[4].setBounds(120,200,40,40);
        btOperation[4].setVisible(true);
        for (int i = 0; i < 10; i++) 
        {
            add(btOperand[i]);
            btOperand[i].addActionListener(this);
        }
        for (int i = 0; i < 5; i++) 
        {
            add(btOperation[i]);
            btOperation[i].addActionListener(this);
        }
        btEqual.setBounds(160,200,40,40);
        btEqual.setVisible(true);
        btClear.setBounds(40,240,80,40);
        btClear.setVisible(true);
        btLeft.setBounds(120,240,40,40);
        btLeft.setVisible(true);
        btRight.setBounds(160,240,40,40);
        btRight.setVisible(true);
        title.setBounds(50,0,280,40);
        title.setForeground(Color.RED);
        title.setVisible(true);
        add(btClear);
        add(title);
        add(btEqual);
        add(btLeft);
        add(btRight);
        tx.setBounds(40,40,160,40);
        add(tx);
        btEqual.addActionListener(this);
        btClear.addActionListener(this);
        btLeft.addActionListener(this);
        btRight.addActionListener(this);
    }
    public static void main(String[] args) 
    {
        Calculator cl=new Calculator();
    }

    public void actionPerformed(ActionEvent e) 
    {
        String s="";
        outer:while(true)
        {
            if(e.getSource()==btClear)
            {
                tx.setText("");
                break outer;
            }
            if(e.getSource()==btEqual)
                {
                    String temp=tx.getText();
                    if(temp.indexOf("=")!=-1)
                    {
                        System.out.println(temp.substring(temp.lastIndexOf("=")+1));
                        result=ac.evaluatePostfix(ac.findPostfix(temp.substring(temp.lastIndexOf("=")+1)));
                        tx.setText(tx.getText()+"="+result);
                        break outer;
                    }
                    result=ac.evaluatePostfix(ac.findPostfix(tx.getText()));
                    tx.setText(tx.getText()+"="+result);
                    break outer;
                }
            if(e.getSource()==btLeft)
                {
                    tx.setText(tx.getText()+"(");
                    break outer;
                }
            if(e.getSource()==btRight)
                {
                    tx.setText(tx.getText()+")");
                    break outer;
                }
            for (int i = 0; i < 10; i++) 
            {
                if(e.getSource()==btOperand[i])
                {
                    s=tx.getText();
                    tx.setText(s+btOperand[i].getLabel());
                    break outer;
                }
            }
            for (int i = 0; i < 5; i++) 
            {
                if(e.getSource()==btOperation[i])
                {
                    s=tx.getText();
                    tx.setText(s+btOperation[i].getLabel());
                    break outer;
                }
            }
        }
            
    }
}
