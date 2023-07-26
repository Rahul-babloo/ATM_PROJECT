

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
class Interface {

    String Name;
    String Username;
    String Password;
    int AccountNo;
    double balance ;
    int transactions ;
    String transactionHistory = "";
    static int RecepeintsCount=0;

    static ArrayList<String> User =new ArrayList<>();
    static ArrayList<String> Pass=new ArrayList<>();

//    String User[]=new String[100];
//    String Pass[]=new String[100];




    //USER REG
    public void register() {
        Interface.RecepeintsCount+=1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Name : ");
        this.Name = sc.nextLine();
        System.out.print("\nEnter Username : ");
        this.Username = sc.nextLine();
        //User[RecepeintsCount]=Username;
        User.add(Username);
        System.out.print("\nEnter the Password : ");
        this.Password = sc.nextLine();
        //Pass[RecepeintsCount]=Password;
        Pass.add(Password);
        System.out.print("Enter your Account No : ");
        this.AccountNo = sc.nextInt();
        System.out.print("\nRegistraion completed successfully! Kindly login to your Account.");
        System.out.println();
//        System.out.println(Arrays.toString(User));
//        System.out.println(Arrays.toString(Pass));

//        System.out.println(User.size());
//        System.out.println(Pass.size());
        System.out.println(User);
        System.out.println(Pass);


    }

    //USER LOGIN

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.print("\nEnter UserName : ");
            String username = sc.nextLine();
            if(User.contains(username)) {
                    while (!isLogin) {
                        System.out.print("\nEnter your Password : ");
                        String Password = sc.nextLine();
                        if (Pass.contains(Password)) {
                            System.out.println("\nLogin Successfull! ");
                            isLogin = true;
                        } else
                            System.out.print("\nIncorrect Password ");
                    }
            }else
                System.out.print("\nUsername not found");
        }
        return isLogin;
    }

    //Withdraw Money

    public void withdraw() {
        System.out.print("\nEnter the amount to be withdrawn: ");
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();
        try{
            if (balance >= amount) {
                balance -= amount;
                System.out.println("\nWithdrawal Successfull");
                String str = "RS " + amount + " withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            }else
                System.out.print("\nInsufficient Balance.");
        } catch (Exception e) {
        }
    }

    //Deposit

    public void deposit() {
        System.out.print("\nEnter amount to be deposited: ");
        Scanner sc = new Scanner(System.in);
        double amount = sc.nextDouble();
        try {
            if (amount <= 100000.00) {
                transactions++;
                balance += amount;
                System.out.println("\nDeposit Successfull ");
                String str = "Rs " + amount + " deposited\n";
                transactionHistory = transactionHistory.concat(str);
            }else
                System.out.print("\nLimit Exceeded!");
        } catch (Exception e) {
        }
    }

    //Transfer

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter recepient Name : ");

            String recepient = sc.nextLine();
            if(RecepeintsCount>=2) {
                if (User.contains(recepient)) {
                    System.out.print("\nEnter amount to be transferred : ");
                    double amount = sc.nextDouble();
                    try {
                        if (balance >= amount) {
                            if (amount <= 50000.00) {
                                transactions++;
                                balance -= amount;
                                System.out.println("\nSuccessfully transfered to " + recepient);
                                String str = "Rs " + amount + " transfered to" + recepient + "\n";
                                transactionHistory = transactionHistory.concat(str);
                            } else
                                System.out.println("\nSORRY! limit is 50000.00");
                        } else
                            System.out.println("\n Insufficient Balance.");
                    } catch (Exception e) {

                    }
                } else {
                    System.out.println("Sorry!!, Recepient not found!!!");
                }
            }
            else{
                System.out.println("Sorry. no Registered recepients");
            }

    }

    //Check Balance

    public void checkBalance() {
        System.out.println("\nRS " + balance);
    }

    //Transaction History

    public void transHistory() {
        if (transactions == 0)
            System.out.println("\nEmpty!");
        else
            System.out.println("\n" + transactionHistory);
    }
}


//Interface of ATM

public class AtmInterface {
    public static int takeInput(int lmt) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (flag && input > lmt || input < 1) {
                    System.out.println("select the number between 1 to " + lmt);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter integer value only");
                flag = false;
            }
        }
        return input;
    }

    //Main Method

    public static void main(String[] args) {
//        boolean isdone = false;
        while (true) {
            System.out.println("\n WELCOME TO CANARA ATM \n");
            System.out.println("1.Register\n 2.Exit");
            System.out.println("Enter your choice : ");
            int choice = takeInput(2);
            if (choice == 1) {
                Interface b = new Interface();
                b.register();
                while (true) {
                    System.out.println("\n1.Login\n 2.Exit\n3.New User Register");
                    System.out.println("Enter your choice : ");
                    int ch = takeInput(3);
                    if (ch == 1) {
                        if (b.login()) {
                            System.out.println("\n\n WELCOME BACK! " + b.Name + "\n\n");
                            boolean isFinished = false;
                            while (!isFinished) {
                                System.out.println("\n1.Deposit\n2.Withdraw\n3.Transfer\n4.Transaction History\n5.Check Balance\n6.Other options");
                                System.out.println("Enter your choice : ");
                                int c = takeInput(6);
                                switch (c) {
                                    case 1:
                                        b.deposit();
                                        break;
                                    case 2:
                                        b.withdraw();
                                        break;
                                    case 3:
                                        b.transfer();
                                        break;
                                    case 4:
                                        b.transHistory();
                                        break;
                                    case 5:
                                        b.checkBalance();
                                        break;
                                    case 6:
                                        isFinished = true;
                                        break;
                                    default:
                                        System.out.println("\nWrong choice!");
                                }
                            }
                        }
                    } else if (ch==3) {
                        break;
                    } else
                        System.exit(0);
                }
            }else
                System.exit(0);

        }
    }
}






































