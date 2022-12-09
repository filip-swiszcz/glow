package com.filipswiszcz.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public final class Server {

    private SocketAddress address;
    private ServerSocketChannel channel;
    private String host;
    private int port;
    private final Selector selector = Selector.open();
    private boolean running = true;

    public Server(final SocketAddress address) throws IOException {
        this.address = address;
        if (address instanceof InetSocketAddress inetSocketAddress) {
            this.host = inetSocketAddress.getHostString();
            this.port = inetSocketAddress.getPort();
        }
    }
    
}
