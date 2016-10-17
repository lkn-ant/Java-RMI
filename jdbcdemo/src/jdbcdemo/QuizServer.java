package jdbcdemo;

import java.rmi.Naming; 

public class QuizServer {

	public static void main(String[] args) {
		try
        {  
				System.out.println("Creating server");
                QuizImplementation stub=new QuizImplementation();  
                Naming.rebind("QuizIntf",stub);  //It binds the remote object to the new name.
                System.out.println("Connected");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }  

	}

}
