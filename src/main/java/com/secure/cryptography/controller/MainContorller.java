package com.secure.cryptography.controller;

import com.secure.cryptography.file.FileDownload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainContorller {

    FileDownload fileDownload = new FileDownload();

    @GetMapping("/")
    public String index() {
        return "page/index";
    }

//    @PostMapping("/read")
//    public void encryptionText(HttpServletRequest request) throws UnsupportedEncodingException {
////        request.setCharacterEncoding("utf-8");
////        String ttest = request.getParameter("text");
//        System.out.println("들어옴 read");
////    return "";
//    }


    @ResponseBody
    @RequestMapping(value = "/read", method = {RequestMethod.POST})
    public List<Integer> test(@RequestBody Object dto) throws Exception {
//        int i =0;
        String jsonString = dto.toString();
//        System.out.println("dto"+dto);
//        System.out.println("스트링"+jsonString);
//        int len = dto.length();
        String[] newline = jsonString.split("\\n");
//        System.out.println("newline"+newline);
        String[] asc;
        List<Integer> list = new ArrayList();
        for (int i = 0; i < newline.length; i++) {
            System.out.println(i + "i");
            System.out.println("값" + newline[i] + "\n");
            for (int k = 0; k < newline[i].length(); k++) {
                char character = newline[i].charAt(k); // 문자열에서 한 글자 가져오기
                int ascii = (int) character; // 글자를 ASCII 코드로 변환
                System.out.println("문자: " + character + ", ASCII 코드+1암호화: " + ((int) ascii + 1));
//                asc=newline[i].split("");
//                System.out.println("start"+asc+"아스키:");
                list.add((int) ascii + 1);

            }
        }
        List<Character> bs = new ArrayList<>();
        for (int k : list) {//K에 a에 있는것을 모두 넘겨라 (확인하고 넘기는거니까 하나씩보기가능)
            System.out.println("복호시작: " + (char) k);
            bs.add((char) k);

        }
        String path = "C:\\cryptography";
        String fname = "encryption.txt";
        fileDownload.write(path,fname,list);

//        System.out.println(len+"개수");
//        while (len<i){
//
//        }
//        System.out.println(dto);

        System.out.println("들어옴 read");
        return list;
    }
}
