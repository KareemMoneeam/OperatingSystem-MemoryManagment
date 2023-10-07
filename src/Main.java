import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        int check;
        int compact;
        Scanner input = new Scanner(System.in);
        Policies obj = new Policies();
        obj.getPartitionInfo(input);
        obj.getProcessInfo(input);
        System.out.println("Select the policy you want to apply: \n 1. First fit \n 2. Worst fit \n 3. Best fit");
        int i=0;
        while(i<1){
            check=(input.nextInt());
            if (check==1)
            {
                obj.FirstFit();
                System.out.println("Do you want to compact? \n 1. Yes \n 2. No");
                compact=input.nextInt();
                if (compact==1){
                    obj.compact();
                    obj.FirstFit();
                }
                else if(compact==2)
                {
                    i++;
                }
            }
            if (check==2)
            {
                obj.WorstFit();
                System.out.println("Do you want to compact? \n 1. Yes \n 2. No");
                compact=input.nextInt();
                if (compact==1){
                    obj.compact();
                    obj.WorstFit();
                }
                else if(compact==2)
                {
                    i++;
                }
            }
            if (check==3)
            {
                obj.BestFit();
                System.out.println("Do you want to compact? \n 1. Yes \n 2. No");
                compact=input.nextInt();
                if (compact==1){
                    obj.compact();
                    obj.BestFit();
                }
                else if(compact==2)
                {
                    i++;
                }
            }
        }
    }
}