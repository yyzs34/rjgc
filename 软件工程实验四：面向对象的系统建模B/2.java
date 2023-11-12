import java.util.Scanner;

public class ControlPanel {
    private static final String CORRECT_PASSWORD = "1234"; // ���õ���ȷ����
    private static final int MAX_ATTEMPTS = 3; // ����Դ���
    private static final int LOCKOUT_TIME_SECONDS = 120; // ����ʱ�䣨�룩

    private String inputPassword = "";
    private int attempts = 0;
    private long lockoutEndTime = 0;

    public void processInput(String userInput) {
        if (isLocked()) {
            // ����������������ȴ�����
            long remainingLockoutTime = lockoutEndTime - System.currentTimeMillis();
            System.out.println("�����������������ȴ� " + (remainingLockoutTime / 1000) + " ����ٴγ����������롣");
        } else if (inputPassword.length() < 4) {
            // ��ȡ�����ַ���������������
            inputPassword += userInput;
            System.out.println("������ " + inputPassword.length() + " λ����");
            if (inputPassword.length() == 4) {
                // �ﵽ���볤�ȣ���֤����
                if (inputPassword.equals(CORRECT_PASSWORD)) {
                    System.out.println("������ȷ������ϵͳ����ѡ��״̬��");
                    // ������ִ�н���ϵͳ���ܵĲ���
                } else {
                    attempts++;
                    System.out.println("������󣬳��Դ�����" + attempts);
                    if (attempts >= MAX_ATTEMPTS) {
                        // �ﵽ����Դ����������������
                        lockoutEndTime = System.currentTimeMillis() + (LOCKOUT_TIME_SECONDS * 1000);
                        System.out.println("�����������������ƣ��������������������ʱ�䣺" + LOCKOUT_TIME_SECONDS + " �롣");
                    }
                }
                inputPassword = ""; // ������������
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
            System.out.print("���������루��һ���ַ��س�����");
            String userInput = scanner.nextLine();
            controlPanel.processInput(userInput);
        }
    }
}
