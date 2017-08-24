package validate;

/**
 * Created by zhengjiarong on 2017/8/23.
 */
public class RefundRequestEntity {
    private String appid = "";
    private String mch_id = "";
    private String device_info = "";
    private String nonce_str = "";
    private String sign = "";
    private String transaction_id = "";
    private String out_trade_no = "";
    private String out_refund_no = "";
    private int total_fee = 0;
    private int refund_fee = 0;
    private String refund_fee_type = "CNY";
    private String op_user_id = "";
}
