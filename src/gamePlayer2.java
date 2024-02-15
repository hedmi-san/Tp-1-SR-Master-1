
import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class game {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        DatagramSocket socket = new DatagramSocket(8000);
        InetAddress address = InetAddress.getByName("Localhost");
        for (int i = 0; i < 5; i++) {
            System.out.println("Try to guess the number ma homie");
            Scanner scan = new Scanner(System.in);
            int number = scan.nextInt();
            byte[] numberSend = new byte[1000];
            numberSend = ByteBuffer.allocate(1000).putInt(number).array();
            DatagramPacket sendingPacket = new DatagramPacket(numberSend, numberSend.length,address, 9000);
            socket.send(sendingPacket);
            String message;
            byte[] messageReceived = new byte[1000];
            DatagramPacket packetReceived = new DatagramPacket(messageReceived, messageReceived.length);
            socket.receive(packetReceived);
            message = new String(packetReceived.getData());
            System.out.println(message);
            if (message.contains("win")) {;
                break;
            }
        }
    }
}
