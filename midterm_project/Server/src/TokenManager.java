import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenManager {
    private static Map<String, String> tokenToUserId = new HashMap<>();
    private static Map<String, User> users = new HashMap<>();

    public static String generateToken(String userId) {
        String token = UUID.randomUUID().toString();
        tokenToUserId.put(token, userId);
        return token;
    }

    public static boolean validateToken(String token) {
        return tokenToUserId.containsKey(token);
    }

    public static String getUserIdFromToken(String token) {
        return tokenToUserId.get(token);
    }

    public static void addUser(String userId, String password) {
        users.put(userId, new User(userId, password));
    }

    public static boolean authenticateUser(String userId, String password) {
        User user = users.get(userId);
        return user != null && user.getPassword().equals(password);
    }

    public static class User {
        private String userId;
        private String password;

        public User(String userId, String password) {
            this.userId = userId;
            this.password = password;
        }

        public void setUserId() {
        	
        }
        
        public void setUserPassword() {
        	
        }

        public String getUserId() {
            return userId;
        }

        public String getPassword() {
            return password;
        }
    }
}
