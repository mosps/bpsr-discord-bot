package io.github.mosps.data;

public enum Classes {

    EARTHFORT("ヘヴィガーディアン", "剛身型", "<:class_1:1480204223835537419>"),
    BLOCK("ヘヴィガーディアン", "剛守型", "<:class_1:1480204223835537419>"),
    SHIELD("シールドファイター", "光盾型", "<:class_2:1480204339556385002>"),
    RECOVERY("シールドファイター", "光砕型", "<:class_2:1480204339556385002>"),
    LIFEBIND("ヴァーダントオラクル", "森癒型", "<:class_3:1480204268375113818>"),
    SMITE("ヴァーダントオラクル", "威咲型", "<:class_3:1480204268375113818>"),
    DISSONANCE("ビートパフォーマー", "狂音型", "<:class_4:1480204374180499477>"),
    CONCERTO("ビートパフォーマー", "響奏型", "<:class_4:1480204374180499477>"),
    MOONSTRIKE("ストームブレイド", "月影型", "<:class_5:1480210020477177866>"),
    IAIDO_SLASH("ストームブレード", "雷刃型", "<:class_5:1480210020477177866>"),
    SKYWARD("ゲイルランサー", "乱風型", "<:class_6:1480204412390608966>"),
    VANGUARD("ゲイルランサー", "烈風型", "<:class_6:1480204412390608966>"),
    ICICLE("フロストメイジ", "氷牙型", "<:class_7:1480204306182308042>"),
    FROSTBEAM("フロストメイジ", "霜天型", "<:class_7:1480204306182308042>"),
    WILDPACK("ディバインアーチャー", "狼弓型", "<:class_8:1480211556590551073>"),
    FALCONRY("ディバインアーチャー", "鷹弓型", "<:class_8:1480211556590551073>");

    private final String name;
    private final String style;
    private final String emoji;

    Classes(String name, String style, String emoji) {
        this.name = name;
        this.style = style;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDisplay() {
        return emoji + name + style;
    }
}
