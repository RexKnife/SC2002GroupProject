import java.util.*;

public class HMS {
    public static void main (String [] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        int role = login(username,password);
        User u;

        switch (role){
            case 0: 
                break;
            case 1: //administrator
                User u = new Administrator(username, password);
                break;
            case 2: //doctor
                User u = new Doctor(username, password);
                break;
            case 3: //patient 
                User u = new Patient(username, password);
                break;
            case 4: //pharmacist
                User u = new Pharmacist(username, password);
                break;
        }
    u.menu();
    }
    private int login (String username, String password){
        //code to check username, and return role of user trying to log in as int, if user doesnt exist return 0
        //if user is logging in for first time, i.e. default password, prompt them to change password 
    }
}
