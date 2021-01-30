import java.util.*;
class Main
{
    Node head=null;
     int row=20,col=50,score=0;
     char board[][]=new char[20][50];
     char c='l';
    class Node
    {
        int x,y;
        char value;
        Node next;
        Node(int x,int y)
        {
            this.x=x;
            this.y=y;
            value='@';
            next=null;
        }

    }
    
    public void createBoard()
    {
        for(int i=0;i<20;i++)
        {
            for(int j=0;j<50;j++)
            {
                if(i==0 || j==0 || i==19 || j==49)
                {
                   board[i][j]='$';
                }
                else
                {
                    board[i][j]=' ';
                }
            }
        }
    }

    public void createNode()          //snake body
    {
       int x1=12,y1=15;  //initially fix coordinates of the snake
       int c=4;      //length of snake = no. of nodes
       while(c!=0)
       {
           Node n = new Node(x1,y1);
           if(head==null)
           {
               head=n;
           }
           else
           {
               Node n1 = head;
               while(n1.next!=null)
               {
                   n1=n1.next;
               }
               n.value='X';
               n1.next=n;
           }
           x1=x1;
           y1=y1+1;
           c--;
       }
    }


    public void createFood()
    {
        Node food;
        int a=(int)(Math.random() * (row-2));
        int b=(int)(Math.random() * (col-2));
        Node n2=new Node(a,b);
        food=n2;
        food.value='o';
        board[food.x][food.y]=food.value;
    }
    public void movement()
    {
        Scanner s = new Scanner(System.in);
        int x2=0,y2=0;
        char ch;
        char left='l',right='r',top='t',bottom='b';
        System.out.println("  *INSTRUCTIONS*");
        System.out.println("Press l to move left!!");
        System.out.println("Press r to move right!!");
       System.out.println("Press t to move up!!");   //top
       System.out.println("Press b to move down!!");  //bottom
       System.out.println("After pressing key press enter too");
       
        while(true)
        {
             ch=s.next().charAt(0);
            if(ch==left)
            {
                if(c==right)
                {
                    continue;
                }
                else
                {
                    c=left;
                    x2=head.x;
                    y2=(head.y)-1;
                }
            }
            else if(ch==right)
            {
                if(c==left)
                {
                    continue;
                }
                else{
                    c=right;
                    x2=head.x;
                    y2=(head.y)+1;
                }
            }
            else if(ch==top)
            {
                if(c==bottom)
                {
                    continue;
                }
                else{
                    c=top;
                    x2=(head.x)-1;
                    y2=head.y;
                }
            }
            else if(ch==bottom)
            {
                if(c==top)
                {
                    continue;
                }
                else{
                    c=bottom;
                    x2=(head.x)+1;
                    y2=head.y;
                }
            }
            else{
                continue;
            }
            moveBody(x2,y2);
        }
    }
    public void moveBody(int x3,int y3)   //checking conditions
    {
       if(x3==0 && y3==0)
       {
           return ;
       }

       if(x3==0)
       {
           x3=row-2;
       }
       else if(x3==row-1)
       {
           x3=1;
       }

       if(y3==0)
       {
           y3=col-2;
       }
       else if(y3==col-1)
       {
           y3=1;
       }

       if(board[x3][y3]=='X')
       {
           collision();
       }
       if(board[x3][y3]=='o')
       {
           addNode();
           createFood();
           score++;
       }
       moveSnakeBody(x3,y3);
       System.out.print("\033[H\033[2J");
       System.out.flush();
       printBoard();
    }
    public void addNode()
    {
        Node n0=head;
        while(n0.next!=null)
        {
            n0=n0.next;
        }
        Node n01=new Node((n0.x),(n0.y)+1);
        n01.value='X';
        n0.next=n01;
    }
    public void moveSnakeBody(int x3,int y3)
    {
        Node n4= new Node(x3,y3);
        head.value='X';
        n4.next=head;
        head=n4;
        board[n4.x][n4.y]=n4.value;
        Node n6=head;
        Node n7=null;
        while(n6!=null)
        {
            if(n6.next==null)
            {
                n7.next=n6.next;
                break;
            }
            n7=n6;
            n6=n6.next;
        }
        board[n6.x][n6.y]=' ';
        Node n5=head;
        while(n5!=null)
        {
            board[n5.x][n5.y]=n5.value;
            n5=n5.next;
        }
    }
    public void collision()
    {
       System.out.print("\033[H\033[2J");
       System.out.flush();
       char str[]={'G','A','M','E',' ','O','V','E','R'};
       int i=0,j=0;
       for(i=20;i<=28;i++)
       {
           board[10][i]=str[j++];
       }
       /*char str1[]={'S','C','O','R','E',' '};
       j=0;
       for(i=23;i<=28;i++)
       {
           board[10][i]=str1[j++];
       }*/
       for(i=0;i<row;i++)
       {
           for(j=0;j<col;j++)
           {
               if(board[i][j]=='$' || board[i][j]>='A'&& board[i][j]<='Z' && board[i][j]!='X' || board[i][j]>='a' && board[i][j]<='z' && board[i][j]!='o')
               {
                   System.out.print(board[i][j]);
               }
               else{
                   System.out.print(" ");
               }
           }
           System.out.println();
       }
       System.out.println("        You Scored : "+score+" points");
       System.out.println();
       System.out.println("        BETTER LUCK NEXT TIME!!");
       System.exit(0);
    }

    public void printBoard()
    {
        System.out.println("Score:"+score);
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<col;j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println(" ");
        }
    }
    
    public static void main(String[] args)
    {
        Main m = new Main();
        m.createBoard(); 
        m.createNode();
        m.createFood();
        m.movement();
    }
    
}

