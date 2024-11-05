
package org.jisu.e_store_user_service.constants;

public class EmailTemplateConstants {
    public static String generateOtpEmail(String otp, String username) {
        return "<!DOCTYPE html>" +
               "<html lang=\"en\">" +
               "<head>" +
               "    <meta charset=\"UTF-8\">" +
               "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
               "    <title>OTP Verification</title>" +
               "    <style>" +
               "        body {" +
               "            font-family: Arial, sans-serif;" +
               "            background-color: #f9f9f9;" +
               "            margin: 0;" +
               "            padding: 0;" +
               "        }" +
               "        .container {" +
               "            max-width: 600px;" +
               "            margin: 40px auto;" +
               "            background-color: #ffffff;" +
               "            border-radius: 8px;" +
               "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);" +
               "            overflow: hidden;" +
               "        }" +
               "        .header {" +
               "            background-color: #007bff;" +
               "            color: #ffffff;" +
               "            text-align: center;" +
               "            padding: 20px;" +
               "            font-size: 24px;" +
               "            font-weight: bold;" +
               "        }" +
               "        .content {" +
               "            padding: 30px;" +
               "            text-align: center;" +
               "            color: #333333;" +
               "        }" +
               "        .content p {" +
               "            font-size: 18px;" +
               "            margin: 0 0 20px;" +
               "        }" +
               "        .otp {" +
               "            font-size: 36px;" +
               "            color: #007bff;" +
               "            font-weight: bold;" +
               "            margin: 20px 0;" +
               "        }" +
               "        .footer {" +
               "            padding: 20px;" +
               "            text-align: center;" +
               "            color: #777777;" +
               "            font-size: 14px;" +
               "        }" +
               "    </style>" +
               "</head>" +
               "<body>" +
               "    <div class=\"container\">" +
               "        <div class=\"header\">" +
               "            OTP Verification" +
               "        </div>" +
               "        <div class=\"content\">" +
               "            <p>Hello, " + username + "!</p>" +
               "            <p>Thank you for choosing our service. Your One-Time Password (OTP) is:</p>" +
               "            <div class=\"otp\">" + otp + "</div>" +
               "            <p>Please enter this OTP to complete your verification. This OTP is valid for a limited time.</p>" +
               "        </div>" +
               "        <div class=\"footer\">" +
               "            If you did not request this, please ignore this email or contact support." +
               "        </div>" +
               "    </div>" +
               "</body>" +
               "</html>";
    }
}