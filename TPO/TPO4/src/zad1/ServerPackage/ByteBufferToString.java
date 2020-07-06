package zad1.ServerPackage;

import java.nio.ByteBuffer;

public class ByteBufferToString {

    public static String convertToString(ByteBuffer buffer) {
    	
        byte[] bytesTab = new byte[buffer.position()];
        
        buffer.rewind();
        buffer.get(bytesTab, 0, bytesTab.length);

        return new String(bytesTab);
    }
}
