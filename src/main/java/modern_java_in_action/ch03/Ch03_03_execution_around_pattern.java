package main.java.modern_java_in_action.ch03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ch03_03_execution_around_pattern {
    public static void main(String[] args) throws IOException {
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String oneLine2 = processFile(BufferedReader::readLine);

        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());

    }

    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine(); // 실제 필요한 작업을 하는 행
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br); // BufferedReader 객체 처리
        }
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }
}
