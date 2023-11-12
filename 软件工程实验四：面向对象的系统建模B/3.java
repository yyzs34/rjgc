import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtractor {
    public static void main(String[] args) {
        String sourceCodeFilePath = "your_source_code_file.java"; // 源代码文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceCodeFilePath))) {
            StringBuilder code = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                code.append(line).append("\n");
            }

            String codeText = code.toString();

            // 使用正则表达式匹配双引号之间的文本，处理转义字符
            Pattern pattern = Pattern.compile("\"(?:\\\\\"|[^\"])*\"");
            Matcher matcher = pattern.matcher(codeText);

            while (matcher.find()) {
                String matchedString = matcher.group();
                // 去掉双引号并处理转义字符
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
