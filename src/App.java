import Password.Password;

public class App {
    public static void main(String[] args)throws Exception  {
        Password pass = new Password();
        //pass.setPassword("BlueGuy@life123");
        System.out.println(pass.getPassword());
        //pass.setPassword("BlueGuy@life123");
        System.out.println(pass.getPassword());
        System.out.println(pass.getEncryptedPassword());
        System.out.println(pass.getDecryptedPassword());
    }
}
