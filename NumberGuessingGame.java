import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Player {
    private String name;
    private int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        List<Player> scoreboard = new ArrayList<>();

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("Guess a number between 1 and 100.");

            while (!guessed && attempts < 10) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                scanner.nextLine(); 
                
                attempts++;

                if (guess == numberToGuess) {
                    guessed = true;
                    System.out.println("Congratulations, " + playerName + "! You guessed the number correctly in " + attempts + " attempts.");
                    boolean isNewHighScore = updateScoreboard(scoreboard, new Player(playerName, attempts));
                    if (isNewHighScore) {
                        System.out.println("Congratulations, " + playerName + "! You achieved a new high score!");
                    }

                } else if (guess < numberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessed) {
                System.out.println("Oops! You ran out of attempts. The number to guess was: " + numberToGuess);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.nextLine();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
            System.out.println();
        }

        System.out.println("---- Scoreboard ----");
        for (int i = 0; i < scoreboard.size(); i++) {
            Player player = scoreboard.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getScore() + " attempts");
        }

        scanner.close();
    }

    private static boolean updateScoreboard(List<Player> scoreboard, Player player) {
        boolean isNewHighScore = false;

        int playerScore = player.getScore();
        if (scoreboard.isEmpty() || playerScore < scoreboard.get(0).getScore()) {
            scoreboard.clear();
            scoreboard.add(player);
            isNewHighScore = true;
        } else if (playerScore == scoreboard.get(0).getScore()) {
            scoreboard.add(player);
        }

        return isNewHighScore;
    }
}
