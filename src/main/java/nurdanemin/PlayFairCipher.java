package nurdanemin;

public class PlayFairCipher {
    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char excludedCharacter = 'J';
    private String[][] charMatrix =  new String[5][5];

    public PlayFairCipher() {
    }


    public void setMatrix() {
        int index = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                if (alphabet.charAt(index) !=excludedCharacter) {
                    charMatrix[i][j] = String.valueOf(alphabet.charAt(index));
                    index++;
                }else{
                    index ++;
                    charMatrix[i][j] = String.valueOf(alphabet.charAt(index));
                    index++;
                }
            }

        }
        printMatrix();
    }

    public void setMatrixWithKeyword(String keyword) {
        this.alphabet = modifyAlphabet(keyword);
        setMatrix();
    }



    public void printMatrix(){
        for (String[] matrix : charMatrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[j] + " ");
                }
            System.out.println();
            }
    }


    public int[] findPositionOnMatrix(String character){
        for (int i=0; i<charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[i].length; j++) {
                if (charMatrix[i][j].equals(character)){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    public String encryptMessage(String plaintext){
        String message = plaintext.toUpperCase().replaceAll("\\s", "");
        String response = "";
        if (message.length()%2==1){
            message = message + "X";
        }

        for (int i=0; i< message.length(); i+=2){
            String firstChar = String.valueOf(message.charAt(i));
            String secondChar = String.valueOf(message.charAt(i+1));

            if (secondChar.equals(firstChar)){
                secondChar = "X";
            }
            int[] locationFirst = findPositionOnMatrix(firstChar);
            int[] locationSecond = findPositionOnMatrix(secondChar);
            

            if (checkIfCharsOnSameRow(locationFirst, locationSecond)){
                locationFirst[1] = (locationFirst[1] +1)%5;
                locationSecond[1] = (locationSecond[1] +1)%5;

            }else if(checkIfCharsOnSameColumn(locationFirst, locationSecond)){
                locationFirst[0] = (locationFirst[0] +1)%5;
                locationSecond[0] = (locationSecond[0] +1)%5;
            }else{
                int a = locationFirst[1];
                locationFirst[1] = locationSecond[1];
                locationSecond[1] = a;
            }
            String encryption =charMatrix[locationFirst[0]][locationFirst[1]] + charMatrix[locationSecond[0]][locationSecond[1]];
            response += encryption;
        }
        System.out.println(response);
        return response;

    }

    boolean checkIfCharsOnSameRow(int[] location1, int[] location2){
        return location1[0] == location2[0];
    }
    boolean checkIfCharsOnSameColumn(int[] location1, int[] location2){
        return location1[1] == location2[1];
    }

    public String decryptMessage(String ciphertext){
        String encryptedMessage = ciphertext.toUpperCase().replaceAll("\\s", "");
        String plaintext = "";


        for (int i=0; i< encryptedMessage.length(); i+=2){
            String firstChar = String.valueOf(encryptedMessage.charAt(i));
            String secondChar = String.valueOf(encryptedMessage.charAt(i+1));
            int[] locationFirst = findPositionOnMatrix(firstChar);
            int[] locationSecond = findPositionOnMatrix(secondChar);

            if (checkIfCharsOnSameRow(locationFirst, locationSecond)){
                locationFirst[1] = (locationFirst[1] -1+5)%5;
                locationSecond[1] = (locationSecond[1] -1+5)%5;

            }else if(checkIfCharsOnSameColumn(locationFirst, locationSecond)){
                locationFirst[0] = (locationFirst[0] -1+5)%5;
                locationSecond[0] = (locationSecond[0] -1+5)%5;
            }else{
                int a = locationFirst[1];
                locationFirst[1] = locationSecond[1];
                locationSecond[1] = a;
            }

            String firstLetter =charMatrix[locationFirst[0]][locationFirst[1]];
            String secondLetter = charMatrix[locationSecond[0]][locationSecond[1]];
            if (secondLetter.equals("X") && i+1 != encryptedMessage.length()-1){
                secondLetter = firstLetter;
            }
            plaintext += firstLetter + secondLetter;
        }
        if (plaintext.charAt(plaintext.length()-1) == 'X'){
            plaintext = plaintext.substring(0, plaintext.length()-1);
        }
        System.out.println(plaintext);
        return plaintext;

    }

    boolean checkIfLetterExistsInKeyword(char letter, String keyword){
        for (int l =0; l<keyword.length(); l++){

            if (keyword.charAt(l)==letter){
                return true;
            }
        }
        return false;
    }

    public String modifyAlphabet(String keyword){
        String modifiedAlhabet = keyword;
        for (int i =0; i<alphabet.length(); i++){
            if (!checkIfLetterExistsInKeyword(alphabet.charAt(i), keyword)){
                modifiedAlhabet += alphabet.charAt(i);
            }


        }
        return modifiedAlhabet;

    }






}
