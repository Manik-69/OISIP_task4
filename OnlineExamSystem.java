import java.util.Scanner;

public class OnlineExamSystem {
    private String studentName;
    private String studentID;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAnswers;
    private int[] correctAnswers;

    public OnlineExamSystem() {
        this.isLoggedIn = false;
        this.timeRemaining = 10;
        this.questionCount = 10;
        this.userAnswers = new int[questionCount];
        this.correctAnswers = new int[questionCount];
        for (int i = 0; i < questionCount; i++) {
            correctAnswers[i] = (int) Math.round(Math.random());
        }
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("****** Registration ******");
        System.out.print("Enter Your Name: ");
        this.studentName = scanner.nextLine();
        System.out.print("Enter Your Student ID: ");
        this.studentID = scanner.nextLine();
        System.out.println("Registration successful. Please log in.");
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n****** Login ******");
        System.out.print("Enter Your Username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter Your Password: ");
        String inputPassword = scanner.nextLine();
        if (true) {
            isLoggedIn = true;
            System.out.println("Login successful. Best of Luck!");
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("You have " + timeRemaining + " minutes to complete the exam.");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.print("Your answer (1 or 2): ");
            int answer = scanner.nextInt();
            userAnswers[i] = answer;
        }
        System.out.println("Would you like to submit? \n1:Yes \n2:NO ");
        int n = scanner.nextInt();
        if (n == 1) {
            submitExam();
        } else {
            isLoggedIn = false;
        }
    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("Please login first.");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (userAnswers[i] == correctAnswers[i]) {
                score++;
            }
        }
        System.out.println("Your score is " + score + " out of " + questionCount + ".");
        logout();
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("Logout successful.");
        System.exit(0);
    }

    public void showWelcomeMessage() {
        System.out.println("\nWelcome to the Online Exam System!");
        System.out.println("1. Register\n2. Login\n3. Exit");
    }

    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OnlineExamSystem examSystem = new OnlineExamSystem();
        
        examSystem.showWelcomeMessage();

        int choice;
        do {
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    examSystem.register();
                    break;
                case 2:
                    examSystem.login();
                    if (examSystem.isLoggedIn) {
                        examSystem.startExam();
                    }
                    break;
                case 3:
                    System.out.println("Exiting the Online Exam System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            if (choice != 3 && !examSystem.isLoggedIn) {
                examSystem.showWelcomeMessage();
            }
        } while (choice != 3 && !examSystem.isLoggedIn);
    }
}