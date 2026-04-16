package io.github.mosps.model.data;

public enum Classes {

    EARTHFORT("ヘヴィガーディアン", "剛身型", "<:class_1:1491703367846723615>", Role.TANK),
    BLOCK("ヘヴィガーディアン", "剛守型", "<:class_1:1491703367846723615>", Role.TANK),
    SHIELD("シールドファイター", "光盾型", "<:class_2:1491703369088237578>", Role.TANK),
    RECOVERY("シールドファイター", "光砕型", "<:class_2:1491703369088237578>", Role.TANK),
    LIFEBIND("ヴァーダントオラクル", "森癒型", "<:class_3:1491703370589671534>", Role.HEALER),
    SMITE("ヴァーダントオラクル", "威咲型", "<:class_3:1491703370589671534>", Role.HEALER),
    DISSONANCE("ビートパフォーマー", "狂音型", "<:class_4:1491703371977981993>", Role.HEALER),
    CONCERTO("ビートパフォーマー", "響奏型", "<:class_4:1491703371977981993>", Role.HEALER),
    MOONSTRIKE("ストームブレイド", "月影型", "<:class_5:1491703373320421396>", Role.DPS),
    IAIDO_SLASH("ストームブレード", "雷刃型", "<:class_5:1491703373320421396>", Role.DPS),
    SKYWARD("ゲイルランサー", "乱風型", "<:class_6:1491703374721318972>", Role.DPS),
    VANGUARD("ゲイルランサー", "烈風型", "<:class_6:1491703374721318972>", Role.DPS),
    ICICLE("フロストメイジ", "氷牙型", "<:class_7:1491703375891267744>", Role.DPS),
    FROSTBEAM("フロストメイジ", "霜天型", "<:class_7:1491703375891267744>", Role.DPS),
    WILDPACK("ディバインアーチャー", "狼弓型", "<:class_8:1491703377111945286>", Role.DPS),
    FALCONRY("ディバインアーチャー", "鷹弓型", "<:class_8:1491703377111945286>", Role.DPS);

    private final String name;
    private final String style;
    private final String emoji;
    private final Role role;

    Classes(String name, String style, String emoji, Role role) {
        this.name = name;
        this.style = style;
        this.emoji = emoji;
        this.role = role;
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

    public Role getRole() {
        return role;
    }

    public String getDisplay() {
        return emoji + name + " " + style;
    }

    public String getTextDisplay() {
        return name + " " + style;
    }
}
