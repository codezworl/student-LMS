//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class SMSSender {
//
//    public static final String ACCOUNT_SID = "your_account_sid";
//    public static final String AUTH_TOKEN = "your_auth_token";
//    public static final String TWILIO_PHONE_NUMBER = "your_twilio_phone_number";
//
//    public static void main(String[] args) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        String toPhoneNumber = "+1234567890";  // Replace with the recipient's phone number
//        String messageBody = "Hello, this is a test message from your Java application.";
//
//        Message message = Message.creator(
//                        new PhoneNumber(toPhoneNumber),
//                        new PhoneNumber(TWILIO_PHONE_NUMBER),
//                        messageBody)
//                .create();
//
//        System.out.println("Message SID: " + message.getSid());
//    }
//}
