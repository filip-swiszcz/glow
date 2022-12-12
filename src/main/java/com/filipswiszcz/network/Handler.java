package com.filipswiszcz.network;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import com.filipswiszcz.Glow;

public final class Handler extends Thread {

    private static final AtomicInteger counter = new AtomicInteger();

    private Server server;
    private Selector selector;

    Handler(final Server server) {
        super("handler_" + counter.getAndIncrement());
        this.server = server;
        try {
            this.selector = Selector.open();
        } catch (IOException exception) {
            throw new RuntimeException("Failed to open the selector.", exception);
        }
    }

    public void start() {
        while (this.server.isRunning()) {
            try {


                this.selector.select(key -> {
                    final SocketChannel channel = (SocketChannel) key.channel();
                    if (!channel.isOpen()) return;
                    if (!key.isReadable()) return;

                    // Check for connection

                }, Glow.getTicks());
            } catch (IOException exception) {
                throw new RuntimeException("Failed to start the handler.", exception);
            }
        }
    }

    public Selector getSelector() {
        return selector;
    }
    
}
