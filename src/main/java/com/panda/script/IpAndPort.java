package com.panda.script;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: IpAndPort.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-08-17
 * @Version: 1.0
 */
public class IpAndPort {

    //    final static String filePath = "/Users/yourName/workspace/sophia/document/sddc/2021/test/openPort2.gnmap";
    final static String openPortFilePath = "/Users/yourName/workspace/sophia/document/sddc/2021/test/openPort.txt";
//    final static String domain2IpFilePath = "/Users/yourName/workspace/sophia/document/sddc/2021/test/domain2ip.txt";

    static String filePath = "/Users/yourName/selfspace/workspace/golang/demo/src/main/resources/file/openPortUniq.txt";
    static String domain2IpFilePath = "/Users/yourName/selfspace/workspace/golang/demo/src/main/resources/file/domain2ipUniq.txt";


    final static String websiteFilePath = "/Users/yourName/selfspace/workspace/golang/demo/src/main/resources/file/website.txt";

    public static void main(String[] args) throws Exception {

//        createOpenPortTxt();
        createWebsiteTxt();

//        response();

    }


//    public static void response() throws Exception {
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(websiteFilePath))) {
//            String website = null;
//            while (StringUtils.hasLength(website = bufferedReader.readLine())) {
//                CloseableHttpClient client = new SSLClient();
//                System.out.println(website);
//                CloseableHttpResponse execute = client.execute(new HttpGet(URI.create(website)));
//                StatusLine statusLine = execute.getStatusLine();
//
//                System.out.println(website+":"+statusLine);
//            }
//        }
//    }


    private static void createWebsiteTxt() throws Exception {


        HashMap<String, List<String>> domainMap = new HashMap<>();

        BufferedReader readerDomain = new BufferedReader(new FileReader(domain2IpFilePath));
        BufferedReader readerPort = new BufferedReader(new FileReader(openPortFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(websiteFilePath));

        try {
            String domainLine = null;
            while (StringUtils.hasLength(domainLine = readerDomain.readLine())) {
                String[] split = domainLine.split(":");
                String domain = split[0];
                String ip = split[1];

                List<String> domains = domainMap.get(ip);
                if (domains == null || domains.size() == 1) {
                    domains = new ArrayList<>();
                }
                domains.add(domain);
                domainMap.put(ip, domains);

            }

            String portLine = null;
            while (StringUtils.hasLength(portLine = readerPort.readLine())) {
                String[] split = portLine.split(":");
                String ip = split[0];
                String port = split[1];
                List<String> domainList = domainMap.get(ip);
                if (domainList == null || domainList.size() == 0) {
                    continue;
                }
                for (String domain : domainList) {
                    String website = null;
                    if ("80".equals(port)) {
                        website = String.format("http://%s", domain);
                    } else if ("443".equals(port)) {
                        website = String.format("https://%s", domain);
                    } else {
                        website = String.format("http://%s:8080", domain);
                    }

                    writer.write(website + "\n");
                    writer.flush();
                }
            }

        } finally {
            readerDomain.close();
            readerPort.close();
            writer.close();
        }


    }


    /**
     * 生成ip:port文件
     */
    private static void createOpenPortTxt() throws IOException {


        String regExIp = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        Pattern patternIp = Pattern.compile(regExIp);

        // 文件不存在会自动创建
        File file = new File(openPortFilePath);
        String line = null;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(openPortFilePath)); BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((StringUtils.hasLength(line = bufferedReader.readLine()))) {
                String[] split = line.split("Ports");
                String ip = split[0];
                String port = split[1];

                Matcher matcherIp = patternIp.matcher(ip);
                while (matcherIp.find()) {
                    writer.write(String.format("%s:%s\n", matcherIp.group(), port.replaceAll("[^0-9]", "")));
                    writer.flush();
                }

            }
        }
    }
}
