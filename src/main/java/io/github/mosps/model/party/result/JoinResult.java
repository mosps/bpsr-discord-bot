package io.github.mosps.model.party.result;

public enum JoinResult {
    EXPIRED("このパーティは期限切れです"),
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
