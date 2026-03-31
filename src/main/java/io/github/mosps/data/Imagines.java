package io.github.mosps.data;

public enum Imagines {

    AIRONA("アルーナ", "<:battle_imagine_s1_npc_01:1480204712170098708>"),
    TINA("ティナ", "<:battle_imagine_s1_npc_02:1480204738279506141>"),
    OLVERA("オルヴェラ", "<:battle_imagine_s1_npc_03:1480204762447085844>"),
    TATTA("タータ", "<:battle_imagine_s1_npc_04:1480204817430483105>"),

    GOLDEN_JUGGERNAUT("猛る金色", "<:battle_imagine_s1_boss_01:1480204859582976101>"),
    FROST_OGRE("フロストオーガ", "<:battle_imagine_s1_boss_02:1480204893376610386>"),
    INFERNO_OGRE("フレイムオーガ", "<:battle_imagine_s1_boss_03:1480204915430395984>"),
    PHANTOM_ARACHNOCRAB("ゴーストカニクモ", "<:battle_imagine_s1_boss_04:1480204964381851861>"),
    BRIGAND_LEADER("異国の山賊長ヒグマ", "<:battle_imagine_s1_boss_05:1480204985269485674>"),
    VEMPBZZAR_INCUBATOR("ヴェノミーンの巣", "<:battle_imagine_s1_boss_06:1480205032765788362>"),
    MUKU_CHIEF("ムークボス", "<:battle_imagine_s1_boss_07:1480205057910902834>"),
    IRON_FANG("鉄牙", "<:battle_imagine_s1_boss_08:1480205098180411595>"),
    STORM_GOBLIN_KING("嵐のキングゴブリン", "<:battle_imagine_s1_boss_09:1480205131218944184>"),
    TEMPEST_OGRE("サンダーオーガ", "<:battle_imagine_s1_boss_10:1480205168040611901>"),
    CELESTIAL_FLIER("ヘヴンスカイ", "<:battle_imagine_s1_boss_11:1480205601068945418>"),
    LIZARDMAN_KING("キングギルミー", "<:battle_imagine_s1_boss_12:1480205631569789151>"),
    GOBLIN_KING("キングゴブリン", "<:battle_imagine_s1_boss_13:1480205756207992833>"),
    MUKU_KING("キングムーク", "<:battle_imagine_s1_boss_14:1480205792488591490>"),

    BOARRIER_TYRANT("マイティボア", "<:battle_imagine_s1_elite_01:1480205937779151008>"),
    GOBLIN_ARCHER("ゴブリン弓兵", "<:battle_imagine_s1_elite_02:1480205987670524055>"),
    GOBLIN_GUARD("ゴブリン衛士", "<:battle_imagine_s1_elite_03:1480206031731822622>"),
    INFERNO_GOBLIN_MAGE("炎のエルダーゴブリン", "<:battle_imagine_s1_elite_04:1480206064250257500>"),
    MIGHTY_COLOSSUS("トロール", "<:battle_imagine_s1_elite_05:1480206094088536074>"),
    VOID_ARACHNOCRAB("オセンカニクモ", "<:battle_imagine_s1_elite_06:1480206122999742646>"),
    BRIGAND_SCOUT_LEADER("山賊の斥候隊長", "<:battle_imagine_s1_elite_07:1480206145908899891>"),
    BRIGAND_GUARD_CAPTAIN("山賊の護衛隊長", "<:battle_imagine_s1_elite_08:1480206166511456317>"),
    VOID_BZZAR("変異ミーン", "<:battle_imagine_s1_elite_09:1480206192268542032>"),
    MUKU_WARRIOR("ムークウォーリアー", "<:battle_imagine_s1_elite_10:1480206218990714880>"),
    MUKU_VANGUARD("ムークスマッシャー", "<:battle_imagine_s1_elite_11:1480206245003530363>"),
    GREAT_WARHOG("グレートファング", "<:battle_imagine_s1_elite_12:1480206266100875495>"),
    STORM_GOBLIN_WARRIOR("嵐のゴブリンウォーリアー", "<:battle_imagine_s1_elite_13:1480206288460976209>"),
    SHADOW_CAPTAIN("暗霧銃士の隊長", "<:battle_imagine_s1_elite_14:1480206326448783581>"),
    SHADOW_COMMANDER("暗霧剣士の隊長", "<:battle_imagine_s1_elite_15:1480206397009297418>"),
    LIZARDMAN_HUNTER("ギルミーハンター", "<:battle_imagine_s1_elite_16:1480206419071336459>"),
    LIZARDMAN_MAGE("ギルミーメイジ", "<:battle_imagine_s1_elite_17:1480206439581487124>"),
    JUMGLE_GOBLIN_WARRIOR("森のゴブリンウォーリアー", "<:battle_imagine_s1_elite_18:1480206462000038091>"),
    MUKU_SCOUT("ムークスカウト", "<:battle_imagine_s1_elite_19:1480206482829086913>"),
    VOID_OGRE("虚飾オーガ", "<:battle_imagine_s1_elite_00:1480206542782333091>");


    private final String name;
    private final String emoji;

    Imagines(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
    }

    public String getName() {
        return name;
    }

    public String getEmoji() {
        return emoji;
    }

    public String getDisplay() {
        return emoji;
    }
}
