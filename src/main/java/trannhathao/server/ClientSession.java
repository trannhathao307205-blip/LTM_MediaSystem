/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trannhathao.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
/**
 *
 * @author LENOVO
 */
public class ClientSession implements Runnable {
    private final Socket socket;

    public ClientSession(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String address = socket.getRemoteSocketAddress().toString();

        try (
            Socket client = socket;
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    client.getInputStream(),
                    StandardCharsets.UTF_8
                )
            );
            PrintWriter out = new PrintWriter(
                client.getOutputStream(), true,
                StandardCharsets.UTF_8
            )
        ) {
            System.out.println("Client dang hoat dong: " + address);

            out.println("WELCOME LTM_MEDIA_SYSTEM");

            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(
                    "Nhan tu " + address + ": " + message
                );

                if ("DISCONNECT".equalsIgnoreCase(message.trim())) {
                    out.println("GOODBYE");
                    break;
                }

                out.println("SERVER_RECEIVED: " + message);
            }

        } catch (IOException e) {
            System.err.println(
                "Loi ket noi voi " + address + ": " + e.getMessage()
            );
        } finally {
            System.out.println("Client da ngat ket noi: " + address);
        }
    }
}
