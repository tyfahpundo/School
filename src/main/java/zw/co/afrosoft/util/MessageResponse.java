package zw.co.afrosoft.util;
/**
 * Date :October 14,2021
 * @author Tafadzwa Pundo
 * @version 1.0
 *
 */

public class MessageResponse {
    private final String message;

    public MessageResponse(String message) {
        this.message = message;
    }
    public static MessageResponse createMessageResponse(String message){
        return new MessageResponse(message);
    }
    public String getMessage(){
        return message;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
