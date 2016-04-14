package server.side.soft.tech.peer2peer.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;

/**
 * This class is constructed for DataPacket to encoded String and decoded String to DataPacket
 * transformations.
 *
 * @author anıl öztürk
 * @author ahmet gül
 * @author asım zorlu
 *
 */
public class Serialization {

  private static Serialization instance = null;

  public static Serialization getInstance() {
    if (Serialization.instance == null) {
      Serialization.instance = new Serialization();
    }
    return Serialization.instance;
  }

  protected Serialization() {
    // Exists only to defeat instantiation.
  }

  /** Read the object from Base64 string. */
  @SuppressWarnings({"unchecked"})
  public <T> T fromString(final String string) throws IOException, ClassNotFoundException {
    final byte[] data = Base64.getDecoder().decode(string);
    final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
    final T object = (T) ois.readObject();
    ois.close();
    return object;
  }

  /** Write the object to a Base64 string. */
  public <T extends Serializable> String toString(final T object) throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    final ObjectOutputStream oos = new ObjectOutputStream(baos);
    oos.writeObject(object);
    oos.close();
    return Base64.getEncoder().encodeToString(baos.toByteArray());
  }
}
