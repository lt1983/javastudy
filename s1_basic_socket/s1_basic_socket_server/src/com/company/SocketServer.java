package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Tars
 */
public class SocketServer {
    public static void main(String[] args) {
        try{
            //创建一个ServerSocket监听8080端口
            ServerSocket server=new ServerSocket(8080);
            Socket socket = server.accept();
            System.out.println("server is running:"+socket.toString());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = is.readLine();
            System.out.println("received from client:"+line);
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println("received data:" + line);
            pw.flush();
            pw.close();
            is.close();
            socket.close();
            server.close();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
