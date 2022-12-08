package xyz.mynt.wcbootcamp.utility;

import xyz.mynt.wcbootcamp.dto.ProductDTO;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NotificationSenderUtils {

    public static String generateNotificationBody(ProductDTO productDTO){
        String moneyString = properMoneyFormat(productDTO.getPrice());
        return "NEW PRODUCT AVAILABLE! \n" +
                " "+productDTO.getName()+"("+productDTO.getCategory()+")\n"+
                "PRICE: Php"+moneyString;
    }

    private static String properMoneyFormat(BigDecimal price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingSize(3);
        decimalFormat.setGroupingUsed(true);
        return decimalFormat.format(price);
    }
}
