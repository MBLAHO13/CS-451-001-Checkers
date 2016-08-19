package network;

import network.messages.Message;
import network.messages.Packet;

import java.util.Observable;
import java.util.function.Consumer;

/**
 *
 */
public class Client extends Observable {
    public static Client client = new Client();
    private String token = "";

    private Client() {

    }

    public void send(Message message, Consumer<Packet> callback) {
        Packet packet = new Packet(token, message);
        String json = packet.toJson();
        if (json == null) {
            callback.accept(Packet.perror("Invalid data"));
        }
        sendData(json, callback);
    }

    private void sendData(String data, Consumer<Packet> callback) {
        new ClientThread(data, callback).start();
    }

}
