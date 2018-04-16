package com.company;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * @author Tars
 */
public class NioSocketServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8080));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            MyHandler handler = new MyHandler();

            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.println("等待请求超时…………");
                    continue;
                }
                System.out.println("处理请求…………");
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    try {
                        if (key.isAcceptable()) {
                            handler.handleAccept(key);
                        }
                        if (key.isReadable()) {
                            handler.handleRead(key);
                        }
                    } catch (IOException ex) {
                        keyIterator.remove();
                        continue;
                    }
                    keyIterator.remove();
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
