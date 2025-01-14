import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static String[] wordsArray;

    public static void main(String[] args) {
       startPlay();
    }

    private static void startPlay() {
        System.out.println("[N]ew game or [E]xit ?");
        String answer1 = scanner.nextLine();
        if (answer1.equalsIgnoreCase("n") || answer1.equalsIgnoreCase("т")) {
            readWordFromFile();
            if (wordsArray == null || wordsArray.length == 0) {
                System.out.println("Файл пуст или не содержит слов.");
                return;
            }
            playGame();
        } else if (answer1.equalsIgnoreCase("e") ||
                   answer1.equalsIgnoreCase("У")) {
            System.out.println("До свидания!");
        }
    }

    private static void readWordFromFile() {
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("word.txt"))) {
            while(bufferedReader.ready()) {
                wordsArray = bufferedReader.readLine().split("\\s+");
            }
        } catch (IOException ex) {
            System.out.println("Ошибка! файл не найден");
        }
    }

    private static void playGame() {
        Random random = new Random();
        int randomWordNumber = random.nextInt(wordsArray.length);
        String wordForPlay = wordsArray[randomWordNumber];
        StringBuilder stringBuilder = new StringBuilder();
        String encodeWordForPlay = wordForPlay.replaceAll(".", "*");

        System.out.println(encodeWordForPlay);
        System.out.println("Введите букву: ");

        int remainingAttempts = 6;

        while (remainingAttempts >= 0) {
            String answeredLetter = scanner.nextLine().trim();
            if (answeredLetter.length() != 1) {
                System.out.println("Пожалуйста, введите только одну букву.");
                continue;
            }

            if (wordForPlay.contains(answeredLetter)) {
                stringBuilder.append(answeredLetter);
                String regularLetter = stringBuilder.toString();
                String encodeWordForAnswer = wordForPlay.replaceAll("[^" + regularLetter + "]", "*");
                System.out.println(encodeWordForAnswer);
                System.out.println("Вы использовали букву " + answeredLetter);

                if (!encodeWordForAnswer.contains("*")) {
                    System.out.println("Ура! Вы выйграли!");
                    return;
                }
            } else {
                System.out.println("Упс, такой буквы нет! Осталось " + remainingAttempts + " попыток.");
                System.out.println(drawGallows(remainingAttempts));
                remainingAttempts--;
            }
        }
        System.out.println("Вы проиграли =(");
    }

    private static String drawGallows(int count) {
        StringBuilder sb = new StringBuilder();
        if (count == 6) {
            sb.append(" ____ ");
        } else if (count == 5) {
            sb.append("""                     
                      |   
                      |   
                      |  
                      | 
                      |
                    __|__
                    """);
        } else if (count == 4) {
            sb.append("""
                      -----
                      |   
                      |   
                      |  
                      |  
                      |
                    __|__
                    """);
        } else if (count == 3) {
            sb.append("""
                      -----
                      |   |
                      |   
                      |  
                      | 
                      |
                    __|__
                    """);
        } else if (count == 2) {
            sb.append("""
                               -----
                               |   |
                               |   O
                               |  
                               |  
                               |
                             __|__
                                                 
                         """);
        } else if (count == 1) {
            sb.append("""
                           -----
                           |   |
                           |   O
                           |  /|\\
                           |  
                           |
                         __|__
                    
                    """);
        } else if (count == 0) {
            sb.append("""
                           -----
                           |   |
                           |   O
                           |  /|\\
                           |  / \\
                           |
                         __|__
                    
                    """);
        }
        return sb.toString();
    }
}


