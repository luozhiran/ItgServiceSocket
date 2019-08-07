package itg.ssocket;

public class Main {

    public static void main(String[] args) {
        ServiceConnect connect = new ServiceConnect(15543);
        connect.accept();
    }
}
