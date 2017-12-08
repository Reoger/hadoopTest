package com.hut.bean;

public class CreateFromIndex {

    public final static String JSON = "{\n" +
            "  \"reoger\": {\n" +
            "    \"properties\": {\n" +
            "      \"name\": {\n" +
            "        \"type\": \"text\"\n" +
            "      },\n" +
            "      \"press\":{\n" +
            "      \t\"type\":\"text\"\n" +
            "      },\n" +
            "      \"author\":{\n" +
            "      \t\"type\":\"text\"\n" +
            "      },\n" +
            "      \"content\":{\n" +
            "      \t\"type\":\"text\"\n" +
            "      \t\n" +
            "      },\n" +
            "      \"key_word\":{\n" +
            "      \t\"type\":\"keyword\"\n" +
            "      },\n" +
            "      \"date_of_brith\":{\n" +
            "    \t  \"type\":\"date\",\n" +
            "    \t  \"format\": \"yyyy-MM-dd HH:mm:ss||yyy-MM-dd||epoch_millis\"\n" +
            "      },\n" +
            "      \"down_link\":{\n" +
            "      \t\"type\":\"text\"\n" +
            "      },\n" +
            "      \"size\":{\n" +
            "      \t\"type\":\"float\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    public final static String JSON_DOC ="{\n" +
//            "\t\"settings\":{\n" +
//            "\t\t\"number_of_shards\":3,\n" +
//            "\t\t\"number_of_replicas\":1\n" +
//            "\t},\n" +
            "\t\"mappings\":{\n" +
            "\t\t\"jd\":{\n" +
            "\t\t\t\"properties\":{\n" +
            "\t\t\t\t\"name\":{\n" +
            "\t\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "                \t\"search_analyzer\": \"ik_max_word\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"key_word\":{\n" +
            "\t\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"size\":{\n" +
            "\t\t\t\t\t\"type\":\"integer\"\t\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"organization\":{\n" +
            "\t\t\t\t\t\"type\":\"text\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"time\":{\n" +
            "\t\t\t\t\t\"type\":\"date\",\n" +
            "\t\t\t\t\t\"format\": \"yyyy\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"content\":{\n" +
            "\t\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "                \t\"search_analyzer\": \"ik_max_word\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"down_links\":{\n" +
            "\t\t\t\t\t\"type\":\"text\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"upload_yime\":{\n" +
            "\t\t\t\t\t\"type\":\"date\",\n" +
            "\t\t\t\t\t\"format\": \"yyyy-MM-dd HH:mm:ss||yyy-MM-dd||epoch_millis\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
//            "\t}\n" +
            "}";

    public final static String JSON_PAPER = "{\n" +
//            "\t\"settings\":{\n" +
//            "\t\t\"number_of_shards\":3,\n" +
//            "\t\t\"number_of_replicas\":1\n" +
//            "\t},\n" +
            "\t\"mappings\":{\n" +
            "\t\t\"man\":{\n" +
            "\t\t\t\"properties\":{\n" +
            "\t\t\t\t\"paper_name\":{\n" +
            "\t\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "                \t\"search_analyzer\": \"ik_max_word\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"uploader\":{\n" +
            "\t\t\t\t\t\"type\":\"keyword\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"size\":{\n" +
            "\t\t\t\t\t\"type\":\"integer\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"upload_time\":{\n" +
            "\t\t\t\t\t\"type\":\"date\",\n" +
            "\t\t\t\t\t\"format\": \"yyyy-MM-dd HH:mm:ss||yyy-MM-dd||epoch_millis\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"update_time\":{\n" +
            "\t\t\t\t\t\"type\":\"date\",\n" +
            "\t\t\t\t\t\"format\": \"yyyy-MM-dd HH:mm:ss||yyy-MM-dd||epoch_millis\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"down_link\":{\n" +
            "\t\t\t\t\t\"type\":\"text\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"content\":{\n" +
            "\t\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "                \t\"search_analyzer\": \"ik_max_word\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"author\":{\n" +
            "\t\t\t\t\t\"type\":\"text\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"abstract\":{\n" +
            "\t\t\t\t\t\"type\":\"text\",\n" +
            "\t\t\t\t\t\"analyzer\": \"ik_max_word\",\n" +
            "                \t\"search_analyzer\": \"ik_max_word\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\n" +
            "\t\t}\n" +
//            "\t}\n" +
            "}";
}
