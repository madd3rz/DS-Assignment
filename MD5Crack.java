import java.util.Scanner;

public class MD5Crack{
    public static void main(String[] args) {

        int numOfThread;
        String hashedMD5;
        int passwordLength;
        int cores = Runtime.getRuntime().availableProcessors();
        Scanner userInput = new Scanner(System.in);
    
        System.out.print("\nHashed MD5 : ");
        hashedMD5 = userInput.nextLine(); //TODO: check userInput data structure/type
        System.out.print("The number of thread do you want use : ");
        numOfThread = userInput.nextInt(); //TODO: check userInput data type
        System.out.print("The number of character of password that you want to crack : ");
        passwordLength = userInput.nextInt(); //TODO: check userInput data type

		if (numOfThread == 1) {
            MD5Thread pwd = new MD5Thread("1", hashedMD5, passwordLength);
            pwd.run();
        }
        else if (numOfThread >1 && numOfThread <= cores) {
            Threading.start_server(hashedMD5,numOfThread,passwordLength);
        }
        else{
            System.err.println("Invalid number of threads!");
        }

        userInput.close();

    }
}
