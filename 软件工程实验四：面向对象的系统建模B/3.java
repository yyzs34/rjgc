import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtractor {
    public static void main(String[] args) {
        String sourceCodeFilePath = "your_source_code_file.java"; // Դ�����ļ�·��

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceCodeFilePath))) {
            StringBuilder code = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line).append("\n");
            }

            String codeText = code.toString();

            // ʹ��������ʽƥ��˫����֮����ı�������ת���ַ�
            Pattern pattern = Pattern.compile("\"(?:\\\\\"|[^\"])*\"");
            Matcher matcher = pattern.matcher(codeText);

            while (matcher.find()) {
                String matchedString = matcher.group();
                // ȥ��˫���Ų�����ת���ַ�
                String extractedString = matchedString.substring(1, matchedString.length() - 1)
                        .replaceAll("\\\\\\\\", "\\\\")
                        .replaceAll("\\\\\"", "\"");
                System.out.println(extractedString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
