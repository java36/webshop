package se.sina.webshop.resource.authentication;

import javax.crypto.KeyGenerator;
import javax.ws.rs.core.HttpHeaders;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class SingleGenerator {

    private static final List<String> tokens = Collections.synchronizedList(new ArrayList<String>());
    private static final SingleGenerator instance = new SingleGenerator();
    private static Key key;
    private KeyGenerator generator;

    private SingleGenerator() {
        this.generator = createGenerator();
        this.key = createKey();
    }
    public static List<String> getTokens() {
        return tokens;
    }
    public static String addToken(String token) {
        tokens.add(token);
        return token;
    }

    public static SingleGenerator getInstance() {
        return instance;
    }

    public static Key getGenerator() {
        return key;
    }

    private Key createKey() {
        return generator.generateKey();
    }

    private KeyGenerator createGenerator() {
        try {
            return generator = KeyGenerator.getInstance("HMACSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generator;
    }
}
