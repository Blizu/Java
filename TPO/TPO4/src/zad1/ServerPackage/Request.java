package zad1.ServerPackage;

import java.nio.channels.SocketChannel;

public abstract class Request {

    protected final SocketChannel socketChannel;

    public Request(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public abstract void serviceRequest();
}
