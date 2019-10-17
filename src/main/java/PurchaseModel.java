public class PurchaseModel {
    public int mPurchaseID, mCustomerID, mProductID;
    public String mDate;
    public double mPrice;
    public int mQuantity;

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(mPurchaseID).append(",");
        sb.append(mCustomerID).append(",");
        sb.append(mProductID).append(",");
        sb.append(mQuantity).append(",");
        sb.append(mPrice).append(",");
        sb.append("\"").append(mDate).append("\"").append(")");
        return sb.toString();
    }
}
