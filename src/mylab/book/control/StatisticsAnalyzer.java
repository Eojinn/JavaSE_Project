package mylab.book.control;

import mylab.book.entity.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class StatisticsAnalyzer {
    
    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> totals = new HashMap<>();
        
        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            counts.put(type, counts.getOrDefault(type, 0) + 1);
            totals.put(type, totals.getOrDefault(type, 0.0) + pub.getPrice());
        }

        Map<String, Double> averages = new HashMap<>();
        for (String type : totals.keySet()) {
            averages.put(type, totals.get(type) / counts.get(type));
        }
        return averages;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Double> distribution = new HashMap<>();
        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            distribution.put(type, distribution.getOrDefault(type, 0.0) + 1);
        }
        for (String type : distribution.keySet()) {
            distribution.put(type, (distribution.get(type) / publications.length) * 100);
        }
        return distribution;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int count = 0;
        for (Publication pub : publications) {
            if (pub.getPublishDate().startsWith(year)) count++;
        }
        return ((double) count / publications.length) * 100;
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("\n===== 출판물 통계 분석 =====");
        
        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> averages = calculateAveragePriceByType(publications);
        averages.forEach((k, v) -> System.out.println("   - " + k + ": " + df.format(v) + "원"));

        System.out.println("\n2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        dist.forEach((k, v) -> System.out.println("   - " + k + ": " + df.format(v) + "%"));

        double ratio2007 = calculatePublicationRatioByYear(publications, "2007");
        System.out.println("\n3. 2007년에 출판된 출판물 비율: " + df.format(ratio2007) + "%");
    }
}