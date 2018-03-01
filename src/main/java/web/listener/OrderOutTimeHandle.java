package web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;
import java.util.TimerTask;

public class OrderOutTimeHandle implements ServletContextListener {

    private Timer timer = null;

    // 初始化监听器，创建实例，执行任务

    public void contextInitialized(ServletContextEvent event) {
        timer = new Timer();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
//                Connection conn = null;
//                ResultSet rs = null;
//                PreparedStatement st = null;
//                try {
//                    conn = DbUtil.getConnection();
//                    conn.setAutoCommit(false);
//                    java.util.Date date = new Date();
//                    System.out.println("开始查询超时未支付订单...");
//                    rs = DbUtil.getResultSet(conn,
//                            "select id, last_payment from mat_info where pay_status=0 and last_payment <'"+ date + "'");
//                    while (rs.next()) {
//                        System.out.println("timeout order id is"+ rs.getString("id"));
//                        st = conn.prepareStatement("update mat_info set room_id ='' where pay_status=0 and id='"+ rs.getString("id") + "'");
//                        st.execute();
//                    }
//                    conn.commit();
//                } catch (Exception e) {
//                    System.out.println(e);
//                    try {
//                        conn.rollback();
//                    } catch (SQLException e1) {
//                        // TODO Auto-generated catch block
//                        e1.printStackTrace();
//                    }
//                    e.printStackTrace();
//                } finally {
//                    DbUtil.closeResultSet(rs);
//                    DbUtil.closeConnection(conn);
//                }
            }
        }, 1000, 15000);
    }

    // 销毁监听器，停止执行任务

    public void contextDestroyed(ServletContextEvent event) {
        // 确保正在执行的任务是此计时器所执行的最后一个任务。
        timer.cancel();
    }
}
