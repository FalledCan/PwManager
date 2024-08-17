package com.github.falledcan.pwmanager;

import com.github.falledcan.pwmanager.Utils.FxmlUtils;
import com.github.falledcan.pwmanager.Utils.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class GitHubReleaseChecker {

    public static void checkVersion(){
        String latestVersion =  getLatestReleaseVersion();
        String currentVersion = "v1.0.0"; // ローカルのバージョンをここに指定します

        try {
            if (latestVersion != null && !latestVersion.equals(currentVersion)) {
                Utils.checkVersion = true;
                FxmlUtils.showPopUp("新しいバージョンが利用可能です: " + latestVersion,true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getLatestReleaseVersion() {
        try {
            Document doc = Jsoup.connect("https://falledcan.github.io/Site/Version/").get();
            return doc.select("p.pw").text(); // バージョンを含む要素を選択します
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
