package vanim.root.modular;

import java.io.IOException;

public interface Convertable {
    boolean write(String latex, String file, float size) throws IOException;

    boolean read(String inSVG, String out, int type);
}
