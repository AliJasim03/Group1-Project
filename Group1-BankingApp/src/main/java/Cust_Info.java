public class Cust_Info {
    private String FullName;
    private String Email;
    private String Password;
    private String Balance ;
    private int AccountNumber;
    private String Address;
    Private String DateOfBirth;

    public String getName() {
        return FullName;
    }
    public void setName(String fullName) {
        this.FullName = FullName;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password){
        this.Password = Password;
    }
    public String getBalance() {
        return Balance;
    }
    public void setBalance() {
        this.Balance = Balance;
    }
    public int AccountNumber(){
        return AccountNumber;
    }
    public void AccountNumber(int AccountNumber){
        this.AccountNumber=AccountNumber;
    }
    
}
