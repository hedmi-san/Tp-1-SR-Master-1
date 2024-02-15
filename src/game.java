
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class game {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket(9000);
        InetAddress address = InetAddress.getByName("Localhost");
        Scanner scan = new Scanner(System.in);
        int secretNumber;
        System.out.println("Enter a number so the other player try to guess it :");
        secretNumber = scan.nextInt();
        for (int i = 0; i < 5; i++) {
            String message;
            byte[] messageReceived = new byte[1000];
            System.out.println("Receive start: ");
            DatagramPacket packetReceived = new DatagramPacket(messageReceived, messageReceived.length);
            socket.receive(packetReceived);
            int Receivednumber = ByteBuffer.wrap(packetReceived.getData()).getInt();
            System.out.println(Receivednumber);
            if (Receivednumber == secretNumber) {
                message = "You win Nigga you got your freedom back!!(don't forget white people say a dead nigga is a good nigga)";
            } else {
                if (Receivednumber < secretNumber) {
                    message = "Your number is Low,same as the price of niggas back then";
                } else {
                    message = "Your number is High,calm down nigga";
                }
            }
            byte[] messageSend = message.getBytes();
            DatagramPacket packetSend = new DatagramPacket(messageSend, messageSend.length, address, 8000);//instead of address you suppose to put the receiver address
            socket.send(packetSend);
            if (Receivednumber == secretNumber) {
                break;
            }
        }
        socket.close();
    }

}
