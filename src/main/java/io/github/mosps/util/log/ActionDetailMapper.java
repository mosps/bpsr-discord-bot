package io.github.mosps.util.log;

public class ActionDetailMapper {

    public static String get(String action) {
        return switch (action) {

            // ===== Party =====
            case "party:join" -> "パーティ参加";
            case "party:leave" -> "パーティ退出";
            case "party:toggle" -> "募集状態切替";
            case "party:create" -> "パーティ作成モーダル表示";
            case "party:create_confirm" -> "パーティ作成確定";
            case "party:info" -> "パーティ情報表示";
            case "party:setting" -> "パーティ設定画面表示";
            case "party:edit" -> "パーティ編集モーダル表示";
            case "party:edit_confirm" -> "パーティ編集確定";
            case "party:role" -> "ロール構成変更";
            case "party:delete" -> "パーティ削除確認表示";
            case "party:delete_accept" -> "パーティ削除確定";
            case "party:delete_deny" -> "パーティ削除キャンセル";

            case "party:admin" -> "パーティ管理メッセージ生成";

            // ===== Profile =====
            case "profile:create" -> "プロフィール表示";
            case "profile:register" -> "プロフィール登録/更新";
            case "profile:next" -> "プロフィールページ送り";
            case "profile:prev" -> "プロフィールページ戻し";

            // ===== Imagine =====
            case "profile:imagine_create" -> "イマジン作成開始";
            case "profile:imagine_edit" -> "イマジン編集操作";
            case "profile:imagine_confirm" -> "イマジン編集確定";
            case "profile:imagine_add_next" -> "イマジン追加ページ送り";
            case "profile:imagine_add_prev" -> "イマジン追加ページ戻し";
            case "profile:imagine_remove_next" -> "イマジン削除ページ送り";
            case "profile:imagine_remove_prev" -> "イマジン削除ページ戻し";

            case "profile:admin" -> "プロフィール管理メッセージ生成";

            default -> null;
        };
    }
}
