package io.github.mosps.data;

public enum Classes {

    EARTHFORT("ヘヴィガーディアン 剛身型"),
    BLOCK("ヘヴィガーディアン 剛守型"),
    SHIELD("シールドファイター 光盾型"),
    RECOVERY("シールドファイター 光砕型"),
    LIFEBIND("ヴァーダントオラクル 森癒型"),
    SMITE("ヴァーダントオラクル 威咲型"),
    DISSONANCE("ビートパフォーマー 狂音型"),
    CONCERTO("ビートパフォーマー 響奏型"),
    MOONSTRIKE("ストームブレイド 月影型"),
    IAIDO_SLASH("ストームブレード 雷刃型"),
    SKYWARD("ゲイルランサー 乱風型"),
    VANGUARD("ゲイルランサー 烈風型"),
    ICICLE("フロストメイジ 氷牙型"),
    FROSTBEAM("フロストメイジ 霜天型"),
    WILDPACK("ディバインアーチャー 狼弓型"),
    FALCONRY("ディバインアーチャー 鷹弓型");

    private final String label;

    Classes(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
