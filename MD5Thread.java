import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Thread extends Thread {

    String hashedMD5;
    String interval;
    static int cmp = 0;
    int id = 0;
    static boolean isFound = false;
    int passwordLength;

    public MD5Thread(String i, String hash, int passLength) {
        interval = i;
        id = cmp;
        hashedMD5 = hash;
        cmp++;
        passwordLength = passLength;
    }

    @Override
    public void run() {
        int a;
        int b;

        // this method takes an interval and start brute force search on it
        String[] A = interval.split("-");
        try {
            a = Integer.parseInt(A[0]);
            b = Integer.parseInt(A[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            // for numOfThread == 1 only
            a = 33;
            b = 126;
        }

        // making search on the first character from a to b
        double timesStart = System.currentTimeMillis();
        switch (passwordLength) {
        case 2:
            TwoCharPassword(a, b, timesStart);
            break;
        case 3:
            ThreeCharPassword(a, b, timesStart);
            break;
        case 4:
            FourCharPassword(a, b, timesStart);
            break;
        case 5:
            FiveCharPassword(a, b, timesStart);
            break;
        default:
            System.out.println("You entered invalid length of password.");
        }

        //TODO: check if password not found in the userinput char length then syserr msg

    }

    public static String getMD5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;

        }

        // for specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void passwordChecking(String password, double timesStart) {
        if (hashedMD5.equals(getMD5(password))) {
            System.out.println("Password found : " + password + " by Thread " + id);
            double timesEnd = System.currentTimeMillis();
            double totalTime = (timesEnd - timesStart) / 1000 / 60;
            System.out.printf("Time : %.5f minutes \n", totalTime);
            isFound = true;
            Threading.stop_threads();
        }
    }

    private void TwoCharPassword(int a, int b, double timesStart) {
        for (int i = a; i < b; i++) {
            char c = (char) i;
            for (int n = 33; n < 127; n++) {
                char v = (char) n;
                String word = c + "" + v;
                passwordChecking(word, timesStart);
            }
        }
    }

    private void ThreeCharPassword(int a, int b, double timesStart) {
        for (int i = a; i < b; i++) {
            char c = (char) i;
            for (int m = 33; m < 127; m++) {
                char x = (char) m;
                for (int n = 33; n < 127; n++) {
                    char v = (char) n;
                    String word = c + "" + x + "" + v;
                    passwordChecking(word, timesStart);
                }
            }
        }
    }

    private void FourCharPassword(int a, int b, double timesStart) {
        for (int i = a; i < b; i++) {
            char c = (char) i;
            for (int l = 33; l < 127; l++) {
                char r = (char) l;
                for (int m = 33; m < 127; m++) {
                    char x = (char) m;
                    for (int n = 33; n < 127; n++) {
                        char v = (char) n;
                        String word = c + "" + r + "" + x + "" + v;
                        // System.out.println(computer + ":" + id + " : " + word);
                        // if we find it the stop flag is true
                        passwordChecking(word, timesStart);

                    }
                }
            }
        }
    }

    private void FiveCharPassword(int a, int b, double timesStart) {
        for (int i = a; i < b; i++) {
            char c = (char) i;
            for (int k = 33; k < 127; k++) {
                char e = (char) k;
                for (int l = 33; l < 127; l++) {
                    char r = (char) l;
                    for (int m = 33; m < 127; m++) {
                        char x = (char) m;
                        for (int n = 33; n < 127; n++) {
                            char v = (char) n;
                            String word = c + "" + e + "" + r + "" + x + "" + v;
                            // System.out.println(computer + ":" + id + " : " + word);
                            // if we find it the stop flag is true
                            passwordChecking(word, timesStart);

                        }
                    }
                }
            }
        }
    }

}
