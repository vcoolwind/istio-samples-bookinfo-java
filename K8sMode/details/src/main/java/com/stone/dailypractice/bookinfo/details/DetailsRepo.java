package com.stone.dailypractice.bookinfo.details;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DetailsRepo {
    private static Map<String, Detail> repoMap = new ConcurrentHashMap();

    static {
        Detail detail1 = new Detail();
        detail1.setId("2099a055-1e21-46ef-825e-9e0de93554ea");
        detail1.setAuthor("BlackStone");
        detail1.setType("Science");
        detail1.setPages(112);
        detail1.setTitle("Paraxial Light Beams with Angular Momentum");
        detail1.setDescription("Fundamental and applied concepts concerning the ability of light beams to carry a certain mechanical angular momentum (AM) with respect to the propagation axis are reviewed and discussed in this book.");
        detail1.setPublisher("Nova Science Pub Incorporated");
        detail1.setLanguage("en");
        detail1.setIsbn10("1604561149");
        detail1.setIsbn13("9781604561142");
        repoMap.put(detail1.getId(), detail1);

        Detail detail2 = new Detail();
        detail2.setId("a071c269-369c-4f79-be03-6a41f27d6b5f");
        detail2.setAuthor("Jone sun");
        detail2.setType("Fiction");
        detail2.setPages(32);
        detail2.setTitle("Lawful Interception, see ISBN 978-1-4668-5384-3");
        detail2.setDescription("An all-new tale of Marcus Yallow, the hero of the bestselling novels Little Brother and Homeland.");
        detail2.setPublisher("Macmillan");
        detail2.setLanguage("en");
        detail2.setIsbn10("1466842172");
        detail2.setIsbn13("9781466842175");
        repoMap.put(detail2.getId(), detail1);
    }

    public static boolean contains(String productID) {
        return repoMap.containsKey(productID);
    }

    public static Detail getDetail(String productID) {
        return repoMap.get(productID);
    }

}
