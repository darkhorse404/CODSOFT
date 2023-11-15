import java.util.*; //package imported
public class NumberGame {

    //Declaring Member variables
    int generatednumber,totalattempts,attemptstaken,totalrounds,roundswon;
    double result;

    //Default constructor
    public NumberGame(){
        //Initialising Member variables
        generatednumber=0;
        totalattempts=5;
        attemptstaken=0;
        totalrounds=0;
        roundswon=0;
        result=0.0;
    }

    //Method to generate a number
    void NumberGenerator(){
        //Incrementing the total number of rounds by 1 for the new round and updating attempts taken for this round to 0
        totalrounds+=1;
        attemptstaken=0;

        System.out.println();
        System.out.println("**  ROUND : "+totalrounds+"  **");

        //Generating the number randomly and scaling it to the range of 0 to 100
        generatednumber = (int)(Math.random()*100);
    }

    //Method to input a number the user guessed
    int NumberInput(){
        Scanner s=new Scanner(System.in); //Initialising Scanner class

        System.out.println();
        System.out.print("Make your Guess : ");
        int n=s.nextInt(); //Accepting user input for a number
        System.out.println("Guessed number = "+n);
        return n; //Returning the inputted number
    }

    //Method to compare the user's guess with the generated number every time and working accordingly
    void check(){
        int inputnumber=NumberInput(); //Calling the NumberInput function to accept the user's guess

        //Comparing the user's guess with the generated number and acting accordingly
        if (generatednumber>inputnumber) {
            attemptstaken+=1;
            System.out.println();
            System.out.println("Your Guess is TOO LOW");
            System.out.println("Remaining Attempts : "+(totalattempts-attemptstaken));
            System.out.println();
            navigation(false);
        } else if (generatednumber<inputnumber) {
            attemptstaken+=1;
            System.out.println();
            System.out.println("Your Guess is TOO HIGH");
            System.out.println("Remaining Attempts : "+(totalattempts-attemptstaken));
            System.out.println();
            navigation(false);
        }else{
            System.out.println();
            System.out.println("CORRECT! YOU WON THIS ROUND");
            System.out.println();
            roundswon+=1;
            result=result+(1-(attemptstaken*0.1));
            navigation(true);
        }
    }

    //Method to calculate the final result based on number of rounds played and display it
    void displayresult(){
        System.out.println("Rounds Won : "+roundswon+"/"+totalrounds);
        result=Math.rint((result/totalrounds)*1000)/100;
        System.out.println("Your SCORE : "+result+"/10");
    }

    //Method to open the navigation panel to the user and accepting their choice and functioning through the game accordingly
    void navigation(boolean WonRound){
        if (WonRound==false) {
            if (attemptstaken==totalattempts) {
                System.out.println("NO Attempts Remaining for this Round");
                System.out.println("YOU LOST THIS ROUND :(");
                System.out.println("Generated number in this Round was "+generatednumber);
            } else {
                System.out.println("Press 1 to Attempt to Guess again");
            }
        }
        System.out.println("Press 2 to play Next Round");
        System.out.println("Press 3 to Quit Game and display your Score");

        Scanner sc=new Scanner(System.in); //Initialising the Scanner class
        int ch=sc.nextInt(); //Input the user's choice

        //Switch-case block for navigation
        switch (ch) {

            case 1: //Case where user chooses to continue guessing in a Round
                if ((attemptstaken==totalattempts) || (WonRound==true)){
                    navigation(false);
                } else {
                    check();
                }
                break;

            case 2: //Case where user chooses to play next Round
                if ((WonRound==false) && (attemptstaken!=totalattempts)) {
                    System.out.println("YOU LOST THIS ROUND");
                    System.out.println("Generated number in this Round was "+generatednumber);
                }
                System.out.println();
                NumberGenerator();
                check();
                break;
            
            case 3: //Case where user chooses to Quit game and display Score
                displayresult();
                break;
            
            default: //Default Case where user makes an invalid choice
                System.out.println("Invalid choice. Please select one of the given options.");
                navigation(WonRound);
                break;
        }
    }

    //Main function
    public static void main(String[] args){

        //Starting the console interface for the game
        System.out.println();
        System.out.println("* * *     G U E S S   T H E   N U M B E R     * * *"); //Game title
        System.out.println("Welcome! This is a Number guessing game. A random number between 0 to 100 will be generated each Round. You have to Guess the number that is generated. You will have 5 Attempts to make your guess each Round. If a wrong guess is made, you will be informed the guess is high or low. You will be rewarded points for each Round won and unused Attempts in that Round and your total Score will be scaled out of 10. Have a nice playtime!"); //Game Info
        System.out.println();

        NumberGame obj=new NumberGame(); //Creating an object

        //Calling Member Methods for the created object
        obj.NumberGenerator();
        obj.check();
    }
}
