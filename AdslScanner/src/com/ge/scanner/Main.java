package com.ge.scanner;

import com.ge.scanner.vo.CoaInfo;
import com.ge.util.WaitSynLinkedList;

/**
 * Created by Storm_Falcon on 2016/11/7.
 * Scan users and kick them off.
 */
public class Main {

    public static void main(String[] args) {
//        System.setProperty("user.dir", "E:\\Code\\IDEAworkspace\\test\\AdslScanner");

        WaitSynLinkedList<CoaInfo> syncList = new WaitSynLinkedList<>(500);

        Scanner scanner = new Scanner(syncList);
//        Healer healer = new Healer(syncList);
//        Timer timer = new Timer(syncList);

        scanner.start();
//        healer.start();
//        timer.start();
    }

}
