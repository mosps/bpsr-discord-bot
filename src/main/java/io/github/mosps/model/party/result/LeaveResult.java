package io.github.mosps.model.party.result;

public enum LeaveResult {
    EXPIRED("このパーティは期限切れです"),
    CLOSED("このパーティは締め切り済みです"),
    NOT_JOINED("パーティに参加していません"),
    SUCCESS(null);

    private final String message;

    LeaveResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
