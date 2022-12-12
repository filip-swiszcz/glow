package com.filipswiszcz.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProtocolFamily;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;

public final class Server {

    public static final int handlerCount = Runtime.getRuntime().availableProcessors();
    public static final int maxPacketSize = 2097151;
    public static final int sendBufferSize = 262143;
    public static final int receiveBufferSize = 32767;

    public static boolean delay = false;
    private boolean running = true;

    private final Selector selector = Selector.open();
    // private final PacketProcessor processor;
    private final List<Handler> handlers;
    private int id;

    private SocketAddress address;
    private ServerSocketChannel channel;
    private String host;
    private int port;

    // include PacketProcessor in arguments
    public Server(final SocketAddress address) throws IOException {
        Handler[] handlers = new Handler[handlerCount];
        Arrays.setAll(handlers, value -> new Handler(this));
        this.handlers = List.of(handlers);
        this.address = address;
        if (address instanceof InetSocketAddress inetSocketAddress) {
            this.host = inetSocketAddress.getHostString();
            this.port = inetSocketAddress.getPort();
        } else throw new RuntimeException("Failed to start the server, because of wrong type of address.");
        final ProtocolFamily family = inetSocketAddress.getAddress()
            .getAddress().length == 4 ? StandardProtocolFamily.INET : StandardProtocolFamily.INET6;
        final ServerSocketChannel channel = ServerSocketChannel.open(family);
        channel.bind(address);
        channel.configureBlocking(false);
        channel.register(this.selector, SelectionKey.OP_ACCEPT);
        this.channel = channel;
    }

    public void start() throws IOException {
        this.handlers.forEach(Thread::start);
        new Thread(() -> {
            while (this.running) {
                try {
                    this.selector.select(key -> {
                        if (!key.isAcceptable()) return;
                        try {
                            final Handler handler = this.findHandler();
                            final SocketChannel client = this.channel.accept();
                            //handler.receiveConnection(client);
                        } catch (IOException exception) {
                            throw new RuntimeException("Failed to start the server, because connection was refused.", exception);
                        }
                    });
                } catch (IOException exception) {
                    throw new RuntimeException("Failed to start the server, because key cannot be selected.", exception);
                }
            }
        }, "glow_entry").start();
    }

    public void stop() {
        this.running = false;
        try {
            if (this.channel != null) 
                this.channel.close();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to stop the server.", exception);
        }
        this.selector.wakeup();
        this.handlers.forEach(handler -> 
            handler.getSelector().wakeup());
    }

    private Handler findHandler() {
        this.id = ++id % handlerCount;
        return handlers.get(id);
    }

    public boolean isRunning() {
        return running;
    }

    public SocketAddress getAddress() {
        return address;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
    
}
