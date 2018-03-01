package web.utilities;

public class RankUtil {
    //    0-7级的最低信用度
    public static int[] rank = new int[]{10,200,1000,1800,2600,3600,5000,10000};
    //    0-7级的会员所享受的优惠
    public static Double[] discount = new Double[]{1.0,0.95,0.9,0.85,0.8,0.8,0.75,0.7};

    public static int updateRank(int credit){
        for (int i = 0;i < rank.length; i++){
            if (rank[i] > credit){
                return i;
            }
        }
        return rank.length;
    }
}
