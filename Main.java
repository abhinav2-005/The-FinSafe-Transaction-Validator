import java.util.Scanner;
import java.util.ArrayList;

class InsufficientFundsException extends Exception{
        public InsufficientFundsException(String msg){
            super(msg);
        }
}

class Account{
    private String Account_holder;
    private double balance;
    private ArrayList<Double> transcations;

    Account(String name, double balance){
        this.Account_holder = name;
        this.balance = balance;
        this.transcations = new ArrayList<>();
    }


    public void deposit(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        } 
        else{
            balance += amount;
            transcations.add(amount);
            maintaing_transcation();
            System.out.println("Amount deposited");
        }
    }
    
    public void Withdraw(double amount) throws InsufficientFundsException{
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be positive");
        }
        else if (amount > balance){
            throw new InsufficientFundsException("Amount must be less than balance");
            
        }
        else{
            balance -= amount;
            transcations.add(-amount);
            maintaing_transcation();
            System.out.println("Withdraw successful");
        }
    }

    public void view_balance(){
        System.out.println(balance);
    }

    public void transcation_history(){
        for(double i : transcations){
            System.out.println(i);
        }
    }

    public void maintaing_transcation(){
        if(transcations.size() > 5){
            transcations.remove(0);
        }
    }
}

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Account Holder Name:");
        String name = sc.nextLine();
        
        Account new_holder = new Account(name,0);

        while (true){
            System.out.println("===== Welcome To Finsafe Transaction Validator =====");
            System.out.println("1.Deposit");
            System.out.println("2.Withdraw");
            System.out.println("3.View Balance");
            System.out.println("4.See transcation history");
            System.out.println("5.Exit");
            
            System.out.println("Enter choice:");
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("Enter deposit amount:");
                    double deposit_amount = sc.nextDouble();
                    new_holder.deposit(deposit_amount);
                    break;
                    
                case 2:
                        System.out.println("Enter withdraw amount:");
                        double withdraw_amount = sc.nextDouble();
                        try{
                        new_holder.Withdraw(withdraw_amount);
                        }
                        catch (InsufficientFundsException e){
                                System.out.println(e.getMessage());
                        }
                        break;

                case 3:
                        System.out.println("Account Balance:");
                        new_holder.view_balance();
                        break;
                
                case 4:
                    System.out.println("Transcation History:");
                    new_holder.transcation_history();
                    break;
                
                case 5:
                    sc.close();
                    return;
                
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}