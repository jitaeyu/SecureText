package com.secure.cryptography.controller;

import com.secure.cryptography.file.DeFileDownload;
import com.secure.cryptography.file.FileDownload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {
    public static class Dto {
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    FileDownload fileDownload = new FileDownload();
    DeFileDownload deFileDownload = new DeFileDownload();

    @GetMapping("/")
    public String index() {
        return "page/index";
    }

    @GetMapping("/encryption")
    public String encryption() {
        return "page/encryption";
    }

    @GetMapping("/decryption")
    public String decryption() {
        return "page/decryption";
    }

    @ResponseBody
    @RequestMapping(value = "/read", method = {RequestMethod.POST})
    public List<Integer> test(@RequestBody Object dto) throws Exception {
        String jsonString = dto.toString();
        String[] newline = jsonString.split("\\n");
        String[] asc;
        List<Integer> list = new ArrayList();
        for (int i = 0; i < newline.length; i++) {
            for (int k = 0; k < newline[i].length(); k++) {
                char character = newline[i].charAt(k); // 문자열에서 한 글자 가져오기
                System.out.println(character);
                int ascii = (int) character; // 글자를 ASCII 코드로 변환
//                System.out.println("문자: " + character + ", ASCII 코드+1암호화: " + ((int) ascii + 1));
//                asc=newline[i].split("");
//                System.out.println("start"+asc+"아스키:");
                list.add((int) ascii + 1);
            }
        }
        List<Character> bs = new ArrayList<>();
        for (int k : list) {//K에 a에 있는것을 모두 넘겨라 (확인하고 넘기는거니까 하나씩보기가능)
            bs.add((char) k);

        }
        String path = "C:\\cryptography";
        String fname = "encryption.txt";
        fileDownload.write(path, fname, list);
        System.out.println("들어옴 read");
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/dec1", method = {RequestMethod.POST})
    public List<Character> dec1(@RequestBody Dto dto) {

        List<Integer> contentList = Arrays.asList(dto.getContent().split(","))
                .stream()
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(contentList);
        System.out.println();
        List<Character> slist = new ArrayList<>();

        for (int i = 9; i < contentList.size(); i++) {
            int su = contentList.get(i) - 1;
            char cu = (char) su;
            slist.add(cu);
        }
        System.out.println(slist);
        String path = "C:\\cryptography";
        String fname = "decryption.txt";
        deFileDownload.write(path, fname, slist);


        System.out.println("들어옴 read");


        return slist;

    }

}
