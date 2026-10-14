/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trannhathao.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import trannhathao.common.AppConfig;
/**
 *
 * @author LENOVO
 */
public class ServerMain {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("       LTM MEDIA SYSTEM");
        System.out.println("==================================");

        try (ServerSocket serverSocket =
                     new ServerSocket(AppConfig.TCP_PORT)) {

            System.out.println(
                "TCP Server dang lang nghe cong "
                + AppConfig.TCP_PORT
            );
            System.out.println("Dang cho Client ket noi...");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println(
                    "Client moi: "
                    + clientSocket.getRemoteSocketAddress()
                );

                Thread thread = new Thread(
                    new ClientSession(clientSocket)
                );

                thread.start();
            }

        } catch (IOException e) {
            System.err.println(
                "Khong the khoi dong TCP Server: "
                + e.getMessage()
            );
        }

    }    }
