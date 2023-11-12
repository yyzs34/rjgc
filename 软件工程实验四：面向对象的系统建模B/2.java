import java.util.Scanner;

public class ControlPanel {
    private static final String CORRECT_PASSWORD = "1234"; // 设置的正确密码
    private static final int MAX_ATTEMPTS = 3; // 最大尝试次数
    private static final int LOCKOUT_TIME_SECONDS = 120; // 锁定时间（秒）

    private String inputPassword = "";
    private int attempts = 0;
    private long lockoutEndTime = 0;

    public void processInput(String userInput) {
        if (isLocked()) {
            // 控制面板已锁定，等待解锁
            long remainingLockoutTime = lockoutEndTime - System.currentTimeMillis();
            System.out.println("控制面板已锁定，请等待 " + (remainingLockoutTime / 1000) + " 秒后再次尝试输入密码。");
        } else if (inputPassword.length() < 4) {
            // 读取键入字符，构建输入密码
            inputPassword += userInput;
            System.out.println("已输入 " + inputPassword.length() + " 位密码");
            if (inputPassword.length() == 4) {
                // 达到密码长度，验证密码
                if (inputPassword.equals(CORRECT_PASSWORD)) {
                    System.out.println("密码正确，进入系统功能选择状态。");
                    // 在这里执行进入系统功能的操作
                } else {
                    attempts++;
                    System.out.println("密码错误，尝试次数：" + attempts);
                    if (attempts >= MAX_ATTEMPTS) {
                        // 达到最大尝试次数，锁定控制面板
                        lockoutEndTime = System.currentTimeMillis() + (LOCKOUT_TIME_SECONDS * 1000);
                        System.out.println("密码错误次数超过限制，控制面板已锁定，锁定时间：" + LOCKOUT_TIME_SECONDS + " 秒。");
                    }
                }
                inputPassword = ""; // 重置输入密码
            }
        }
    }

    private boolean isLocked() {
        return System.currentTimeMillis() < lockoutEndTime;
    }

    public static void main(String[] args) {
        ControlPanel controlPanel = new ControlPanel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("请输入密码（按一次字符回车）：");
            String userInput = scanner.nextLine();
            controlPanel.processInput(userInput);
        }
    }
}
