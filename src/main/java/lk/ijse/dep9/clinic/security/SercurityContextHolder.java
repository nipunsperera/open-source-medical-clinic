package lk.ijse.dep9.clinic.security;

public class SercurityContextHolder {
    private static User user;

    public static User getPrincipal(){
        if(user==null){
            throw new RuntimeException("No Authenticated User");
        }
        return user;
    }

    public static void setPrincipal(User user){
        if(user==null){
            throw new NullPointerException("Principal can't be null");
        } else if (user.getUsername()==null || user.getUsername().isBlank() || user.getRole()==null) {
            throw new RuntimeException("Invalid User");
        }
        SercurityContextHolder.user = user;
    }

    public void clear(){
        user = null;
    }
}
