package jdbcdemo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuizInterface extends Remote{
	public void addQuestion(String category, int loc, String question, String answer) throws RemoteException;
	public void book() throws Exception;
	public String[] getQuestion(int uCode, String category, int loc) throws RemoteException;
	public void getResult(int uCode, int loc) throws RemoteException;
	public String getAnswer(String sanswer, int uCode, int loc, String category) throws RemoteException;
	public void calculateResult(int uCode, String category, int loc) throws RemoteException;

}
