package io.github.mosps.model.party.result;

public enum JoinResult {
    EXPIRED("存在しないパーティです。\n再度パーティを作り直してください。"),
    CLOSED("このパーティは締め切り済みです"),
    NONE_PROFILE("プロフィールを作成してください"),
    UNKNOWN_CLASS("メインクラスを設定してください"),
    FULL("このパーティは満員です"),
    SUCCESS(null);

    private final String message;

    JoinResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
