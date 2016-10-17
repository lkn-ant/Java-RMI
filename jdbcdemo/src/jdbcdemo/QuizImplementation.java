package jdbcdemo;

import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.util.Random;

public class QuizImplementation extends UnicastRemoteObject implements
		QuizInterface {
	/**
	 * 
	 */
	
	QuizImplementation() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String str[]= new String[2];

	public void addQuestion(String category, int loc, String question,
			String answer) throws RemoteException {
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";

		try {
			// Get connection to the database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// Create a statement
			Statement myStmt = myConn.createStatement();

			// Execute SQL Query
			String sql = "insert into answers"
					+ "(category, loc, question, answer)" + "values ("
					+ category + ", " + loc + ", " + question + ", " + answer
					+ ") ";

			myStmt.executeUpdate(sql);

			System.out.println("Question Added");

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void book() throws Exception {
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(99999999);
		System.out.println("Your session code is: " + randomInt);
		System.out.println("Please write down and save this code");
		
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";

		try {
			// Get connection to the database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// Create a statement
			Statement myStmt = myConn.createStatement();

			// Execute SQL Query
			String sql = "CALL `rmi_quiz`.`insertAnswers_procedure`("+randomInt+");";

			myStmt.executeUpdate(sql);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public String[] getQuestion(int uCode, String category, int loc)
			throws RemoteException {
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";
		
		

		try {

			// 1. Get a connection to database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			// 3. Execute SQL Query
			ResultSet myRs = myStmt
					.executeQuery("select * from rmi_quiz.answers "
							+"where rmi_quiz.answers.category="+category+" "
							+"and rmi_quiz.answers.loc="+loc+" "
							+"and rmi_quiz.answers.ucode="+uCode+" ;");
			// 4. Process the result set
			if(myRs.next())
            {
                    str[0]=Integer.toString(myRs.getInt("aid"));
                    str[1]=myRs.getString("question");

            }
            else
            {
                str = null;
            }
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return(str);
	}

	public void getResult(int uCode, int loc) throws RemoteException {
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";

		try {

			// 1. Get a connection to database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// 2. Create a statement
			Statement myStmt = myConn.createStatement();
			// 3. Execute SQL Query
			ResultSet myRs = myStmt
						.executeQuery("select sum(rmi_quiz.answers.result) as score"
						+"from rmi_quiz.answers where "
						+"rmi_quiz.answers.ucode="+uCode+" "
						+"and rmi_quiz.answers.loc="+loc+" ;");
			// 4. Process the result set
			while (myRs.next()) {
				System.out.println(myRs.getString("score"));
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public String getAnswer(String sanswer, int uCode, int loc, String category) throws RemoteException {
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";

		try {
			// Get connection to the database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// Create a statement
			Statement myStmt = myConn.createStatement();

			// Execute SQL Query
			String sql = 	"update rmi_quiz.answers "
							+"set rmi_quiz.answers.sanswer = "+sanswer+" "
							+"where rmi_quiz.answers.category= "+category+" "
							+"and rmi_quiz.answers.loc= "+loc+" "
							+"and rmi_quiz.answers.uCode= "+uCode+";";

			myStmt.executeUpdate(sql);

			System.out.println("Answer Saved");

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public void calculateResult(int uCode, String category, int loc) {
		String url = "jdbc:mysql://localhost:5000/rmi_quiz";
		String user = "root";
		String password = "root";

		try {
			// Get connection to the database
			Connection myConn = DriverManager
					.getConnection(url, user, password);

			// Create a statement
			Statement myStmt = myConn.createStatement();

			// Execute SQL Query
			String sql = 	"CALL `rmi_quiz`.`getResult2_procedure`("+str[0]+");";

			myStmt.executeUpdate(sql);

			System.out.println("Answer Saved");

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}



}
