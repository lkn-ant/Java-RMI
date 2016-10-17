package jdbcdemo;

import java.rmi.*;  
import java.io.*;

public class QuizClient {

	public static void main(String[] args) {
		String category, question, answer, sanswer;
		int aid, loc, uCode, result, ch;
		String[] q = new String[2];
		
		try
        {   
         do
         { 
                QuizIntf stub=(QuizInterface)Naming.lookup("rmi://localhost:5000"); 
                System.out.println("\n1.Add Question\n2.Book Quiz Session\n3.Attempt Quiz\n4.View Result\n5.Exit"); 
                System.out.println("\nEnter the choice");
                
                BufferedReader reader, reader1,reader2,reader3,reader4;

                reader=new BufferedReader(new InputStreamReader(System.in));
                ch=Integer.parseInt(reader.readLine());


                
                switch(ch)
                {
                    case 1:

                        //Store question details in variables.
                        System.out.println("Please enter the category of the question");
            
                        reader1=new BufferedReader(new InputStreamReader(System.in));
                        category=reader1.readLine();
                        
                        System.out.println("Enter the Level of Complexity (1 - 3):");
                     
                        reader2=new BufferedReader(new InputStreamReader(System.in));
                        loc=Integer.parseInt(reader2.readLine());
            
                        System.out.println("Enter Question :");
                   
                        reader3=new BufferedReader(new InputStreamReader(System.in));
                        question=reader3.readLine();
             
                        System.out.println("Enter Answer :");
                        
                        reader4=new BufferedReader(new InputStreamReader(System.in));
                        answer=reader4.readLine();
            
                          
                        stub.addQuestion(category,loc,question,answer); //Send details to server using Client Stub
                        
                        break;

                    case 2:
                        
                        stub.book();
                   
                        break;
                       
                    case 3:
                        //Enter Quiz Session code
                    	System.out.println("Please enter your session code!");
                    	
                    	reader1=new BufferedReader(new InputStreamReader(System.in));
                    	uCode=Integer.parseInt(reader1.readLine());
                    	
                    	System.out.println("Enter a category");
                    	
                    	reader2=new BufferedReader(new InputStreamReader(System.in));
                    	category=reader2.readLine();
                    	
                    	System.out.println("Enter level of complexity");
                    	
                    	reader3=new BufferedReader(new InputStreamReader(System.in));
                    	loc=Integer.parseInt(reader3.readLine());
                    	
                    	q = stub.getQuestion(uCode, category, loc);
                    	aid = Integer.parseInt(q[0]);
                    	
                    	if(q!= null)
                        {
                            System.out.println("Question ID: "+q[0]+"");
                            System.out.println("Question : "+q[1]+"");
                            System.out.println("Answer : ");
                            
                        }
                        else
                        {
                            System.out.println("No question with that category and/or level of complexity can be found in the database.");
                        }
                    	
                    	reader4=new BufferedReader(new InputStreamReader(System.in));
                        sanswer=reader4.readLine();
                        
                        stub.getAnswer(sanswer, uCode, loc, category);
                        stub.calculateResult(uCode, category, loc);
                        
                        break;
                    	
                    case 4:
                    	System.out.println("Please enter your session code!");
                    	
                    	reader1=new BufferedReader(new InputStreamReader(System.in));
                    	uCode=Integer.parseInt(reader1.readLine());
                    	
                    	System.out.println("Enter level of complexity.");
                    	
                    	reader2=new BufferedReader(new InputStreamReader(System.in));
                    	loc=Integer.parseInt(reader1.readLine());
                    	
                    	stub.getResult(uCode, loc);
                        

                        break;
                }
         }while(ch!=5);
        }
        catch(Exception e)
        {
            
        }

	}

}
