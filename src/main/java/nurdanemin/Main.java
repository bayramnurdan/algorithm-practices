package nurdanemin;

public class Main {
    public static void main(String[] args) {
        PlayFairCipher cipher = new PlayFairCipher();
        cipher.setMatrixWithKeyword("LIZARD");
        cipher.encryptMessage("BALLOON");
        cipher.decryptMessage(cipher.encryptMessage("BALLOON"));


    }
}