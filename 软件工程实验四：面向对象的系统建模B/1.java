import java.util.Date;
import java.text.SimpleDateFormat;

public class WaterHeaterControl {
    public static void main(String[] args) {
        // 模拟水箱初始状态
        boolean hasWater = true;
        boolean isHeating = false;
        int currentTemperature = 25; // 初始温度

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        while (true) {
            // 获取当前时间
            Date now = new Date();
            String currentTime = sdf.format(now);

            // 判断是否晚上11点，如果是则断开电源，进入休眠状态
            if (currentTime.equals("23:00")) {
                isHeating = false;
                System.out.println("晚上11点，断开电源，进入休眠状态");
            }

            // 判断是否早上7点，如果是则开始烧水
            if (currentTime.equals("07:00")) {
                isHeating = true;
                System.out.println("早上7点，开始烧水");
            }

            // 如果正在烧水
            if (isHeating) {
                // 检测温度是否达到100度
                if (currentTemperature >= 100) {
                    isHeating = false;
                    System.out.println("水温达到100度，断开电源");
                } else {
                    // 检测水量传感器
                    if (hasWater) {
                        // 烧水
                        currentTemperature += 10; // 每次增加10度
                        System.out.println("当前温度：" + currentTemperature + "度");
                    } else {
                        // 没有水，不烧水
                        System.out.println("水箱无水，不烧水");
                    }
                }
            }

            // 模拟水箱烧坏的情况，这里简化为温度超过200度
            if (currentTemperature > 200) {
                System.out.println("水箱烧坏，需要维修");
                break;
            }

            // 模拟水箱状态，这里简化为随机变化
            if (Math.random() < 0.3) {
                hasWater = false;
            } else {
                hasWater = true;
            }

            // 模拟每隔一段时间检测一次状态
            try {
                Thread.sleep(1000); // 1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
